package FITNESSTRACKER;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
public class TrainerDashboard extends JPanel {
    private JPanel contentPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String username;
    private int userId = -1; // Add class field


    public TrainerDashboard(String username) {
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

        JLabel statusLabel = new JLabel("Trainer"); // Change to "Trainer" if applicable
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

        buttonPanel.add(homeButton);
        buttonPanel.add(routineButton);
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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(50, 50, 50));
        
        // Welcome Panel with Refresh Button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(50, 50, 50));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        
        JButton refreshButton = new JButton("⟳ Refresh Stats");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refreshStatsPanel(panel));
        
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);
    
        // Stats Panel
        JPanel statsPanel = createStatsPanel();
        panel.add(statsPanel, BorderLayout.CENTER);
    
        return panel;
    }
    
    private JPanel createStatsPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
    
        // Stats Cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        statsPanel.setBackground(new Color(50, 50, 50));
        
        statsPanel.add(createStatsCard("Total Clients", String.valueOf(getTotalClients())));
        statsPanel.add(createStatsCard("Total Routines", String.valueOf(getTotalRoutines())));
    
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(statsPanel, gbc);
    
        // Clients Table
        JPanel clientsPanel = createClientsTable();
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        contentPanel.add(clientsPanel, gbc);
    
        return contentPanel;
    }
    
    private void refreshStatsPanel(JPanel homePanel) {
        // Remove current stats panel
        Component[] components = homePanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && comp != homePanel.getComponent(0)) {
                homePanel.remove(comp);
            }
        }
        
        // Add new stats panel
        homePanel.add(createStatsPanel(), BorderLayout.CENTER);
        homePanel.revalidate();
        homePanel.repaint();
    }

private JPanel createStatsCard(String title, String value) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(new Color(60, 60, 60)); // Changed to darker gray
    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JLabel titleLabel = new JLabel(title);
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel valueLabel = new JLabel(value);
    valueLabel.setForeground(Color.WHITE);
    valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
    valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    card.add(titleLabel);
    card.add(Box.createVerticalStrut(10));
    card.add(valueLabel);

    return card;
}

private JPanel createClientsTable() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(60, 60, 60));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel titleLabel = new JLabel("Your Clients");
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    panel.add(titleLabel, BorderLayout.NORTH);

    String[] columns = {"Client Name", "Routine Status", "Last Updated"};
    Object[][] data = getClientsData();
    
    JTable table = new JTable(data, columns);
    table.setBackground(new Color(60, 60, 60));
    table.setForeground(Color.WHITE);
    table.getTableHeader().setBackground(new Color(70, 130, 180));
    table.getTableHeader().setForeground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.getViewport().setBackground(new Color(60, 60, 60));
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}



private int getTotalClients() {
    String query = """
        SELECT COUNT(*) 
        FROM user_trainer ut
        WHERE ut.trainer_id = (SELECT trainer_id FROM trainer WHERE user_id = ?)
    """;
    
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        int userId = getUserId();
        System.out.println("Getting clients for user ID: " + userId); // Debug
        
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Found " + count + " clients"); // Debug
            return count;
        }
    } catch (SQLException e) {
        System.err.println("Error getting total clients: " + e.getMessage());
        e.printStackTrace();
    }
    return 0;
}

private int getTotalRoutines() {
    String query = "SELECT COUNT(DISTINCT day) FROM routine WHERE user_id = ?";
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, getUserId());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

private Object[][] getClientsData() {
    List<Object[]> clientsList = new ArrayList<>();
    String query = """
        SELECT u.fullname, u.username,
               CASE WHEN r.user_id IS NULL THEN 'No Routine' ELSE 'Has Routine' END as status
        FROM user_trainer ut
        JOIN users u ON ut.user_id = u.user_id
        LEFT JOIN routine r ON u.user_id = r.user_id
        WHERE ut.trainer_id = (SELECT trainer_id FROM trainer WHERE user_id = ?)
    """;

    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        int userId = getUserId();
        System.out.println("Getting clients data for user ID: " + userId); // Debug
        
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            String fullname = rs.getString("fullname");
            String username = rs.getString("username");
            String status = rs.getString("status");
            System.out.println("Found client: " + fullname); // Debug
            
            clientsList.add(new Object[]{fullname, username, status});
        }
    } catch (SQLException e) {
        System.err.println("Error getting clients data: " + e.getMessage());
        e.printStackTrace();
    }

    System.out.println("Total clients found: " + clientsList.size()); // Debug
    return clientsList.toArray(new Object[0][]);
}

    

