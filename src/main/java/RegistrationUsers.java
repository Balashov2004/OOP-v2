import java.sql.PreparedStatement;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegistrationUsers{

    public static void signUpUser(HashMap<String, String> profile){
        String url = "jdbc:mysql://localhost:3306/tinder?serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_ID + "," + Const.USER_LOGIN + "," + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_CITY
                + "," + Const.USER_AGE + "," + Const.USER_GENDER + "," + Const.USER_SPORT + "," + Const.USER_TRAVEL
                + "," + Const.USER_DISCOS + "," + Const.USER_ABOUTME + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, profile.get("idUser"));
            statement.setString(2, profile.get("login"));
            statement.setString(3, profile.get("firstname"));
            statement.setString(4, profile.get("lastname"));
            statement.setString(5, profile.get("city"));
            statement.setInt(6, Integer.parseInt(profile.get("age")));
            statement.setString(7, profile.get("gender"));
            statement.setInt(8, Integer.parseInt(profile.get("sport")));
            statement.setInt(9, Integer.parseInt(profile.get("travel")));
            statement.setInt(10, Integer.parseInt(profile.get("discos")));
            statement.setString(11, profile.get("aboutMe"));

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Пользователь добавлен в базу данных.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

