package uz.tsx.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.RegionEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto extends BaseDto {

    @NotBlank(message = "name must not be empty")
    private String nameUz;

    @NotBlank(message = "name must not be empty")
    private String nameEn;

    @NotBlank(message = "name must not be empty")
    private String nameRu;

    private Long parentId;

    private List<RegionDto> children;

    public RegionEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new RegionEntity(), ignoreProperties);
    }
}
