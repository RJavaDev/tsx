package uz.tsx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.constants.ByLoginEnumType;
import uz.tsx.dto.base.BaseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String firstname;

    private String lastname;

    private String emailOrPhone;

    private String addressRu;
    private String addressUz;
    private String addressEn;

    private AttachUrlResponse attach;

    private List<RoleEnum> roleEnumList;

    private ByLoginEnumType byLogin;
    public UserEntity toEntity( String... ignoreProperties) {
        return super.toEntity(this, new UserEntity(), ignoreProperties);
    }

}
