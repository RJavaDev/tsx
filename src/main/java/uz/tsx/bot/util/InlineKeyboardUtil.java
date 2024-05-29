package uz.tsx.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DevOps
 */
public class InlineKeyboardUtil {
    public static ReplyKeyboard getFileButton(){
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(getButton("\uD83D\uDCC3 PDF ga yuklab olish", "pdf"));
        row.add(getButton("\uD83D\uDCDC EXCEL ga yuklab olish","excel"));
        rowList.add(row);
        return new InlineKeyboardMarkup(rowList);
    }

    private static InlineKeyboardButton getButton(String demo, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(callbackData);
        return button;
    }
}
