import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать, для получения списка команд введите /help");
        Scanner input = new Scanner(System.in);
        String[] listCommand = {"/help: список команд", "/exit: завершить работу", "/weather: погода", "/joke: анекдоты", "/wikipedia: википедия", "/game: blackjacke"};
        String answer = "";
        Bot bot = new Bot();
        while (!answer.equals("До связи")) {
            answer = bot.start(input.nextLine(), listCommand);
            System.out.println(answer);
        }
    }

}