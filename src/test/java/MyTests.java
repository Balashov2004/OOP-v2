import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyTests {
//    String[] listCommand = {"/help: список команд", "/exit: завершить работу", "/weather: погода", "/joke: анекдоты", "/wikipedia: википедия", "/game: blackjacke"};
//    @Test
//    public void testExit(){
//        Bot bot = new Bot();
//        Assert.assertEquals("До связи", bot.start("/exit", listCommand));
//    }
    Commands commands = new Commands();
    @Test
    public void testDefault(){
        String request = "TyTy paravozik";
        Assert.assertEquals("Вы написали: " + request + "Напишите /help, чтобы узнать список команд", commands.start(request));
    }
    @Test
    public  void testJoke(){
        List<String> jokes = Reader.read("./joke.txt");
        String request = "/joke";
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(jokes.get(i), commands.start(request));
        }
    }
//    @Test
//    public void testHelp(){
//        Bot bot = new Bot();
//        String commandString = String.join("\n", listCommand);
//        Assert.assertEquals("Список команд + пояснение\n" + commandString + "\n", bot.start("/help", listCommand));
//    }
}