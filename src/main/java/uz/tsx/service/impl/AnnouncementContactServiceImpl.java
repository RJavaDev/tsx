package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.entity.announcement.AnnouncementContactEntity;
import uz.tsx.repository.AnnouncementContactRepository;
import uz.tsx.service.AnnouncementContactService;

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
}
