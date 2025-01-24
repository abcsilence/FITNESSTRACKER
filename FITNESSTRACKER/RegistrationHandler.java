package FITNESSTRACKER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationHandler {
    public boolean registerUser(String fullname, String username, String emailAddress, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        try (Connection conn = dbHandler.getConnection()) {
            String query = "INSERT INTO users (fullname, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fullname);
            pstmt.setString(2, username);
            pstmt.setString(3, emailAddress);
            pstmt.setString(4, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}