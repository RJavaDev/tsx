package uz.tsx.dto.announcement.option;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class OptionDto extends BaseDto {
    private String nameUz;

    private String nameRu;

    private String nameEn;

    private Long optionGroupId;

    private OptionGroupDto optionGroup;

    private Long categoryId;

    private CategoryDto category;
}
