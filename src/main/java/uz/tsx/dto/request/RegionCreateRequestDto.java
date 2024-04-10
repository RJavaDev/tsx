package uz.tsx.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

@Getter
@Setter
public class RegionCreateRequestDto extends BaseParentAndChildDto {

    @NotEmpty(message = "Region name should not be empty")
    private String nameUz;

    @NotEmpty(message = "Region name should not be empty")
    private String nameRu;

    @NotEmpty(message = "Region name should not be empty")
    private String nameEn;
}
