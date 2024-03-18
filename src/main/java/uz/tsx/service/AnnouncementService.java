package uz.tsx.service;

import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.DataTable;

import java.util.List;
import java.util.Map;

public interface AnnouncementService {

    List<AnnouncementDto> findAllAnnouncements();

    AnnouncementDto findAnnouncementByIdAbout(Long id);

    AnnouncementDto createNewAnnouncement(AnnouncementDto dto);

    DataTable<AnnouncementDto> table1(Map<String, Object> filter);

    DataTable<AnnouncementDto> table2(Map<String, Object> filter);

    AnnouncementDto saveAnnounceImages(Long announceId, MultipartFile[] imgFiles);
}
