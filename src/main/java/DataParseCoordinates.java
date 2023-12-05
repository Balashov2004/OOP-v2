import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

interface CoordinatesParserInterface {
    String[] getCoordinates(String city) throws IOException;
}
public class DataParseCoordinates implements CoordinatesParserInterface{
    @Override
    public String[] getCoordinates(String city) throws IOException {
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
            return toJson(responseData);

        } else {
            System.out.println("Ошибка при получении данных. Код ответа: " + responseCode);
        }

        connection.disconnect();
        return null;
    }
    public static String[] toJson(String request) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(request).getAsJsonObject();
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");
        JsonObject firstElement  = resultsArray.get(0).getAsJsonObject();
        JsonObject geometryObject = firstElement.getAsJsonObject("geometry");
        String[] arrayCoordinate = {String.valueOf(geometryObject.get("lat")), String.valueOf(geometryObject.get("lng"))}; // Широта Долгота
        return arrayCoordinate;
    }
}