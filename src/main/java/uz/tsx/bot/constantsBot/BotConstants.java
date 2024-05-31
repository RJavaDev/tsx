package uz.tsx.bot.constantsBot;

import uz.tsx.bot.enums.StateEnum;
import uz.tsx.entity.announcement.AnnouncementEntity;

import java.util.HashMap;

public interface BotConstants {
   String START = "/start";
   String SETTINGS="Sozlamalar \uD83D\uDEE0";
   String MENU="E'lonlar \uD83D\uDCE2";
   String MYANNS="Mening e'lonlarim \uD83D\uDCDC";

   String SHARE_CONTACT = "Share contact";
   String CREATE_NEW_ANN = "E'lon qo'shish";

   HashMap<String, StateEnum> USER_STATE = new HashMap<>();

   HashMap<String, AnnouncementEntity> ANN = new HashMap<>();

   HashMap<String, String> USER_PHONE_NUMBER = new HashMap<>();

}
