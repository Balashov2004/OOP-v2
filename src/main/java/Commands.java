import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.List;

public class Commands {
    private static int jokeCount = 0;
    public static String start(String request){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        switch (request) {
            case ("/weather"):
                return ("Пока в разработке");
            case ("/joke"):
                List<String> jokes = List.of();
                try {
                    String str = new String(Commands.class.getResourceAsStream("/joke.txt").readAllBytes(), StandardCharsets.UTF_8);
                    jokes = List.of(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //jokes = Reader.read("C:\\Users\\HP\\Desktop\\Java\\OOP-v2\\src\\joke.txt");
                if (jokeCount == 10){
                    jokeCount = 0;
                }
                return jokes.get(jokeCount++);
            case ("/wikipedia"):
                return("Пока в разработке");
            case ("/game"):
                return("В скорой разработке");
            case ("/exit"):
                return("До связи");
            default:
                return("Вы написали: " + request + "Напишите /help, чтобы узнать список команд");
        }
    }
}