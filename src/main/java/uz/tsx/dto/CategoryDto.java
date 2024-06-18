package uz.tsx.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.CategoryEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseDto {

    @NotBlank(message = "name must not be empty")
    private String nameUz;

    @NotBlank(message = "name must not be empty")
    private String nameRu;

    @NotBlank(message = "name must not be empty")
    private String nameEn;

    private Long parentId;

    private CategoryDto parent;

    private List<CategoryDto> children;

    private AttachUrlResponse attach;

    private String router;

    public CategoryEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new CategoryEntity(), ignoreProperties);
    }
}
