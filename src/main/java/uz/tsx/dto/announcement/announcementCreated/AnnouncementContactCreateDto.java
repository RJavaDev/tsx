package uz.tsx.dto.announcement.announcementCreated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementContactCreateDto {

    private Double longitude;

    private Double latitude;

    private String phone;

    private String gmail;

    private Long regionId;

    private String address;
}
