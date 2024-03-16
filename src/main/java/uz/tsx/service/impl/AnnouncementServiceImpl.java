package uz.tsx.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionGroupDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionInfoDto;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.repository.AnnounceAdditionGroupRepository;
import uz.tsx.repository.AnnouncementRepository;
import uz.tsx.repository.OptionRepository;
import uz.tsx.service.AnnouncementContactService;
import uz.tsx.service.AnnouncementPriceService;
import uz.tsx.service.AnnouncementService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnounceAdditionGroupRepository announceAdditionGroupRepository;
    private final OptionRepository optionRepository;
    private final AnnouncementContactService announcementContactService;
    private final AnnouncementPriceService announcementPriceService;

    @Override
    public List<AnnouncementDto> findAllAnnouncements() {
        List<AnnouncementEntity> list = announcementRepository.findAllBy();
        List<AnnouncementDto> announcementDtos = list.stream().map(AnnouncementEntity::toDto).toList();

        List<Long> groupIds = new ArrayList<>();
        List<Long> optionIds = new ArrayList<>();
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalInfos() != null) {
                dto.getAdditionalInfos().forEach(additionGroup -> groupIds.add(additionGroup.getAdditionGroupId()));
            }

            if(dto.getAdditionalOptions() != null) {
                dto.getAdditionalOptions().forEach(additionOption -> optionIds.add(additionOption.getOptionId()));
            }
        }

        Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap = findAdditionInfoGroups(groupIds);
        fillAnnounceAdditionGroup(announcementDtos, groupIdToValueMap);

        Map<Long, OptionDto> optionIdToValueMap = findAdditionOptions(optionIds);
        fillAnnounceAdditionOptions(announcementDtos, optionIdToValueMap);
        return announcementDtos;
    }


    private Map<Long, AnnounceAdditionGroupDto> findAdditionInfoGroups(List<Long> additionGroupIds) {
        Map<Long, AnnounceAdditionGroupDto> map = new HashMap<>();
        if(additionGroupIds == null || additionGroupIds.isEmpty())
            return map;

        List<AdditionGroupEntity> additionGroupEntities = announceAdditionGroupRepository.getAdditionGroupEntitiesByIds(additionGroupIds);
        for(AdditionGroupEntity entity : additionGroupEntities) {
            map.put(entity.getId(), entity.toDto(entity, new AnnounceAdditionGroupDto()));
        }

        return map;
    }

    private void fillAnnounceAdditionGroup(List<AnnouncementDto> announcementDtos, Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap) {
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalInfos() != null) {
                for(AnnouncementInfoSelector info : dto.getAdditionalInfos())
                    info.setAdditionGroup(groupIdToValueMap.get(info.getAdditionGroupId()));
            }
        }
    }

    private Map<Long, OptionDto> findAdditionOptions(List<Long> optionIds) {
        Map<Long, OptionDto> map = new HashMap<>();
        if(optionIds == null || optionIds.isEmpty())
            return map;

        List<OptionEntity> optionEntities = optionRepository.findAllOptionByIds(optionIds);
        for(OptionEntity entity : optionEntities) {
            map.put(entity.getId(), entity.toDto(entity, new OptionDto()));
        }

        return map;
    }

    private void fillAnnounceAdditionOptions(List<AnnouncementDto> announcementDtos, Map<Long, OptionDto> groupIdToValueMap) {
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalOptions() != null) {
                for(AnnounceOptionSelector option : dto.getAdditionalOptions())
                    option.setOption(groupIdToValueMap.get(option.getOptionId()));
            }
        }
    }

    @Override
    public AnnouncementDto createNewAnnouncement(AnnouncementDto dto) {
        if(dto == null) return null;

        if(StringUtils.isEmpty(dto.getTitle())) throw new IllegalStateException("Announce title can't be empty");
        if(dto.getCategoryId() == null) throw new IllegalStateException("CategoryId is null");

        // CHECK contactInfo
        if(dto.getContactInfo() == null || (StringUtils.isEmpty(dto.getContactInfo().getGmail()) && StringUtils.isEmpty(dto.getContactInfo().getPhone())))
            throw new IllegalStateException("Contact info is not full");

        // CHECK priceTag
        if(dto.getPriceTag() == null || dto.getPriceTag().getCurrencyId() == null || dto.getPriceTag().getPrice() == null)
            throw new IllegalStateException("PriceTag is not full");

        AnnouncementContactDto createdContactInfo = announcementContactService.createAnnounceContact(dto.getContactInfo());
        AnnouncementPriceDto createdPriceTag = announcementPriceService.createAnnouncePrice(dto.getPriceTag());

        AnnouncementEntity entity = new AnnouncementEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.forCreate();

        if(dto.getAdditionalInfos() != null) {
            Set<AnnounceAdditionInfoDto> announceAdditionInfos = new HashSet<>();
            for(AnnouncementInfoSelector sInfo : dto.getAdditionalInfos())
                announceAdditionInfos.add(sInfo.toDto());

            entity.setAdditionalInfos(announceAdditionInfos);
        }

        if(dto.getAdditionalOptions() != null) {
            Set<AnnounceOptionDto> announceOptionDtos = new HashSet<>();
            for(AnnounceOptionSelector sOption : dto.getAdditionalOptions())
                announceOptionDtos.add(sOption.toDto());

            entity.setAdditionalOptions(announceOptionDtos);
        }

        announcementRepository.save(entity);
        AnnouncementDto createdAnnounce = entity.toDto();
        createdAnnounce.setContactInfo(createdContactInfo);
        createdAnnounce.setPriceTag(createdPriceTag);

        return createdAnnounce;
    }
}
