package uz.tsx.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.entity.AttachEntity;

import java.util.List;

public interface AttachService {
    String SUFFIX_MINI_IMG = "_h48";

    AttachEntity saveAttach(MultipartFile file);

    List<AttachEntity> saveAttach(List<MultipartFile> multipartFiles);

    List<AttachEntity> saveAttach(MultipartFile [] multipartFiles);

    AttachDownloadDTO download(String fileName);

    Page<AttachResponseDto> getWithPage(Integer page, Integer size);

    String deleteById(String fileName);

    List<AttachEntity> saveImgFiles(MultipartFile[] files);

    String getMinAttachImgName(String originName);
}
