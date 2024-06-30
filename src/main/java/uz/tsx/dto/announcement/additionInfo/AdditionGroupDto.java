package uz.tsx.dto.announcement.additionInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

import java.util.Locale;

@Getter
@Setter
public class AdditionGroupDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameUz;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameRu;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameEn;

    private AdditionType type;

    private CategoryDto categoryDto;

    public String getName() {
        Locale locale = LocaleContextHolder.getLocale();
        return switch (locale.getLanguage()) {
            case "uz" -> nameUz;
            case "ru" -> nameRu;
            default -> nameEn;
        };
    }
}
