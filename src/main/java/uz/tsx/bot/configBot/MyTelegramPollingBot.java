package uz.tsx.bot.configBot;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.tsx.bot.handler.impl.UpdateHandler;


@Component
public class MyTelegramPollingBot extends TelegramLongPollingBot {

    String BOT_TOKEN ="7022773757:AAGkwgMhmTZzJcClTIsl3HaT5srh0BWxQLM";
    String BOT_USERNAME ="t.me/Tez_Sotish_Xizmatibot";

    @Resource
    private UpdateHandler updateHandler;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
     updateHandler.handleMessage(update);
    }



}
