package uz.tsx.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AdditionGroupUpdate {

    @NotBlank(message = "id must not be empty")
    private Long id;

    private String nameUz;

    private String nameRu;

    private String nameEn;

    private AdditionType type;

    private Long categoryId;
}
