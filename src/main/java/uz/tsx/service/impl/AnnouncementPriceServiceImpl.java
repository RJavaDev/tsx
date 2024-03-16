package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;
import uz.tsx.repository.AnnouncementPriceTagRepository;
import uz.tsx.service.AnnouncementPriceService;

@Service
@RequiredArgsConstructor
public class AnnouncementPriceServiceImpl implements AnnouncementPriceService {
    private final AnnouncementPriceTagRepository announcementPriceTagRepository;

    @Override
    public AnnouncementPriceDto createAnnouncePrice(AnnouncementPriceDto dto) {
        if(dto == null || dto.getCurrencyId() == null || dto.getPrice() == null)
            throw new IllegalStateException("All requirements are not satisfied?!");

        AnnouncementPriceEntity entity = new AnnouncementPriceEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.forCreate();
        announcementPriceTagRepository.save(entity);

        return entity.toDto(entity, new AnnouncementPriceDto());
    }
}
