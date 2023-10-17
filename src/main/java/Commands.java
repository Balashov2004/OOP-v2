import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Commands {
    public static String start(String request){
        StringBuilder stringBuilder = new StringBuilder();
        switch (request) {
            case ("/weather"):
                return ("Пока в разработке");
            case ("/joke"):
                return("Пока в разработке");
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
