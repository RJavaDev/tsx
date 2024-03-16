package uz.tsx.dto.announcement.additionInfo;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;
import uz.tsx.validation.Validation;

@Getter
@Setter
public class AnnounceAdditionGroupDto extends BaseDto {
    private String nameUz;

    private String nameRu;

    private String nameEn;

    private AdditionType type;

    private Long categoryId;

    private CategoryDto category;
/*
    private String comboListUrl;            // This url is used to fill combo, if type is COMBOBOX

    public String getComboListUrl() {
        String url = "";
        if(type == AdditionType.COMBOBOX && Validation.checkId(getId())) {
            url = "/tsx_add_group/list?groupId=" + getId();
        }
        return url;
    }*/
}
