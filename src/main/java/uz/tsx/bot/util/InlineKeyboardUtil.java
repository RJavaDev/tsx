package uz.tsx.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.tsx.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardUtil {

    public static ReplyKeyboard getFileButton(){
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(getButton("\uD83D\uDCC3 PDF ga yuklab olish", "pdf"));
        row.add(getButton("\uD83D\uDCDC EXCEL ga yuklab olish","excel"));
        rowList.add(row);
        return new InlineKeyboardMarkup(rowList);
    }

    public static ReplyKeyboard getCategoryButton(List<CategoryEntity> categoryEntityList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < categoryEntityList.size(); i++) {
            CategoryEntity categoryEntity = categoryEntityList.get(i);

            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(categoryEntity.getNameUz());
            inlineKeyboardButton.setCallbackData("category-" + categoryEntity.getId());
            row.add(inlineKeyboardButton);
            if ((i + 1) % 2 == 0) {
                row = new ArrayList<>();
            } else {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardButton getButton(String demo, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(callbackData);
        return button;
    }
}
