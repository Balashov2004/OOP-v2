import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class DataParsingCountry {
    public static String getter() throws IOException {
        String key = null;
        String urlString = "http://htmlweb.ru/geo/api.php?locations&json&api_key=API_KEY_из_профиля;
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
        return "0";
    }
}
