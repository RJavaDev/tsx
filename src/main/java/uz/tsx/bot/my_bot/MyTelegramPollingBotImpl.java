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

import java.io.File;
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
            System.out.println(e.getMessage());
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
            if (userBotService.isUserNotExistByPhoneNumberAndChatId(chatId)) {
                userBotService.setUserState(chatId, StateEnum.MY_ANNS);
                sendMessage.setText("Iltimos telefon raqamingizni yuboring!");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactButton());
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.LOGINED);
                sendMessage.setText("Sahifangizga xush kelibsiz \uD83C\uDF1F");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
                sendMsg(sendMessage);
                getUserAnnouncements(chatId, 0);
            }
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) && Objects.isNull(message.getContact()) && !text.equals(BotConstants.BACK_BUTTON)) {
            sendMessage.setText("Telefon raqam yuborish uchun pastdagi '<b>Share contact</b>' tugmasini bosing!");
            sendMessage.setParseMode("html");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getContactButton());
            sendMsg(sendMessage);
        }
        else if(text.equals("/delete")) {
            // shaxsiy method test uchun
            userBotRepository.deleteUserByChatId(chatId);
            userRepository.deleteUserByPhoneNumber("+998997558142");
            sendMessage.setText("deleted");
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_NEW_PASS)) {
            userBotService.registerUser(chatId, BotConstants.USER_PHONE_NUMBER.get(chatId), text, message.getFrom().getFirstName());
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText("Siz muvaffaqiyatli ro'yxatdan o'tdingiz ✅ \n\nSahifangizga xush kelibsiz \\uD83C\\uDF1F");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.LOGINED) && !text.equals(BotConstants.MAIN_MENU) && !text.equals(BotConstants.MYANNS)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_TITLE);
            sendMessage.setText("E'lon uchun nom kiriting");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getBackButton());
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_TITLE) && !text.equals(BotConstants.BACK_BUTTON)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);
            announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle(text);

            userBotService.getUserByChatId(chatId).ifPresent(userByChatId -> {
                announcementEntity.setUserEntity(userByChatId.getUserEntity());
                announcementEntity.setUserEntityId(userByChatId.getUserEntity().getId());
            });

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

        else if ((userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) || userBotService.getUserState(chatId).equals(StateEnum.ENTER_LOGIN_PASS)) && text.equals(BotConstants.BACK_BUTTON)) {
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
            getUserAnnouncements(chatId, 0);
        }

    }

    private void handleCallback(Message message, String data) {
        String chatId = String.valueOf(message.getChatId());
        Integer messageId = message.getMessageId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String[] split = data.split("-");
        String key = split[0];

        if(key.equals("category") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CATEGORY)) {
            deletePreviousMessage(chatId, messageId);
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
            sendMsg(sendMessage);
        }
        else if(key.equals("currency") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CURRENCY)) {
            deletePreviousMessage(chatId, messageId);
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
            String code = split[1];

            currencyService.getCurrencyByCode(code).ifPresent(currency -> {
                announcementPriceEntity = new AnnouncementPriceEntity();
                announcementPriceEntity.setCurrencyId(currency.getId());
                announcementPriceEntity.setCurrency(currency);
            });

            sendMessage.setText("Narx kiriting");
            sendMsg(sendMessage);
        }
        else if(key.equals("region") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_REGION)) {
            deletePreviousMessage(chatId, messageId);
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
                sendMessage.setText("Region tanlandi✅\n\nEndi e'lon uchun tavsif kiriting");
            } else {
                sendMessage.setText("Region ...");
                sendMessage.setReplyMarkup(InlineKeyboardUtil.regionButtons(childRegionsByParentId));
            }
            sendMsg(sendMessage);
        }
        else if(key.equals("finish") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_IMAGE)) {
            deletePreviousMessage(chatId, messageId);
            userBotService.setUserState(chatId, StateEnum.FINISH);
            finish(sendMessage, chatId);
        }
        else if (key.equals("yes") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            deletePreviousMessage(chatId, messageId - 1);
            deletePreviousMessage(chatId, messageId);
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText("Bir oz kuting ⏳");
            sendMsg(sendMessage);

            List<AttachEntity> attachEntityList = fileService.savePhotos(photoSizeList, documentList);
            if(!attachEntityList.isEmpty()) {
                AnnouncementContactEntity savedAnnouncementContactEntity = announcementContactService.addNewAnnounceContact(announcementContactEntity);
                announcementEntity.setContactInfo(savedAnnouncementContactEntity);
                announcementEntity.setContactInfoId(savedAnnouncementContactEntity.getId());

                AnnouncementPriceEntity savedAnnouncementPriceEntity = announcementPriceService.addNewAnnouncementPrice(announcementPriceEntity);
                announcementEntity.setPriceTag(savedAnnouncementPriceEntity);
                announcementEntity.setPriceTagId(savedAnnouncementPriceEntity.getId());

                announcementEntity.setAttachPhotos(attachEntityList);

                announcementEntity.setIsActive(true);

                announcementService.createNewAnnouncement(announcementEntity);

                deletePreviousMessage(chatId, message.getMessageId() + 1);

                sendMessage.setText("E'lon muvaffaqiyatli yaratildi ✅");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            } else {
                sendMessage.setText("E'lon yaratilmadi ❌\n\n Iltimos yana urinib ko'ring.");
            }
            sendMsg(sendMessage);

            photoSizeList.clear();
            documentList.clear();
        }
        else if (key.equals("not") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            deletePreviousMessage(chatId, messageId - 1);
            deletePreviousMessage(chatId, messageId);
            userBotService.setUserState(chatId, StateEnum.LOGINED);

            photoSizeList.clear();
            documentList.clear();

            sendMessage.setText("Profil sahifasi \uD83D\uDC64");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
            sendMsg(sendMessage);
            getUserAnnouncements(chatId, 0);
        }
        else if(key.equals("page")) {
            deletePreviousMessage(chatId, message.getMessageId());
            deletePreviousMessage(chatId, message.getMessageId() - 1);
            int page = Integer.parseInt(split[1]);
            getUserAnnouncements(chatId, page);
        }

    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        BotConstants.USER_PHONE_NUMBER.put(chatId, phoneNumber);

        sendMessage.setReplyMarkup(ReplyKeyboardUtil.getBackButton());

        if(userBotService.isUserExistByPhoneNumber(phoneNumber)) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);

            userBotService.mergeUserAccounts(phoneNumber, chatId);

            sendMessage.setText("Sahifangizga xush kelibsiz \uD83C\uDF1F");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getUserProfileButton());
        } else {
            userBotService.setUserState(chatId, StateEnum.ENTERED_NEW_PASS);
            sendMessage.setText("Yangi parolni kiriting");
        }
        sendMsg(sendMessage);
    }

    @SneakyThrows
    private void handleImage(Message message) {
        String chatId =String.valueOf(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_IMAGE)) {

            int attachCount = photoSizeList.size() + documentList.size();

            if(attachCount < 7) {
                if(Objects.nonNull(message.getPhoto())) {
                    PhotoSize photoSize1 = message.getPhoto().get(0);
                    PhotoSize photoSize2 = message.getPhoto().get(message.getPhoto().size() - 1);
                    photoSizeList.add(List.of(photoSize1, photoSize2));
                    sendMessage.setText(attachCount + 1 + "/8 | Rasm yuklandi ✅ \n\nYana rasm yuklang yoki 'Tugatish' tugmasini bosing");
                    sendMessage.setReplyMarkup(InlineKeyboardUtil.finishButton());
                }
                else if(Objects.nonNull(message.getDocument())) {
                    documentList.add(message.getDocument());
                }
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.FINISH);
                sendMessage.setText("8 ta rasm yuklandi ✅");
                finish(sendMessage, chatId);
            }

        }

    }

    private void finish(SendMessage sendMessage, String chatId) {
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
                        "E'loningiz \n\nTitle: " + announcementEntity.getTitle() +
                        "\nDescription: "+ announcementEntity.getDescription() + "\n" +
                        "Kategoriya: " + announcementEntity.getCategory().getNameUz() + "\n" +
                        "Region: " + announcementContactEntity.getRegion().getNameUz() + "\n" +
                        "Narxi: " + announcementPriceEntity.getPrice() + "\n\n" +
                        "E'lonni tasdiqlaysizmi?"
        );
        sendMessage.setReplyMarkup(InlineKeyboardUtil.yesOrNotButtons());
        sendMsg(sendMessage);
    }

    private void getUserAnnouncements(String  chatId, int page) {

        userBotRepository.getUserByChatId(chatId).ifPresent(userBotEntity -> {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            Long userEntityId = userBotEntity.getUserEntity().getId();
            List<AnnouncementEntity> announcementListByUserEntity = announcementService.getAnnouncementListByUserEntity(userEntityId);

            if(!announcementListByUserEntity.isEmpty()) {
                AnnouncementEntity announcementEntity1 = announcementListByUserEntity.get(page);
                AttachEntity attachEntity = announcementEntity1.getAttachPhotos().get(1);

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId);
                File file = new File("src/main/resources/images/" + attachEntity.getPath() + attachEntity.getId() + attachEntity.getContentType());
                sendPhoto.setPhoto(new InputFile(file));
                sendMsg(sendPhoto);

                sendMessage.setParseMode("html");
                if(announcementEntity1.getIsActive()) {
                    sendMessage.setText(
                                    "<strong>Aktiv ✅</strong>" +
                                    "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                    "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() +
                                    "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                    "\n\n" + announcementEntity1.getCreatedDate()
                    );
                } else {
                    sendMessage.setText(
                                    "<strong>Aktiv emas ❌</strong>" +
                                    "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                    "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() +
                                    "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                    "\n\n" + announcementEntity1.getCreatedDate()
                    );
                }

                sendMessage.setReplyMarkup(InlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), announcementListByUserEntity.size(), announcementEntity1.getIsActive()));

            }
            sendMsg(sendMessage);
        });

    }

