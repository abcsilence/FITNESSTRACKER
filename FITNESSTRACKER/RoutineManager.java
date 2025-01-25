package FITNESSTRACKER;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoutineManager {
    public static class RoutineInfo {
        public String day;
        public String workout;
        public String caloriesBurned;
        public int userId;
    }

    public boolean saveRoutine(RoutineInfo routine) {
        // Check if routine exists for this day
        String checkQuery = "SELECT COUNT(*) FROM routine WHERE user_id = ? AND day = ?";
        String updateQuery = "UPDATE routine SET workout = ?, calories_burned = ? WHERE user_id = ? AND day = ?";
        String insertQuery = "INSERT INTO routine (user_id, day, workout, calories_burned) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = new DatabaseHandler().getConnection()) {
            // Check existing routine
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, routine.userId);
                checkStmt.setString(2, routine.day);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next() && rs.getInt(1) > 0) {
                    // Update existing routine
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, routine.workout);
                        updateStmt.setString(2, routine.caloriesBurned);
                        updateStmt.setInt(3, routine.userId);
                        updateStmt.setString(4, routine.day);
                        return updateStmt.executeUpdate() > 0;
                    }
                } else {
                    // Insert new routine
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, routine.userId);
                        insertStmt.setString(2, routine.day);
                        insertStmt.setString(3, routine.workout);
                        insertStmt.setString(4, routine.caloriesBurned);
                        return insertStmt.executeUpdate() > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoutine(RoutineInfo routine) {
        String query = "UPDATE routine SET workout = ?, calories_burned = ? WHERE user_id = ? AND day = ?";
        
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, routine.workout);
            pstmt.setString(2, routine.caloriesBurned);
            pstmt.setInt(3, routine.userId);
            pstmt.setString(4, routine.day);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<RoutineInfo> getRoutines(int userId) {
        List<RoutineInfo> routines = new ArrayList<>();
        String query = "SELECT * FROM routine WHERE user_id = ?";
    
        try (Connection conn = new DatabaseHandler().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                RoutineInfo routine = new RoutineInfo();
                routine.userId = rs.getInt("user_id");
                routine.day = rs.getString("day");
                routine.workout = rs.getString("workout");
                routine.caloriesBurned = rs.getString("calories_burned");
                routines.add(routine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routines;
    }
}