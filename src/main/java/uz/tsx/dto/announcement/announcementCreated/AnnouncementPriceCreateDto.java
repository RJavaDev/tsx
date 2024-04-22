package uz.tsx.dto.announcement.announcementCreated;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AnnouncementPriceCreateDto {

    private Long currencyId;

    private BigDecimal price;

    private Boolean isDeal = Boolean.FALSE;

    private Boolean isFree = Boolean.FALSE;

    private Boolean isExchange = Boolean.FALSE;
}
