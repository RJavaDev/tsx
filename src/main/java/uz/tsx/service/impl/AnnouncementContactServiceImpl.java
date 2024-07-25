package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.entity.announcement.AnnouncementContactEntity;
import uz.tsx.repository.AnnouncementContactRepository;
import uz.tsx.service.AnnouncementContactService;
import uz.tsx.validation.Validation;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnnouncementContactServiceImpl implements AnnouncementContactService {
    private final AnnouncementContactRepository announcementContactRepository;

    @Override
    public AnnouncementContactDto createAnnounceContact(AnnouncementContactDto dto) {
        if(dto == null) return null;

        if(StringUtils.isEmpty(dto.getGmail()) && StringUtils.isEmpty(dto.getPhone()))
            throw new IllegalStateException("Email or phone is empty");

        AnnouncementContactEntity entity = new AnnouncementContactEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.forCreate();
        announcementContactRepository.save(entity);

        return entity.toDto(entity, new AnnouncementContactDto());
    }

    @Override
    public AnnouncementContactEntity addNewAnnounceContact(AnnouncementContactEntity entity) {

        String phone = entity.getPhone();
        if(StringUtils.isEmpty(phone) && Objects.isNull(phone))
            throw new IllegalStateException("Email or phone is empty");


        entity.forCreate(SecurityUtils.getUserId());

        return announcementContactRepository.save(entity);
    }

    @Override
    public AnnouncementContactDto getById(Long id) {
        if(!Validation.checkId(id)) return null;
        return announcementContactRepository.findById(id).map(entity -> entity.toDto(entity, new AnnouncementContactDto())).orElse(null);
    }

    @Override
    public List<AnnouncementContactDto> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        announcementContactRepository.deleteById(id);
    }
}
