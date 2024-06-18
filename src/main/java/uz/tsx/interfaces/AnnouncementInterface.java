package uz.tsx.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AnnouncementInterface {
    Long getId();
    String getAttachId();
    String getTitle();
    LocalDateTime getCreatedDate();
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
    Integer getISaw();
    String getAttachType();
    String getAttachPath();
    String getRouter();
}
