package uz.tsx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.dto.dtoUtil.PageParam;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.List;
import java.util.Map;

public interface AnnouncementService {

    AnnouncementEntity createNewAnnouncement(AnnouncementEntity entity);

    List<AnnouncementDto> findAllAnnouncements();

    AnnouncementDto findAnnouncementByIdAbout(Long id);

   // AnnouncementDto createNewAnnouncement(AnnouncementDto dto);

    DataTable<AnnouncementDto> table1(Map<String, Object> filter);

    DataTable<AnnouncementDto> table2(Map<String, Object> filter);

    AnnouncementEntity saveAnnounceImages(Long announceId, MultipartFile[] imgFiles);

    AnnouncementEntity getById(Long id);

    Page<AnnouncementEntity> getPageHomeData(PageParam pageParam);

    Integer iSaw (Long id, HttpServletRequest httpServletRequest);

    DataTable<AnnouncementInterface> getAnnouncementListByCategory(Long categoryId, PageParam pageParam);
}
