package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;



@Getter
@Setter
public class CategoryUpdateRequestDto extends BaseParentAndChildDto {

    private String name;

    private String attachId;

}
