package uz.tsx.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OptionGroupUpdate {

    @NotNull(message = "id must not be empty")
    private Long id;

    private String nameUz;

    private String nameRu;

    private String nameEn;
}
