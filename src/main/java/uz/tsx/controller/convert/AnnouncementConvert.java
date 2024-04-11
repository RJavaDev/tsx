package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import uz.tsx.dto.CurrencyDto;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionInfoDto;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementCreatedDto;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@UtilityClass
public class AnnouncementConvert {

    public AnnouncementEntity convertToEntity(AnnouncementCreatedDto dto){
        AnnouncementEntity entity = new AnnouncementEntity();

        entity.setTitle(dto.getTitle());
        entity.setCategoryId(dto.getCategoryId());
        entity.setDescription(dto.getDescription());
        entity.setPriceTag(AnnouncementPriceConvert.convertToEntity(dto.getPriceTag()));
        entity.setContactInfo(AnnouncementContactConvert.convertToEntity(dto.getContactInfo()));
        entity.setAdditionalInfos(selectorToAnnounceAdditionInfoDto(dto.getAdditionalInfos()));
        entity.setAdditionalOptions(selectorToAnnounceOptionDto(dto.getAdditionalOptions()));

        return entity;
    }

    public AnnouncementDto convertToDto(AnnouncementEntity newAnnouncementEntity) {
        AnnouncementDto dto = newAnnouncementEntity.toDto();
        dto.setAdditionalInfos(announceAdditionInfoToSelector(newAnnouncementEntity.getAdditionalInfos()));
        dto.setAdditionalOptions(announceOptionDtoToSelector(newAnnouncementEntity.getAdditionalOptions()));
        dto.setAttachUrlResponses(AttachConvert.convertToAttachUrlDto(newAnnouncementEntity.getAttachPhotos()));

        return dto;
    }

    public AnnouncementDto convertToDto(AnnouncementInterface interfaceDB){
        AnnouncementDto dto = new AnnouncementDto();
        AnnouncementContactDto contactDto = new AnnouncementContactDto();
        AnnouncementPriceDto priceDto = new AnnouncementPriceDto();
        CurrencyDto currencyDto = new CurrencyDto();

        dto.setId(interfaceDB.getId());
        dto.setTitle(interfaceDB.getTitle());
        dto.setCreatedDate(interfaceDB.getCreatedDate());

        contactDto.setLongitude(interfaceDB.getLongitude());
        contactDto.setLatitude(interfaceDB.getLatitude());
        contactDto.setPhone(interfaceDB.getPhone());
        contactDto.setGmail(interfaceDB.getGmail());
        contactDto.setAddress(interfaceDB.getAddress());

        dto.setContactInfo(contactDto);

        priceDto.setPrice(interfaceDB.getPrice());
        priceDto.setIsDeal(interfaceDB.getIsDeal());
        priceDto.setIsFree(interfaceDB.getIsFree());
        priceDto.setIsExchange(interfaceDB.getIsExchange());
        priceDto.setCurrencyId(interfaceDB.getCurrencyId());
        currencyDto.setCode(interfaceDB.getCurrencyCode());
        priceDto.setCurrency(currencyDto);


        dto.setPriceTag(priceDto);

        return dto;
    }

    public Set<AnnounceAdditionInfoDto> selectorToAnnounceAdditionInfoDto(Set<AnnouncementInfoSelector> additionalInfos){
        if(Objects.nonNull(additionalInfos)){
            Set<AnnounceAdditionInfoDto> announceAdditionInfos = new HashSet<>();
            for(AnnouncementInfoSelector sInfo : additionalInfos)
                announceAdditionInfos.add(sInfo.toDto());
            return announceAdditionInfos;
        }
        return null;
    }

    public Set<AnnouncementInfoSelector> announceAdditionInfoToSelector(Set<AnnounceAdditionInfoDto> additionalInfos){
        if(Objects.nonNull(additionalInfos)){
            Set<AnnouncementInfoSelector> announceAdditionInfos = new HashSet<>();
            for(AnnounceAdditionInfoDto sInfo : additionalInfos){
                AnnouncementInfoSelector selector = new AnnouncementInfoSelector();
                BeanUtils.copyProperties(sInfo, selector);
                announceAdditionInfos.add(selector);
            }

            return announceAdditionInfos;
        }
        return null;
    }

    public Set<AnnounceOptionDto> selectorToAnnounceOptionDto(Set<AnnounceOptionSelector> additionalOptions){
        if(Objects.nonNull(additionalOptions)){
            Set<AnnounceOptionDto> announceOptionDtoList = new HashSet<>();
            for(AnnounceOptionSelector sOption : additionalOptions)
                announceOptionDtoList.add(sOption.toDto());

            return announceOptionDtoList;
        }

        return null;
    }

    public Set<AnnounceOptionSelector> announceOptionDtoToSelector(Set<AnnounceOptionDto> additionalOptions){
        if(Objects.nonNull(additionalOptions)){
            Set<AnnounceOptionSelector> announceOptionDtoList = new HashSet<>();
            for(AnnounceOptionDto sOption : additionalOptions){
                AnnounceOptionSelector selector = new AnnounceOptionSelector();
                BeanUtils.copyProperties(sOption, selector);
                announceOptionDtoList.add(selector);
            }

            return announceOptionDtoList;
        }

        return null;
    }


    public List<AnnouncementDto> convertToDto(Page<AnnouncementEntity> pageHomeData) {
        return pageHomeData.stream().map(AnnouncementConvert::convertToDto).toList();
    }
}
