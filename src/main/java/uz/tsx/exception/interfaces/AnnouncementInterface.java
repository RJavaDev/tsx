package uz.tsx.interfaces;

import uz.tsx.dto.CurrencyDto;

import java.math.BigDecimal;
import java.util.Date;

public interface AnnouncementInterface {
    Long getId();
    String getTitle();
    Date getCreatedDate();
    Double getLongitude();
    Double getLatitude();
    String getPhone();
    String getGmail();
    String getAddress();
    Long getCurrencyId();
    String getCurrencyCode();
    BigDecimal getPrice();
    Boolean getIsDeal();
    Boolean getIsFree();
    Boolean getIsExchange();
}
