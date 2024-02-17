package uz.tsx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.dto.base.BaseServerModifierDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseServerModifierDto {

    private String firstname;

    private String phoneNumber;

    private String username;

    private String address;

    private AttachUrlResponse attach;

    private List<RoleEnum> roleEnumList;
    public UserEntity toEntity( String... ignoreProperties) {
        return super.toEntity(this, new UserEntity(), ignoreProperties);
    }

}
