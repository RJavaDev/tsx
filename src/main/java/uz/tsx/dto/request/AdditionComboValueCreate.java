package uz.tsx.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionComboValueCreate {

    @NotNull(message = "nameUz must not be empty")
    private String nameUz;

    @NotNull(message = "nameRu must not be empty")
    private String nameRu;

    @NotNull(message = "nameEn must not be empty")
    private String nameEn;

    @NotBlank(message = "groupId must not be empty")
    private Long groupId;
}
