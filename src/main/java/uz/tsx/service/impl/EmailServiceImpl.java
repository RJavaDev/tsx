package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.validation.CommonSchemaValidator;


import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    public boolean sendMessage(String emailAddress, int code) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("tsx@gmail.com");
            simpleMailMessage.setTo(emailAddress);
            simpleMailMessage.setSubject("tsx@gmail.com");
            simpleMailMessage.setText(String.valueOf(code));
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
