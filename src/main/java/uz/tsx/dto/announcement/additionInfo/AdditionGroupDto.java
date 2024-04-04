package uz.tsx.dto.announcement.additionInfo;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AdditionGroupDto extends BaseDto {

    private String nameUz;

    private String nameRu;

    private String nameEn;

    private AdditionType type;

    private CategoryDto categoryDto;
}
