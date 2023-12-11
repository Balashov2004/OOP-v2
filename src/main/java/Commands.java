import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class Commands {
    static Update update = new Update();
    static Message message = update.getMessage();
    static SendMessage sendMessage = new SendMessage();
    private static int jokeCount = 0;
    static Map<String, Integer> chatIDJokeCount = new HashMap<String, Integer>();
    private static Map<String, Boolean> isWaitingForCity = new HashMap<>();
    private static Map<String, String> cityMap = new HashMap<>();

    public static String start(String request, String chatID) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (!chatIDJokeCount.containsKey(chatID)) {
            chatIDJokeCount.put(chatID, jokeCount);
        } else {
            chatIDJokeCount.computeIfPresent(chatID, (k, v) -> v + 1);
            jokeCount = chatIDJokeCount.get(chatID);
        }
        switch (request) {
            case ("/weather"):
                String spisok = DataParseCity.parser().toString();
                //System.out.println(spisok);
                if (!isWaitingForCity.containsKey(chatID) || !isWaitingForCity.get(chatID)) {
                    isWaitingForCity.put(chatID, true);
                    return "Введите город для получения погоды на Англйском языке или выберите из списка:\n" + spisok;
                } else {
                    isWaitingForCity.put(chatID, false);
                    return getWeather(cityMap.get(chatID));
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
                if (isWaitingForCity.containsKey(chatID) && isWaitingForCity.get(chatID)) {
                    cityMap.put(chatID, request);
                    isWaitingForCity.put(chatID, false);
                    return getWeather(request);
                } else {
                    return ("Вы написали: " + request + "Напишите /help, чтобы узнать список команд");
                }
        }
    }

    private static String getWeather(String city) throws IOException {
        CoordinatesParserInterface сoordinatesParser = new DataParseCoordinates();
        //String[] arrayCoordinate = сoordinatesParser.getCoordinates(city);
        WeatherParserInterface weatherParser = new DataParseWeather();
        CityWeatherService cityWeatherService = new CityWeatherService(сoordinatesParser, weatherParser);
        //return weatherParser.getWeather(arrayCoordinate[0], arrayCoordinate[1]);
        return cityWeatherService.getCityWeather(city);
    }
}