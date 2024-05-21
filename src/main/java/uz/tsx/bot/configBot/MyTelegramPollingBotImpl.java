package uz.tsx.bot.configBot;


import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;
import uz.tsx.bot.constantsBot.StatusMessage;
import uz.tsx.bot.dto.CategoryDto;
import uz.tsx.bot.handler.MarkupHandler;
import uz.tsx.bot.serviceBot.impl.CategoryBotServiceImpl;
import uz.tsx.bot.serviceBot.impl.UserBotServiceImpl;
import uz.tsx.service.impl.AnnouncementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class MyTelegramPollingBotImpl extends TelegramLongPollingBot {

    String BOT_TOKEN ="6324307720:AAGBgwwyXDLfQrzA-7HaFbgfv7hBey9u3aU";
    String BOT_USERNAME ="@BotABUSAXIY_bot";

    Boolean password=false;
    private final UserBotServiceImpl userBotService;
    private final CategoryBotServiceImpl categoryBotService;
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
        MarkupHandler markupHandler = new MarkupHandler();

        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = "";
            if (Objects.nonNull(message.getText())) {
                text = message.getText();
            }
            if (text.equals(StatusMessage.START)) {
                if (userBotService.getUserById(chatId)) {
                    ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of("ADD"), 1);
                    myExecute(replyKeyboardMarkup, chatId);
                }
                ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of(StatusMessage.MENU, StatusMessage.SETTINGS, StatusMessage.MYADS), 2);
                String languageCode = message.getFrom().getLanguageCode();
                userBotService.createBot(chatId, languageCode);
                myExecute(replyKeyboardMarkup, StatusMessage.HI + message.getFrom().getFirstName(), chatId);
            } else if (text.equals("Mening e'lonlarim \uD83D\uDCDC")) {
                getUserPhoneNumber(message);
            } else if (text.equals("ADD")) {
                List<CategoryDto> dto = categoryBotService.getCategory();
                InlineKeyboardMarkup categoryInlineKeyboardMarkup = markupHandler.getCategoryInlineKeyboardMarkup1(dto, 2);
                myExecute(categoryInlineKeyboardMarkup, chatId);
            } else if (update.hasMessage() && update.getMessage().hasContact()) {
                Contact contact = update.getMessage().getContact();
                String phoneNumber = contact.getPhoneNumber();
                if (userBotService.add(chatId, phoneNumber, message.getFrom().getFirstName())) {
                    password = true;
                    myExecute(StatusMessage.PASSWORD, chatId);
                } else {
                    ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of("ADD"), 1);
                    myExecute(replyKeyboardMarkup, "✅", chatId);
                }
            } else if (password && update.hasMessage() && update.getMessage().getText() != null) {
                String passwordText = update.getMessage().getText();
                if (password) {
                    ReplyKeyboardMarkup replyKeyboardMarkup = markupHandler.replyKeyboardMarkup(List.of("ADD"), 1);
                    password = false;
                    userBotService.addPassword(chatId, passwordText);
                    myExecute(replyKeyboardMarkup, "✅", chatId);
                }
            }
        }
        else if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            Message message = update.getCallbackQuery().getMessage();
            String[] split = data.split(":");
            String category = split[1].substring(1, 9);
            Long chatIdQuery = update.getCallbackQuery().getMessage().getChatId();
            if (category.equals("category")){
                String id = split[2].substring(1, split[2].length() - 2);
                List<CategoryDto>categoryDtoList=categoryBotService.getCategoryListById(Long.valueOf(id));
                InlineKeyboardMarkup categoryInlineKeyboardMarkup = markupHandler.getCategoryInlineKeyboardMarkup1(categoryDtoList, 2);
                myExecute(categoryInlineKeyboardMarkup, chatIdQuery,message.getMessageId());

            }
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
    private void myExecute(InlineKeyboardMarkup i, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText("CATEGORY");
        sendMessage.setReplyMarkup(i);
        execute(sendMessage);
    }
    private void myExecute(ReplyKeyboardMarkup r, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setReplyMarkup(r);
        execute(sendMessage);
    }
    private void myExecute(InlineKeyboardMarkup i, Long chatId,Integer massage) throws TelegramApiException {
        EditMessageText sendMessage=new EditMessageText();
//        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setMessageId(massage);
        sendMessage.setText("CATEGORY");
        sendMessage.setReplyMarkup(i);
        execute(sendMessage);
    }
    private void myExecute( String text, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        execute(sendMessage);
    }



}
