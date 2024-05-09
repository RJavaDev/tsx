package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.AttachEntity;
import uz.tsx.exception.OriginalFileNameNullException;

import java.io.File;
import java.util.*;

import static uz.tsx.constants.Characters.FOLDER_BOUNDARY;
import static uz.tsx.service.AttachService.SUFFIX_MINI_IMG_200;

@UtilityClass
public class AttachConvert {


    private static final String ATTACH_UPLOAD_FOLDER = "src/main/resources/images/";

    private static final String ATTACH_PATH = "https://api.tez-sotish.uz/images/";

    private static final String DEFAULT_USER_IMAGE = "user/user.jpg";

    public List<AttachResponseDto> from(List<AttachEntity> categoryList) {
        return categoryList.stream().map(AttachConvert::from).toList();
    }

    public AttachResponseDto from(AttachEntity attachEntity) {
        AttachResponseDto dto = new AttachResponseDto();
        dto.setId(attachEntity.getId());
        dto.setOriginalName(attachEntity.getOriginName());
        dto.setDuration(attachEntity.getDuration());
        dto.setPath(attachEntity.getPath());
        dto.setType(attachEntity.getType());
        dto.setSize(attachEntity.getSize());
        dto.setCreatedData(attachEntity.getCreatedDate());
        dto.setAttachUrl(AttachConvert.convertToAttachUrlDto(attachEntity));
        return dto;
    }

    public AttachEntity generateAttachEntity(String fileOriginalFilename, long fileSize, String fileContentType) {
        String pathFolder = getYmDString();
        File folder = new File(ATTACH_UPLOAD_FOLDER + pathFolder);

        if (!folder.exists()) folder.mkdirs();

        String fileName = UUID.randomUUID().toString();
        String extension = getExtension(fileOriginalFilename);

        return attachSetProperty(fileName, fileOriginalFilename, extension, pathFolder, fileSize, fileContentType);
    }

    private AttachEntity attachSetProperty(String fileName, String originalFilename, String extension, String pathFolder, long size, String contentType) {
        AttachEntity entity = new AttachEntity();
        entity.setId(fileName);
        entity.setOriginName(originalFilename);
        entity.setType(extension);
        entity.setPath(pathFolder);
        entity.setSize(size);
        entity.setContentType(contentType);
        return entity;
    }

    private String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        return year + FOLDER_BOUNDARY + month + FOLDER_BOUNDARY; // 2022/04/23
    }

    private String getExtension(String fileName) {
        // mp3/jpg/npg/mp4.....
        if (fileName == null) {
            throw new OriginalFileNameNullException("File name null");
        }
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public AttachUrlResponse convertToAttachUrlDtoForUser(AttachEntity attach) {
        if (Objects.isNull(attach)) {
            return convertToAttachUrlDto(DEFAULT_USER_IMAGE);
        } else {
            return convertToAttachUrlDto(attach);
        }
    }

    public AttachUrlResponse convertToAttachUrlDtoForUser(String attachId, String path, String type) {
        if (Objects.nonNull(attachId)) {
            return convertToAttachUrlDto(attachId, path, type);
        } else {
            return convertToAttachUrlDto(DEFAULT_USER_IMAGE);
        }
    }

    public AttachUrlResponse convertToAttachUrlDto(String attachId, String path, String type) {

        AttachUrlResponse url = new AttachUrlResponse();

        url.setOriginFile(ATTACH_PATH + path + attachId + "." + type);
        url.setMinFile(ATTACH_PATH + path + attachId + SUFFIX_MINI_IMG_200 + "." + type);

        return url;
    }

    public AttachUrlResponse convertToAttachUrlDto(AttachEntity attach) {
        if (Objects.nonNull(attach)) {
            AttachUrlResponse url = new AttachUrlResponse();

            url.setOriginFile(ATTACH_PATH + attach.getPath() + attach.getId() + "." + attach.getType());
            url.setMinFile(ATTACH_PATH + attach.getPath() + attach.getId() + SUFFIX_MINI_IMG_200 + "." + attach.getType());

            return url;
        }
        return null;
    }

    public List<AttachUrlResponse> convertToAttachUrlDto(List<AttachEntity> attachList) {
        if (Objects.nonNull(attachList)) {
            return attachList.stream().map(AttachConvert::convertToAttachUrlDto).toList();
        }
        return null;
    }
    public List<String>convertToAttachUrlMiniDto(List<AttachUrlResponse> mini){
        List<String> minlist=new ArrayList<>();
        if (Objects.nonNull(mini)){
          for (AttachUrlResponse min:mini){
              if (Objects.nonNull(min.getMinFile())){
                  minlist.add(min.getMinFile());
              }
          }
        }
        return minlist;
    }

    public List<String>convertToAttachUrlOriginDto(List<AttachUrlResponse> origin){
        List<String> originlist=new ArrayList<>();
        if (Objects.nonNull(origin)){
            for (AttachUrlResponse min:origin){
                if (Objects.nonNull(min.getOriginFile())){
                    originlist.add(min.getOriginFile());
                }
            }
        }
        return originlist;
    }


    private AttachUrlResponse convertToAttachUrlDto(String defaultURL) {
        AttachUrlResponse url = new AttachUrlResponse();

        url.setOriginFile(ATTACH_PATH + defaultURL);

        return url;
    }
}
