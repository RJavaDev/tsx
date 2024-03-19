package uz.tsx.service.smsUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.service.smsUtil.util.SMSCodeDB;
import uz.tsx.service.smsUtil.util.SMSTokenService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMSCode {

    private final SMSTokenService smsTokenService;
    public String save(){
        String randomCode = String.valueOf(new Random().nextInt(10000, 100000));
        String username = SecurityUtils.getUsername();

        if(username.endsWith("@gmail.com")){

        }

        String token = smsTokenService.generateToken(randomCode);

        SMSCodeDB.save(username, token);
        return randomCode;
    }

    public boolean isValid(String smsCode){

        String username = SecurityUtils.getUsername();

        String token = SMSCodeDB.getToken(username);
        return smsTokenService.isTokenValid(token, smsCode);
    }
}
