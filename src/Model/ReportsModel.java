package Model;
import Database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ReportsModel {

            public List<Object[]> getFullReport(int month, int year) {
            List<Object[]> data = new ArrayList<>();



            String sql = "SELECT " +
                    "    b.booking_id, " +
                    "    g.guest_id, " +
                    "    CONCAT(g.first_name, ' ', g.last_name) AS full_name, " +
                    "    b.check_in, " +
                    "    b.check_out, " +
                    "    p.card_type " +
                    "FROM room_booking b " +
                    "INNER JOIN guests g ON b.guest_id = g.guest_id " +
                    "LEFT JOIN payments p ON g.guest_id = p.guest_id " +
                    "WHERE MONTH(b.check_in) = ? AND YEAR(b.check_in) = ?";

            try (Connection conn = DataBaseConnection.getDatabaseConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, month);
                stmt.setInt(2, year);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Object[] row = {
                                rs.getInt("booking_id"),
                                rs.getInt("guest_id"),
                                rs.getString("full_name"),
                                rs.getDate("check_in"),
                                rs.getDate("check_out"),
                                rs.getString("card_type")
                        };
                        data.add(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

