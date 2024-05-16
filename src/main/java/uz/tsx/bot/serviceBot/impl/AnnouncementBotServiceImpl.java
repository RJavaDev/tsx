package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.tsx.controller.convert.AnnouncementConvert;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.BigDataTable;
import uz.tsx.interfaces.AnnouncementInterface;
import uz.tsx.repository.AnnouncementRepository;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementBotServiceImpl {

    private final CommonSchemaValidator commonSchemaValidator;
    private final AnnouncementRepository repository;

    public List<AnnouncementDto> getAnnouncementListByPhoneNumber(String phoneNumber){
//        BigDataTable<AnnouncementInterface> pageAnnouncementData = repository.getAnnouncementListByPhoneNumber(phoneNumber);
//        BigDataTable<AnnouncementDto> dtoList = AnnouncementConvert.convertInterfaceToDto(pageAnnouncementData);
          return null;
    }
}
