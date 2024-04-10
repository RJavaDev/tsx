package uz.tsx.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

@Getter
@Setter
public class CategoryCreateRequestDto extends BaseParentAndChildDto {

    @NotBlank(message = "Category name uz must not be null!!!")
    private String nameUz;
    @NotBlank(message = "Category name ru must not be null!!!")
    private String nameRu;
    @NotBlank(message = "Category name en must not be null!!!")
    private String nameEn;

    private String attachId;
}
