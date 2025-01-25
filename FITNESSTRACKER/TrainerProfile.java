package FITNESSTRACKER;

import java.sql.*;

public class TrainerProfile {
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String specialization;
    private int experience;
    private int userId;

    public TrainerProfile(String username) {
        this.username = username;
        fetchTrainerDetails();
    }

    private void fetchTrainerDetails() {
        String query = """
            SELECT u.*, t.specialization, t.experience 
            FROM users u 
            JOIN trainer t ON u.user_id = t.user_id 
            WHERE u.username = ?
        """;
        
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                this.userId = rs.getInt("user_id");
                this.fullname = rs.getString("fullname");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                this.specialization = rs.getString("specialization");
                this.experience = rs.getInt("experience");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateProfile(String fullname, String email, String password, 
                               String specialization, int experience) {
        try (Connection conn = new DatabaseHandler().getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Update users table
                String userQuery = """
                    UPDATE users 
                    SET fullname = ?, email = ?, password = ? 
                    WHERE user_id = ?
                """;
                try (PreparedStatement pstmt = conn.prepareStatement(userQuery)) {
                    pstmt.setString(1, fullname);
                    pstmt.setString(2, email);
                    pstmt.setString(3, password);
                    pstmt.setInt(4, userId);
                    pstmt.executeUpdate();
                }

                // Update trainer table
                String trainerQuery = """
                    UPDATE trainer 
                    SET specialization = ?, experience = ? 
                    WHERE user_id = ?
                """;
                try (PreparedStatement pstmt = conn.prepareStatement(trainerQuery)) {
                    pstmt.setString(1, specialization);
                    pstmt.setInt(2, experience);
                    pstmt.setInt(3, userId);
                    pstmt.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Getters
    public String getFullname() { return fullname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }
}