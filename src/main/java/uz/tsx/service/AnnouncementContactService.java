package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementContactDto;

public interface AnnouncementContactService extends BaseInterface<AnnouncementContactDto> {
    AnnouncementContactDto createAnnounceContact(AnnouncementContactDto dto);
}
