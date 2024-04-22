package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;

public interface AnnouncementPriceService extends BaseInterface<AnnouncementPriceDto> {
    AnnouncementPriceDto createAnnouncePrice(AnnouncementPriceDto dto);

    AnnouncementPriceEntity addNewAnnouncementPrice(AnnouncementPriceEntity entity);
}
