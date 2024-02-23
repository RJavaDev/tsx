package uz.tsx.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;



@Getter
@Setter
public class CategoryCreateRequestDto extends BaseParentAndChildDto {

    @NotBlank(message = "category name should not be empty")
    private String name;

//    @NotBlank(message = "category attachId should not be empty")
    private String attachId;
}
