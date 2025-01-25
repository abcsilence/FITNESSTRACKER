package FITNESSTRACKER;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerManager {
    public static class TrainerInfo {
        public String fullname;
        public String username;
        public String specialization;
        public int experience;
    }

    public List<TrainerInfo> getTrainers() {
        List<TrainerInfo> trainers = new ArrayList<>();
        String query = """
            SELECT u.fullname, u.username, t.specialization, t.experience 
            FROM users u 
            JOIN trainer t ON u.user_id = t.user_id 
            WHERE u.Is_trainer = true
        """;
        
        try (Connection conn = new DatabaseHandler().getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection");
                return trainers;
            }
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    TrainerInfo trainer = new TrainerInfo();
                    trainer.fullname = rs.getString("fullname");
                    trainer.username = rs.getString("username");
                    trainer.specialization = rs.getString("specialization");
                    trainer.experience = rs.getInt("experience");
                    trainers.add(trainer);
                    System.out.println("Loaded trainer: " + trainer.fullname);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("Total trainers loaded: " + trainers.size());
        return trainers;
    }
}