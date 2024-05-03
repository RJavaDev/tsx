package uz.tsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.tsx.telegiram_bot.TsxBot;

@SpringBootApplication
public class TsxApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(TsxApplication.class, args);
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TsxBot());
    }

}
