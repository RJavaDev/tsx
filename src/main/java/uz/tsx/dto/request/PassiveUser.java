package uz.tsx.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PassiveUser {

    @NotBlank(message = "username cannot be empty")
    private String emailOrPhone;
}
