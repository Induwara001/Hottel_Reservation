package Model;

import Database.DataBaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class checkoutModel {

    // 1. Room Number එකෙන් දැනට ඉන්න Guest සහ බිල සෙවීම
    public static String[] getActiveBookingDetails(String roomNo) {
        // මෙතනදී අපි guests සහ room_bookings table join කරනවා
        String sql = "SELECT g.first_name, g.last_name, b.check_in, b.check_out, r.price_per_night " +
                "FROM room_bookings b " +
                "JOIN guests g ON b.guest_id = g.guest_id " +
                "JOIN rooms r ON b.room_number = r.room_number " +
                "WHERE b.room_number = ? AND b.status = 'Active'";

        try (Connection conn = DataBaseConnection.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String checkIn = rs.getString("check_in");
                String checkOut = rs.getString("check_out");
                double price = rs.getDouble("price_per_night");

                // දින ගණන සහ මුළු මුදල ගණනය කිරීම
                long days = ChronoUnit.DAYS.between(LocalDate.parse(checkIn), LocalDate.parse(checkOut));
                if (days <= 0) days = 1;
                double total = days * price;

                return new String[]{name, checkIn, checkOut, String.valueOf(total)};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2. Checkout එක Confirm කර රූම් එක 'Available' කිරීම
    public static boolean performCheckout(String roomNo) {
        String updateBooking = "UPDATE room_bookings SET status = 'Completed' WHERE room_number = ? AND status = 'Active'";
        String updateRoom = "UPDATE rooms SET status = 'Available' WHERE room_number = ?";

        Connection conn = null;
        try {
            conn = DataBaseConnection.getDatabaseConnection();
            conn.setAutoCommit(false); // පියවර දෙකම එකවර වෙන්න ඕන නිසා

            try (PreparedStatement ps1 = conn.prepareStatement(updateBooking)) {
                ps1.setString(1, roomNo);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(updateRoom)) {
                ps2.setString(1, roomNo);
                ps2.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
            return false;
        }
    }
}