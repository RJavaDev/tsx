package uz.tsx.service.smsUtil.util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class SMSCodeDB {

    private static final Map<String, String> smsCode = new HashMap<>();

    public void save(String username, String token){
        smsCode.put(username, token);
    }

    public String getToken(String username){
        return smsCode.get(username);
    }
}
