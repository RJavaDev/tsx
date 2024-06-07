package uz.tsx.bot.my_bot;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.container.ComponentContainer;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.bot.service.UserBotService;
import uz.tsx.bot.service.FileService;
import uz.tsx.bot.util.InlineKeyboardUtil;
import uz.tsx.bot.util.ReplyKeyboardUtil;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.CurrencyEntity;
import uz.tsx.entity.RegionEntity;
import uz.tsx.entity.announcement.AnnouncementContactEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MyTelegramPollingBotImpl extends TelegramLongPollingBot {

    //repositories
    private final UserBotRepository userBotRepository;
    private final UserRepository userRepository;

    //services
    private final UserBotService userBotService;
    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CurrencyService currencyService;
    private final AnnouncementContactService announcementContactService;
    private final AnnouncementPriceService announcementPriceService;
    private final AnnouncementService announcementService;
    private final FileService fileService;

    //entities
    private AnnouncementEntity announcementEntity;
    private AnnouncementContactEntity announcementContactEntity;
    private AnnouncementPriceEntity announcementPriceEntity;
    private final List<List<PhotoSize>> photoSizeList = new ArrayList<>();
    private final List<Document> documentList = new ArrayList<>();

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
            } else if (message.hasPhoto() || message.hasDocument()) {
                handleImage(message);
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
            } else if (obj instanceof SendMediaGroup) {
                execute((SendMediaGroup)obj);
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
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.START) && text.equals(BotConstants.MYANNS)) {
            userBotService.setUserState(chatId, StateEnum.MY_ANNS);
            sendMessage.setText("Iltimos telefon raqamingizni yuboring!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactButton());
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) && Objects.isNull(message.getContact()) && !text.equals(BotConstants.BACK_BUTTON)) {
            sendMessage.setText("Telefon raqam yuborish uchun pastdagi '<b>Share contact</b>' tugmasini bosing!");
            sendMessage.setParseMode("html");
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
        }
