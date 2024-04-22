package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;
import uz.tsx.repository.AnnouncementPriceTagRepository;
import uz.tsx.service.AnnouncementPriceService;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementPriceServiceImpl implements AnnouncementPriceService {

    private final AnnouncementPriceTagRepository repository;

    private final CommonSchemaValidator schemaValidator;

    @Override
    public AnnouncementPriceDto createAnnouncePrice(AnnouncementPriceDto dto) {
        if(dto == null || dto.getCurrencyId() == null || dto.getPrice() == null)
            throw new IllegalStateException("All requirements are not satisfied?!");

        AnnouncementPriceEntity entity = new AnnouncementPriceEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.forCreate();
        repository.save(entity);

        return entity.toDto(entity, new AnnouncementPriceDto());
    }

    @Override
    public AnnouncementPriceEntity addNewAnnouncementPrice(AnnouncementPriceEntity entity) {

        if(!entity.getIsFree()){
            schemaValidator.validateCurrencyId(entity.getCurrencyId());
        }

        entity.forCreate();

        return repository.save(entity);
    }


    @Override
    public AnnouncementPriceDto getById(Long id) {
        if(!Validation.checkId(id)) return null;
        return repository.findById(id).map(entity -> entity.toDto(entity, new AnnouncementPriceDto())).orElse(null);
    }

    @Override
    public List<AnnouncementPriceDto> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
