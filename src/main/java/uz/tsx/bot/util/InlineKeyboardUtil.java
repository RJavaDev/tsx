package uz.tsx.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.CurrencyEntity;
import uz.tsx.entity.RegionEntity;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardUtil {

    public static InlineKeyboardMarkup actionButtonsWithPage(Long ann_id, int pages, Boolean isActive) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

//        row.add(button("edit ✏",ann_id + "-edit"));
        if(isActive) {
            row.add(button("inActive ❌","inActive-" + ann_id));
        } else {
            row.add(button("active ✅","inActive-" + ann_id));
        }
        row.add(button("delete \uD83D\uDDD1","delete-" + ann_id));
        rows.add(row);

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

    public static ReplyKeyboard yesOrNotButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button("Ha ✅","yes"));
        row.add(button("Yo'q ❌","not"));
        rows.add(row);

        return inlineKeyboardMarkup;
    }

    public static ReplyKeyboard finishButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button("Tugatish","finish"));
        rows.add(row);

        return inlineKeyboardMarkup;
    }

    public static ReplyKeyboard regionButtons(List<RegionEntity> regionEntityList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < regionEntityList.size(); i++) {
            RegionEntity regionEntity = regionEntityList.get(i);
            row.add(button(regionEntity.getNameUz(),"region-" + regionEntity.getId()));
            if ((i + 1) % 2 == 0) {
                row = new ArrayList<>();
            } else {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

    public static ReplyKeyboard currencyButtons(List<CurrencyEntity> currencyEntityList) {
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

    public static ReplyKeyboard categoryButtons(List<CategoryEntity> categoryEntityList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < categoryEntityList.size(); i++) {
            CategoryEntity categoryEntity = categoryEntityList.get(i);
            row.add(button(categoryEntity.getNameUz(), "category-" + categoryEntity.getId()));
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
