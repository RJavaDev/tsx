package uz.tsx.bot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.handler.Handler;
import uz.tsx.bot.serviceBot.TelegramService;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Lazy
public class UpdateHandler implements Handler<Update> {
    private final TelegramService telegramService;

    @Override
    public void handleMessage(Update update) throws TelegramApiException {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = message.getText();
            if (text.equals("/start")) {
               myExecute(replyKeyboardMarkup(List.of("SOZLAMALAR", "MENIYU", "MENING ELONLARIM"),1), null, "Assalomu alaykum "+message.getChat().getFirstName(), chatId);
            }
        }
    }
    private ReplyKeyboardMarkup replyKeyboardMarkup(List<String> menuItems, int n){
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        List<KeyboardRow> buttonRow = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        for (int i = 0; i < menuItems.size(); i++) {
            keyboardButtons.add(new KeyboardButton(menuItems.get(i)));
            if (i % n == 0){
                buttonRow.add(keyboardButtons);
                keyboardButtons = new KeyboardRow();
            }
        }
        if (keyboardButtons.size() > 0){
            buttonRow.add(keyboardButtons);
        }
        r.setKeyboard(buttonRow);
        return r;
    }
    public void myExecute(ReplyKeyboardMarkup r, InlineKeyboardMarkup i, String text, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setReplyMarkup(r == null ? i : r);
       telegramService.executeMessage(sendMessage);
    }



}
