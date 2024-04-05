package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;



@Getter
@Setter
public class CategoryUpdateRequestDto extends BaseParentAndChildDto {
    private String nameUz;
    private String nameRu;
    private String nameEn;

    private String attachId;

}
