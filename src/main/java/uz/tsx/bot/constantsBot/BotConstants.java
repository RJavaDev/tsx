package uz.tsx.bot.constantsBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BotConstants {
   String SHARE_CONTACT = "Share contact";

   HashMap<String, String> USER_PHONE_NUMBER = new HashMap<>();
   HashMap<String, Integer> USER_SELECTED_PAGE = new HashMap<>();

   List<String> admins = new ArrayList<>(List.of("1359515957", "1231810507"));
}
