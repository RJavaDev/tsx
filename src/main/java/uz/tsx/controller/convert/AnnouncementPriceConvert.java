package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementPriceCreateDto;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;

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
}
