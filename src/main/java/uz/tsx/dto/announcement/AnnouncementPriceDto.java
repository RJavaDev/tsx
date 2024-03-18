package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.CurrencyDto;
import uz.tsx.dto.base.BaseDto;

import java.math.BigDecimal;

@Getter
@Setter
public class AnnouncementPriceDto extends BaseDto {
    private Long currencyId;

    private CurrencyDto currency;

    private BigDecimal price;

    private Boolean isDeal = Boolean.FALSE;

    private Boolean isFree = Boolean.FALSE;

    private Boolean isExchange = Boolean.FALSE;
}
