import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class Commands {
    private static int jokeCount = 0;
    static Map<String, Integer> chatIDJokeCount = new HashMap<String, Integer>();
    public static String start(String request, String chatID) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!chatIDJokeCount.containsKey(chatID)) {
            chatIDJokeCount.put(chatID, jokeCount);
        } else {
            chatIDJokeCount.computeIfPresent(chatID, (k, v) -> v + 1);
            jokeCount = chatIDJokeCount.get(chatID);
        }
        switch (request) {
            case ("/weather"):
                return ("Пока в разработке");
            case ("/joke"):
                List<String> jokes = Reader.read("/joke.txt");
                if (jokeCount == 10) {
                    chatIDJokeCount.computeIfPresent(chatID, (k, v) -> 0);
                    jokeCount = 0;
                }
                return jokes.get(jokeCount);
            case ("/wikipedia"):
                return ("Пока в разработке");
            case ("/game"):
                return ("В скорой разработке");
            case ("/exit"):
                return ("До связи");
            default:
                return ("Вы написали: " + request + "Напишите /help, чтобы узнать список команд");
        }
    }
}