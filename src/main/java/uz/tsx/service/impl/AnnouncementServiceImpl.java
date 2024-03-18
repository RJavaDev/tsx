package uz.tsx.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.CurrencyDto;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionGroupDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionInfoDto;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.interfaces.AnnouncementInterface;
import uz.tsx.repository.AnnounceAdditionGroupRepository;
import uz.tsx.repository.AnnouncementRepository;
import uz.tsx.repository.OptionRepository;
import uz.tsx.service.*;
import uz.tsx.validation.Validation;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnounceAdditionGroupRepository announceAdditionGroupRepository;
    private final OptionRepository optionRepository;
    private final AnnouncementContactService announcementContactService;
    private final AnnouncementPriceService announcementPriceService;
    private final CategoryService categoryService;
    private final AttachService attachService;

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
    @Override
    public AnnouncementDto findAnnouncementByIdAbout(Long announceId) {
        if(!Validation.checkId(announceId)) return null;

        Optional<AnnouncementEntity> entityOpt = announcementRepository.findById(announceId);
        AnnouncementDto announcementDto = entityOpt.map(AnnouncementEntity::toDto).
                orElseThrow(() -> new IllegalStateException("Announce is not found"));

        announcementDto.setCategory(categoryService.findTreeFromBottom(announcementDto.getCategoryId()));
        announcementDto.setPriceTag(announcementPriceService.getById(announcementDto.getPriceTagId()));
        announcementDto.setContactInfo(announcementContactService.getById(announcementDto.getContactInfoId()));

        List<String> attachImages = announcementRepository.getAttachImages(entityOpt.get().getId());
        attachImages.forEach(originName -> {
            announcementDto.getAttachPhotosUrl().add("/attach/file/" + originName);
            announcementDto.getAttachMiniPhotosUrl().add("/attach/file/" + attachService.getMinAttachImgName(originName));
        });

        List<Long> groupIds = new ArrayList<>();
        List<Long> optionIds = new ArrayList<>();
        if(announcementDto.getAdditionalInfos() != null) {
            announcementDto.getAdditionalInfos().forEach(additionGroup -> groupIds.add(additionGroup.getAdditionGroupId()));
        }

        if(announcementDto.getAdditionalOptions() != null) {
            announcementDto.getAdditionalOptions().forEach(additionOption -> optionIds.add(additionOption.getOptionId()));
        }

        Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap = findAdditionInfoGroups(groupIds);
        fillAnnounceAdditionGroup(Collections.singletonList(announcementDto), groupIdToValueMap);

        Map<Long, OptionDto> optionIdToValueMap = findAdditionOptions(optionIds);
        fillAnnounceAdditionOptions(Collections.singletonList(announcementDto), optionIdToValueMap);
        return announcementDto;
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
        entity.setPriceTagId(createdPriceTag.getId());
        entity.setContactInfoId(createdContactInfo.getId());
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

    @Override
    public DataTable<AnnouncementDto> table1(Map<String, Object> filter) {
        Integer page = 1;
        Integer size = 20;

        if(filter != null) {
            page = MapUtils.getInteger(filter, "page", 1);
            size = MapUtils.getInteger(filter, "size", 20);
        }

        Page<AnnouncementEntity> pageAnnouncement = announcementRepository.findPage(PageRequest.of(page - 1, size));
        List<AnnouncementDto> dtos = pageAnnouncement.stream().map(AnnouncementEntity::toDto).toList();

        DataTable<AnnouncementDto> datatable = new DataTable<>();
        datatable.setTotal((int) pageAnnouncement.getTotalElements());
        datatable.setRows(dtos);

        return datatable;
    }

    @Override
    public DataTable<AnnouncementDto> table2(Map<String, Object> filter) {
        Integer page = 1;
        Integer size = 20;

        if(filter != null) {
            page = MapUtils.getInteger(filter, "page", 1);
            size = MapUtils.getInteger(filter, "size", 20);
        }

        Page<AnnouncementInterface> pageAnnouncement = announcementRepository.findPageInterface(PageRequest.of(page - 1, size));
        List<AnnouncementDto> dtos = pageAnnouncement.stream().map(aInterface -> {
            AnnouncementDto dto = new AnnouncementDto();
            AnnouncementPriceDto priceDto = new AnnouncementPriceDto();
            AnnouncementContactDto contactDto = new AnnouncementContactDto();
            BeanUtils.copyProperties(aInterface, dto);
            BeanUtils.copyProperties(aInterface, priceDto, "id");
            BeanUtils.copyProperties(aInterface, contactDto, "id");

            if(Validation.checkId(aInterface.getCurrencyId())) {
                CurrencyDto cDto = new CurrencyDto();
                cDto.setId(aInterface.getCurrencyId());
                cDto.setCode(aInterface.getCurrencyCode());
                priceDto.setCurrencyId(aInterface.getCurrencyId());
                priceDto.setCurrency(cDto);
            }

            dto.setPriceTag(priceDto);
            dto.setContactInfo(contactDto);
            return dto;
        }).toList();

        DataTable<AnnouncementDto> datatable = new DataTable<>();
        datatable.setTotal((int) pageAnnouncement.getTotalElements());
        datatable.setRows(dtos);

        return datatable;
    }

    @Override
    public AnnouncementDto saveAnnounceImages(Long announceId, MultipartFile[] imgFiles) {
        if(!Validation.checkId(announceId) || imgFiles == null)
            throw new IllegalStateException("Bad request");

        AnnouncementEntity entity = announcementRepository.findById(announceId).
                orElseThrow(() -> new IllegalStateException("Announce is not found"));

        AnnouncementDto dto = entity.toDto();
        List<AttachEntity> attachEntities = attachService.saveImgFiles(imgFiles);
        entity.setAttachPhotos(attachEntities);
        announcementRepository.save(entity);

        attachEntities.forEach(aEntity -> {
            dto.getAttachPhotosUrl().add("/attach/file/" + aEntity.getOriginName());
            dto.getAttachMiniPhotosUrl().add("/attach/file/" + aEntity.getMiniName());
        });

        return dto;
    }
}
