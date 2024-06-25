package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import uz.tsx.entity.AttachEntity;
import uz.tsx.service.AttachService;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AttachService attachService;
    private final RestTemplate restTemplate;

    @Value("${tsx.project.bot.token}")
    private String BOT_TOKEN;

    public List<AttachEntity> savePhotos(List<List<PhotoSize>> photoSizeList, List<Document> documentList) {
        List<AttachEntity> attachEntityList = new ArrayList<>();
        try {
            for (List<PhotoSize> photoSizes : photoSizeList) {
                for (PhotoSize photoSize : photoSizes) {
                    AttachEntity attachEntity = saveAttachWithExceptionHandling(photoSize.getFileId(), photoSize.getFileSize());
                    attachEntityList.add(attachEntity);
                }
            }

            for (Document document : documentList) {
                AttachEntity attachEntity = saveAttachWithExceptionHandling(document.getFileId(), document.getFileSize());
                attachEntityList.add(attachEntity);
            }

            return attachEntityList;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    private AttachEntity saveAttachWithExceptionHandling(String fileId, int fileSize) throws IOException {
        String filePath = getFilePath(fileId);
        return saveAttach(filePath, fileSize);
    }

    private AttachEntity saveAttach(String filePath, Integer size) throws IOException {
        String url = "https://api.telegram.org/file/bot" + BOT_TOKEN + "/" + filePath;
        Resource resource = restTemplate.getForObject(URI.create(url), Resource.class);

        if (resource != null && resource.exists()) {
            String type = FileService.getFileExtension(filePath);

            String fileName = UUID.randomUUID() + FileService.getFileExtension(filePath);

            return attachService.saveAttach(resource.getInputStream(), fileName, Long.valueOf(size), type);

        }

        return null;
    }

    private String getFilePath(String fileId) throws IOException {
        String requestUrl =  "https://api.telegram.org/bot" + BOT_TOKEN + "/getFile?file_id=" + fileId;

        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream responseStream = connection.getInputStream();
            Scanner scanner = new Scanner(responseStream);
            String responseBody = scanner.useDelimiter("\\A").next();
            scanner.close();

            String filePath = extractFilePath(responseBody);
            connection.disconnect();
            return filePath;
        } else {
            System.out.println("GET request failed. Response Code: " + responseCode);
            connection.disconnect();
            return null;
        }
    }

    private String extractFilePath(String jsonResponse) {
        String filePathKey = "\"file_path\":\"";
        int startIndex = jsonResponse.indexOf(filePathKey);
        if (startIndex != -1) {
            startIndex += filePathKey.length();
            int endIndex = jsonResponse.indexOf("\"", startIndex);
            return jsonResponse.substring(startIndex, endIndex);
        }
        return null;
    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex);
        } else {
            return "";
        }
    }
}
