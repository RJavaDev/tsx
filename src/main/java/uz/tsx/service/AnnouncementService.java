package uz.tsx.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.BigDataTable;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.dto.dtoUtil.FilterForm;
import uz.tsx.dto.dtoUtil.PageParam;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnnouncementService {

    AnnouncementEntity createNewAnnouncement(AnnouncementEntity entity);

    List<AnnouncementDto> findAllAnnouncements();

    AnnouncementDto findAnnouncementByIdAbout(Long id);

   // AnnouncementDto createNewAnnouncement(AnnouncementDto dto);

    DataTable<AnnouncementDto> table1(Map<String, Object> filter);

    DataTable<AnnouncementDto> table2(Map<String, Object> filter);

    AnnouncementEntity saveAnnounceImages(Long announceId, MultipartFile[] imgFiles);

    AnnouncementEntity getById(Long id);

    BigDataTable<AnnouncementInterface> getPageHomeData(Integer page, Integer size);

    Integer iSaw (Long id, HttpServletRequest httpServletRequest);

    BigDataTable<AnnouncementInterface> getAnnouncementListByCategory(Long categoryId, PageParam pageParam);

    BigDataTable<AnnouncementInterface> searchAnnouncementAndFilter(FilterForm filter);

    List<AnnouncementEntity> getAnnouncementListByUserEntity(Long userId);

    void changeActiveStatus(Long announcementId, Boolean isActive);

    void deleteAnnouncement(Long announcementId);

    void update(AnnouncementEntity announcementEntity);

    List<AnnouncementInterface> getMeAnnouncementList();

    void activeReverseUpdate(Long id);
}
