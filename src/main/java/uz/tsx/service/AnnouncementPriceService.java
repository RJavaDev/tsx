package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementPriceDto;

public interface AnnouncementPriceService extends BaseInterface<AnnouncementPriceDto> {
    AnnouncementPriceDto createAnnouncePrice(AnnouncementPriceDto dto);
}
