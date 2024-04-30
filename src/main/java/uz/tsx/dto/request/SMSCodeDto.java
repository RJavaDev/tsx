package uz.tsx.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SMSCodeDto {
    @NotBlank(message = "code cannot be empty")
    private String smsCode;
}
