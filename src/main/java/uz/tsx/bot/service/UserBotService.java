package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.entity.UserBotEntity;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.entity.UserEntity;
import uz.tsx.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserBotService {

    private final UserBotRepository userBotRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserBotEntity> getUserByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId);
    }

    public boolean isUserNotExistByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId).isEmpty();
    }

    public boolean isUserNotExistByPhoneNumberAndChatId(String phoneNumber, String chatId) {
        return userRepository.getByPhoneNumberAndChatId(phoneNumber, chatId).isEmpty();
    }

    public void setUserState(String chatId, StateEnum state) {
        userBotRepository.setUserState(chatId, String.valueOf(state));
    }

    public StateEnum getUserState(String chatId) {
        String state = userBotRepository.getUserState(chatId);
        return StateEnum.valueOf(state);
    }

    public void createBotUser(String chatId, String languageCode){
        UserBotEntity userBotEntity=new UserBotEntity();
        userBotEntity.setLanguage(languageCode);
        userBotEntity.setChatId(chatId);
        userBotEntity.setCreatedDate(LocalDateTime.now());
        userBotRepository.save(userBotEntity);
    }

    public void registerUser(String chatId, String phoneNumber, String password, String firstName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailOrPhone(phoneNumber);
        userEntity.setFirstname(firstName);
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(password));
        UserEntity savedUser = userRepository.save(userEntity);

        Optional<UserBotEntity> userBotEntityOptional = getUserByChatId(chatId);
        if (userBotEntityOptional.isPresent()) {
            UserBotEntity userBotEntity = userBotEntityOptional.get();
            userBotEntity.setUserEntity(savedUser);
            userBotRepository.save(userBotEntity);
        }
    }

//    public boolean login(String chatId, String password) {
//        String phoneNumber = String.valueOf(BotConstants.USER_PHONE_NUMBER.get(chatId));
//        Optional<UserEntity> userEntityOptional = userRepository.getUserByPhoneNumberAndChatId(phoneNumber);
//
//        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new IllegalArgumentException("User not found for phone number: " + phoneNumber));
//        return passwordEncoder.matches(password, userEntity.getPassword());
//    }

}
