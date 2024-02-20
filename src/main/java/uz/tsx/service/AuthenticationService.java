package uz.tsx.service;

import uz.tsx.dto.request.LoginRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;

public interface AuthenticationService {

    TokenResponseDto register(UserEntity request);

    TokenResponseDto authenticate(LoginRequestDto request);

    UserEntity saveUser(UserEntity user);
}
