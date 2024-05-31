package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.entity.UserBotEntity;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.entity.UserEntity;
import uz.tsx.exception.AuthenticationException;
import uz.tsx.repository.UserRepository;
import uz.tsx.validation.CommonSchemaValidator;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserBotService {

    private final UserBotRepository userBotRepository;
    private final UserRepository userRepository;
    private final CommonSchemaValidator commonSchemaValidator;
    private final PasswordEncoder passwordEncoder;

//    public boolean add(String chatId,String phoneNumber,String fullName){
//        try {
//           UserEntity user= commonSchemaValidator.validateUserBotPhoneNumber(phoneNumber);
//           UserBotEntity botEntity = userBotRepository.getUserByChatId(chatId).get();
//           botEntity.setUserEntity(user);
//           botEntity.forCreate();
//           userBotRepository.save(botEntity);
//            return false;
//        }catch (IllegalStateException e){
//            UserCreateRequestDto dto = UserConvert.fromBot( phoneNumber,fullName);
//            UserEntity userEntity = UserConvert.convertToEntity(dto);
//            Optional<UserBotEntity> botEntity = userBotRepository.getUserByChatId(chatId);
//            if (botEntity.isPresent()){
//                UserEntity save = repository.save(userEntity);
//                UserBotEntity bot = botEntity.get();
//                bot.setUserEntity(save);
//                userEntity.forCreate();
//                bot.forCreate();
//                userBotRepository.save(bot);
//            }
//
//            return true;
//        }
//    }

    public Optional<UserBotEntity> getUserByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId);
    }

    public boolean isUserNotExistByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId).isEmpty();
    }

    public boolean isUserNotExistByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber).isEmpty();
    }

    public void setUserState(String chatId, StateEnum state) {
        BotConstants.USER_STATE.put(chatId, state);
    }

    public StateEnum getUserState(String chatId) {
        return BotConstants.USER_STATE.get(chatId);
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

            BotConstants.USER_PHONE_NUMBER.remove(chatId);
        }
    }

    public boolean login(String chatId, String password) {
        String phoneNumber = String.valueOf(BotConstants.USER_PHONE_NUMBER.get(chatId));
        Optional<UserEntity> userEntityOptional = userRepository.getUserByPhoneNumber(phoneNumber);

        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new IllegalArgumentException("User not found for phone number: " + phoneNumber));
        return passwordEncoder.matches(password, userEntity.getPassword());
    }

}
