package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementContactCreateDto;
import uz.tsx.entity.announcement.AnnouncementContactEntity;

@UtilityClass
public class AnnouncementContactConvert {

    public AnnouncementContactEntity convertToEntity(AnnouncementContactCreateDto dto){
        AnnouncementContactEntity entity = new AnnouncementContactEntity();

        entity.setLatitude(dto.getLatitude());
        entity.setLatitude(dto.getLatitude());
        entity.setPhone(dto.getPhone());
        entity.setGmail(dto.getGmail());
        entity.setAddress(dto.getAddress());

        return entity;
    }
}
