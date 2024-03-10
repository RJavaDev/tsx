package uz.tsx.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseUserDto;
import uz.tsx.entity.UserEntity;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto extends BaseUserDto {

    @NotBlank(message = "firstname must not be null!!!")
    private String firstname;

    private String lastname;

    @NotBlank(message = "emailOrPhone must not be null!!!")
    @Schema(name = "emailOrPhone", example = "example@gmail.com OR +998901389918")
    private String emailOrPhone;

    @NotBlank(message = "password must not be null!!!")
    private String password;

    public UserEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new UserEntity(), ignoreProperties);
    }

}
