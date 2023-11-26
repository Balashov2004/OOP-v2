import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataParsingCoordinates {
    public static String getter(String city) throws IOException {
        String key = null;
        try {
            key = new String(Commands.class.getResourceAsStream("/keyCity.txt").readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String urlString = "https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=" + key;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

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
    public static String toJson(String request) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(request).getAsJsonObject();
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");
        JsonObject firstElement  = resultsArray.get(0).getAsJsonObject();
        JsonObject geometryObject = firstElement.getAsJsonObject("geometry");
        String lat = String.valueOf(geometryObject.get("lat")); // Широта
        String lng = String.valueOf(geometryObject.get("lng")); // Долгота
        return DataParsingWeather.getter(lat, lng);
    }
}
