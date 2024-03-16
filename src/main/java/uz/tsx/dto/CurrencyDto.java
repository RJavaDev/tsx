package uz.tsx.dto;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.base.BaseDto;

@Getter
@Setter
public class CurrencyDto extends BaseDto {
    private String name;

    private String code;
}
