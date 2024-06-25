package uz.tsx.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;

import java.io.File;

@Service
@RequiredArgsConstructor
public class BotService {
    public DeleteMessage deletePreviousMessage(String chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        return deleteMessage;
    }

    public EditMessageMedia editPhotoMessage(String chatId, Integer messageId, AnnouncementEntity announcementEntity) {
        AttachEntity attachEntity = announcementEntity.getAttachPhotos().get(0);

        String filePath = "images/" + attachEntity.getPath() + attachEntity.getId() + attachEntity.getContentType();
        File file = new File(filePath);

        EditMessageMedia editMessageMedia = new EditMessageMedia();
        editMessageMedia.setChatId(chatId);
        editMessageMedia.setMessageId(messageId - 1);

        InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
        inputMediaPhoto.setMedia(file, file.getName());

        editMessageMedia.setMedia(inputMediaPhoto);
        return editMessageMedia;
    }

    public SendPhoto sendPhoto(String chatId, AttachEntity attachEntity) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        File file = new File("images/" + attachEntity.getPath() + attachEntity.getId() + attachEntity.getContentType());
        sendPhoto.setPhoto(new InputFile(file));
        return sendPhoto;
    }
}
