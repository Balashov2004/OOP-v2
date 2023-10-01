import org.junit.Assert;
import org.junit.Test;

public class MyTests {
    String[] listCommand = {"/help: список команд", "/exit: завершить работу", "/weather: погода", "/joke: анекдоты", "/wikipedia: википедия", "/game: blackjacke"};
    @Test
    public void testExit(){
        Bot bot = new Bot();
        Assert.assertEquals("До связи", bot.start("/exit", listCommand));
    }
    @Test
    public void testDefault(){
        Bot bot = new Bot();
        String request = "TyTy paravozik";
        Assert.assertEquals("Вы написали: " + request + "Напишите /help, чтобы узнать список команд", bot.start(request, listCommand));
    }
    @Test
    public void testHelp(){
        Bot bot = new Bot();
        String commandString = String.join("\n", listCommand);
        Assert.assertEquals("Список команд + пояснение\n" + commandString + "\n", bot.start("/help", listCommand));
    }
}