package uz.tsx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.CategoryEntity;

import java.util.List;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameUz;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameRu;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "name must not be empty")
    private String nameEn;

    private Long parentId;

    private CategoryDto parent;

    private List<CategoryDto> children;

    private AttachUrlResponse attach;

    private String router;



    public CategoryEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new CategoryEntity(), ignoreProperties);
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
