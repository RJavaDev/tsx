package uz.tsx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseServerModifierDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.CategoryEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseServerModifierDto {


    private String name;

    private Integer parentId;

    private List<CategoryDto> children;

    private AttachUrlResponse attach;

    public CategoryEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new CategoryEntity(), ignoreProperties);
    }
}
