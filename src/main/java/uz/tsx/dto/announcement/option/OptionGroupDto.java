package uz.tsx.dto.announcement.option;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.RegionEntity;

@Getter
@Setter
public class OptionGroupDto extends BaseDto {
    private String nameUz;

    private String nameRu;

    private String nameEn;

}
