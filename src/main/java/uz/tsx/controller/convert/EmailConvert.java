package uz.tsx.controller.convert;

import lombok.experimental.UtilityClass;
import uz.tsx.dto.request.EmailCreateRequestDto;
import uz.tsx.entity.EmailEntity;

import java.util.Random;

@UtilityClass
public class EmailConvert {
    public EmailEntity convertToEntity(EmailCreateRequestDto dto){
        Random random=new Random();
        int random1=random.nextInt(90000) + 10000;
        EmailEntity email=new EmailEntity();
        email.setCod(random1);
        email.setEmail(dto.getEmail());
        return email;
    }
}
