import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataParsingWeather {
    public static String getter(String lat, String lng) throws IOException {

        String urlString = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lng + "&lang=ru_RU" + "$limit=3";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        String key = null;
        try {
            key = new String(Commands.class.getResourceAsStream("/keyYandex.txt").readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        connection.setRequestProperty("X-Yandex-API-Key", key);


        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            String responseData = response.toString();
            //System.out.println("Полученные данные: " + responseData);
            return toJson(responseData);

        } else {
            System.out.println("Ошибка при получении данных. Код ответа: " + responseCode);
        }

        connection.disconnect();

        return null;
    }
    public static String toJson(String request){
        //System.out.println(request);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(request).getAsJsonObject();
        JsonArray forecastsArray = jsonObject.getAsJsonArray("forecasts");
        JsonObject firstDay = forecastsArray.get(0).getAsJsonObject().getAsJsonObject("parts").getAsJsonObject("day_short");
        JsonObject secondDay = forecastsArray.get(1).getAsJsonObject().getAsJsonObject("parts").getAsJsonObject("day_short");
        JsonObject thirdDay = forecastsArray.get(2).getAsJsonObject().getAsJsonObject("parts").getAsJsonObject("day_short");
        String forecasts;
        forecasts = forecastsArray.get(0).getAsJsonObject().get("date") + " " + firstDay.get("temp") + " condition " + firstDay.get("condition") + "\n" +
                forecastsArray.get(1).getAsJsonObject().get("date") + " " + secondDay.get("temp") + " condition " + secondDay.get("condition") + "\n" +
                forecastsArray.get(2).getAsJsonObject().get("date") + " " + thirdDay.get("temp") + " condition " + thirdDay.get("condition");
        //System.out.println(forecasts);
        return forecasts;
    }
}

