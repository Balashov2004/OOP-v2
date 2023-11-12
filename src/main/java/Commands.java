import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;


public class Commands {
    static Update update = new Update();
    static Message message = update.getMessage();
    static SendMessage sendMessage = new SendMessage();
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

                // НИЖЕ НУЖНО РЕАЛИЗОВАТЬ ЧТОБЫ ПОЛЬЗОВАТЕЛЬ ВВОДИЛ СНАЧАЛА СТРАНУ ПОТОМ ГОРОД ТО ЧТО НИЖЕ В КОММЕНТАРИИ НЕ РАБОТАЕТ

//                sendMessage.setText("Введите страну:");
//                sendMessage.setChatId(message.getChatId());
//                execute(String.valueOf(sendMessage));
//                if (message.hasText()) {
//                    String country = message.getText();
//
//                    sendMessage.setText("Введите город:");
//                    sendMessage.setChatId(message.getChatId());
//                    execute(String.valueOf(sendMessage));
//                }

                try {
                    return DataParsingCity.getter();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

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