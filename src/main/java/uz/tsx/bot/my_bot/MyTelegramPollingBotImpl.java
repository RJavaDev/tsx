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
import uz.tsx.bot.entity.UserBotEntity;
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
import java.time.format.DateTimeFormatter;
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
    private final AttachService attachService;

    //components
    private final ReplyKeyboardUtil replyKeyboardUtil;
    private final InlineKeyboardUtil inlineKeyboardUtil;

    //entities
    private AnnouncementEntity announcementEntity;
    private AnnouncementContactEntity announcementContactEntity;
    private AnnouncementPriceEntity announcementPriceEntity;
    private final List<List<PhotoSize>> photoSizeList = new ArrayList<>();
    private final List<Document> documentList = new ArrayList<>();

    private AnnouncementPriceEntity editableAnnouncementPriceEntity = new AnnouncementPriceEntity();
    private AnnouncementContactEntity editableAnnouncementContactEntity = new AnnouncementContactEntity();
    private AnnouncementEntity editableAnnouncement = new AnnouncementEntity();

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private UserBotEntity userBot;


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
            userBotService.getUserByChatId(String.valueOf(update.getMessage().getChatId())).ifPresent(userBotEntity ->
                userBot = userBotEntity
            );
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
            userBotService.getUserByChatId(String.valueOf(message.getChatId())).ifPresent(userBotEntity ->
                userBot = userBotEntity
            );
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

        if (text.equals("/start")){
            if(userBotService.isUserNotExistByChatId(chatId)) {
                userBotService.createBotUser(chatId);
            }

            if (userBot.getLanguage() == null) {
                userBotService.setUserState(chatId, StateEnum.START);
                sendMessage.setText("Iltimos tilni tanlang!\n\nПожалуйста, выберите язык!\n\nPlease select a language!");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.langButtons());
            } else {
                userBotService.setUserState(chatId, StateEnum.HOME);
                String greetingMessage = messageUtils.getMessage("bot.hello", userBot.getLanguage()) + " " + user.getFirstName() + ". " + messageUtils.getMessage("bot.welcome", userBot.getLanguage());
                sendMessage.setText(greetingMessage);
                sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(userBot.getLanguage()));
            }
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.HOME) && text.equals(messageUtils.getMessage("bot.button.my_anns", userBot.getLanguage()))) {
            if (userBotService.isUserNotExistByPhoneNumberAndChatId(chatId)) {
                userBotService.setUserState(chatId, StateEnum.MY_ANNS);
                sendMessage.setText(messageUtils.getMessage("bot.share_contact", userBot.getLanguage()));
                sendMessage.setReplyMarkup(replyKeyboardUtil.contactButton(userBot.getLanguage()));
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.LOGINED);
                sendMessage.setText(messageUtils.getMessage("bot.welcome_your_page", userBot.getLanguage()));
                sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));
                sendMsg(sendMessage);
                showUserAnnouncements(chatId);
            }
        }
        else if (userBot.getState().equals(StateEnum.MY_ANNS) && Objects.isNull(message.getContact()) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            sendMessage.setText(messageUtils.getMessage("bot.share_contact_error", userBot.getLanguage()));
            sendMessage.setParseMode("html");
            sendMessage.setReplyMarkup(replyKeyboardUtil.contactButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.HOME) && text.equals(messageUtils.getMessage("bot.button.settings", userBot.getLanguage()))) {
            userBotService.setUserState(chatId, StateEnum.SETTINGS);
            sendMessage.setText(messageUtils.getMessage("bot.button.settings", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.settingsButtons(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.SETTINGS) && text.equals(messageUtils.getMessage("bot.button.change_lang", userBot.getLanguage()))) {
            userBotService.setUserState(chatId, StateEnum.CHANGE_LANG);
            sendMessage.setText("Iltimos tilni tanlang!\n\nПожалуйста, выберите язык!\n\nPlease select a language!");
            sendMessage.setReplyMarkup(inlineKeyboardUtil.langButtons());
            sendMsg(sendMessage);
        }
        else if (text.equals("/delete")) {
            // shaxsiy method test uchun
            userBotRepository.deleteUserByChatId(chatId);
            userRepository.deleteUserByPhoneNumber("+998997558142");
            sendMessage.setText("deleted");
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.ENTERED_NEW_PASS) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            userBotService.registerUser(chatId, BotConstants.USER_PHONE_NUMBER.get(chatId), text, message.getFrom().getFirstName());
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.success_login", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if ((userBot.getState().equals(StateEnum.LOGINED) || userBot.getState().equals(StateEnum.EDIT_ANN)) && text.equals(messageUtils.getMessage("bot.button.create_new_ann", userBot.getLanguage()))) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_TITLE);
            sendMessage.setText(messageUtils.getMessage("bot.enter_name_for_ann", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.backButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.ENTERED_ANN_TITLE) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);
            announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle(text);

            userBotService.getUserByChatId(chatId).ifPresent(userByChatId -> {
                announcementEntity.setUserEntity(userByChatId.getUserEntity());
                announcementEntity.setUserId(userByChatId.getUserEntity().getId());
            });

            List<CategoryEntity> categoryEntityList = categoryService.getAllTree();

            sendMessage.setText(messageUtils.getMessage("bot.select_category_for_ann", userBot.getLanguage()));
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
            sendMsg(sendMessage);

            sendMessage.setText(messageUtils.getMessage("bot.categories", userBot.getLanguage()));
            sendMessage.setReplyMarkup(inlineKeyboardUtil.categoryButtons(categoryEntityList, userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.ENTERED_ANN_DESCRIPTION)) {
            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CURRENCY);
            announcementEntity.setDescription(text);
            sendMessage.setText(messageUtils.getMessage("bot.select_currency_for_ann", userBot.getLanguage()));

            List<CurrencyEntity> allCurrencies = currencyService.getAllCurrencies();
            sendMessage.setReplyMarkup(inlineKeyboardUtil.currencyButtons(allCurrencies));
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.ENTERED_ANN_PRICE)) {
            if(text.matches("^\\d+$")) {
                announcementPriceEntity.setPrice(BigDecimal.valueOf(Long.parseLong(text)));
                sendMessage.setText(messageUtils.getMessage("bot.send_image_for_ann", userBot.getLanguage()) + "\n" + messageUtils.getMessage("bot.send_only_eight_images", userBot.getLanguage()));
                sendMessage.setParseMode("html");
            } else {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
                sendMessage.setText(messageUtils.getLocalizedMessage("bot.enter_price_for_ann_error", userBot.getLanguage()));
            }
            sendMsg(sendMessage);
        }
        else if (userBot.getState().equals(StateEnum.ENTER_NEW_TITLE) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            editableAnnouncement.setTitle(text);
            announcementService.update(editableAnnouncement);
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId() - 1));
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId()));
            editUserAnnouncement(chatId);
        }
        else if (userBot.getState().equals(StateEnum.ENTER_NEW_DESCRIPTION) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            editableAnnouncement.setDescription(text);
            announcementService.update(editableAnnouncement);
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId() - 1));
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId()));
            editUserAnnouncement(chatId);
        }
        else if (userBot.getState().equals(StateEnum.ENTER_NEW_PRICE) && !text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            if(text.matches("^\\d+$")) {
                editableAnnouncementPriceEntity.setPrice(BigDecimal.valueOf(Long.parseLong(text)));

                AnnouncementPriceEntity editableAnnouncementPriceEntity1 = announcementPriceService.addNewAnnouncementPrice(editableAnnouncementPriceEntity);

                editableAnnouncement.setPriceTagId(editableAnnouncementPriceEntity1.getId());
                editableAnnouncement.setPriceTag(editableAnnouncementPriceEntity1);

                announcementService.update(editableAnnouncement);

                sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId() - 1));
                sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId()));
                editUserAnnouncement(chatId);
            } else {
                userBotService.setUserState(chatId, StateEnum.ENTER_NEW_PRICE);
                sendMessage.setText(messageUtils.getLocalizedMessage("bot.enter_price_for_ann_error", userBot.getLanguage()));
                sendMsg(sendMessage);
            }
        }
        else if (
                (userBot.getState().equals(StateEnum.MY_ANNS) ||
                    userBot.getState().equals(StateEnum.ENTERED_NEW_PASS) ||
                        userBot.getState().equals(StateEnum.SETTINGS)
                ) && text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            sendMessage.setText(messageUtils.getMessage("bot.home_page", userBot.getLanguage()));
            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (
                (userBot.getState().equals(StateEnum.ENTER_NEW_TITLE) ||
                        userBot.getState().equals(StateEnum.ENTER_NEW_DESCRIPTION) ||
                            userBot.getState().equals(StateEnum.ENTER_NEW_CURRENCY) ||
                                userBot.getState().equals(StateEnum.ENTER_NEW_PRICE) ||
                                    userBot.getState().equals(StateEnum.ENTER_NEW_IMAGE) ||
                                        userBot.getState().equals(StateEnum.ENTER_NEW_REGION) ||
                                            userBot.getState().equals(StateEnum.ENTER_NEW_CATEGORY)
                ) && text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))
        ) {
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId() - 1));
            sendMsg(botService.deletePreviousMessage(chatId, message.getMessageId()));
            photoSizeList.clear();
            documentList.clear();
            editUserAnnouncement(chatId);
        }
        else if(
                (userBot.getState().equals(StateEnum.LOGINED) ||
                        userBot.getState().equals(StateEnum.EDIT_ANN)) &&
                        text.equals(messageUtils.getMessage("bot.button.home_page", userBot.getLanguage()))
        ) {
            editableAnnouncementPriceEntity = new AnnouncementPriceEntity();
            editableAnnouncementContactEntity = new AnnouncementContactEntity();
            editableAnnouncement = new AnnouncementEntity();
            sendMessage.setText(messageUtils.getMessage("bot.home_page", userBot.getLanguage()));
            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if(userBot.getState().equals(StateEnum.ENTERED_ANN_TITLE) &&
                text.equals(messageUtils.getMessage("bot.button.back", userBot.getLanguage()))) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.profile_page", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));
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

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);

        String[] split = data.split("-");
        String key = split[0];

        User user = callbackQuery.getFrom();

        if (key.equals("lang") && userBot.getState().equals(StateEnum.START)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            String langCode = String.valueOf(split[1]);
            userBot = userBotService.setUserLang(chatId, langCode);

            userBotService.setUserState(chatId, StateEnum.HOME);
            sendMessage.setText(messageUtils.getMessage("bot.hello", userBot.getLanguage()) + " " + user.getFirstName() + ". " + messageUtils.getMessage("bot.welcome", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.mainMenuButton(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (key.equals("lang") && userBot.getState().equals(StateEnum.CHANGE_LANG)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            String langCode = String.valueOf(split[1]);
            userBot = userBotService.setUserLang(chatId, langCode);

            userBotService.setUserState(chatId, StateEnum.SETTINGS);
            sendMessage.setText(messageUtils.getMessage("bot.lang_changed_success", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.settingsButtons(userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (key.equals("category") && userBot.getState().equals(StateEnum.ENTERED_ANN_CATEGORY)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_CATEGORY);

            Long categoryId = Long.valueOf(data.split("-")[1]);

            List<CategoryEntity> childCategoriesByParentId = categoryService.getChildCategoriesByParentId(categoryId);

            if(childCategoriesByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_REGION);

                announcementEntity.setCategoryId(categoryId);
                announcementEntity.setCategory(categoryService.getById(categoryId));

                sendMessage.setText(messageUtils.getMessage("bot.category_selected_for_ann", userBot.getLanguage())+"\n\n" + messageUtils.getMessage("bot.select_region_for_ann", userBot.getLanguage()));
                sendMsg(sendMessage);
                sendMessage.setText(messageUtils.getMessage("bot.regions", userBot.getLanguage()));
                List<RegionEntity> regionEntityList = regionService.getAllTree();
                sendMessage.setReplyMarkup(inlineKeyboardUtil.regionButtons(regionEntityList, userBot.getLanguage()));
            } else {
                sendMessage.setText(messageUtils.getMessage("bot.categories", userBot.getLanguage()) + " ...");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.categoryButtons(childCategoriesByParentId, userBot.getLanguage()));
            }
            sendMsg(sendMessage);
        }
        else if (key.equals("currency") && userBot.getState().equals(StateEnum.ENTERED_ANN_CURRENCY)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_PRICE);
            String code = split[1];

            currencyService.getCurrencyByCode(code).ifPresent(currency -> {
                announcementPriceEntity = new AnnouncementPriceEntity();
                announcementPriceEntity.setCurrencyId(currency.getId());
                announcementPriceEntity.setCurrency(currency);
            });

            sendMessage.setText(messageUtils.getMessage("bot.enter_price_for_ann", userBot.getLanguage()));
            sendMsg(sendMessage);
        }
        else if (key.equals("region") && userBot.getState().equals(StateEnum.ENTERED_ANN_REGION)) {
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
                sendMessage.setText(messageUtils.getMessage("bot.region_selected_for_ann", userBot.getLanguage()) + "\n\n" + messageUtils.getMessage("bot.enter_desc_for_ann", userBot.getLanguage()));
            } else {
                sendMessage.setText(messageUtils.getMessage("bot.regions", userBot.getLanguage()) + " ...");
                sendMessage.setReplyMarkup(inlineKeyboardUtil.regionButtons(childRegionsByParentId, userBot.getLanguage()));
            }
            sendMsg(sendMessage);
        }
        else if (key.equals("finish") &&
                userBot.getState().equals(StateEnum.ENTERED_ANN_IMAGE)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.FINISH);
            finish(sendMessage, chatId);
        }
        else if (key.equals("yes") && userBot.getState().equals(StateEnum.FINISH)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.LOGINED);
            sendMessage.setText(messageUtils.getMessage("bot.waiting", userBot.getLanguage()));
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

                sendMessage.setText(messageUtils.getMessage("bot.ann_created_success", userBot.getLanguage()));
                sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));

            } else {
                sendMessage.setText(messageUtils.getMessage("bot.ann_created_error", userBot.getLanguage()) + "\n\n" + messageUtils.getMessage("bot.try_again", userBot.getLanguage()));
            }
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
            photoSizeList.clear();
            documentList.clear();
        }
        else if (key.equals("not") && userBot.getState().equals(StateEnum.FINISH)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));
            sendMsg(botService.deletePreviousMessage(chatId, messageId));

            userBotService.setUserState(chatId, StateEnum.LOGINED);

            photoSizeList.clear();
            documentList.clear();

            sendMessage.setText(messageUtils.getMessage("bot.profile_page", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
        }
        else if (key.equals("page") && userBot.getState().equals(StateEnum.LOGINED)) {
            int page = Integer.parseInt(split[1]);
            showUserAnnouncements(chatId, page, messageId);
        }
        else if (key.equals("inActive") && userBot.getState().equals(StateEnum.LOGINED)) {
            Long annId = Long.valueOf(split[1]);
            announcementService.changeActiveStatus(annId, !announcementService.getById(annId).getIsActive());
            showUserAnnouncements(chatId, BotConstants.USER_SELECTED_PAGE.get(chatId), messageId);
        }
        else if (key.equals("delete") && userBot.getState().equals(StateEnum.LOGINED)) {
            Long annId = Long.valueOf(split[1]);
            announcementService.deleteAnnouncement(annId);

            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));

            sendMessage.setText(messageUtils.getMessage("bot.ann_deleted_success", userBot.getLanguage()));
            sendMsg(sendMessage);
            showUserAnnouncements(chatId);
        }
        else if (key.equals("edit") && userBot.getState().equals(StateEnum.LOGINED)) {
            Long annId = Long.valueOf(split[1]);
            editableAnnouncement = announcementService.getById(annId);
            editUserAnnouncement(chatId, messageId);
        }
        else if (key.equals("backButton") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId - 1));
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            showUserAnnouncements(chatId, BotConstants.USER_SELECTED_PAGE.get(chatId));
            userBotService.setUserState(chatId, StateEnum.LOGINED);
            editableAnnouncement = new AnnouncementEntity();
            editableAnnouncementContactEntity = new AnnouncementContactEntity();
            editableAnnouncementPriceEntity = new AnnouncementPriceEntity();
        }
        else if (key.equals("edit_title") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_TITLE);

            editMessageText.setText(messageUtils.getMessage("bot.enter_new_name", userBot.getLanguage()));
            editMessageText.setReplyMarkup(inlineKeyboardUtil.backButton(userBot.getLanguage()));
            sendMsg(editMessageText);
        }
        else if (key.equals("edit_description") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_DESCRIPTION);


            editMessageText.setText(messageUtils.getMessage("bot.enter_new_description", userBot.getLanguage()));
            editMessageText.setReplyMarkup(inlineKeyboardUtil.backButton(userBot.getLanguage()));
            sendMsg(editMessageText);
        }
        else if (key.equals("edit_price") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_CURRENCY);

            editMessageText.setText(messageUtils.getMessage("bot.select_currency_for_ann", userBot.getLanguage()));

            List<CurrencyEntity> allCurrencies = currencyService.getAllCurrencies();
            editMessageText.setReplyMarkup(inlineKeyboardUtil.currencyButtonsWithBackButton(allCurrencies, userBot.getLanguage()));
            sendMsg(editMessageText);
        }
        else if (key.equals("currency") && userBot.getState().equals(StateEnum.ENTER_NEW_CURRENCY)) {
            String code = split[1];

            editableAnnouncementPriceEntity.setId(announcementService.getById(editableAnnouncement.getId()).getPriceTag().getId());

            currencyService.getCurrencyByCode(code).ifPresent(currency -> {
                editableAnnouncementPriceEntity.setCurrencyId(currency.getId());
                editableAnnouncementPriceEntity.setCurrency(currency);
            });

            editMessageText.setText(messageUtils.getMessage("bot.enter_new_price", userBot.getLanguage()));
            editMessageText.setReplyMarkup(inlineKeyboardUtil.backButton(userBot.getLanguage()));
            sendMsg(editMessageText);
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_PRICE);
        }
        else if (key.equals("edit_category") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            List<CategoryEntity> categoryEntityList = categoryService.getAllTree();

            editMessageText.setText(messageUtils.getMessage("bot.select_new_category", userBot.getLanguage()));
            editMessageText.setReplyMarkup(inlineKeyboardUtil.categoryButtonsWithBackButton(categoryEntityList, userBot.getLanguage()));
            sendMsg(editMessageText);
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_CATEGORY);
        }
        else if (key.equals("category") && userBot.getState().equals(StateEnum.ENTER_NEW_CATEGORY)) {

            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_CATEGORY);

            Long categoryId = Long.valueOf(data.split("-")[1]);

            List<CategoryEntity> childCategoriesByParentId = categoryService.getChildCategoriesByParentId(categoryId);

            if(childCategoriesByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTER_NEW_REGION);

                editableAnnouncement.setCategoryId(categoryId);
                editableAnnouncement.setCategory(categoryService.getById(categoryId));

                announcementService.update(editableAnnouncement);

                editUserAnnouncement(chatId, messageId);
            } else {
                editMessageText.setText(messageUtils.getMessage("bot.categories", userBot.getLanguage()) + " ...");
                editMessageText.setReplyMarkup(inlineKeyboardUtil.categoryButtonsWithBackButton(childCategoriesByParentId, userBot.getLanguage()));
                sendMsg(editMessageText);
            }
        }
        else if (key.equals("edit_region") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            editMessageText.setText(messageUtils.getMessage("bot.select_new_region", userBot.getLanguage()));
            List<RegionEntity> regionEntityList = regionService.getAllTree();
            editMessageText.setReplyMarkup(inlineKeyboardUtil.regionButtonsWithBackButton(regionEntityList, userBot.getLanguage()));
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_REGION);
            sendMsg(editMessageText);
        }
        else if (key.equals("region") && userBot.getState().equals(StateEnum.ENTER_NEW_REGION)) {
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_REGION);

            Long regionId = Long.valueOf(data.split("-")[1]);

            List<RegionEntity> childRegionsByParentId = regionService.getChildRegionsByParentId(regionId);
            if(childRegionsByParentId.isEmpty()) {
                userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_DESCRIPTION);

                editableAnnouncementContactEntity.setRegionId(regionId);
                editableAnnouncementContactEntity.setRegion(regionService.getById(regionId));
                userBotService.getUserByChatId(chatId).ifPresent(user1 ->
                        editableAnnouncementContactEntity.setPhone(user1.getUserEntity().getEmailOrPhone())
                );

                editableAnnouncementContactEntity.setId(announcementService.getById(editableAnnouncement.getId()).getContactInfo().getId());

                AnnouncementContactEntity announcementContactEntity1 = announcementContactService.addNewAnnounceContact(editableAnnouncementContactEntity);

                editableAnnouncement.setContactInfoId(announcementContactEntity1.getId());
                editableAnnouncement.setContactInfo(announcementContactEntity1);

                announcementService.update(editableAnnouncement);
                editUserAnnouncement(chatId, messageId);
            } else {
                editMessageText.setText(messageUtils.getMessage("bot.regions", userBot.getLanguage()) + " ...");
                editMessageText.setReplyMarkup(inlineKeyboardUtil.regionButtonsWithBackButton(childRegionsByParentId, userBot.getLanguage()));
                sendMsg(editMessageText);
            }
        }
        else if (key.equals("edit_image") && userBot.getState().equals(StateEnum.EDIT_ANN)) {
            editMessageText.setText(messageUtils.getMessage("bot.send_new_image", userBot.getLanguage()) + "\n" + messageUtils.getMessage("bot.send_only_eight_images", userBot.getLanguage()));
            editMessageText.setReplyMarkup(inlineKeyboardUtil.backButton(userBot.getLanguage()));
            editMessageText.setParseMode("html");
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_IMAGE);
            sendMsg(editMessageText);
        }
        else if (key.equals("finish") && userBot.getState().equals(StateEnum.ENTER_NEW_IMAGE)) {
            sendMsg(botService.deletePreviousMessage(chatId, messageId));
            sendMessage.setText(messageUtils.getMessage("bot.waiting", userBot.getLanguage()));
            sendMsg(sendMessage);
            List<AttachEntity> attachEntityList = fileService.savePhotos(photoSizeList, documentList);
            if(!attachEntityList.isEmpty()) {

                List<AttachEntity> attachPhotos = editableAnnouncement.getAttachPhotos();

                attachPhotos.forEach(attachEntity ->
                    attachService.deleteById(attachEntity.getId())
                );

                editableAnnouncement.setAttachPhotos(attachEntityList);

                announcementService.update(editableAnnouncement);

                sendMsg(botService.deletePreviousMessage(chatId, messageId + 1));

            } else {
                sendMessage.setText(messageUtils.getMessage("bot.ann_created_error", userBot.getLanguage()) + "\n\n" + messageUtils.getMessage("bot.try_again", userBot.getLanguage()));
                sendMsg(sendMessage);
            }
            editUserAnnouncementWithImage(chatId);
            photoSizeList.clear();
            documentList.clear();
        }
        else if (key.equals("backButton") &&
                userBot.getState().equals(StateEnum.ENTER_NEW_TITLE) ||
                userBot.getState().equals(StateEnum.ENTER_NEW_DESCRIPTION) ||
                userBot.getState().equals(StateEnum.ENTER_NEW_CURRENCY) ||
                userBot.getState().equals(StateEnum.ENTER_NEW_CATEGORY) ||
                userBot.getState().equals(StateEnum.ENTER_NEW_REGION) ||
                userBot.getState().equals(StateEnum.ENTER_NEW_IMAGE)
        ) {
            editUserAnnouncement(chatId, messageId);
        }
        else if (key.equals("backButton") && userBot.getState().equals(StateEnum.ENTER_NEW_PRICE)) {
            userBotService.setUserState(chatId, StateEnum.ENTER_NEW_CURRENCY);
            editMessageText.setText(messageUtils.getMessage("bot.select_currency_for_ann", userBot.getLanguage()));

            List<CurrencyEntity> allCurrencies = currencyService.getAllCurrencies();
            editMessageText.setReplyMarkup(inlineKeyboardUtil.currencyButtonsWithBackButton(allCurrencies, userBot.getLanguage()));
            sendMsg(editMessageText);
        }
    }

    private void handleContact(Message message) {
        String chatId =String.valueOf(message.getChatId());
        String phoneNumber = message.getContact().getPhoneNumber();

        if(!phoneNumber.startsWith("+"))  {
            phoneNumber = "+"+phoneNumber;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        BotConstants.USER_PHONE_NUMBER.put(chatId, phoneNumber);

        sendMessage.setReplyMarkup(replyKeyboardUtil.backButton(userBot.getLanguage()));

        if(userBotService.isUserExistByPhoneNumber(phoneNumber)) {
            userBotService.setUserState(chatId, StateEnum.LOGINED);

            userBotService.mergeUserAccounts(phoneNumber, chatId);

            sendMessage.setText(messageUtils.getMessage("bot.welcome_your_page", userBot.getLanguage()));
            sendMessage.setReplyMarkup(replyKeyboardUtil.userProfileButton(userBot.getLanguage()));
        } else {
            userBotService.setUserState(chatId, StateEnum.ENTERED_NEW_PASS);
            sendMessage.setText(messageUtils.getMessage("bot.enter_new_pass", userBot.getLanguage()));
        }
        sendMsg(sendMessage);
    }

    @SneakyThrows
    private void handleImage(Message message) {
        String chatId =String.valueOf(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);


        if(userBot.getState().equals(StateEnum.ENTERED_ANN_PRICE) || userBot.getState().equals(StateEnum.ENTER_NEW_IMAGE)) {

            int attachCount = photoSizeList.size() + documentList.size();

            if(attachCount < 7) {
                if(Objects.nonNull(message.getPhoto())) {

                    if(userBot.getState().equals(StateEnum.ENTERED_ANN_PRICE)) {
                        userBotService.setUserState(chatId, StateEnum.ENTERED_ANN_IMAGE);
                    }
                    else if (userBot.getState().equals(StateEnum.ENTER_NEW_IMAGE)) {
                        userBotService.setUserState(chatId, StateEnum.ENTER_NEW_IMAGE);
                    }

                    PhotoSize photoSize = message.getPhoto().get(message.getPhoto().size() - 1); // eng sifatlisi
                    photoSizeList.add(Collections.singletonList(photoSize));
                    sendMessage.setText(attachCount + 1 + "/" + messageUtils.getMessage("bot.eight_images_uploaded", userBot.getLanguage()) + "\n\n" + messageUtils.getMessage("bot.upload_again_image_or_stop", userBot.getLanguage()));
                    sendMessage.setReplyMarkup(inlineKeyboardUtil.finishButton(userBot.getLanguage()));
                }
                else if(Objects.nonNull(message.getDocument())) {
                    documentList.add(message.getDocument());
                }
                sendMsg(sendMessage);
            } else {
                userBotService.setUserState(chatId, StateEnum.FINISH);
                sendMessage.setText(messageUtils.getMessage("bot.eight_images_saved", userBot.getLanguage()));
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
                messageUtils.getMessage("bot.your_ann", userBot.getLanguage()) + "\n\n" +
                messageUtils.getMessage("bot.ann_name", userBot.getLanguage()) + announcementEntity.getTitle() + "\n" +
                messageUtils.getMessage("bot.ann_desc", userBot.getLanguage())+ announcementEntity.getDescription() + "\n" +
                messageUtils.getMessage("bot.ann_category", userBot.getLanguage()) + announcementEntity.getCategory().getNameUz() + "\n" +
                messageUtils.getMessage("bot.ann_region", userBot.getLanguage()) + announcementContactEntity.getRegion().getNameUz() + "\n" +
                messageUtils.getMessage("bot.ann_price", userBot.getLanguage()) + announcementPriceEntity.getPrice() + " " + announcementPriceEntity.getCurrency().getName() + "\n" +
                messageUtils.getMessage("bot.ann_is_save", userBot.getLanguage())
        );
        sendMessage.setReplyMarkup(inlineKeyboardUtil.yesOrNotButtons(userBot.getLanguage()));
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

                String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                        messageUtils.getMessage("bot.inActive", userBot.getLanguage());

                sendMessage.setText(
                        "<strong>№" + 1 + "</strong>" +
                                "\n\n<strong>" + status + "</strong>" +
                                "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                                "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                                "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
                );

                sendMessage.setReplyMarkup(inlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), userBotService.getUserAnnouncementCount(chatId), announcementEntity1.getIsActive(), userBot.getLanguage()));

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

                String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                        messageUtils.getMessage("bot.inActive", userBot.getLanguage());

                editMessageText.setText(
                        "<strong>№" + (page + 1) + "</strong>" +
                                "\n\n<strong>" + status + "</strong>" +
                                "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                                "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                                "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                                "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                                "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
                );

                editMessageText.setReplyMarkup(inlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), userBotService.getUserAnnouncementCount(chatId), announcementEntity1.getIsActive(), userBot.getLanguage()));

                sendMsg(editMessageText);

            });

        });
    }

    private void showUserAnnouncements(String chatId, int page) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        userBotRepository.getUserByChatId(chatId).flatMap(userBotEntity -> userBotService.getUserAnnouncement(chatId, page)).ifPresent(announcementEntity1 -> {
            sendMsg(botService.sendPhoto(chatId, announcementEntity1.getAttachPhotos().get(0)));

            sendMessage.setParseMode("html");

            BotConstants.USER_SELECTED_PAGE.put(chatId, page);

            String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                    messageUtils.getMessage("bot.inActive", userBot.getLanguage());

            sendMessage.setText(
                    "<strong>№" + (page + 1) + "</strong>" +
                            "\n\n<strong>" + status + "</strong>" +
                            "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                            "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                            "\n\n<b>" + announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                            "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                            "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
            );

            sendMessage.setReplyMarkup(inlineKeyboardUtil.actionButtonsWithPage(announcementEntity1.getId(), userBotService.getUserAnnouncementCount(chatId), announcementEntity1.getIsActive(), userBot.getLanguage()));

            sendMsg(sendMessage);

        });
    }

    private void editUserAnnouncement(String chatId, Integer messageId) {
        AnnouncementEntity announcementEntity1 = announcementService.getById(editableAnnouncement.getId());
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setParseMode("html");

        String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                messageUtils.getMessage("bot.inActive", userBot.getLanguage());

        editMessageText.setText(
                "\n\n<strong>" + status + "</strong>" +
                        "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                        "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                        "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                        "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                        "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
        );

        editMessageText.setReplyMarkup(inlineKeyboardUtil.annActionButtons(userBot.getLanguage()));

        sendMsg(editMessageText);
        userBotService.setUserState(chatId, StateEnum.EDIT_ANN);
    }

    private void editUserAnnouncement(String chatId) {
        AnnouncementEntity announcementEntity1 = announcementService.getById(editableAnnouncement.getId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("html");
        sendMessage.setChatId(chatId);

        String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                messageUtils.getMessage("bot.inActive", userBot.getLanguage());

        sendMessage.setText(
                "\n\n<strong>" + status + "</strong>" +
                        "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                        "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                        "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                        "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                        "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
        );

        sendMessage.setReplyMarkup(inlineKeyboardUtil.annActionButtons(userBot.getLanguage()));

        sendMsg(sendMessage);
        userBotService.setUserState(chatId, StateEnum.EDIT_ANN);
    }

    private void editUserAnnouncementWithImage(String chatId) {
        AnnouncementEntity announcementEntity1 = announcementService.getById(editableAnnouncement.getId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("html");
        sendMessage.setChatId(chatId);

        sendMsg(botService.sendPhoto(chatId, announcementEntity1.getAttachPhotos().get(0)));

        String status = announcementEntity1.getIsActive() ? messageUtils.getMessage("bot.active", userBot.getLanguage()) :
                messageUtils.getMessage("bot.inActive", userBot.getLanguage());

        sendMessage.setText(
                "\n\n<strong>" + status + "</strong>" +
                        "\n\n<i>" + announcementEntity1.getTitle() + "</i>" +
                        "\n\n<i>" + announcementEntity1.getDescription() + "</i>" +
                        "\n\n<b>"+ announcementEntity1.getPriceTag().getPrice() + " " + announcementEntity1.getPriceTag().getCurrency().getName() +
                        "</b>\n\n" + announcementEntity1.getContactInfo().getRegion().getNameUz() +
                        "\n\n" + announcementEntity1.getCreatedDate().format(formatter)
        );

        sendMessage.setReplyMarkup(inlineKeyboardUtil.annActionButtons(userBot.getLanguage()));

        sendMsg(sendMessage);
        userBotService.setUserState(chatId, StateEnum.EDIT_ANN);
    }
}
