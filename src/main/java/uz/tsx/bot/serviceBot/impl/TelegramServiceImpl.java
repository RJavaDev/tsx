package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.configBot.MyTelegramPollingBot;
import uz.tsx.bot.serviceBot.TelegramService;

@RequiredArgsConstructor
@Service
public class TelegramServiceImpl implements TelegramService {

    private final MyTelegramPollingBot telegramLongPollingBot;

    public void executeMessage(SendMessage message) throws TelegramApiException {
        telegramLongPollingBot.execute(message);
    }

    public void executeDocument(SendDocument document) throws TelegramApiException {
        telegramLongPollingBot.execute(document);
    }

    public void executePhoto(SendPhoto photo) throws TelegramApiException {
        telegramLongPollingBot.execute(photo);
    }

    public void executeVideo(SendVideo video) throws TelegramApiException {
        telegramLongPollingBot.execute(video);
    }

    public void executeEditMessageReplyMarkup(EditMessageReplyMarkup editMarkup) throws TelegramApiException {
        telegramLongPollingBot.execute(editMarkup);
    }
}
