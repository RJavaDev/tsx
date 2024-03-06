package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseParentAndChildDto;

@Getter
@Setter
public class RegionUpdateRequestDto extends BaseParentAndChildDto {

    private String name_uz;
    private String name_ru;
    private String name_en;
}
