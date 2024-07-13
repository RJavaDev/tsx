package uz.tsx.bot.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.tsx.bot.service.UserBotService;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.CurrencyEntity;
import uz.tsx.entity.RegionEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InlineKeyboardUtil {
    private final MessageUtils messageUtils;
    private final UserBotService userBotService;

    public InlineKeyboardMarkup actionButtonsWithPage(Long annId, int pages, Boolean isActive, String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button(messageUtils.getMessage("bot.button.edit", userBotService.getUserLang(chatId)),"edit-" + annId));

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        if(isActive) {
            row1.add(button(messageUtils.getMessage("bot.button.inActive", userBotService.getUserLang(chatId)),"inActive-" + annId));
        } else {
            row1.add(button(messageUtils.getMessage("bot.button.active", userBotService.getUserLang(chatId)), "inActive-" + annId));
        }
        row.add(button(messageUtils.getMessage("bot.button.delete", userBotService.getUserLang(chatId)), "delete-" + annId));
        rows.add(row);
        rows.add(row1);

        if (pages > 1) {
            row = new ArrayList<>();
            for (int i = 0; i < pages; i++) {
                row.add(button(String.valueOf(i + 1), "page-" + i));
                if ((i + 1) % 5 == 0 || i == pages - 1) {
                    rows.add(row);
                    row = new ArrayList<>();
                }
            }
        }

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup annActionButtons(String chatId, Long annId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button(messageUtils.getMessage("bot.button.edit_title", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button(messageUtils.getMessage("bot.button.edit_description", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button(messageUtils.getMessage("bot.button.edit_price", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button(messageUtils.getMessage("bot.button.edit_category", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(button(messageUtils.getMessage("bot.button.edit_region", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(button(messageUtils.getMessage("bot.button.edit_image", userBotService.getUserLang(chatId)),"inActive-" + annId));

        List<InlineKeyboardButton> backButton = new ArrayList<>();
        backButton.add(button(messageUtils.getMessage("bot.button.back", userBotService.getUserLang(chatId)),"backButton-" + annId));
        rows.addAll(List.of(row, row1, row2, row3, row4, row5, backButton));

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup langButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button("Uz \uD83C\uDDFA\uD83C\uDDFF","lang-uz"));
        row.add(button("Ru \uD83C\uDDF7\uD83C\uDDFA","lang-ru"));
        row.add(button("Eng \uD83C\uDDFA\uD83C\uDDF8","lang-eng"));
        rows.add(row);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup yesOrNotButtons(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button(messageUtils.getMessage("bot.button.yes", userBotService.getUserLang(chatId)), "yes"));
        row.add(button(messageUtils.getMessage("bot.button.no", userBotService.getUserLang(chatId)), "not"));
        rows.add(row);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup finishButton(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button(messageUtils.getMessage("bot.button.completion", userBotService.getUserLang(chatId)), "finish"));
        rows.add(row);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup regionButtons(List<RegionEntity> regionEntityList, String lang) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < regionEntityList.size(); i++) {
            RegionEntity regionEntity = regionEntityList.get(i);
            row.add(button(regionEntity.languageFilterForBot(lang),"region-" + regionEntity.getId()));
            if ((i + 1) % 2 == 0) {
                row = new ArrayList<>();
            } else {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup currencyButtons(List<CurrencyEntity> currencyEntityList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < currencyEntityList.size(); i++) {
            CurrencyEntity currencyEntity = currencyEntityList.get(i);
            row.add(button(currencyEntity.getName(), "currency-" + currencyEntity.getCode()));
            if ((i + 1) % 2 == 0) {
                row = new ArrayList<>();
            } else {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup categoryButtons(List<CategoryEntity> categoryEntityList, String lang) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < categoryEntityList.size(); i++) {
            CategoryEntity categoryEntity = categoryEntityList.get(i);
            row.add(button(categoryEntity.languageFilterForBot(lang), "category-" + categoryEntity.getId()));
            if ((i + 1) % 2 == 0) {
                row = new ArrayList<>();
            } else {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardButton button(String demo, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(callbackData);
        return button;
    }
}
