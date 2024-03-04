package uz.tsx.service;

import uz.tsx.entity.EmailEntity;

public interface EmailService extends BaseInterface<EmailEntity>{
    boolean add(EmailEntity emailEntity);
}
