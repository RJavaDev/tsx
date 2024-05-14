package uz.tsx.bot.handler;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkupHandler {
    public ReplyKeyboardMarkup replyKeyboardMarkup(List<String> menuItems, int n){
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        List<KeyboardRow> buttonRow = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        for (int i = 0; i < menuItems.size(); i++) {
            keyboardButtons.add(new KeyboardButton(menuItems.get(i)));
            if (i % n == 0){
                buttonRow.add(keyboardButtons);
                keyboardButtons = new KeyboardRow();
            }
        }
        if (keyboardButtons.size() > 0){
            buttonRow.add(keyboardButtons);
        }
        r.setKeyboard(buttonRow);
        return r;
    }

    private String getKey(Map<String, InlineKeyboardMarkup> map) {
        for (Map.Entry<String, InlineKeyboardMarkup> pair : map.entrySet()) {
            return pair.getKey();
        }
        return null;
    }
}
