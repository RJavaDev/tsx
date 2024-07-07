package uz.tsx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.entity.RegionEntity;

import java.util.List;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameUz;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameEn;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameRu;

    private Long parentId;

    private List<RegionDto> children;

    public RegionEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new RegionEntity(), ignoreProperties);
    }

    public String getName() {
        Locale locale = LocaleContextHolder.getLocale();
        return switch (locale.getLanguage()) {
            case "uz" -> nameUz;
            case "ru" -> nameRu;
            default -> nameEn;
        };
    }
}
