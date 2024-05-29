package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.entity.UserBotEntity;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.entity.UserEntity;
import uz.tsx.repository.UserRepository;
import uz.tsx.validation.CommonSchemaValidator;

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
//    @Transactional
//    public void addPassword(String chatId, String passwordText) {
//        String passwordUser = passwordEncoder.encode(passwordText);
//        userBotRepository.userAddPassword(chatId,passwordUser);
//    }

    public boolean getUserById(String chatId) {
        Optional<UserBotEntity> userByChatId = userBotRepository.getUserByChatId(chatId);
        return userByChatId.isPresent();
    }

    public boolean isUserNotExistByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId).isEmpty();
    }

    public boolean isUserNotExistByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber).isEmpty();
    }

    public String getUserState(String chatId) {
        return userBotRepository.getUserState(chatId);
    }

    public void registerUser(String chatId, String phoneNumber, String langCode) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailOrPhone(phoneNumber);
        UserEntity savedUser = userRepository.save(userEntity);

        UserBotEntity userBotEntity = new UserBotEntity();
        userBotEntity.setUserEntity(savedUser);
        userBotEntity.setState(BotConstants.SHARE_CONTACT);
        userBotEntity.setLanguage(langCode);
        userBotEntity.setChatId(chatId);
        userBotRepository.save(userBotEntity);
    }

}
