package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.DataGrid;

import java.util.List;

public interface AnnouncementService {

    List<AnnouncementDto> findAllAnnouncements();

    AnnouncementDto createNewAnnouncement(AnnouncementDto dto);
}
