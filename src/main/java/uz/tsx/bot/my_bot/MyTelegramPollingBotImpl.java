package uz.tsx.bot.my_bot;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.container.ComponentContainer;
import uz.tsx.bot.service.UserBotService;
import uz.tsx.bot.util.ReplyKeyboardUtil;
import uz.tsx.service.impl.AnnouncementServiceImpl;

@Component
@RequiredArgsConstructor
public class MyTelegramPollingBotImpl extends TelegramLongPollingBot {

    private final UserBotService userBotService;
    private final AnnouncementServiceImpl announcementService;

    @Override
    public String getBotUsername() {
        return ComponentContainer.BOT_USERNAME;
    }
    @Override
    public String getBotToken() {
        return ComponentContainer.BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                handleText(message);
            } else if (message.hasContact()) {
                handleContact(message);
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            String data = callbackQuery.getData();
            handleCallback(message, data);
        }
    }

    public void sendMsg(Object obj) {
        try {
            if (obj instanceof SendMessage) {
                execute((SendMessage)obj);
            }
            else if (obj instanceof EditMessageText ) {
                execute((EditMessageText)obj);
            }
            else if (obj instanceof DeleteMessage) {
                execute((DeleteMessage)obj);
            }
            else if (obj instanceof SendPhoto) {
                execute((SendPhoto)obj);
            }
            else if (obj instanceof SendDocument) {
                execute((SendDocument)obj);
            }
            else if (obj instanceof ForwardMessage) {
                execute((ForwardMessage)obj);
            }
            else if (obj instanceof EditMessageReplyMarkup) {
                execute((EditMessageReplyMarkup)obj);
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleText(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        User user = message.getFrom();
        if(text.equals("/start")){
            sendMessage.setText("Assalomu alaykum, "+user.getFirstName()+"\n" + "botga xush kelibsiz.\n");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMainMenuKeyboard());
            sendMsg(sendMessage);
        } else if (text.equals(BotConstants.MYANNS)) {
            if(userBotService.isUserNotExistByChatId(chatId)) {
                sendMessage.setText("Siz ro'yxatdan o'tishingiz kerak. \n\n Iltimos telefon raqamingizni yuboring!");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactButton());
            } else {
                sendMessage.setText("Siz ro'yxatdan o'tgansiz (e'lonlari chiqariladi)");
            }
            sendMsg(sendMessage);
        }

    }

    private void handleCallback(Message message, String data) {
        String chatId =String.valueOf(message.getChatId());

        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);

        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        sendMsg(deleteMessage);


    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();
        String langCode = message.getFrom().getLanguageCode();
        userBotService.registerUser(chatId, phoneNumber, langCode);
    }

}
