package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdditionGroupCreate {

    @NotNull(message = "nameUz must not be empty")
    private String nameUz;

    @NotNull(message = "nameRu must not be empty")
    private String nameRu;

    @NotNull(message = "nameEn must not be empty")
    private String nameEn;

    @NotBlank(message = "AdditionType must not be empty")
    private AdditionType type;

    @NotBlank(message = "categoryId must not be empty")
    private Long categoryId;
}
