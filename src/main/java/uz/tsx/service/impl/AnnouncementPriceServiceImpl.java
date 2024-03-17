package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;
import uz.tsx.repository.AnnouncementPriceTagRepository;
import uz.tsx.service.AnnouncementPriceService;
import uz.tsx.validation.Validation;

import java.util.List;

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


    @Override
    public AnnouncementPriceDto getById(Long id) {
        if(!Validation.checkId(id)) return null;
        return announcementPriceTagRepository.findById(id).map(entity -> entity.toDto(entity, new AnnouncementPriceDto())).orElse(null);
    }

    @Override
    public List<AnnouncementPriceDto> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
