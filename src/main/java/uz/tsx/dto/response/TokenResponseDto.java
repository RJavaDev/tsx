package uz.tsx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.tsx.dto.UserDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {

  private String token;
  private UserDto user;
}
