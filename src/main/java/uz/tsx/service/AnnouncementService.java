package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {

    List<AnnouncementDto> findAllAnnouncements();

    AnnouncementDto findAnnouncementByIdAbout(Long id);

    AnnouncementDto createNewAnnouncement(AnnouncementDto dto);
}
