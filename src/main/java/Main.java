import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.ArrayList;

public class Main {

    public static void game(){
        int[] card_deck = IntStream.rangeClosed(0, 11).toArray();
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(arr));
//        Collections.shuffle(list);
//        Integer[] card_deck = list.toArray(new Integer[0]);
        for (int i = 0; i < card_deck.length; i++){
            System.out.println(card_deck[i]);
        }
        return;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Добро пожаловать, для получения списка команд введите /help");
        String[] list_command = {"/help: список команд", "/exit: завершить работу"};

        loop: while (true){
            String command = input.nextLine();
            switch (command){
                case ("/help"):
                    System.out.println("Список команд + пояснение");
                    for (int i = 0; i < list_command.length; i++){
                        System.out.println(list_command[i]);
                    }
                    break;
                case ("/weather"):
                    System.out.println("Пока в разработке");
                    break;
                case ("/joke"):
                    System.out.println("Пока в разработке");
                    break;
                case ("/wikipedia"):
                    System.out.println("Пока в разработке");
                    break;
                case ("/exit"):
                    System.out.println("До связи");
                    break loop;
                case ("/game"):
                    game();
                    break;
                default:
                    System.out.println("Вы написали: " + command);

            }
        }
    }

}