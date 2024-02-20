package uz.tsx.dto.request;

import lombok.*;
import uz.tsx.dto.base.BaseUserDto;
import uz.tsx.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto extends BaseUserDto {

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String username;

    private String password;

    private String attachId;

    public UserEntity toEntity(String... ignoreProperties) {
        return super.toEntity(this, new UserEntity(), ignoreProperties);
    }

}
