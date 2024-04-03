package uz.tsx.SMS.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.service.impl.EmailService;
import uz.tsx.SMS.model.SMSCodeDB;

import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMSCodeService {

    private final EmailService emailService;

    private final SMSTokenService smsTokenService;

    private final SMSService smsService;
    public boolean save(){

        String randomCode = String.valueOf(new Random().nextInt(10000, 100000));
        String username = SecurityUtils.getUsername();

        //Send sms email or phone number

        if(Objects.requireNonNull(username).endsWith("@gmail.com")){
            emailService.sendMessage(username, randomCode);
        }else {
            smsService.sendSMS(username, randomCode);
        }

        String token = smsTokenService.generateToken(randomCode);
        SMSCodeDB.save(username, token);

        return true;
    }

    public boolean isValid(String smsCode){

        String username = SecurityUtils.getUsername();

        String token = SMSCodeDB.getToken(username);
        return smsTokenService.isTokenValid(token, smsCode);
    }
}
