package uz.tsx.bot.my_bot;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
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
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.bot.service.UserBotService;
import uz.tsx.bot.util.ReplyKeyboardUtil;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.impl.AnnouncementServiceImpl;

@Service
@RequiredArgsConstructor
public class MyTelegramPollingBotImpl extends TelegramLongPollingBot {

    private final UserBotService userBotService;
    private final AnnouncementServiceImpl announcementService;

    private final UserBotRepository userBotRepository;
    private final UserRepository userRepository;

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
        String languageCode = user.getLanguageCode();

        if(text.equals("/start")){
            if(userBotService.isUserNotExistByChatId(chatId)) {
                userBotService.createBotUser(chatId, languageCode);
            }
            userBotService.setUserState(chatId, StateEnum.START);

            sendMessage.setText("Assalomu alaykum, "+user.getFirstName()+"\n" + "botga xush kelibsiz.\n");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMainMenuButton());
            sendMsg(sendMessage);
        } else if (userBotService.getUserState(chatId).equals(StateEnum.START) && text.equals(BotConstants.MYANNS)) {
            userBotService.setUserState(chatId, StateEnum.MY_ANNS);
            sendMessage.setText("Iltimos telefon raqamingizni yuboring!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactButton());
            sendMsg(sendMessage);
        }
        else if(text.equals("/delete")) {
            // shaxsiy method test uchun
            BotConstants.USER_STATE.remove(chatId);
            userBotRepository.deleteUserByChatId(chatId);
            userRepository.deleteUserByPhoneNumber("+998997558142");
            sendMessage.setText("deleted");
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_NEW_PASS)) {
            userBotService.registerUser(chatId, BotConstants.USER_PHONE_NUMBER.get(chatId), text, message.getFrom().getFirstName());
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText("Siz muvaffaqiyatli ro'yxatdan o'tdingiz ✅");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            sendMsg(sendMessage);
        } else if (userBotService.getUserState(chatId).equals(StateEnum.ENTER_LOGIN_PASS)) {
            if(userBotService.login(chatId, text)) {
                userBotService.setUserState(chatId, StateEnum.LOGINED);
                sendMessage.setText("Sahifangizga xush kelibsiz \uD83C\uDF1F");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            } else {
                userBotService.setUserState(chatId, StateEnum.ENTER_LOGIN_PASS);
                sendMessage.setText("Parol noto'g'ri. Iltimos yana urinib ko'ring!");
            }
            sendMsg(sendMessage);
        } else if (userBotService.getUserState(chatId).equals(StateEnum.LOGINED)) {
            sendMessage.setText("E'lon uchun title kiriting");
            sendMsg(sendMessage);
        }

    }

    private void handleCallback(Message message, String data) {
        String chatId =String.valueOf(message.getChatId());
        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        sendMsg(deleteMessage);

    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ReplyKeyboardUtil.removeButton(sendMessage);

        BotConstants.USER_PHONE_NUMBER.put(chatId, phoneNumber);

        if(userBotService.isUserNotExistByPhoneNumber(phoneNumber)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_NEW_PASS);
            sendMessage.setText("Yangi parolni kiriting");
            sendMsg(sendMessage);
        } else {
            userBotService.setUserState(chatId, StateEnum.ENTER_LOGIN_PASS);
            sendMessage.setText("Parolni kiriting");
            sendMsg(sendMessage);
        }

    }

}
