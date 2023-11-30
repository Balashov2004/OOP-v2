import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParseCity {
        public static String parser() throws IOException {
            String url = "https://en.wikipedia.org/wiki/List_of_cities_and_towns_in_Russia";

            StringBuilder sb = new StringBuilder();
            try {
                URL website = new URL(url);
                URLConnection connection = website.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                Pattern pattern = Pattern.compile("<td><a href=\"/wiki/[^>]+>([^<]+)</a></td>");
                while (sb.length() <= 2500) {
                    line = reader.readLine();
                            Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String city = matcher.group(1);
                        sb.append('/');
                        sb.append(city);
                        sb.append(", ");
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
}