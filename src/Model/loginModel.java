package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Database.DataBaseConnection;

public class loginModel {

    public boolean checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT * FROM login WHERE user_name = ? AND password = ?";

        Connection connection = DataBaseConnection.getDatabaseConnection();


        if (connection == null) {
            throw new SQLException("Could not establish database connection.");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } finally {
            if (connection != null) connection.close();
        }
    }
}
