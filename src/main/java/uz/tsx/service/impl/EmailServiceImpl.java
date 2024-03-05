package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.entity.EmailEntity;
import uz.tsx.repository.EmailRepository;
import uz.tsx.service.EmailService;
import uz.tsx.validation.CommonSchemaValidator;


import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final ThreadEmailServiceImpl threadService;

    private final EmailRepository repository;
    private final CommonSchemaValidator commonSchemaValidator;
    @Override
    public EmailEntity getById(Integer id) {
        return null;
    }

    @Override
    public List<EmailEntity> getAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    @Transactional
    public boolean add(EmailEntity emailEntity) {

        commonSchemaValidator.validateEmail(emailEntity.getEmail());
        emailEntity.forCreate();
        repository.save(emailEntity);
        sendMessage(emailEntity.getEmail(), emailEntity.getCod());

       threadService.start();
        return true;
    }
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
