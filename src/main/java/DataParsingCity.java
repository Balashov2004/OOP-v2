import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParsingCity {
    public static String parser() throws IOException {
        //System.out.println("this");
        String url = "https://en.wikipedia.org/wiki/List_of_cities_and_towns_in_Russia";
        String fileName = "./src/main/resources/output.txt";

        StringBuilder sb = null;
        try {
            // Скачивание HTML-страницы
            InputStream inputStream = new URL(url).openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer, 0, 1024)) >= 0) {
                fileOutputStream.write(buffer, 0, bytesRead);
                //System.out.println(buffer);
            }
            fileOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();

            // Чтение содержимого файла
            String htmlContent = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            List<String> cities = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("<td><a href=\"/wiki/[^>]+>([^<]+)</a></td>");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String city = matcher.group(1);
                        cities.add(city);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = new StringBuilder();
            for (int i = 0; i < 25; i++) {
                sb.append('/');
                sb.append(cities.get(i));
                sb.append(", ");
            }
            //System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}