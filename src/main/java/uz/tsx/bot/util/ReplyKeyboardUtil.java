package uz.tsx.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.tsx.bot.constantsBot.BotConstants;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardUtil {
    public static ReplyKeyboard getMainMenuButton() {

        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();

        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl("https://tsx-bot-web.netlify.app/");

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(BotConstants.MENU);
        keyboardButton.setWebApp(webAppInfo);

        row1.add(keyboardButton);
        row2.add(new KeyboardButton(BotConstants.SETTINGS));
        row2.add(new KeyboardButton(BotConstants.MYANNS));
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

    public static ReplyKeyboard getContactButton() {
        KeyboardRow row = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(BotConstants.SHARE_CONTACT);
        keyboardButton.setRequestContact(true);
        row.add(keyboardButton);
        row.add(new KeyboardButton(BotConstants.BACK_BUTTON));

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;

    }

    public static ReplyKeyboard getBackButton() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(BotConstants.BACK_BUTTON));
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }


    public static ReplyKeyboard getUserProfileButton() {
        KeyboardRow row = new KeyboardRow();

        row.add(new KeyboardButton(BotConstants.CREATE_NEW_ANN));
        row.add(new KeyboardButton(BotConstants.MAIN_MENU));

        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        return keyboardMarkup;
    }

}
