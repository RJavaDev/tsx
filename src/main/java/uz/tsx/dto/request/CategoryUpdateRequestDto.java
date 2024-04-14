package uz.tsx.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;



@Getter
@Setter
public class CategoryUpdateRequestDto extends BaseParentAndChildDto {

    @NotNull(message = "You need to provide the id number of the category you want to change!")
    private Long id;

    private String nameUz;
    private String nameRu;
    private String nameEn;

    private String attachId;
}
