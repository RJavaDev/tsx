package uz.tsx.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.announcement.additionInfo.AdditionType;


@Getter
@Setter
public class AdditionGroupCreate {

    @NotNull(message = "nameUz must not be empty")
    private String nameUz;

    @NotNull(message = "nameRu must not be empty")
    private String nameRu;

    @NotNull(message = "nameEn must not be empty")
    private String nameEn;

    @NotNull(message = "AdditionType must not be empty")
    private AdditionType type;

    @NotNull(message = "categoryId must not be empty")
    private Long categoryId;
}
