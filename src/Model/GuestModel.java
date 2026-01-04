package Model;

import Database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestModel {




        public List<Object[]> searchGuests(String nic) {
            List<Object[]> data = new ArrayList<>();


            String sql = "SELECT * FROM guests WHERE nic LIKE ? ";


            try (Connection conn = DataBaseConnection.getDatabaseConnection();
                 PreparedStatement statement = conn.prepareStatement(sql)) {


                statement.setString(1, "%" + nic + "%");


                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {

                        Object[] row = {
                                rs.getInt("guest_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getInt("phone"),
                                rs.getString("email"),
                                rs.getString("nic"),
                                rs.getString("address"),
                                rs.getString("city"),
                                rs.getString("nationality")
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

