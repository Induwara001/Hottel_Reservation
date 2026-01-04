package Model;

import Database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterModel {
    public boolean saveGuest(String name, String mobile) {
        String sql = "INSERT INTO guests (name, mobile) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, mobile);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

