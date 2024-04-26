package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementPriceCreateDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;

import java.util.Objects;

@UtilityClass
public class AnnouncementPriceConvert {

    public AnnouncementPriceEntity convertToEntity(AnnouncementPriceCreateDto dto){
        AnnouncementPriceEntity entity = new AnnouncementPriceEntity();

        entity.setCurrencyId(dto.getCurrencyId());
        entity.setPrice(dto.getPrice());
        entity.setIsDeal(dto.getIsDeal());
        entity.setIsFree(dto.getIsFree());
        entity.setIsExchange(dto.getIsExchange());

        return entity;
    }

    public static AnnouncementPriceDto convertToDto(AnnouncementPriceEntity priceTag) {
        if(Objects.isNull(priceTag)) return null;

        AnnouncementPriceDto dto = new AnnouncementPriceDto();

        dto.setId(priceTag.getId());
        dto.setCurrencyId(priceTag.getCurrencyId());
        dto.setCurrency(priceTag.getCurrency().toDto());
        dto.setPrice(priceTag.getPrice());
        dto.setIsDeal(priceTag.getIsDeal());
        dto.setIsFree(priceTag.getIsFree());
        dto.setIsExchange(priceTag.getIsExchange());

        return dto;
    }
}
