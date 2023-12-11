import java.io.IOException;

public class CityWeatherService {
    private CoordinatesParserInterface coordinatesParser;
    private WeatherParserInterface weatherParser;

    public CityWeatherService(CoordinatesParserInterface coordinatesParser, WeatherParserInterface weatherParser) {
        this.coordinatesParser = coordinatesParser;
        this.weatherParser = weatherParser;
    }

    public String getCityWeather(String city) throws IOException {
        String[] coordinates = coordinatesParser.getCoordinates(city);
        String lat = coordinates[0];
        String lng = coordinates[1];
        return weatherParser.getWeather(lat, lng);
    }
}
