package uz.tsx.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.entity.AttachEntity;

import java.util.List;

public interface AttachService {

    public AttachEntity saveAttach(MultipartFile file);

    public List<AttachEntity> saveAttach(List<MultipartFile> multipartFiles);

    public AttachDownloadDTO download(String fileName);

    public Page<AttachResponseDto> getWithPage(Integer page, Integer size);

    public String deleteById(String fileName);
}
