package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementContactCreateDto;
import uz.tsx.entity.announcement.AnnouncementContactEntity;

import java.util.Objects;

@UtilityClass
public class AnnouncementContactConvert {

    public AnnouncementContactEntity convertToEntity(AnnouncementContactCreateDto dto){
        AnnouncementContactEntity entity = new AnnouncementContactEntity();

        entity.setPhone(dto.getPhone());
        entity.setGmail(dto.getGmail());
        entity.setRegionId(dto.getRegionId());

        return entity;
    }

    public static AnnouncementContactDto convertToDto(AnnouncementContactEntity contactInfo) {
        if(Objects.isNull(contactInfo)) return null;

        AnnouncementContactDto dto = new AnnouncementContactDto();

        dto.setId(contactInfo.getId());
        dto.setPhone(contactInfo.getPhone());
        dto.setGmail(contactInfo.getGmail());

        return dto;
    }
}
