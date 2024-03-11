package uz.tsx.dto;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AnnounceAdditionGroupDto extends BaseDto {
    private String nameUz;

    private String nameRu;

    private String nameEn;

    private Integer intValue;

    private Float floatValue;

    private String colorValue;

    private String stringValue;

    private AdditionType type;

    private Integer categoryId;

    private CategoryDto category;

    private String comboListUrl;            // This url is used to fill combo, if type is COMBOBOX
}
