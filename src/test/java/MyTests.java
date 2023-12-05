import org.junit.Assert;
import org.junit.Test;

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
    public void testPasingWeather() throws IOException{
        String request = read("./src/main/resources/testYandexAPI");
        String answer = "\"2023-12-05\" -5 condition \"light-snow\"\n" +
                "\"2023-12-06\" -6 condition \"light-snow\"\n" +
                "\"2023-12-07\" -8 condition \"light-snow\"";
        Assert.assertEquals(DataParseWeather.toJson(request), answer);
    }
    @Test
    public void testWeather() throws IOException{
        String request = "/weather";
        String cities = read("./src/main/resources/testWeather");
        String answer = "Введите город для получения погоды на Англйском языке или выберите из списка:\n" + cities;
        Assert.assertEquals(answer, commands.start(request, "777"));
    }
}