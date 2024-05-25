package uz.tsx.bot.handler;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.*;

@UtilityClass
public class UserStateHandler {
    private static final HashMap<Long, List<InlineKeyboardMarkup>> userStates = new HashMap<>();

    public void setUserState(Long chatId, InlineKeyboardMarkup state) {
        List<InlineKeyboardMarkup> inlineKeyboardMarkups = userStates.get(chatId);
        if (Objects.isNull(inlineKeyboardMarkups)){
            inlineKeyboardMarkups= new ArrayList<>();
        }
        inlineKeyboardMarkups.add(state);
        userStates.put(chatId, inlineKeyboardMarkups);
    }

    public InlineKeyboardMarkup getUserState(Long chatId) {
        List<InlineKeyboardMarkup> inlineKeyboardMarkups = userStates.get(chatId);
        if (!inlineKeyboardMarkups.isEmpty()){
            if (inlineKeyboardMarkups.size()>1){
                int i = inlineKeyboardMarkups.size() - 2;
                inlineKeyboardMarkups.remove(i+1);
                return inlineKeyboardMarkups.get(i);
            } else {
                return inlineKeyboardMarkups.remove(0);
            }
        }
        return null;
    }
}
