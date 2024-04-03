package uz.tsx.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OptionGroupCreateDto {

    @NotBlank(message = "OperationGroup name uzb must not be null!!!")
    private String nameUz;

    @NotBlank(message = "OperationGroup name rus must not be null!!!")
    private String nameRu;

    @NotBlank(message = "OperationGroup name eng must not be null!!!")
    private String nameEn;
}
