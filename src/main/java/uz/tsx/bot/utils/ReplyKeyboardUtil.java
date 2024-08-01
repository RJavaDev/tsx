package uz.tsx.bot.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.tsx.bot.constantsBot.BotConstants;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReplyKeyboardUtil {
    private final MessageUtils messageUtils;

    public ReplyKeyboard mainMenuButton(String lang) {

        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();

        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl("https://tsx-bot-web.netlify.app/");

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(messageUtils.getMessage("bot.button.anns", lang));
        keyboardButton.setWebApp(webAppInfo);

        row1.add(keyboardButton);
        row2.add(new KeyboardButton(messageUtils.getMessage("bot.button.settings", lang)));
        row2.add(new KeyboardButton(messageUtils.getMessage("bot.button.my_anns", lang)));
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

    public ReplyKeyboard settingsButtons(String lang) {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        row1.add(new KeyboardButton(messageUtils.getMessage("bot.button.change_lang", lang)));
        row1.add(new KeyboardButton(messageUtils.getMessage("bot.button.feedback", lang)));

        row2.add(new KeyboardButton(messageUtils.getMessage("bot.button.back", lang)));
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row1, row2));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

    public ReplyKeyboard contactButton(String lang) {
        KeyboardRow row = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(BotConstants.SHARE_CONTACT);
        keyboardButton.setRequestContact(true);
        row.add(keyboardButton);
        row.add(new KeyboardButton(messageUtils.getMessage("bot.button.back", lang)));

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;

    }

    public ReplyKeyboard backButton(String lang) {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(messageUtils.getMessage("bot.button.back", lang)));
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

    public ReplyKeyboard userProfileButton(String lang) {
        KeyboardRow row = new KeyboardRow();

        row.add(new KeyboardButton(messageUtils.getMessage("bot.button.create_new_ann", lang)));
        row.add(new KeyboardButton(messageUtils.getMessage("bot.button.home_page", lang)));

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

}
