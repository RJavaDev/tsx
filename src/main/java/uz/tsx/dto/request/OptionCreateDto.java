package uz.tsx.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionCreateDto {

    @NotNull(message = "nameUz must not be empty")
    private String nameUz;

    @NotNull(message = "nameRu must not be empty")
    private String nameRu;

    @NotNull(message = "nameEn must not be empty")
    private String nameEn;

    @NotNull(message = "optionGroupId must not be empty")
    private Long optionGroupId;

    @NotNull(message = "categoryId must not be empty")
    private Long categoryId;
}
