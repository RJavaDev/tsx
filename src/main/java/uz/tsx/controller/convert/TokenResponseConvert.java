package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;

@UtilityClass
public class TokenResponseConvert {

    public TokenResponseDto from(String token, UserInterface user){
        return TokenResponseDto.builder()
                .token(token)
                .user(UserConvert.from(user))
                .build();
    }

    public TokenResponseDto from(String token, UserEntity user){
        return TokenResponseDto.builder()
                .token(token)
                .user(UserConvert.from(user))
                .build();
    }

}
