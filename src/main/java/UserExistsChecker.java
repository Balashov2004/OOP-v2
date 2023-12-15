import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserExistsChecker {
    public static Boolean checkUserExistence(String chatID){
        String url = "jdbc:mysql://localhost:3306/tinder?serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL-запрос для проверки наличия пользователя
            String sql = "SELECT * FROM users WHERE idusers = ?";

            // Создание объекта PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);

            // Установка значения параметра в запросе (например, имя пользователя)
            statement.setString(1, chatID);

            // Выполнение запроса
            ResultSet resultSet = statement.executeQuery();

            // Проверка наличия результатов
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                connection.close();
                return true;
                // Дополнительные действия при нахождении пользователя
            } else {
                resultSet.close();
                statement.close();
                connection.close();
                return false;
                // Дополнительные действия при отсутствии пользователя
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
