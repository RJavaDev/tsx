package uz.tsx;

import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.tsx.bot.my_bot.MyTelegramPollingBotImpl;

@SpringBootApplication
public class TsxApplication {

    public static void main(String[] args){
        SpringApplication.run(TsxApplication.class, args);
    }
    @Resource
    MyTelegramPollingBotImpl myTelegramPollingBot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(myTelegramPollingBot);
        }
        catch (TelegramApiException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
