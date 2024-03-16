package uz.tsx.dto.announcement.option;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class OptionDto extends BaseDto {
    private String name_uz;

    private String name_ru;

    private String name_en;

    private Long optionGroupId;

    private OptionGroupDto optionGroup;

    private Long categoryId;

    private CategoryDto category;
}
