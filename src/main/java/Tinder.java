import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tinder {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tinder?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    private static HashMap<String, Integer[]> users = new HashMap<>();

    public static String tinder(String chatID){
        List<Integer> userIds= getAllUserIdsFromMySQL();
        int sizeList = userIds.size();
        if (!users.containsKey(String.valueOf(chatID))) {
            Integer[] values = {0, 0};
            users.put(String.valueOf(chatID), values);
        }
        Integer[] arr = users.get(String.valueOf(chatID));
        arr[1] = sizeList;
        if (chatID.equals(String.valueOf(userIds.get(arr[0])))){
            arr[0] += 1;
        }
        Map<String, String> info1, info2;
        info1 = GetInfoUser.getUserInfoByID(Integer.valueOf(chatID));
        info2 = GetInfoUser.getUserInfoByID(userIds.get(arr[0]));
        arr[0] += 1;
        if (arr[1] - arr[0] == 0){
            arr[0] = 0;
        }
        users.put(chatID, arr);

        int coincide = coincidenceOfInterests(Integer.valueOf(info1.get("sport")), Integer.valueOf(info1.get("travel")),
                Integer.valueOf(info1.get("discos")), Integer.valueOf(info2.get("sport")),
                Integer.valueOf(info2.get("travel")), Integer.valueOf(info2.get("discos")));

        String answer = "login: " + info2.get("login") + "\n"
                + "Фамилия: " + info2.get("lastname") + "\n"
                + "Имя: " + info2.get("firstname") + "\n"
                + "Возраст: " + info2.get("age") + "\n"
                + "Gender: " + info2.get("gender") + "\n"
                + "Город проживания : " + info2.get("city") + "\n"
                + "Интересы совпадают на: " + coincide + "%" + "\n"
                + "Спорт: " + info2.get("sport") + "\n"
                + "Путешествия: " + info2.get("travel") + "\n"
                + "Клубы: " + info2.get("discos") + "\n"
                + info2.get("aboutMe");

        return answer;

    }

    public static List<Integer> getAllUserIdsFromMySQL() {
        List<Integer> userIds = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT idusers FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    userIds.add(rs.getInt("idusers"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userIds;
    }

    public static int coincidenceOfInterests(int a1, int b1, int c1, int a2, int b2, int c2){
        double a, b, c;
        if (a2 >= a1) {
            a = (double) a1 / a2;
        }
        else{
            a =  (double) a2 / a1;
        }
        if (b2 >= b1) {
            b =  (double) b1 / b2;
        }
        else{
            b = (double) b2 / b1;
        }
        if (c2 >= c1) {
            c = (double) c1 / c2;
        }
        else{
            c = (double) c2 / c1;
        }
        return ((int) (((a + b + c) / 3) * 100));
    }

}
