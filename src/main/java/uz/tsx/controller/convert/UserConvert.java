package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.request.UserCreateRequestDto;
import uz.tsx.dto.request.UserUpdateRequestDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class UserConvert {


    public UserDto from(UserEntity user) {
        UserDto userDto = user.toDto("password", "birtDate");
        AttachEntity attach = user.getAttach();
        if(Objects.nonNull(attach)){
            userDto.setAttach(AttachConvert.convertToAttachUrlDto(attach));
        }
        return userDto;
    }

    public UserDto from(String fullName, String phoneNumber){
        UserDto userDto = new UserDto();
        userDto.setFirstname(fullName);
        userDto.setPhoneNumber(phoneNumber);
        return userDto;
    }

    public List<UserDto> fromEntity(List<UserEntity> userEntityList) {
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
