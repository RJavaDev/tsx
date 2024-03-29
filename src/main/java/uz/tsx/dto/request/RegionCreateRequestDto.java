package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegionCreateRequestDto extends BaseParentAndChildDto {

    @NotEmpty(message = "Region name should not be empty")
    private String name_uz;
    @NotEmpty(message = "Region name should not be empty")
    private String name_ru;
    @NotEmpty(message = "Region name should not be empty")
    private String name_en;
}
