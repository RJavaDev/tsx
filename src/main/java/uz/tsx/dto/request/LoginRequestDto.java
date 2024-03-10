package uz.tsx.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
