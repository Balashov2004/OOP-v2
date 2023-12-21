import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserExistsChecker {
    public Boolean checkUserExistence(String chatID){
        String url = "jdbc:mysql://localhost:3306/tinder?serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM users WHERE idusers = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, chatID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                connection.close();
                return true;

            } else {
                resultSet.close();
                statement.close();
                connection.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
