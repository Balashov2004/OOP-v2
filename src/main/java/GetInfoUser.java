import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GetInfoUser {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tinder?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    public static Map<String, String> getUserInfoByID(int userID) {
        Map<String, String> userInfo = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE idusers = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        userInfo.put("login", rs.getString("login"));
                        userInfo.put("firstname", rs.getString("firstname"));
                        userInfo.put("lastname", rs.getString("lastname"));
                        userInfo.put("city", rs.getString("city"));
                        userInfo.put("age", rs.getString("age"));
                        userInfo.put("gender", rs.getString("gender"));
                        userInfo.put("sport", rs.getString("sport"));
                        userInfo.put("travel", rs.getString("travel"));
                        userInfo.put("discos", rs.getString("discos"));
                        userInfo.put("aboutMe", rs.getString("aboutMe"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
