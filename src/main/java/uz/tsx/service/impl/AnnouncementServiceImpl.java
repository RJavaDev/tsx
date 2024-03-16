package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.repository.AnnouncementRepository;
import uz.tsx.service.AnnouncementService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementDto> findAllAnnouncements() {
        List<AnnouncementEntity> list = announcementRepository.findAllBy();
        return list.stream().map(AnnouncementEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto createNewAnnouncement(AnnouncementDto dto) {
        return null;
    }
}
