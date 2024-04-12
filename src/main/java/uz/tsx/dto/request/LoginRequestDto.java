package uz.tsx.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

  @NotBlank(message = "emailOrPhone must not be empty")
  private String emailOrPhone;
  @NotBlank(message = "password must not be empty")
  String password;
}
