package uz.tsx.bot.serviceBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface TelegramService {

      void executeMessage(SendMessage message) throws TelegramApiException;
}
