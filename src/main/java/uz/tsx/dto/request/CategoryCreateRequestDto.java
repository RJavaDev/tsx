package uz.tsx.dto.request;


import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class CategoryCreateRequestDto extends BaseParentAndChildDto {

    @NotBlank(message = "Category name must not be null!!!")
    private String name_uz;
    @NotBlank(message = "Category name must not be null!!!")
    private String name_ru;
    @NotBlank(message = "Category name must not be null!!!")
    private String name_en;

    private String attachId;
}
