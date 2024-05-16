package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.request.UserCreateRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.UserService;
import uz.tsx.validation.CommonSchemaValidator;


@Service
@RequiredArgsConstructor
public class UserBotServiceImpl {

    private final UserRepository repository;
    private final CommonSchemaValidator commonSchemaValidator;

    public Boolean add(String phoneNumber,String fullName){
        try {
            commonSchemaValidator.validateUserBotPhoneNumber(phoneNumber);
            return false;
        }catch (Exception e){
            UserCreateRequestDto dto = UserConvert.fromBot(fullName, phoneNumber);
            UserEntity userEntity = UserConvert.convertToEntity(dto);
            userEntity.forCreate();
            repository.save(userEntity);
            return true;
        }


    }



}
