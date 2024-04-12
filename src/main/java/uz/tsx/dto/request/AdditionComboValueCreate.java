package uz.tsx.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionComboValueCreate {

    @NotBlank(message = "nameUz must not be empty")
    private String nameUz;

    @NotBlank(message = "nameRu must not be empty")
    private String nameRu;

    @NotBlank(message = "nameEn must not be empty")
    private String nameEn;

    @NotNull(message = "groupId must not be empty")
    private Long groupId;
}
