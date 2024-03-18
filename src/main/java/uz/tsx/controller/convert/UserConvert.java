package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.constants.Characters;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.request.UserCreateRequestDto;
import uz.tsx.dto.request.UserUpdateRequestDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;
import uz.tsx.exception.interfaces.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class UserConvert {


    public UserDto from(UserEntity user) {
        UserDto userDto = user.toDto("password", "birtDate");

        userDto.setAttach(AttachConvert.convertToAttachUrlDtoForUser(user.getAttach()));

        return userDto;
    }

    public UserDto from(String fullName, String emailOrPhone){
        UserDto userDto = new UserDto();
        userDto.setFirstname(fullName);
        userDto.setEmailOrPhone(emailOrPhone);
        return userDto;
    }

    public UserDto from(UserInterface userInterface){
        UserDto dto = new UserDto();
        dto.setId(userInterface.getId());
        dto.setFirstname(userInterface.getFirstname());
        dto.setLastname(userInterface.getLastname());
        dto.setCreatedBy(userInterface.getCreated_by());
        dto.setUpdatedDate(userInterface.getUpdated_date());
        dto.setCreatedDate(userInterface.getCreated_date());
        dto.setModifiedBy(userInterface.getModified_by());
        dto.setEmailOrPhone(userInterface.getEmail_or_phone());

        dto.setRoleEnumList(stringToRoleList(userInterface.getRole_enum_list()));
        dto.setAttach(AttachConvert.convertToAttachUrlDtoForUser(userInterface.getAttach_id(), userInterface.getPath(), userInterface.getType()));

        dto.setStatus(userInterface.getStatus());
        String userRegion = userInterface.getAddress();

        if(!Objects.equals(userRegion, Characters.USER_ADDRESS_CHECK)){
            String[] address = userRegion.split("\\|");
            dto.setAddress_en(address[0]);
            dto.setAddress_ru(address[1]);
            dto.setAddress_uz(address[2]);
        }

        return dto;
    }

    public List<UserDto> fromEntity(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(UserConvert::from).toList();
    }

    public List<UserDto> from(List<UserInterface> userEntityList) {
        return userEntityList.stream().map(UserConvert::from).toList();
    }

    private UserEntity userIgnorePropertiesAdd(UserEntity user, List<RoleEnum> role) {
            user.setRoleEnumList(setRoleEnum(role));
        return user;
    }

    private List<RoleEnum> setRoleEnum(List<RoleEnum> roleEnums) {
        return roleEnums == null ? List.of(RoleEnum.USER) : roleEnums;
    }

    private List<RoleEnum> stringToRoleList(String roles) {
        String[] split = roles.split(",");
        List<RoleEnum> roleEnums = new ArrayList<>();
        for (String s : split) {
            roleEnums.add(RoleEnum.valueOf(s));
        }
        return roleEnums;
    }

    private void attachIdVerifyAndSet(String attachId, String path, String type, UserDto dto) {
        if (Objects.nonNull(attachId)) {
            AttachUrlResponse attachUrlResponse = AttachConvert.convertToAttachUrlDto(attachId, path, type);
            dto.setAttach(attachUrlResponse);
        }
    }
    public  UserEntity convertToEntity(UserUpdateRequestDto userUpdateRequestDto){
        return userUpdateRequestDto.toEntity("regionId","attachId");
    }
    public UserEntity convertToEntity(UserCreateRequestDto userCreateRequestDto) {
        return userIgnorePropertiesAdd(userCreateRequestDto.toEntity("role"), null);
    }

}
