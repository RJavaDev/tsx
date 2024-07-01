package uz.tsx.dto.announcement.additionInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;

import java.util.Locale;

@Getter
@Setter
public class AdditionComboValueDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameUz;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameRu;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nameEn;

    private Long groupId;

    private AdditionGroupDto group;

    public String getName() {
        Locale locale = LocaleContextHolder.getLocale();
        return switch (locale.getLanguage()) {
            case "uz" -> nameUz;
            case "ru" -> nameRu;
            default -> nameEn;
        };
    }
}
