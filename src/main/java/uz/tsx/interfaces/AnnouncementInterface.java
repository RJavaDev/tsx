package uz.tsx.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    default String[] getAddressByAcceptLanguage(){
//      en ru uz
        if(Objects.nonNull(getAddress())){
            return getAddress().split("\\|");
        }
        return getAddress().split("");
    }
}
