package FITNESSTRACKER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
public class UserDashboard extends JPanel {
    private JPanel contentPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String username;

    public UserDashboard(String username) {
        this.username = username;
        
        // Main container with BorderLayout
        setLayout(new BorderLayout());
        setBackground(new Color(50, 50, 50));

        // Sidebar on the left
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setBackground(new Color(50, 50, 50)); // Darker background color
        sidebar.setPreferredSize(new Dimension(250, 600)); // Set fixed width

        // User info panel at the top of the sidebar
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.setBackground(new Color(50, 50, 50)); // Match the background color of the sidebar
        userInfoPanel.setPreferredSize(new Dimension(250, 100)); // Set the height of the user info panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20)); // Add margin at the top

        // Profile picture
        JLabel profilePicLabel = new JLabel();
        profilePicLabel.setIcon(new ImageIcon(new ImageIcon("/Users/admin/Desktop/FITNESSTRACKER/FITNESSTRACKER/user.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        profilePicLabel.setPreferredSize(new Dimension(60, 60));
        userInfoPanel.add(profilePicLabel, BorderLayout.WEST);

        // Spacer region
        JPanel spacer = new JPanel();
        spacer.setBackground(new Color(50, 50, 50)); // Match the background color
        userInfoPanel.add(spacer, BorderLayout.CENTER);

        // User details
        JPanel userDetailsPanel = new JPanel();
        userDetailsPanel.setLayout(new BoxLayout(userDetailsPanel, BoxLayout.Y_AXIS));
        userDetailsPanel.setBackground(new Color(50, 50, 50)); // Match the background color

        JLabel nameLabel = new JLabel(username);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userDetailsPanel.add(nameLabel);

        JLabel statusLabel = new JLabel("User"); // Change to "Trainer" if applicable
        statusLabel.setForeground(Color.LIGHT_GRAY);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userDetailsPanel.add(statusLabel);

        userInfoPanel.add(userDetailsPanel, BorderLayout.EAST);

        sidebar.add(userInfoPanel, BorderLayout.NORTH);

        // Sidebar buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 5));
        buttonPanel.setBackground(new Color(50, 50, 50)); // Darker background color

        JButton homeButton = createSidebarButton("Home");
        JButton profileButton = createSidebarButton("Profile");
        JButton routineButton = createSidebarButton("Routine");
        JButton trainerButton = createSidebarButton("Trainer");

        buttonPanel.add(homeButton);
        buttonPanel.add(routineButton);
        buttonPanel.add(trainerButton);
        buttonPanel.add(profileButton);

        sidebar.add(buttonPanel, BorderLayout.CENTER);

        // Adding sidebar to the left
        add(sidebar, BorderLayout.WEST);

        // Right content panel with CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Add different pages to the card panel
        cardPanel.add(createHomePage(), "Home");
        cardPanel.add(createRoutinePage(), "Routine");
        cardPanel.add(createTrainerPage(), "Trainer");
        cardPanel.add(createProfilePage(), "Profile");

        // Adding card panel to the center
        add(cardPanel, BorderLayout.CENTER);

        // Set initial selected button and show home page
        setSelectedButton(homeButton);
        cardLayout.show(cardPanel, "Home");

        // Add action listeners to buttons
        homeButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Home");
            setSelectedButton(homeButton);
        });
        profileButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Profile");
            setSelectedButton(profileButton);
        });
        routineButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Routine");
            setSelectedButton(routineButton);
        });
        trainerButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Trainer");
            setSelectedButton(trainerButton);
        });
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(new Color(100, 100, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void setSelectedButton(JButton selectedButton) {
        Component[] components = selectedButton.getParent().getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button == selectedButton) {
                    button.setBackground(new Color(70, 130, 180));
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(new Color(100, 100, 100));
                    button.setForeground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    private JPanel createHomePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome, " + username + "! This is the Home page.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createProfilePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fetch user details
        UserProfile userProfile = new UserProfile(username);
        
        // Create form fields
        JTextField fullnameField = new JTextField(userProfile.getFullname(), 20);
        JTextField usernameField = new JTextField(userProfile.getUsername(), 20);
        JTextField emailField = new JTextField(userProfile.getEmail(), 20);
        JPasswordField passwordField = new JPasswordField(userProfile.getPassword(), 20);
        
        // Make username field read-only
        usernameField.setEditable(false);
        
        // Add components with labels
        addFormField(panel, "Full Name:", fullnameField, gbc, 0);
        addFormField(panel, "Username:", usernameField, gbc, 1);
        addFormField(panel, "Email:", emailField, gbc, 2);
        addFormField(panel, "Password:", passwordField, gbc, 3);
        
        // Save button
        JButton saveButton = new JButton("Save Changes");
        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(saveButton, gbc);
        
        saveButton.addActionListener(e -> {
            String newFullname = fullnameField.getText().trim();
            String newEmail = emailField.getText().trim();
            String newPassword = new String(passwordField.getPassword()).trim();
            
            if (userProfile.updateProfile(newFullname, newEmail, newPassword)) {
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update profile!");
            }
        });
        
        return panel;
    }
    
    private void addFormField(JPanel panel, String labelText, JTextField field, 
                             GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JPanel createRoutinePage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));
    
        // Create header panel with refresh button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(50, 50, 50));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Your Routine", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton refreshButton = new JButton("⟳ Refresh");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refreshRoutinePage());
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(refreshButton, BorderLayout.EAST);
        panel.add(headerPanel, BorderLayout.NORTH);
    
        // Get selected trainer's routines
        int trainerId = getSelectedTrainerId(getUserId());
        
        if (trainerId == -1) {
            // No trainer selected
            JLabel noTrainerLabel = new JLabel("Please select a trainer first!", SwingConstants.CENTER);
            noTrainerLabel.setForeground(Color.WHITE);
            noTrainerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(noTrainerLabel, BorderLayout.CENTER);
            return panel;
        }
    
        // Create grid for routine display
        JPanel routinesGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        routinesGrid.setBackground(new Color(50, 50, 50));
        routinesGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Get trainer's routines
        List<RoutineManager.RoutineInfo> routines = getTrainerRoutines(trainerId);
        
        // Create a card for each day
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : days) {
            JPanel dayCard = createDayCard(day, routines);
            routinesGrid.add(dayCard);
        }
    
        JScrollPane scrollPane = new JScrollPane(routinesGrid);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(50, 50, 50));
        panel.add(scrollPane, BorderLayout.CENTER);
    
        return panel;
    }
    private void refreshRoutinePage() {
        // Remove existing routine page
        for (Component comp : cardPanel.getComponents()) {
            if ("Routine".equals(comp.getName())) {
                cardPanel.remove(comp);
                break;
            }
        }
        
        // Create and add new routine page
        JPanel routinePage = createRoutinePage();
        routinePage.setName("Routine");
        cardPanel.add(routinePage, "Routine");
        
        // Show updated page
        cardLayout.show(cardPanel, "Routine");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

private int getSelectedTrainerId(int userId) {
    String query = "SELECT trainer_id FROM user_trainer WHERE user_id = ?";
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("trainer_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}

private List<RoutineManager.RoutineInfo> getTrainerRoutines(int trainerId) {
    String query = """
        SELECT r.* 
        FROM routine r 
        JOIN trainer t ON r.user_id = t.user_id 
        WHERE t.trainer_id = ?
    """;
    
    List<RoutineManager.RoutineInfo> routines = new ArrayList<>();
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, trainerId);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            RoutineManager.RoutineInfo routine = new RoutineManager.RoutineInfo();
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

private JPanel createDayCard(String day, List<RoutineManager.RoutineInfo> routines) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(new Color(60, 60, 60));
    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Day header
    JLabel dayLabel = new JLabel(day);
    dayLabel.setForeground(Color.WHITE);
    dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
    dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(dayLabel);
    card.add(Box.createVerticalStrut(10));

    // Find routine for this day
    Optional<RoutineManager.RoutineInfo> dayRoutine = routines.stream()
        .filter(r -> r.day.equals(day))
        .findFirst();

    if (dayRoutine.isPresent()) {
        addLabel(card, "Workout: " + dayRoutine.get().workout, Font.PLAIN, 14);
        addLabel(card, "Calories: " + dayRoutine.get().caloriesBurned, Font.PLAIN, 14);
    } else {
        addLabel(card, "No routine set", Font.ITALIC, 14);
    }

    return card;
}

    private JPanel createTrainerPage() {
        System.out.println("Creating trainer page");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));
    
        // Header
        JLabel headerLabel = new JLabel("Available Trainers", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        panel.add(headerLabel, BorderLayout.NORTH);
    
        // Trainers Grid
        JPanel trainersGrid = new JPanel(new GridLayout(0, 2, 10, 10));
        trainersGrid.setBackground(new Color(50, 50, 50));
        trainersGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        TrainerManager trainerManager = new TrainerManager();
        List<TrainerManager.TrainerInfo> trainers = trainerManager.getTrainers();
        System.out.println("Retrieved trainers: " + trainers.size());
    
        if (trainers.isEmpty()) {
            System.out.println("No trainers found!");
            JLabel noTrainersLabel = new JLabel("No trainers available", SwingConstants.CENTER);
            noTrainersLabel.setForeground(Color.WHITE);
            panel.add(noTrainersLabel, BorderLayout.CENTER);
        } else {
            for (TrainerManager.TrainerInfo trainer : trainers) {
                System.out.println("Adding trainer card for: " + trainer.fullname);
                trainersGrid.add(createTrainerCard(trainer));
            }
            JScrollPane scrollPane = new JScrollPane(trainersGrid);
            scrollPane.setBorder(null);
            scrollPane.getViewport().setBackground(new Color(50, 50, 50));
            panel.add(scrollPane, BorderLayout.CENTER);
        }
    
        return panel;
    }
    
    private JPanel createTrainerCard(TrainerManager.TrainerInfo trainer) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(new Color(60, 60, 60));
    card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    addLabel(card, "Name: " + trainer.fullname, Font.BOLD, 16);
    addLabel(card, "Username: " + trainer.username, Font.PLAIN, 14);
    addLabel(card, "Specialization: " + trainer.specialization, Font.PLAIN, 14);
    addLabel(card, "Experience: " + trainer.experience + " years", Font.PLAIN, 14);

    JButton selectButton = new JButton("Select Trainer");
    selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    selectButton.setMaximumSize(new Dimension(150, 30));
    
    selectButton.addActionListener(e -> {
        int userId = getUserId();
        TrainerManager trainerManager = new TrainerManager();
        
        if (trainerManager.selectTrainer(userId, trainer.trainerId)) {
            JOptionPane.showMessageDialog(this, "Trainer selected successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to select trainer!");
        }
    });

    card.add(Box.createVerticalStrut(10));
    card.add(selectButton);

    return card;
}

private int getUserId() {
    String query = "SELECT user_id FROM users WHERE username = ?";
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("user_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}
    
    private void addLabel(JPanel panel, String text, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
    }
}