private JPanel createRoutinePage() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBackground(new Color(50, 50, 50));

    // Input Panel
    JPanel inputPanel = new JPanel(new GridBagLayout());
    inputPanel.setBackground(new Color(50, 50, 50));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Wider input fields
    String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    JComboBox<String> dayCombo = new JComboBox<>(days);
    JTextField workoutField = new JTextField(30); // Wider
    JTextField caloriesField = new JTextField(15); // Wider

    JButton addButton = new JButton("Add Routine");

    // Layout components
    gbc.gridx = 0; gbc.gridy = 0;
    inputPanel.add(new JLabel("Day:"), gbc);
    gbc.gridx = 1;
    inputPanel.add(dayCombo, gbc);
    
    gbc.gridx = 0; gbc.gridy = 1;
    inputPanel.add(new JLabel("Workout:"), gbc);
    gbc.gridx = 1;
    inputPanel.add(workoutField, gbc);
    
    gbc.gridx = 0; gbc.gridy = 2;
    inputPanel.add(new JLabel("Calories:"), gbc);
    gbc.gridx = 1;
    inputPanel.add(caloriesField, gbc);
    
    gbc.gridx = 1; gbc.gridy = 3;
    inputPanel.add(addButton, gbc);

    panel.add(inputPanel, BorderLayout.NORTH);

    // Routines Display Panel
    JPanel routinesPanel = new JPanel(new GridLayout(0, 3, 10, 10));
    routinesPanel.setBackground(new Color(50, 50, 50));
    routinesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Load existing routines
    RoutineManager manager = new RoutineManager();
    List<RoutineManager.RoutineInfo> routines = manager.getRoutines(getUserId());
    
    for (String day : days) {
        JPanel dayPanel = createDayPanel(day, routines);
        routinesPanel.add(dayPanel);
    }

    JScrollPane scrollPane = new JScrollPane(routinesPanel);
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    panel.add(scrollPane, BorderLayout.CENTER);

    // Add Button Action
    addButton.addActionListener(e -> {
        String workout = workoutField.getText().trim();
        String calories = caloriesField.getText().trim();
        
        if (workout.isEmpty() || calories.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        RoutineManager.RoutineInfo routine = new RoutineManager.RoutineInfo();
        routine.day = (String) dayCombo.getSelectedItem();
        routine.workout = workout;
        routine.caloriesBurned = calories;
        routine.userId = getUserId();

        if (manager.saveRoutine(routine)) {
            workoutField.setText("");
            caloriesField.setText("");
            refreshRoutinePage();
        }
    });

    return panel;
}

private JPanel createDayPanel(String day, List<RoutineManager.RoutineInfo> routines) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(new Color(60, 60, 60));
    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Day header
    JLabel dayLabel = new JLabel(day);
    dayLabel.setForeground(Color.WHITE);
    dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
    dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(dayLabel);
    panel.add(Box.createVerticalStrut(10));

    // Find routine for this day
    Optional<RoutineManager.RoutineInfo> dayRoutine = routines.stream()
        .filter(r -> r.day.equals(day))
        .findFirst();

    if (dayRoutine.isPresent()) {
        addLabel(panel, "Workout: " + dayRoutine.get().workout, Font.PLAIN, 14);
        addLabel(panel, "Calories: " + dayRoutine.get().caloriesBurned, Font.PLAIN, 14);
    } else {
        addLabel(panel, "No routine set", Font.ITALIC, 14);
    }

    return panel;
}

private void addLabel(JPanel panel, String text, int fontStyle, int fontSize) {
    JLabel label = new JLabel(text);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Arial", fontStyle, fontSize));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(label);
    panel.add(Box.createVerticalStrut(5));
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
    private int getUserId() {
    if (userId != -1) {
        return userId;
    }

    String query = "SELECT user_id FROM users WHERE username = ?";
    try (Connection conn = new DatabaseHandler().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            userId = rs.getInt("user_id");
            return userId;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}

private JPanel createProfilePage() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(50, 50, 50));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    // Fetch trainer details
    TrainerProfile trainerProfile = new TrainerProfile(username);
    
    // Create form fields
    JTextField fullnameField = new JTextField(trainerProfile.getFullname(), 20);
    JTextField usernameField = new JTextField(trainerProfile.getUsername(), 20);
    JTextField emailField = new JTextField(trainerProfile.getEmail(), 20);
    JPasswordField passwordField = new JPasswordField(trainerProfile.getPassword(), 20);
    JTextField specializationField = new JTextField(trainerProfile.getSpecialization(), 20);
    JTextField experienceField = new JTextField(String.valueOf(trainerProfile.getExperience()), 20);
    
    // Make username field read-only
    usernameField.setEditable(false);
    
    // Add components with labels
    addFormField(panel, "Full Name:", fullnameField, gbc, 0);
    addFormField(panel, "Username:", usernameField, gbc, 1);
    addFormField(panel, "Email:", emailField, gbc, 2);
    addFormField(panel, "Password:", passwordField, gbc, 3);
    addFormField(panel, "Specialization:", specializationField, gbc, 4);
    addFormField(panel, "Experience (years):", experienceField, gbc, 5);
    
    // Save button
    JButton saveButton = new JButton("Save Changes");
    gbc.gridy = 6;
    gbc.gridx = 1;
    panel.add(saveButton, gbc);
    
    saveButton.addActionListener(e -> {
        String newFullname = fullnameField.getText().trim();
        String newEmail = emailField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();
        String newSpecialization = specializationField.getText().trim();
        String newExperience = experienceField.getText().trim();
        
        if (newFullname.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty() || 
            newSpecialization.isEmpty() || newExperience.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out!");
            return;
        }
        
        try {
            int experience = Integer.parseInt(newExperience);
            if (trainerProfile.updateProfile(newFullname, newEmail, newPassword, 
                                          newSpecialization, experience)) {
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update profile!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Experience must be a number!");
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

    private JPanel createTrainerPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("This is the Trainer page.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
} 
