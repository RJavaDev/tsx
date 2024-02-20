package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;

@UtilityClass
public class TokenResponseConvert {

    public TokenResponseDto from(String token, UserEntity user){
        return TokenResponseDto.builder()
                .token(token)
                .user(UserConvert.from(user))
                .build();
    }

}
