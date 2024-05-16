package uz.tsx.bot.configBot;


import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.constantsBot.StatusMessage;
import uz.tsx.bot.handler.MarkupHandler;
import uz.tsx.bot.serviceBot.impl.UserBotServiceImpl;
import uz.tsx.service.impl.AnnouncementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class MyTelegramPollingBotImpl extends TelegramLongPollingBot {

    String BOT_TOKEN ="7022773757:AAGkwgMhmTZzJcClTIsl3HaT5srh0BWxQLM";
    String BOT_USERNAME ="@Tez_Sotish_Xizmatibot";


    private final   UserBotServiceImpl userBotService;

    private final AnnouncementServiceImpl announcementService;




    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        MarkupHandler markupHandler=new MarkupHandler();
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text="";
        if (Objects.nonNull(message.getText())){
            text=message.getText();
        }
        if (text.equals(StatusMessage.START)) {
            ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of(StatusMessage.MENU, StatusMessage.SETTINGS, StatusMessage.MYADS), 2);
            myExecute(replyKeyboardMarkup,StatusMessage.HI+message.getFrom().getFirstName(),chatId);
        }else if (update.hasMessage() && update.getMessage().hasContact()) {
            Contact contact = update.getMessage().getContact();
            String phoneNumber = contact.getPhoneNumber();
            if (userBotService.add(phoneNumber,message.getFrom().getFirstName())){
                myExecute(StatusMessage.PASSWORD,chatId);
            }
        }else if (text.equals("Mening e'lonlarim \uD83D\uDCDC")){
            getUserPhoneNumber(message);
           }
    }

    public void getUserPhoneNumber( Message message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Mening kantaktim ☎️");
        keyboardButton.setRequestContact(true);
        keyboardRow.add(keyboardButton);
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        try {
            myExecute(replyKeyboardMarkup,"Iltimos, kontaktingizni men bilan baham ko'ring:",message.getChatId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

     private void myExecute(ReplyKeyboardMarkup r, String text, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setReplyMarkup(r);
        execute(sendMessage);
    }
    private void myExecute( String text, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        execute(sendMessage);
    }



}
