package uz.tsx.telegiram_bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TsxBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("jjjjjjjjjjj");
    }

    @Override
    public String getBotToken() {
        return "7022773757:AAGkwgMhmTZzJcClTIsl3HaT5srh0BWxQLM";
    }

    @Override
    public String getBotUsername() {
        return "t.me/Tez_Sotish_Xizmatibot";
    }
}
