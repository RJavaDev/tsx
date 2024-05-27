package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.bot.entity.UserBotEntity;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.request.UserCreateRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.UserService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserBotServiceImpl {

    private final UserBotRepository botRepository;
    private final UserRepository repository;
    private final CommonSchemaValidator commonSchemaValidator;
    private final PasswordEncoder passwordEncoder;
    public void createBot(Long chatId, String languageCode){
        UserBotEntity botEntity=new UserBotEntity();
        botEntity.setLanguage(languageCode);
        botEntity.setChatId(chatId);
        botRepository.save(botEntity);
    }
    public Boolean add(Long chatId,String phoneNumber,String fullName){
        try {
           UserEntity user= commonSchemaValidator.validateUserBotPhoneNumber(phoneNumber);
           UserBotEntity botEntity = botRepository.getUserByChatId(chatId).get();
           botEntity.setUserEntity(user);
           botEntity.forCreate();
           botRepository.save(botEntity);
            return false;
        }catch (IllegalStateException e){
            UserCreateRequestDto dto = UserConvert.fromBot( phoneNumber,fullName);
            UserEntity userEntity = UserConvert.convertToEntity(dto);
            Optional<UserBotEntity> botEntity = botRepository.getUserByChatId(chatId);
            if (botEntity.isPresent()){
                UserEntity save = repository.save(userEntity);
                UserBotEntity bot = botEntity.get();
                bot.setUserEntity(save);
                userEntity.forCreate();
                bot.forCreate();
                botRepository.save(bot);
            }

            return true;
        }


    }
    @Transactional
    public void addPassword(Long chatId, String passwordText) {
        String passwordUser = passwordEncoder.encode(passwordText);
        botRepository.userAddPassword(chatId,passwordUser);
    }

    public boolean getUserById(Long chatId) {
        Optional<UserBotEntity> userByChatId = botRepository.getUserByChatId(chatId);
        return userByChatId.isPresent();
    }
}
