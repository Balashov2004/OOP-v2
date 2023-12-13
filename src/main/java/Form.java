import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Scanner;

public class Form {
    public static HashMap<String, String> form(String chatID, HashMap<String, String> profile){
        Scanner scanner = new Scanner(System.in);
        profile.put("idUser", chatID);
        Bot bot = new Bot();
        bot.ptintToTg(Long.valueOf(chatID),"Введите ваш login tg");
//        System.out.println("Введите ваш login tg");
        profile.put("login", scanner.nextLine());

        bot.ptintToTg(Long.valueOf(chatID),"Введите ваш firstname");
        profile.put("firstname", scanner.nextLine());

        System.out.println("Введите ваш lastname");
        profile.put("lastname", scanner.nextLine());

        System.out.println("Введите ваш city");
        profile.put("city", scanner.nextLine());

        System.out.println("Введите ваш age");
        profile.put("age", scanner.nextLine());

        System.out.println("Введите ваш gender");
        profile.put("gender", scanner.nextLine());

        System.out.println("Введите ваш sport");
        profile.put("sport", scanner.nextLine());

        System.out.println("Введите ваш travel");
        profile.put("travel", scanner.nextLine());

        System.out.println("Введите ваш discos");
        profile.put("discos", scanner.nextLine());

        System.out.println("Введите ваш aboutMe");
        profile.put("aboutMe", scanner.nextLine());

        return profile;
    }
}
