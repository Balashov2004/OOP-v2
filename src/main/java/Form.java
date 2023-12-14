import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Scanner;

public class Form {
    public static HashMap<String, String> form(String chatID, HashMap<String, String> profile, int request, String text){
        Bot bot = new Bot();
        switch (request) {
            case -1:
                bot.ptintToTg(Long.valueOf(chatID),"Введите ваш login tg");
                return profile;
            case 0:
                bot.ptintToTg(Long.valueOf(chatID),"Введите firstname");
                profile.put("login", text);
                return profile;
            case 1:
                bot.ptintToTg(Long.valueOf(chatID),"Введите lastname");
                profile.put("firstname", text);
                return profile;
            case 2:
                bot.ptintToTg(Long.valueOf(chatID),"Где проживаете");
                profile.put("lastname", text);
                return profile;
            case 3:
                bot.ptintToTg(Long.valueOf(chatID),"Введите ваш возраст");
                profile.put("city", text);
                return profile;
            case 4:
                bot.ptintToTg(Long.valueOf(chatID),"Введите ваш gender на английском (men/women)");
                profile.put("age", text);
                return profile;
            case 5:
                bot.ptintToTg(Long.valueOf(chatID),"Увлекаетись ли вы спортом от 0 до 10");
                profile.put("gender", text);
                return profile;
            case 6:
                bot.ptintToTg(Long.valueOf(chatID),"Нравятся ли вам путешествия от 0 до 10");
                profile.put("sport", text);
                return profile;
            case 7:
                bot.ptintToTg(Long.valueOf(chatID),"Люибите ходить в клубы от 0 до 10");
                profile.put("travel", text);
                return profile;
            case 8:
                bot.ptintToTg(Long.valueOf(chatID),"aboutMe");
                profile.put("discos", text);
                return profile;
            case 9:
                bot.ptintToTg(Long.valueOf(chatID),"Любой символ отправьте");
                profile.put("aboutMe", text);
                System.out.println(chatID);
                profile.put("idUser", chatID);
                return profile;
            default:
                return profile;
        }

    }
}