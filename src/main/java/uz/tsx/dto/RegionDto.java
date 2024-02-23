package uz.tsx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseServerModifierDto;
import uz.tsx.entity.RegionEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto extends BaseServerModifierDto {
    @NotBlank(message = "name must not be empty")
    private String name;

    private Integer parentId;

    private List<RegionDto> children;

    public RegionEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new RegionEntity(), ignoreProperties);
    }
}
