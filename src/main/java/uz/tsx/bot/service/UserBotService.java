package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.bot.entity.UserBotEntity;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.role.RoleEnum;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.AnnouncementService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserBotService {

    private final UserBotRepository userBotRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnnouncementService announcementService;

    public Optional<UserBotEntity> getUserByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId);
    }

    public boolean isUserNotExistByChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId).isEmpty();
    }

    public boolean isUserNotExistByPhoneNumberAndChatId(String chatId) {
        return userBotRepository.getUserByChatId(chatId)
                .map(UserBotEntity::getUserEntity)
                .isEmpty();
    }

    public boolean isUserExistByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber).isPresent();
    }

    @Transactional
    public void mergeUserAccounts(String phoneNumber, String chatId) {
        userRepository.getUserByPhoneNumber(phoneNumber).ifPresent(userEntity ->
                userBotRepository.getUserByChatId(chatId).ifPresent(userBotEntity -> {
                    userBotEntity.setUserEntity(userEntity);
                    userBotRepository.save(userBotEntity);
                })
        );
    }

    public void setUserState(String chatId, StateEnum state) {
        userBotRepository.setUserState(chatId, String.valueOf(state));
    }

    public StateEnum getUserState(String chatId) {
        String state = userBotRepository.getUserState(chatId);
        return StateEnum.valueOf(state);
    }

    public String getUserLang(String chatId) {
       return userBotRepository.getUserLang(chatId);
    }

    public void createBotUser(String chatId){
        UserBotEntity userBotEntity=new UserBotEntity();
        userBotEntity.setChatId(chatId);
        userBotEntity.setCreatedDate(LocalDateTime.now());
        userBotRepository.save(userBotEntity);
    }

    public void setUserLang(String chatId, String languageCode) {
        getUserByChatId(chatId).ifPresent(userBotEntity -> {
            userBotEntity.setLanguage(languageCode);
            userBotRepository.save(userBotEntity);
        });
    }

    public Optional<AnnouncementEntity> getUserAnnouncement(String chatId, int page) {
        return userBotRepository.getUserByChatId(chatId)
                .flatMap(userBotEntity -> {
                    Long userEntityId = userBotEntity.getUserEntity().getId();
                    List<AnnouncementEntity> announcementList = announcementService.getAnnouncementListByUserEntity(userEntityId);
                    return (page >= 0 && page < announcementList.size()) ? Optional.of(announcementList.get(page)) : Optional.empty();
                });
    }

    public int getUserAnnouncementCount(String chatId) {
        return userBotRepository.getUserByChatId(chatId)
                .map(userBotEntity -> announcementService.getAnnouncementListByUserEntity(userBotEntity.getUserEntity().getId()).size())
                .orElse(0);
    }

    public void registerUser(String chatId, String phoneNumber, String password, String firstName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailOrPhone(phoneNumber);
        userEntity.setFirstname(firstName);
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRoleEnumList(Collections.singletonList(RoleEnum.USER));
        UserEntity savedUser = userRepository.save(userEntity);

        Optional<UserBotEntity> userBotEntityOptional = getUserByChatId(chatId);
        if (userBotEntityOptional.isPresent()) {
            UserBotEntity userBotEntity = userBotEntityOptional.get();
            userBotEntity.setUserEntity(savedUser);
            userBotRepository.save(userBotEntity);
        }
    }

}
