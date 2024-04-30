package uz.tsx.SMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.constants.Characters;
import uz.tsx.exception.UsernameNotFoundException;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.impl.EmailService;
import uz.tsx.SMS.model.SMSCodeDB;
import uz.tsx.validation.UserValidation;

import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMSCodeService {

    private final EmailService emailService;

    private final SMSTokenService smsTokenService;

    private final SMSService smsService;

    private final UserRepository userRepository;

    public boolean send(String username){
        sendSMS(username);
        return true;
    }

    public boolean send(){
        sendSMS(SecurityUtils.getUsername());
        return true;
    }

    public void sendSMS(String emailOrPhone){

        String randomCode = String.valueOf(new Random().nextInt(10000, 100000));
        String sendSMSEmailOrPhone = null;
        if(UserValidation.isUserEmailAddress(emailOrPhone)){
            userRepository.getByUsernameOriginDB(emailOrPhone).orElseThrow(() -> new UsernameNotFoundException(emailOrPhone + " user not found"));
            emailService.sendMessage(emailOrPhone, randomCode);
            sendSMSEmailOrPhone = emailOrPhone;
        }else{
            String phoneNumberByPattern = UserValidation.getPhoneNumberByPattern(emailOrPhone);
            if(Objects.nonNull(phoneNumberByPattern)){
                sendSMSEmailOrPhone = Characters.PHONE_NUMBER_PREFIX + phoneNumberByPattern;
                userRepository.getByUsernameOriginDB(sendSMSEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException(emailOrPhone + " user not found"));
                smsService.sendSMS(phoneNumberByPattern, randomCode);
            }else{
                throw new IllegalArgumentException(emailOrPhone + " Email or phone number is incorrect");
            }
        }

        String token = smsTokenService.generateToken(randomCode);
        SMSCodeDB.save(sendSMSEmailOrPhone, token);
    }

    public boolean isValid(String smsCode){

        String username = SecurityUtils.getUsername();

        String token = SMSCodeDB.getToken(username);
        return smsTokenService.isTokenValid(token, smsCode);
    }
}
