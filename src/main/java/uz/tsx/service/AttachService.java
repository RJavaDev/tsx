package uz.tsx.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.entity.AttachEntity;

import java.io.InputStream;
import java.util.List;

public interface AttachService {
    String SUFFIX_MINI_IMG_200 = "_h200";
    String SUFFIX_MINI_IMG_48 = "_h48";

    AttachEntity saveAttach(MultipartFile file);

    List<AttachEntity> saveAttach(List<MultipartFile> multipartFiles);

    List<AttachEntity> saveAttach(MultipartFile [] multipartFiles);

    AttachEntity saveAttach(InputStream inputStream, String originalFilename, Long size, String type);

    AttachDownloadDTO download(String fileName);

    Page<AttachResponseDto> getWithPage(Integer page, Integer size);

    String deleteById(String fileName);

    List<AttachEntity> saveImgFiles(MultipartFile[] files);

    String getMinAttachImgName(String originName);

    AttachEntity getById(String id);
}
