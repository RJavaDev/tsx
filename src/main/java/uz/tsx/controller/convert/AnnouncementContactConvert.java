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

        entity.setLatitude(dto.getLatitude());
        entity.setLatitude(dto.getLatitude());
        entity.setPhone(dto.getPhone());
        entity.setGmail(dto.getGmail());
        entity.setAddress(dto.getAddress());

        return entity;
    }

    public static AnnouncementContactDto convertToDto(AnnouncementContactEntity contactInfo) {
        if(Objects.isNull(contactInfo)) return null;

        AnnouncementContactDto dto = new AnnouncementContactDto();

        dto.setId(contactInfo.getId());
        dto.setLongitude(contactInfo.getLatitude());
        dto.setLatitude(contactInfo.getLatitude());
        dto.setPhone(contactInfo.getPhone());
        dto.setGmail(contactInfo.getGmail());
        dto.setAddress(contactInfo.getAddress());

        return dto;
    }
}