//    private void getUserAnnouncements(String chatId, int page, Integer messageId) {
//        Long userId = userBotRepository.getUserByChatId(chatId).get().getUserEntity().getId();
//        AnnouncementEntity announcementEntity1 = announcementService.getAnnouncementListByUserEntity(userId).get(page);
//        AttachEntity attachEntity = announcementEntity1.getAttachPhotos().get(1);
//
//        String filePath = "src/main/resources/images/" + attachEntity.getPath() + attachEntity.getId() + attachEntity.getContentType();
//        File file = new File(filePath);
//
//        // Edit message media
//        EditMessageMedia editMessageMedia = new EditMessageMedia();
//        editMessageMedia.setChatId(chatId);
//        editMessageMedia.setMessageId(messageId);
//        InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
//        inputMediaPhoto.setMedia(file.getPath());
//        inputMediaPhoto.setCaption(announcementEntity1.getTitle());
//        editMessageMedia.setMedia(inputMediaPhoto);
//        sendMsg(editMessageMedia);
//
//        EditMessageText editMessageText = new EditMessageText();
//        editMessageText.setChatId(chatId);
//        editMessageText.setMessageId(messageId);
//        editMessageText.setParseMode("html");
//        editMessageText.setText(
//                "<i>" + announcementEntity1.getTitle() + "</i>" +
//                        "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() +
//                        "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
//                        "\n\n" + announcementEntity1.getCreatedDate()
//        );
//        editMessageText.setReplyMarkup(InlineKeyboardUtil.editAndDeleteButton(announcementService.getAnnouncementListByUserEntity(userBotRepository.getUserByChatId(chatId).get().getUserEntity().getId()).get(0).getId(), announcementService.getAnnouncementListByUserEntity(userId).size()));
//        sendMsg(editMessageText);
//    }

    private void deletePreviousMessage(String chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        sendMsg(deleteMessage);
    }

}
