import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.Random;
import java.util.List;

public class Commands {
    public static String start(String request){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int jokeCount = random.nextInt(10);
        switch (request) {
            case ("/weather"):
                return ("Пока в разработке");
            case ("/joke"):
                List<String> jokes = Reader.read("C:\\Users\\HP\\Desktop\\Java\\OOP-v2\\src\\joke.txt");
                return jokes.get(jokeCount);
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
