package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.controller.convert.AttachConvert;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.entity.AttachEntity;
import uz.tsx.exception.*;
import uz.tsx.exception.FileNotFoundException;
import uz.tsx.repository.AttachRepository;
import uz.tsx.service.AttachService;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AttachServiceImpl implements AttachService {

    @Value("${attach.upload.folder}")
    private String ATTACH_UPLOAD_FOLDER;


    private final AttachRepository repository;

    @Override
    public AttachEntity saveAttach(MultipartFile file) {

        AttachEntity attachEntity = AttachConvert.generateAttachEntity(file.getOriginalFilename(), file.getSize(), file.getContentType());

        fileSaveToSystem(file, attachEntity.getPath(), attachEntity.getId(), attachEntity.getType());

        return repository.save(attachEntity);

    }

    @Override
    public AttachEntity saveAttach(InputStream inputStream, String originalFilename, Long size, String type) {

        AttachEntity attachEntity = AttachConvert.generateAttachEntity(originalFilename, size, type);

        fileSaveToSystem(inputStream, attachEntity.getPath(), attachEntity.getId(), attachEntity.getType());

        return repository.save(attachEntity);

    }

    @Override
    public List<AttachEntity> saveAttach(List<MultipartFile> multipartFiles) {
        List<AttachEntity> attachList = new ArrayList<>();
        for (MultipartFile attach : multipartFiles) {
            attachList.add(saveAttach(attach));
        }
        return attachList;
    }

    @Override
    public List<AttachEntity> saveAttach(MultipartFile [] multipartFiles) {
        List<AttachEntity> attachList = new ArrayList<>();
        for (MultipartFile attach : multipartFiles) {
            attachList.add(saveAttach(attach));
        }
        return attachList;
    }


    @Override
    public AttachDownloadDTO download(String fileName) {
        try {
            AttachEntity entity = getAttach(fileName);

            File file = new File(ATTACH_UPLOAD_FOLDER + entity.getPath() + "/" + fileName);

            File dir = file.getParentFile();
            File rFile = new File(dir, entity.getId() + "." + entity.getType());

            Resource resource = new UrlResource(rFile.toURI());

            if (resource.exists() || resource.isReadable()) {
                return new AttachDownloadDTO(resource, entity.getContentType());
            } else {
                throw new CouldNotRead("Could not read");
            }
        } catch (MalformedURLException e) {
            throw new SomethingWentWrong("Something went wrong");
        }
    }

    @Override
    public Page<AttachResponseDto> getWithPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<AttachEntity> pageObj = repository.findAll(pageable);

        List<AttachEntity> entityList = pageObj.getContent();
        List<AttachResponseDto> dtoList = AttachConvert.from(entityList);

        return new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
    }

    @Override
    public String deleteById(String fileName) {
        try {
            AttachEntity attach = getAttach(fileName);
            Path file1 = Paths.get(ATTACH_UPLOAD_FOLDER + attach.getPath() + "/" + attach.getId() + "." + attach.getType());
            Path file2 = Paths.get(ATTACH_UPLOAD_FOLDER + attach.getPath() + "/" + attach.getId() + "_h200." + attach.getType());

            Files.delete(file1);
            Files.delete(file2);

            repository.deleteAnnouncementAttachByAttachId(attach.getId());

            repository.delete(attach);

            return "deleted";
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private AttachEntity getAttach(String fileName) {
        String id = fileName.split("\\.")[0];
        Optional<AttachEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new FileNotFoundException("File Not Found");
        }
        return optional.get();
    }

    private void contentCheck(String extension, File newFile) {
        try {

            if (extension.equalsIgnoreCase("mp4")

                    || extension.equalsIgnoreCase("wmv")
                    || extension.equalsIgnoreCase("avi")
                    || extension.equalsIgnoreCase("avchd")
                    || extension.equalsIgnoreCase("flv")
                    || extension.equalsIgnoreCase("mkv")) {

                MultimediaObject instance = new MultimediaObject(newFile);
                MultimediaInfo result = instance.getInfo();
            }
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMinAttachImgName(String originName) {
        return new StringBuilder(originName).insert(originName.lastIndexOf("."), SUFFIX_MINI_IMG_200).toString();
    }

    private void fileSaveToSystem(MultipartFile file, String pathFolder, String fileName, String type){
        try {
            String newFileName = fileName + "." + type;
            Files.copy(file.getInputStream(), Paths.get(ATTACH_UPLOAD_FOLDER + pathFolder ).resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

            if (Objects.requireNonNull(file.getContentType()).startsWith("image")) {
                String newImgHeight48File = fileName + SUFFIX_MINI_IMG_200 + "." + type;
                Thumbnails.of(file.getInputStream()).height(200).toFile(Paths.get(ATTACH_UPLOAD_FOLDER + pathFolder).resolve(newImgHeight48File).toAbsolutePath().toString());
            }

        } catch (IOException ignore) {}
    }

    public void fileSaveToSystem(InputStream inputStream, String pathFolder, String fileName, String type) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] fileBytes = baos.toByteArray();

            String newFileName = fileName + "." + type;
            ByteArrayInputStream originalInputStream = new ByteArrayInputStream(fileBytes);
            Files.copy(originalInputStream, Paths.get(ATTACH_UPLOAD_FOLDER + pathFolder).resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
            originalInputStream.close();

            String newImgHeight48File = fileName + SUFFIX_MINI_IMG_200 + "." + type;
            ByteArrayInputStream thumbnailInputStream = new ByteArrayInputStream(fileBytes);
            Thumbnails.of(thumbnailInputStream).height(200).toFile(Paths.get(ATTACH_UPLOAD_FOLDER + pathFolder).resolve(newImgHeight48File).toAbsolutePath().toString());
            thumbnailInputStream.close();

        } catch (IOException ignored) { }
    }

    @Override
    public AttachEntity getById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<AttachEntity> saveImgFiles(MultipartFile[] files) {                      // save files and return attaches
        List<AttachEntity> attachEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            AttachEntity attach = AttachConvert.generateAttachEntity(file.getOriginalFilename(), file.getSize(), file.getContentType());
            attachEntities.add(attach);

            try {
                String newFileName = UUID.randomUUID() + "_" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("[+]", " ");
                Files.copy(file.getInputStream(), Paths.get(ATTACH_UPLOAD_FOLDER).resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
                attach.setOriginName(newFileName);

                if (Objects.requireNonNull(file.getContentType()).startsWith("image")) {
                    String newImgHeight48File = new StringBuilder(newFileName).insert(newFileName.lastIndexOf("."), SUFFIX_MINI_IMG_48).toString();
                    Thumbnails.of(file.getInputStream()).height(48).toFile(Paths.get(ATTACH_UPLOAD_FOLDER).resolve(newImgHeight48File).toAbsolutePath().toString());
                    attach.setMiniName(newImgHeight48File);
                }

            } catch (IOException ignore) {}

            repository.save(attach);
        }

        return attachEntities;
    }

}