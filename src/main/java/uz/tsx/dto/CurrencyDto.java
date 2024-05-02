package uz.tsx.dto;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.CurrencyEntity;

@Getter
@Setter
public class CurrencyDto extends BaseDto {
    private String name;

    private String code;
    public CurrencyEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new CurrencyEntity(), ignoreProperties);
    }
}
