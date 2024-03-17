package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class AnnouncementContactDto extends BaseDto {
    private Double longitude;

    private Double latitude;

    private String phone;

    private String gmail;

    private String address;
}