//        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTER_LOGIN_PASS) && !text.equals(BotConstants.BACK_BUTTON)) {
//            if(userBotService.login(chatId, text)) {
//                userBotService.setUserState(chatId, StateEnum.LOGINED);
//                sendMessage.setText("Sahifangizga xush kelibsiz \uD83C\uDF1F");
//                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
//            } else {
//                userBotService.setUserState(chatId, StateEnum.ENTER_LOGIN_PASS);
//                sendMessage.setText("Parol noto'g'ri. Iltimos yana urinib ko'ring!");
//            }
//            sendMsg(sendMessage);
//        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.LOGINED) && !text.equals(BotConstants.MAIN_MENU) && !text.equals(BotConstants.MYANNS)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_TITLE);
            sendMessage.setText("E'lon uchun title kiriting");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getBackButton());
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_TITLE) && !text.equals(BotConstants.BACK_BUTTON)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);
            announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle(text);

            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));

            List<CategoryEntity> categoryEntityList = categoryService.getAllTree();

            sendMessage.setText("E'lon uchun kategoriya tanlang");
            sendMessage.setReplyMarkup(InlineKeyboardUtil.categoryButtons(categoryEntityList));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_DESCRIPTION)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CURRENCY);
            announcementEntity.setDescription(text);
            sendMessage.setText("E'lonning narxini kiritish uchun valyuta tanlang");

            List<CurrencyEntity> allCurrencies = currencyService.getAllCurrencies();
            sendMessage.setReplyMarkup(InlineKeyboardUtil.currencyButtons(allCurrencies));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_PRICE)) {
            if(text.matches("^\\d+$")) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_IMAGE);
                announcementPriceEntity.setPrice(BigDecimal.valueOf(Long.parseLong(text)));
                sendMessage.setText("E'lon uchun rasm yuboring \n<strong> 8 tagacha rasm yubrishingiz mumkin </strong>");
                sendMessage.setParseMode("html");
            } else {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
                sendMessage.setText("Narx kiritishda faqat sonlardan foydalaning!");
            }
            sendMsg(sendMessage);
        }



        else if ((
                userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) ||
                userBotService.getUserState(chatId).equals(StateEnum.ENTER_LOGIN_PASS)
            ) && text.equals(BotConstants.BACK_BUTTON)) {
            sendMessage.setText("Bosh sahifa");
            userBotService.setUserState(chatId, StateEnum.START);
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMainMenuButton());
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.LOGINED) && text.equals(BotConstants.MAIN_MENU)) {
            sendMessage.setText("Bosh sahifa");
            userBotService.setUserState(chatId, StateEnum.START);
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMainMenuButton());
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_TITLE) && text.equals(BotConstants.BACK_BUTTON)) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText("Profil sahifasi \uD83D\uDC64");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            sendMsg(sendMessage);
        }

    }

    private void handleCallback(Message message, String data) {
        String chatId = String.valueOf(message.getChatId());
        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        sendMsg(deleteMessage);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String[] split = data.split("-");
        String key = split[0];

        if(key.equals("category") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CATEGORY)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);

            Long categoryId = Long.valueOf(data.split("-")[1]);

            List<CategoryEntity> childCategoriesByParentId = categoryService.getChildCategoriesByParentId(categoryId);

            if(childCategoriesByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_REGION);

                announcementEntity.setCategoryId(categoryId);
                announcementEntity.setCategory(categoryService.getById(categoryId));

                sendMessage.setText("Kategoriya tanlandi✅\n\nEndi region tanlang");
                sendMsg(sendMessage);
                sendMessage.setText("Regionlar");
                List<RegionEntity> regionEntityList = regionService.getAllTree();
                sendMessage.setReplyMarkup(InlineKeyboardUtil.regionButtons(regionEntityList));
            } else {
                sendMessage.setText("Kategoriya ...");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.categoryButtons(childCategoriesByParentId));
            }
        }
        else if(key.equals("currency") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CURRENCY)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
            String code = split[1];

            currencyService.getCurrencyByCode(code).ifPresent(currency -> {
                announcementPriceEntity = new AnnouncementPriceEntity();
                announcementPriceEntity.setCurrencyId(currency.getId());
                announcementPriceEntity.setCurrency(currency);
            });

            sendMessage.setText("Narx kiriting");
        }
        else if(key.equals("region") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_REGION)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_REGION);

            Long regionId = Long.valueOf(data.split("-")[1]);

            List<RegionEntity> childRegionsByParentId = regionService.getChildRegionsByParentId(regionId);
            if(childRegionsByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_DESCRIPTION);

                announcementContactEntity = new AnnouncementContactEntity();
                announcementContactEntity.setRegionId(regionId);
                announcementContactEntity.setRegion(regionService.getById(regionId));
                userBotService.getUserByChatId(chatId).ifPresent(user ->
                    announcementContactEntity.setPhone(user.getUserEntity().getEmailOrPhone())
                );
                sendMessage.setText("Region tanlandi✅\n\nEndi e'lon uchun description kiriting");
            } else {
                sendMessage.setText("Region ...");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.regionButtons(childRegionsByParentId));
            }
        }
        else if(key.equals("finish") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_IMAGE)) {
            userBotService.setUserState(chatId, StateEnum.FINISH);
            finish(sendMessage, chatId);
        }
        else if (key.equals("yes") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            List<AttachEntity> attachEntityList = fileService.savePhotos(photoSizeList, documentList);
            if(!attachEntityList.isEmpty()) {
                AnnouncementContactEntity savedAnnouncementContactEntity = announcementContactService.addNewAnnounceContact(announcementContactEntity);
                announcementEntity.setContactInfo(savedAnnouncementContactEntity);
                announcementEntity.setContactInfoId(savedAnnouncementContactEntity.getId());

                AnnouncementPriceEntity savedAnnouncementPriceEntity = announcementPriceService.addNewAnnouncementPrice(announcementPriceEntity);
                announcementEntity.setPriceTag(savedAnnouncementPriceEntity);
                announcementEntity.setPriceTagId(savedAnnouncementPriceEntity.getId());

                announcementEntity.setAttachPhotos(attachEntityList);

                announcementService.createNewAnnouncement(announcementEntity);

                sendMessage.setText("E'lon muvaffaqiyatli yaratildi ✅");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            } else {
                sendMessage.setText("E'lon yaratilmadi ❌\n\n Iltimos yana urinib ko'ring.");
            }
        }
        else if (key.equals("not") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);

            photoSizeList.clear();
            documentList.clear();

            sendMessage.setText("Profil sahifasi \uD83D\uDC64");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
        }

        sendMsg(sendMessage);
    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        BotConstants.USER_PHONE_NUMBER.put(chatId, phoneNumber);

        sendMessage.setReplyMarkup(ReplyKeyboardUtil.getBackButton());

        if(userBotService.isUserNotExistByPhoneNumberAndChatId(phoneNumber, chatId)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_NEW_PASS);
            sendMessage.setText("Yangi parolni kiriting");
            sendMsg(sendMessage);
        } else {
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText("Sahifangizga xush kelibsiz \uD83C\uDF1F");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            sendMsg(sendMessage);
        }

    }

    @SneakyThrows
    private void handleImage(Message message) {
        String chatId =String.valueOf(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_IMAGE)) {
            int attachCount = photoSizeList.size() + documentList.size();

            if(attachCount < 9) {
                if(Objects.nonNull(message.getPhoto())) {
                    PhotoSize photoSize1 = message.getPhoto().get(0);
                    PhotoSize photoSize2 = message.getPhoto().get(message.getPhoto().size() - 1);
                    photoSizeList.add(List.of(photoSize1, photoSize2));
                    sendMessage.setText(attachCount + 1 + "/8 | Rasm yuklandi ✅ \n\n Yana rasm yuklang yoki 'Tugatish' tugmasini bosing");
                    sendMessage.setReplyMarkup(InlineKeyboardUtil.finishButton());
                }
                else if(Objects.nonNull(message.getDocument())) {
                    documentList.add(message.getDocument());
                }
            } else {
                sendMessage.setText("8 ta rasm yuklandi ✅");
                finish(sendMessage, chatId);
            }

            sendMsg(sendMessage);
        }

    }

    private void finish(SendMessage sendMessage, String chatId) {
        sendMessage.setText("E'loningiz: ");
        sendMsg(sendMessage);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);

        photoSizeList.forEach(photoSize -> {
            sendPhoto.setPhoto(new InputFile(photoSize.get(1).getFileId()));  // 1 sifati zo'ri; 0 sifati pasti
            sendMsg(sendPhoto);
        });

        documentList.forEach(document -> {
            sendPhoto.setPhoto(new InputFile(document.getFileId()));
            sendMsg(sendPhoto);
        });

        sendMessage.setText(
                "Title: " + announcementEntity.getTitle() +
                        "\nDescription: "+ announcementEntity.getDescription() + "\n" +
                        "Kategoriya: " + announcementEntity.getCategory().getNameUz() + "\n" +
                        "Region: " + announcementContactEntity.getRegion().getNameUz() + "\n" +
                        "Narxi: " + announcementPriceEntity.getPrice() + "\n\n" +
                        "E'lonni tasdiqlaysizmi?"
        );
        sendMessage.setReplyMarkup(InlineKeyboardUtil.yesOrNotButtons());
    }

}
