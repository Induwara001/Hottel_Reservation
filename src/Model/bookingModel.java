package Model;

import Database.DataBaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookingModel {
    private String firstName, lastName, phone, email, address, city, nationality, nic;
    private String roomType, roomCapacity, checkInDate, checkOutDate, roomNo;
    private String cardType, cardNumber, cvc;

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public String getRoomCapacity() { return roomCapacity; }
    public void setRoomCapacity(String roomCapacity) { this.roomCapacity = roomCapacity; }
    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }
    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCvc() { return cvc; }
    public void setCvc(String cvc) { this.cvc = cvc; }

    public boolean saveReservation() {
        String guestSql = "INSERT INTO guests (first_name, last_name, phone, email, nic, address, city, nationality) VALUES (?,?,?,?,?,?,?,?)";
        String bookingSql = "INSERT INTO room_bookings (guest_id, room_number, room_type, room_capacity, check_in, check_out) VALUES (?,?,?,?,?,?)";

        Connection conn = null;
        try {
            conn = DataBaseConnection.getDatabaseConnection();
            conn.setAutoCommit(false);

            int genId = -1;
            try (PreparedStatement ps = conn.prepareStatement(guestSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, firstName); ps.setString(2, lastName); ps.setString(3, phone);
                ps.setString(4, email); ps.setString(5, nic); ps.setString(6, address);
                ps.setString(7, city); ps.setString(8, nationality);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) genId = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(bookingSql)) {
                ps.setInt(1, genId); ps.setString(2, roomNo); ps.setString(3, roomType);
                ps.setString(4, roomCapacity); ps.setString(5, checkInDate); ps.setString(6, checkOutDate);
                ps.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try { if(conn!=null) conn.rollback(); } catch(Exception ex){}
            return false;
        }
    }

    // අලුත් Method: Type/Capacity අනුව Available Rooms සෙවීම
    public static List<String> getAvailableRooms(String type, String cap) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT room_number FROM rooms WHERE type=? AND capacity=? AND status='Available'";
        try (Connection conn = DataBaseConnection.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type); ps.setString(2, cap);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(rs.getString("room_number"));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // අලුත් Method: Room Number එකෙන් සර්ච් කිරීම
    public static String[] findRoomByNumber(String rNo) {
        String sql = "SELECT type, capacity FROM rooms WHERE room_number=?";
        try (Connection conn = DataBaseConnection.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new String[]{rs.getString("type"), rs.getString("capacity")};
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}