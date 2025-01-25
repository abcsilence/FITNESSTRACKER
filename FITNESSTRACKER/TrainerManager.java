package FITNESSTRACKER;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerManager {
    public static class TrainerInfo {
        public int trainerId;
        public String fullname;
        public String username;
        public String specialization;
        public int experience;
    }

    public List<TrainerInfo> getTrainers() {
        List<TrainerInfo> trainers = new ArrayList<>();
        String query = """
            SELECT u.fullname, u.username, t.trainer_id, t.specialization, t.experience
            FROM users u 
            JOIN trainer t ON u.user_id = t.user_id 
            WHERE u.Is_trainer = true
        """;
    
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TrainerInfo trainer = new TrainerInfo();
                trainer.trainerId = rs.getInt("trainer_id");
                trainer.fullname = rs.getString("fullname");
                trainer.username = rs.getString("username");
                trainer.specialization = rs.getString("specialization");
                trainer.experience = rs.getInt("experience");
                trainers.add(trainer);
                System.out.println("Loaded trainer: " + trainer.fullname + " with ID: " + trainer.trainerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }
    
    public boolean selectTrainer(int userId, int trainerId) {
        // First delete any existing trainer assignment
        String deleteQuery = "DELETE FROM user_trainer WHERE user_id = ?";
        String insertQuery = "INSERT INTO user_trainer (user_id, trainer_id) VALUES (?, ?)";
    
        try (Connection conn = new DatabaseHandler().getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Remove existing trainer
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                    deleteStmt.setInt(1, userId);
                    deleteStmt.executeUpdate();
                }
    
                // Assign new trainer
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, trainerId);
                    insertStmt.executeUpdate();
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
    public TrainerInfo getSelectedTrainer(int userId) {
        String query = """
            SELECT u.fullname, u.username, t.specialization, t.experience, t.trainer_id
            FROM users u 
            JOIN trainer t ON u.user_id = t.user_id
            JOIN user_trainer ut ON t.trainer_id = ut.trainer_id
            WHERE ut.user_id = ?
        """;
    
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                TrainerInfo trainer = new TrainerInfo();
                trainer.trainerId = rs.getInt("trainer_id");
                trainer.fullname = rs.getString("fullname");
                trainer.username = rs.getString("username");
                trainer.specialization = rs.getString("specialization");
                trainer.experience = rs.getInt("experience");
                return trainer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}