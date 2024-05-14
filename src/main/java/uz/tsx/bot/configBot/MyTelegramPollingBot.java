package uz.tsx.bot.configBot;


import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class MyTelegramPollingBot extends TelegramLongPollingBot {

    String BOT_TOKEN ="7022773757:AAGkwgMhmTZzJcClTIsl3HaT5srh0BWxQLM";
    String BOT_USERNAME ="t.me/Tez_Sotish_Xizmatibot";

    @Resource
    private UserBotServiceImpl userBotService;


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
        String text="";
        if (Objects.nonNull(message.getText())){
            text=message.getText();
        }
        if (text.equals(StatusMessage.START)) {
            ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of(StatusMessage.MENU, StatusMessage.SETTINGS, StatusMessage.MYADS), 2);
            myExecute(replyKeyboardMarkup,StatusMessage.HI+message.getFrom().getFirstName(),message.getChatId());
        }else if (update.hasMessage() && update.getMessage().hasContact()) {
            Contact contact = update.getMessage().getContact();
            String phoneNumber = contact.getPhoneNumber();
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
        MyTelegramPollingBot bot=new MyTelegramPollingBot();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setReplyMarkup(r);
        execute(sendMessage);
    }



}
