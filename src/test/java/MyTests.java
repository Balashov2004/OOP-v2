import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MyTests {

    public static String read(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        line = reader.readLine();
        return line;
    }

    Commands commands = new Commands();

    @Test
    public void testDefault() throws IOException {
        String request = "TyTy paravozik";
        Assert.assertEquals("Вы написали: " + request + "Напишите /help, чтобы узнать список команд", commands.start(request, "777"));
    }

    @Test
    public void testJoke() throws IOException {
        List<String> jokes = Reader.read("./joke.txt");
        String request = "/joke";
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(jokes.get(i), commands.start(request, "777"));
        }
    }

    @Test
    public void testPasingWeather() throws IOException {
        String request = read("./src/main/resources/testYandexAPI");
        String answer = "\"2023-12-05\" -5 condition \"light-snow\"\n" +
                "\"2023-12-06\" -6 condition \"light-snow\"\n" +
                "\"2023-12-07\" -8 condition \"light-snow\"";
        Assert.assertEquals(DataParseWeather.toJson(request), answer);
    }

    @Test
    public void testWeather() throws IOException {
        String request = "/weather";
        String cities = read("./src/main/resources/testWeather");
        String answer = "Введите город для получения погоды на Англйском языке или выберите из списка:\n" + cities;
        Assert.assertEquals(answer, commands.start(request, "777"));
    }

    private CoordinatesParserInterface coordinatesParser;

    @BeforeEach
    public void setup() {
        coordinatesParser = new DataParseCoordinates();
        weatherParser = new DataParseWeather();
    }

    @Test
    public void testGetCoordinates() throws IOException {
        String city = "Лондон";
        String[] expectedCoordinates = {"51.5074", "-0.1278"};

        CoordinatesParserInterface mockedCoordinatesParser = Mockito.mock(CoordinatesParserInterface.class);
        Mockito.when(mockedCoordinatesParser.getCoordinates(city)).thenReturn(expectedCoordinates);

        String[] actualCoordinates = mockedCoordinatesParser.getCoordinates(city);
        Assertions.assertArrayEquals(expectedCoordinates, actualCoordinates);
    }

    private WeatherParserInterface weatherParser;


    @Test
    public void testGetWeather() throws IOException {
        String lat = "51.5074";
        String lng = "-0.1278";
        String expectedWeather = "Some expected weather data";

        WeatherParserInterface mockedWeatherParser = Mockito.mock(WeatherParserInterface.class);
        Mockito.when(mockedWeatherParser.getWeather(lat, lng)).thenReturn(expectedWeather);

        String actualWeather = mockedWeatherParser.getWeather(lat, lng);

        Assertions.assertEquals(expectedWeather, actualWeather);
    }
}
