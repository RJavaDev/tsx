package uz.tsx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.CategoryEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseDto {

    @NotBlank(message = "name must not be empty")
    private String name_uz;
    @NotBlank(message = "name must not be empty")
    private String name_ru;
    @NotBlank(message = "name must not be empty")
    private String name_en;

    private Long parentId;

    private CategoryDto parent;

    private List<CategoryDto> children;

    private AttachUrlResponse attach;

    public CategoryEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new CategoryEntity(), ignoreProperties);
    }
}
