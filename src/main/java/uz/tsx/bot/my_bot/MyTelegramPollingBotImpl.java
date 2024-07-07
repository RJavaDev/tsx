package uz.tsx.bot.my_bot;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.tsx.bot.constantsBot.BotConstants;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.bot.repository.UserBotRepository;
import uz.tsx.bot.service.BotService;
import uz.tsx.bot.utils.MessageUtils;
import uz.tsx.bot.service.UserBotService;
import uz.tsx.bot.service.FileService;
import uz.tsx.bot.utils.InlineKeyboardUtil;
import uz.tsx.bot.utils.ReplyKeyboardUtil;
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
import java.util.Collections;
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
    private final BotService botService;
    private final MessageUtils messageUtils;

    //components
    private final ReplyKeyboardUtil replyKeyboardUtil;
    private final InlineKeyboardUtil inlineKeyboardUtil;

    //entities
    private AnnouncementEntity announcementEntity;
    private AnnouncementContactEntity announcementContactEntity;
    private AnnouncementPriceEntity announcementPriceEntity;
    private final List<List<PhotoSize>> photoSizeList = new ArrayList<>();
    private final List<Document> documentList = new ArrayList<>();

    @Value("${tsx.project.bot.token}")
    private String BOT_TOKEN;
    @Value("${tsx.project.bot.username}")
    private String BOT_USERNAME;

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
            handleCallback(message, callbackQuery);
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
            else if (obj instanceof SendMediaGroup) {
                execute((SendMediaGroup)obj);
            }
            else if (obj instanceof EditMessageMedia) {
                execute((EditMessageMedia)obj);
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

        if(text.equals("/start")){
            if(userBotService.isUserNotExistByChatId(chatId)) {
                userBotService.createBotUser(chatId);
            }

            String userLang = userBotService.getUserLang(chatId);

            if (Objects.isNull(userLang)) {
                userBotService.setUserState(chatId, StateEnum.START);
                sendMessage.setText("Iltimos tilni tanlang!\n\nПожалуйста, выберите язык!\n\nPlease select a language!");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.langButtons());
            } else {
                userBotService.setUserState(chatId, StateEnum.HOME);
                String greetingMessage = messageUtils.getMessage("bot.hello", userLang) + " " + user.getFirstName() + ". " + messageUtils.getMessage("bot.welcome", userLang);
                sendMessage.setText(greetingMessage);
                sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(chatId));
            }
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.HOME) && text.equals(messageUtils.getMessage("bot.button.my_anns", userBotService.getUserLang(chatId)))) {
            if (userBotService.isUserNotExistByPhoneNumberAndChatId(chatId)) {
                userBotService.setUserState(chatId, StateEnum.MY_ANNS);
                sendMessage.setText(messageUtils.getMessage("bot.share_contact", userBotService.getUserLang(chatId)));
                sendMessage.setReplyMarkup(replyKeyboardUtil.contactButton(chatId));
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.LOGINED);
                sendMessage.setText(messageUtils.getMessage("bot.welcome_your_page", userBotService.getUserLang(chatId)));
                sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));
                sendMsg(sendMessage);
                showUserAnnouncements(chatId);
            }
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) && Objects.isNull(message.getContact()) && !text.equals(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)))) {
            sendMessage.setText(messageUtils.getMessage("bot.share_contact_error", userBotService.getUserLang(chatId)));
            sendMessage.setParseMode("html");
            sendMessage.setReplyMarkup(replyKeyboardUtil.contactButton(chatId));
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.HOME) && text.equals(messageUtils.getMessage("bot.button.settings", userBotService.getUserLang(chatId)))) {
            userBotService.setUserState(chatId, StateEnum.SETTINGS);
            sendMessage.setText(messageUtils.getMessage("bot.button.settings", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.settingsButtons(chatId));
            sendMsg(sendMessage);
        } else if (userBotService.getUserState(chatId).equals(StateEnum.SETTINGS) && text.equals(messageUtils.getMessage("bot.button.change_lang", userBotService.getUserLang(chatId)))) {
            userBotService.setUserState(chatId, StateEnum.CHANGE_LANG);
            sendMessage.setText("Iltimos tilni tanlang!\n\nПожалуйста, выберите язык!\n\nPlease select a language!");
            sendMessage.setReplyMarkup(inlineKeyboardUtil.langButtons());
            sendMsg(sendMessage);
        }
        else if(text.equals("/delete")) {
            // shaxsiy method test uchun
            userBotRepository.deleteUserByChatId(chatId);
            userRepository.deleteUserByPhoneNumber("+998997558142");
            sendMessage.setText("deleted");
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_NEW_PASS) && !text.equals(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)))) {
            userBotService.registerUser(chatId, BotConstants.USER_PHONE_NUMBER.get(chatId), text, message.getFrom().getFirstName());
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.success_login", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.LOGINED) && text.equals(messageUtils.getMessage("bot.button.create_new_ann", userBotService.getUserLang(chatId)))) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_TITLE);
            sendMessage.setText(messageUtils.getMessage("bot.enter_name_for_ann", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.backButton(chatId));
            sendMsg(sendMessage);
        }
        else if (userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_TITLE) && !text.equals(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)))) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);
            announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle(text);

            userBotService.getUserByChatId(chatId).ifPresent(userByChatId -> {
                announcementEntity.setUserEntity(userByChatId.getUserEntity());
                announcementEntity.setUserId(userByChatId.getUserEntity().getId());
            });

            List<CategoryEntity> categoryEntityList = categoryService.getAllTree();

            sendMessage.setText(messageUtils.getMessage("bot.select_category_for_ann", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
            sendMsg(sendMessage);

            sendMessage.setText(messageUtils.getMessage("bot.categories", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(inlineKeyboardUtil.categoryButtons(categoryEntityList, userBotService.getUserLang(chatId)));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_DESCRIPTION)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CURRENCY);
            announcementEntity.setDescription(text);
            sendMessage.setText(messageUtils.getMessage("bot.select_currency_for_ann", userBotService.getUserLang(chatId)));

            List<CurrencyEntity> allCurrencies = currencyService.getAllCurrencies();
            sendMessage.setReplyMarkup(inlineKeyboardUtil.currencyButtons(allCurrencies));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_PRICE)) {
            if(text.matches("^\\d+$")) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_IMAGE);
                announcementPriceEntity.setPrice(BigDecimal.valueOf(Long.parseLong(text)));
                sendMessage.setText(messageUtils.getMessage("bot.send_image_for_ann", userBotService.getUserLang(chatId)) + "\n" + messageUtils.getMessage("bot.send_only_eight_images", userBotService.getUserLang(chatId)));
                sendMessage.setParseMode("html");
            } else {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
                sendMessage.setText(messageUtils.getLocalizedMessage("bot.enter_price_for_ann_error", userBotService.getUserLang(chatId)));
            }
            sendMsg(sendMessage);
        }

        else if (
                (userBotService.getUserState(chatId).equals(StateEnum.MY_ANNS) ||
                    userBotService.getUserState(chatId).equals(StateEnum.ENTERED_NEW_PASS) ||
                        userBotService.getUserState(chatId).equals(StateEnum.SETTINGS)
                ) && text.equals(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)))) {
            sendMessage.setText(messageUtils.getMessage("bot.home_page", userBotService.getUserLang(chatId)));
            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(chatId));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.LOGINED) && text.equals(messageUtils.getMessage("bot.button.home_page", userBotService.getUserLang(chatId)))) {
            sendMessage.setText(messageUtils.getMessage("bot.home_page", userBotService.getUserLang(chatId)));
            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(chatId));
            sendMsg(sendMessage);
        }
        else if(userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_TITLE) && text.equals(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)))) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.profile_page", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
        }

    }

    private void handleCallback(Message message, CallbackQuery callbackQuery) {
        String chatId = String.valueOf(message.getChatId());
        Integer messageId = message.getMessageId();
        String data = callbackQuery.getData();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String[] split = data.split("-");
        String key = split[0];

        User user = callbackQuery.getFrom();

        if (key.equals("lang") && userBotService.getUserState(chatId).equals(StateEnum.START)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            String langCode = String.valueOf(split[1]);
            userBotService.setUserLang(chatId, langCode);

            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setText(messageUtils.getMessage("bot.hello", userBotService.getUserLang(chatId)) + " " + user.getFirstName() + ". " + messageUtils.getMessage("bot.welcome", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(chatId));
            sendMsg(sendMessage);
        }
        else if (key.equals("lang") && userBotService.getUserState(chatId).equals(StateEnum.CHANGE_LANG)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            String langCode = String.valueOf(split[1]);
            userBotService.setUserLang(chatId, langCode);

            userBotService.setUserState(chatId, StateEnum.SETTINGS);
            sendMessage.setText(messageUtils.getMessage("bot.lang_changed_success", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.settingsButtons(chatId));
            sendMsg(sendMessage);
        }
        else if (key.equals("category") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CATEGORY)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);

            Long categoryId = Long.valueOf(data.split("-")[1]);

            List<CategoryEntity> childCategoriesByParentId = categoryService.getChildCategoriesByParentId(categoryId);

            if(childCategoriesByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_REGION);

                announcementEntity.setCategoryId(categoryId);
                announcementEntity.setCategory(categoryService.getById(categoryId));

                sendMessage.setText(messageUtils.getMessage("bot.category_selected_for_ann", userBotService.getUserLang(chatId))+"\n\n" + messageUtils.getMessage("bot.select_region_for_ann", userBotService.getUserLang(chatId)));
                sendMsg(sendMessage);
                sendMessage.setText(messageUtils.getMessage("bot.regions", userBotService.getUserLang(chatId)));
                List<RegionEntity> regionEntityList = regionService.getAllTree();
                sendMessage.setReplyMarkup(inlineKeyboardUtil.regionButtons(regionEntityList, userBotService.getUserLang(chatId)));
            } else {
                sendMessage.setText(messageUtils.getMessage("bot.categories", userBotService.getUserLang(chatId)) + " ...");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.categoryButtons(childCategoriesByParentId, userBotService.getUserLang(chatId)));
            }
            sendMsg(sendMessage);
        }
        else if (key.equals("currency") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_CURRENCY)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
            String code = split[1];

            currencyService.getCurrencyByCode(code).ifPresent(currency -> {
                announcementPriceEntity = new AnnouncementPriceEntity();
                announcementPriceEntity.setCurrencyId(currency.getId());
                announcementPriceEntity.setCurrency(currency);
            });

            sendMessage.setText(messageUtils.getMessage("bot.enter_price_for_ann", userBotService.getUserLang(chatId)));
            sendMsg(sendMessage);
        }
        else if (key.equals("region") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_REGION)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_REGION);

            Long regionId = Long.valueOf(data.split("-")[1]);

            List<RegionEntity> childRegionsByParentId = regionService.getChildRegionsByParentId(regionId);
            if(childRegionsByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_DESCRIPTION);

                announcementContactEntity = new AnnouncementContactEntity();
                announcementContactEntity.setRegionId(regionId);
                announcementContactEntity.setRegion(regionService.getById(regionId));
                userBotService.getUserByChatId(chatId).ifPresent(user1 ->
                    announcementContactEntity.setPhone(user1.getUserEntity().getEmailOrPhone())
                );
                sendMessage.setText(messageUtils.getMessage("bot.region_selected_for_ann", userBotService.getUserLang(chatId)) + "\n\n" + messageUtils.getMessage("bot.enter_desc_for_ann", userBotService.getUserLang(chatId)));
            } else {
                sendMessage.setText(messageUtils.getMessage("bot.regions", userBotService.getUserLang(chatId)) + " ...");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.regionButtons(childRegionsByParentId, userBotService.getUserLang(chatId)));
            }
            sendMsg(sendMessage);
        }
        else if (key.equals("finish") && userBotService.getUserState(chatId).equals(StateEnum.ENTERED_ANN_IMAGE)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.FINISH);
            finish(sendMessage, chatId);
        }
        else if (key.equals("yes") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.waiting", userBotService.getUserLang(chatId)));
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

                sendMsg(botService.deletePreviousMessage(chatId, messageId + 1));

                sendMessage.setText(messageUtils.getMessage("bot.ann_created_success", userBotService.getUserLang(chatId)));
                sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));

            } else {
                sendMessage.setText(messageUtils.getMessage("bot.ann_created_error", userBotService.getUserLang(chatId)) + "\n\n" + messageUtils.getMessage("bot.try_again", userBotService.getUserLang(chatId)));
            }
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
            photoSizeList.clear();
            documentList.clear();
        }
        else if (key.equals("not") && userBotService.getUserState(chatId).equals(StateEnum.FINISH)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.LOGINED);

            photoSizeList.clear();
            documentList.clear();

            sendMessage.setText(messageUtils.getMessage("bot.profile_page", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
        }
        else if (key.equals("page") && userBotService.getUserState(chatId).equals(StateEnum.LOGINED)) {
            int page = Integer.parseInt(split[1]);
            showUserAnnouncements(chatId, page, messageId);
        }
        else if (key.equals("inActive") && userBotService.getUserState(chatId).equals(StateEnum.LOGINED)) {
            Long annId = Long.valueOf(split[1]);
            announcementService.changeActiveStatus(annId, !announcementService.getById(annId).getIsActive());
            showUserAnnouncements(chatId, BotConstants.USER_SELECTED_PAGE.get(chatId), messageId);
        }
        else if (key.equals("delete") && userBotService.getUserState(chatId).equals(StateEnum.LOGINED)) {
            Long annId = Long.valueOf(split[1]);
            announcementService.deleteAnnouncement(annId);

            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));

            sendMessage.setText(messageUtils.getMessage("bot.ann_deleted_success", userBotService.getUserLang(chatId)));
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
        }

    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        BotConstants.USER_PHONE_NUMBER.put(chatId, phoneNumber);

        sendMessage.setReplyMarkup(replyKeyboardUtil.backButton(chatId));

        if(userBotService.isUserExistByPhoneNumber(phoneNumber)) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);

            userBotService.mergeUserAccounts(phoneNumber, chatId);

            sendMessage.setText(messageUtils.getMessage("bot.welcome_your_page", userBotService.getUserLang(chatId)));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(chatId));
        } else {
            userBotService.setUserState(chatId, StateEnum.ENTERED_NEW_PASS);
            sendMessage.setText(messageUtils.getMessage("bot.enter_new_pass", userBotService.getUserLang(chatId)));
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
                    PhotoSize photoSize = message.getPhoto().get(message.getPhoto().size() - 1); // eng sifatlisi
                    photoSizeList.add(Collections.singletonList(photoSize));
                    sendMessage.setText(attachCount + 1 + "/" + messageUtils.getMessage("bot.eight_images_uploaded", userBotService.getUserLang(chatId)) + "\n\n" + messageUtils.getMessage("bot.upload_again_image_or_stop", userBotService.getUserLang(chatId)));
                    sendMessage.setReplyMarkup(inlineKeyboardUtil.finishButton(chatId));
                }
                else if(Objects.nonNull(message.getDocument())) {
                    documentList.add(message.getDocument());
                }
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.FINISH);
                sendMessage.setText(messageUtils.getMessage("bot.eight_images_saved", userBotService.getUserLang(chatId)));
                finish(sendMessage, chatId);
            }

        }

    }

    private void finish(SendMessage sendMessage, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);

        photoSizeList.forEach(photoSize -> {
            sendPhoto.setPhoto(new InputFile(photoSize.get(0).getFileId()));  // 1 sifati zo'ri; 0 sifati pasti
            sendMsg(sendPhoto);
        });

        documentList.forEach(document -> {
            sendPhoto.setPhoto(new InputFile(document.getFileId()));
            sendMsg(sendPhoto);
        });

        sendMessage.setText(
                messageUtils.getMessage("bot.your_ann", userBotService.getUserLang(chatId)) + "\n\n" +
                messageUtils.getMessage("bot.ann_name", userBotService.getUserLang(chatId)) + announcementEntity.getTitle() + "\n" +
                messageUtils.getMessage("bot.ann_desc", userBotService.getUserLang(chatId))+ announcementEntity.getDescription() + "\n" +
                messageUtils.getMessage("bot.ann_category", userBotService.getUserLang(chatId)) + announcementEntity.getCategory().getNameUz() + "\n" +
                messageUtils.getMessage("bot.ann_region", userBotService.getUserLang(chatId)) + announcementContactEntity.getRegion().getNameUz() + "\n" +
                messageUtils.getMessage("bot.ann_price", userBotService.getUserLang(chatId)) + announcementPriceEntity.getPrice() + " " + announcementPriceEntity.getCurrency().getName() + "\n" +
                messageUtils.getMessage("bot.ann_is_save", userBotService.getUserLang(chatId))
        );
        sendMessage.setReplyMarkup(inlineKeyboardUtil.yesOrNotButtons(chatId));
        sendMsg(sendMessage);
    }

    private void showUserAnnouncements(String  chatId) {
        userBotRepository.getUserByChatId(chatId).ifPresent(userBotEntity -> {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            userBotService.getUserAnnouncement(chatId, 0).ifPresent(announcementEntity1 -> {
                sendMsg(botService.sendPhoto(chatId, announcementEntity1.getAttachPhotos().get(0)));

                sendMessage.setParseMode("html");

                BotConstants.USER_SELECTED_PAGE.put(chatId, 0);

                String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBotService.getUserLang(chatId)) :
                        messageUtils.getMessage("bot.inActive", userBotService.getUserLang(chatId));

                sendMessage.setText(
                        "<strong>№" + 1 + "</strong>" +
                                "\n\n<strong>" + status + "</strong>" +
                                "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                                "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                "\n\n" + announcementEntity1.getCreatedDate()
                );

                sendMessage.setReplyMarkup(inlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), userBotService.getUserAnnouncementCount(chatId), announcementEntity1.getIsActive(), chatId));

                sendMsg(sendMessage);
            });

        });

    }

    private void showUserAnnouncements(String chatId, int page, Integer messageId) {

        userBotRepository.getUserByChatId(chatId).ifPresent(userBotEntity -> {
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(messageId);

            userBotService.getUserAnnouncement(chatId, page).ifPresent(announcementEntity1 -> {
                sendMsg(botService.editPhotoMessage(chatId, messageId, announcementEntity1));

                editMessageText.setParseMode("html");

                BotConstants.USER_SELECTED_PAGE.put(chatId, page);

                String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBotService.getUserLang(chatId)) :
                        messageUtils.getMessage("bot.inActive", userBotService.getUserLang(chatId));

                editMessageText.setText(
                        "<strong>№" + (page + 1) + "</strong>" +
                                "\n\n<strong>" + status + "</strong>" +
                                "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                                "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                "\n\n" + announcementEntity1.getCreatedDate()
                );

                editMessageText.setReplyMarkup(inlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), userBotService.getUserAnnouncementCount(chatId), announcementEntity1.getIsActive(), chatId));

                sendMsg(editMessageText);

            });

        });
    }


}
