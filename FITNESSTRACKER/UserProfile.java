package FITNESSTRACKER;

import java.sql.*;

public class UserProfile {
    private int userId;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private boolean isTrainer;

    public UserProfile(String username) {
        fetchUserDetails(username);
    }

    private void fetchUserDetails(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                this.userId = rs.getInt("user_id");
                this.fullname = rs.getString("fullname");
                this.username = rs.getString("username");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                this.isTrainer = rs.getBoolean("Is_trainer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateProfile(String fullname, String email, String password) {
        String query = "UPDATE users SET fullname = ?, email = ?, password = ? WHERE user_id = ?";
        
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, this.userId);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                this.fullname = fullname;
                this.email = email;
                this.password = password;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Getters
    public String getFullname() { return fullname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isTrainer() { return isTrainer; }
}