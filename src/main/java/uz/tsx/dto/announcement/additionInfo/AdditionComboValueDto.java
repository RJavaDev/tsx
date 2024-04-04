package uz.tsx.dto.announcement.additionInfo;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

@Getter
@Setter
public class AdditionComboValueDto extends BaseDto {
    private String nameUz;

    private String nameRu;

    private String nameEn;

    private Long groupId;

    private AdditionGroupDto group;
}
