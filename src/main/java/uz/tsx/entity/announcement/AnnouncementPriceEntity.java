package uz.tsx.entity.announcement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.CurrencyEntity;
import uz.tsx.entity.base.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = TableNames.ANNOUNCEMENT_PRICE)
public class AnnouncementPriceEntity extends BaseEntity {
    @Column(name = "currency_id")
    private Long currencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", insertable = false, updatable = false)
    private CurrencyEntity currency;

    private BigDecimal price;

    private Boolean isDeal = Boolean.FALSE;

    private Boolean isFree = Boolean.FALSE;

    private Boolean isExchange = Boolean.FALSE;
}
