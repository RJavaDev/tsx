package uz.tsx.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

@Getter
@Setter
public class RegionUpdateRequestDto extends BaseParentAndChildDto {

    @Min(value = 1, message = "id must be greater than 0")
    private Long id;
    private String nameUz;
    private String nameRu;
    private String nameEn;
}
