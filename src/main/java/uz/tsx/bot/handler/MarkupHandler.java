package uz.tsx.bot.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.tsx.bot.dto.CategoryDto;

import java.util.ArrayList;
import java.util.HashMap;
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
    public InlineKeyboardMarkup getCategoryInlineKeyboardMarkup1(List<CategoryDto> categoryList, int n) throws JsonProcessingException {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < categoryList.size(); i++) {
            CategoryDto category = categoryList.get(i);
            Map<String, String> callbackData = new HashMap<>();
            callbackData.put("id", category.getId().toString());
            callbackData.put("taype", "category");
            String categoryDtoJson = objectMapper.writeValueAsString(callbackData);
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(category.getName());
            inlineKeyboardButton.setCallbackData(categoryDtoJson);
            row.add(inlineKeyboardButton);
            if ((i + 1) % n == 0) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (row.size() > 0) {
            rows.add(row);
        }

        return inlineKeyboardMarkup;
    }

}
