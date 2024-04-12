package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.entity.announcement.AnnouncementContactEntity;

public interface AnnouncementContactService extends BaseInterface<AnnouncementContactDto> {
    AnnouncementContactDto createAnnounceContact(AnnouncementContactDto dto);

    AnnouncementContactEntity addNewAnnounceContact(AnnouncementContactEntity entity);
}
