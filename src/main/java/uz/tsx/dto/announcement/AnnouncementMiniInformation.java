package uz.tsx.dto.announcement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
public class AnnouncementMiniInformation extends BaseDto {

    private String title;

    private String description;

    private BigDecimal price;

    private String currencyCode;

    private AttachUrlResponse attachUrlResponses;

    private String address;

    private String router;

    private Integer iSaw;

    private LocalDateTime createDateTime=this.getCreatedDate();

    public String setAddress(String[] address) {
        Locale locale = LocaleContextHolder.getLocale();
        return switch (locale.getLanguage()) {
            case "uz" -> this.address = address[2];
            case "ru" -> this.address = address[1];
            default -> this.address = address[0];
        };
    }
}
