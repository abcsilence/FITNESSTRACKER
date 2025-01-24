package FITNESSTRACKER;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;   

public class DashboardMainInterface {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public DashboardMainInterface() {
        mainPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout) mainPanel.getLayout();

        // Create navigation menu
        JPanel navigationMenu = createNavigationMenu();
        mainPanel.add(navigationMenu, "NavigationMenu");

        // Create dashboard stats
        JPanel dashboardStats = createDashboardStats();
        mainPanel.add(dashboardStats, "DashboardStats");

        // Create member profile section
        JPanel memberProfile = createMemberProfile();
        mainPanel.add(memberProfile, "MemberProfile");

        // Create workout program section
        JPanel workoutProgram = createWorkoutProgram();
        mainPanel.add(workoutProgram, "WorkoutProgram");

        // Create membership and payments section
        JPanel membershipPayments = createMembershipPayments();
        mainPanel.add(membershipPayments, "MembershipPayments");

        // Create BMI section
        JPanel bmiSection = createBMISection();
        mainPanel.add(bmiSection, "BMI");

        // Create trainers section
        JPanel trainersSection = createTrainersSection();
        mainPanel.add(trainersSection, "Trainers");

        // Create settings section
        JPanel settingsSection = createSettingsSection();
        mainPanel.add(settingsSection, "Settings");

        // Create nutrition tips section
        JPanel nutritionTipsSection = createNutritionTipsSection();
        mainPanel.add(nutritionTipsSection, "NutritionTips");

        // Create class schedule section
        JPanel classScheduleSection = createClassScheduleSection();
        mainPanel.add(classScheduleSection, "ClassSchedule");

        // Show the navigation menu by default
        cardLayout.show(mainPanel, "NavigationMenu");
    }

    private JPanel createNavigationMenu() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 0, 10));
        panel.setBackground(new Color(50, 54, 61)); // Dark background for the navigation menu
        panel.setPreferredSize(new Dimension(250, 800));

        String[] menuItems = {"Home", "Member Profile", "Workout Programs", "Membership & Payments", "BMI", "Trainers", "Settings"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setForeground(Color.WHITE); // White text
            button.setBackground(new Color(70, 73, 79)); // Dark gray background
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
            button.addActionListener(e -> cardLayout.show(mainPanel, item.replace(" ", "")));

            // Hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(90, 93, 99)); // Lighter gray on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(70, 73, 79)); // Restore original color
                }
            });

            panel.add(button);
        }

        return panel;
    }

    private JPanel createDashboardStats() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Dashboard Stats", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel totalMembersLabel = new JLabel("Total Members: 100");
        totalMembersLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalMembersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(totalMembersLabel);

        JLabel totalEarningsLabel = new JLabel("Total Earnings: $5000");
        totalEarningsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalEarningsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(totalEarningsLabel);

        JLabel upcomingClassesLabel = new JLabel("Upcoming Classes: Yoga, HIIT");
        upcomingClassesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        upcomingClassesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(upcomingClassesLabel);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createMemberProfile() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Member Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name: John Doe");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nameLabel);

        JLabel emailLabel = new JLabel("Email: john.doe@example.com");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(emailLabel);

        JLabel genderLabel = new JLabel("Gender: Male");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(genderLabel);

        JLabel membershipLabel = new JLabel("Membership: Gold");
        membershipLabel.setFont(new Font("Arial", Font.BOLD, 18));
        membershipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(membershipLabel);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createWorkoutProgram() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Workout Programs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel availableProgramsLabel = new JLabel("Available Programs:");
        availableProgramsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availableProgramsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(availableProgramsLabel);

        String[] programs = {"Weight Loss", "Muscle Gain", "Fitness"};
        JComboBox<String> programDropdown = new JComboBox<>(programs);
        programDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        programDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(programDropdown);

        JLabel personalizedProgramLabel = new JLabel("Personalized Program:");
        personalizedProgramLabel.setFont(new Font("Arial", Font.BOLD, 18));
        personalizedProgramLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(personalizedProgramLabel);

        JLabel dailyWorkoutLogLabel = new JLabel("Daily Workout Log:");
        dailyWorkoutLogLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dailyWorkoutLogLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(dailyWorkoutLogLabel);

        JLabel programRatingsLabel = new JLabel("Program Ratings/Feedback:");
        programRatingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        programRatingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(programRatingsLabel);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createMembershipPayments() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Membership & Payments", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel membershipPlanLabel = new JLabel("Membership Plan:");
        membershipPlanLabel.setFont(new Font("Arial", Font.BOLD, 18));
        membershipPlanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(membershipPlanLabel);

        String[] plans = {"Monthly", "Quarterly", "Yearly"};
        JComboBox<String> planDropdown = new JComboBox<>(plans);
        planDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        planDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(planDropdown);

        JLabel paymentHistoryLabel = new JLabel("Payment History:");
        paymentHistoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        paymentHistoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(paymentHistoryLabel);

        JLabel renewalLabel = new JLabel("Renewal:");
        renewalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        renewalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(renewalLabel);

        // Payment button
        JButton paymentButton = new JButton("Make Payment");
        paymentButton.setFont(new Font("Arial", Font.PLAIN, 18));
        paymentButton.setForeground(Color.WHITE);
        paymentButton.setBackground(new Color(70, 73, 79));
        paymentButton.setFocusPainted(false);
        paymentButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        paymentButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Payment Successful!", "Payment", JOptionPane.INFORMATION_MESSAGE));

        // Hover effect for payment button
        paymentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paymentButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                paymentButton.setBackground(new Color(70, 73, 79));
            }
        });

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(paymentButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createBMISection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("BMI Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 18));
        weightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setFont(new Font("Arial", Font.PLAIN, 18));
        weightField.setAlignmentX(Component.CENTER_ALIGNMENT);
        weightField.setMaximumSize(new Dimension(200, 30));
        panel.add(weightField);

        JLabel heightLabel = new JLabel("Height (cm):");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 18));
        heightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setFont(new Font("Arial", Font.PLAIN, 18));
        heightField.setAlignmentX(Component.CENTER_ALIGNMENT);
        heightField.setMaximumSize(new Dimension(200, 30));
        panel.add(heightField);

        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(new Color(70, 73, 79));
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.addActionListener(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText()) / 100; // Convert cm to meters
                double bmi = weight / (height * height);
                JOptionPane.showMessageDialog(panel, "Your BMI is: " + String.format("%.2f", bmi), "BMI Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(panel, "Please enter valid numbers for weight and height.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Hover effect for calculate button
        calculateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                calculateButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                calculateButton.setBackground(new Color(70, 73, 79));
            }
        });

        panel.add(calculateButton);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createTrainersSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Trainers", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel trainerListLabel = new JLabel("Available Trainers:");
        trainerListLabel.setFont(new Font("Arial", Font.BOLD, 18));
        trainerListLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(trainerListLabel);

        JTextArea trainerListArea = new JTextArea();
        trainerListArea.setEditable(false);
        trainerListArea.setLineWrap(true);
        trainerListArea.setWrapStyleWord(true);
        trainerListArea.setText("1. Trainer John - Specializes in Weight Loss\n" +
                                "2. Trainer Sarah - Specializes in Muscle Gain\n" +
                                "3. Trainer Mike - Specializes in Fitness");
        JScrollPane trainerScrollPane = new JScrollPane(trainerListArea);
        trainerScrollPane.setPreferredSize(new Dimension(400, 100));
        panel.add(trainerScrollPane);

        JLabel trainerFeedbackLabel = new JLabel("Trainer Feedback:");
        trainerFeedbackLabel.setFont(new Font("Arial", Font.BOLD, 18));
        trainerFeedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(trainerFeedbackLabel);

        JTextArea trainerFeedbackArea = new JTextArea();
        trainerFeedbackArea.setEditable(false);
        trainerFeedbackArea.setLineWrap(true);
        trainerFeedbackArea.setWrapStyleWord(true);
        trainerFeedbackArea.setText("Trainer John: Excellent guidance!\n" +
                                    "Trainer Sarah: Very motivating!\n" +
                                    "Trainer Mike: Great workout plans!");
        JScrollPane feedbackScrollPane = new JScrollPane(trainerFeedbackArea);
        feedbackScrollPane.setPreferredSize(new Dimension(400, 100));
        panel.add(feedbackScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createSettingsSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Settings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel accountSettingsLabel = new JLabel("Account Settings:");
        accountSettingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        accountSettingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(accountSettingsLabel);

        JTextArea accountSettingsArea = new JTextArea();
        accountSettingsArea.setEditable(false);
        accountSettingsArea.setLineWrap(true);
        accountSettingsArea.setWrapStyleWord(true);
        accountSettingsArea.setText("Update Email\nManage Profile");
        JScrollPane accountScrollPane = new JScrollPane(accountSettingsArea);
        accountScrollPane.setPreferredSize(new Dimension(400, 100));
        panel.add(accountScrollPane);

        JLabel notificationSettingsLabel = new JLabel("Notification Settings:");
        notificationSettingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        notificationSettingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(notificationSettingsLabel);

        JTextArea notificationSettingsArea = new JTextArea();
        notificationSettingsArea.setEditable(false);
        notificationSettingsArea.setLineWrap(true);
        notificationSettingsArea.setWrapStyleWord(true);
        notificationSettingsArea.setText("Enable Email Notifications\nEnable SMS Notifications");
        JScrollPane notificationScrollPane = new JScrollPane(notificationSettingsArea);
        notificationScrollPane.setPreferredSize(new Dimension(400, 100));
        panel.add(notificationScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createNutritionTipsSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Nutrition Tips", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JTextArea tipsArea = new JTextArea();
        tipsArea.setEditable(false);
        tipsArea.setLineWrap(true);
        tipsArea.setWrapStyleWord(true);
        tipsArea.setText("1. Eat a variety of foods\n" +
                         "2. Base your diet on plenty of foods rich in carbohydrates\n" +
                         "3. Replace saturated with unsaturated fat\n" +
                         "4. Enjoy plenty of fruits and vegetables\n" +
                         "5. Reduce salt and sugar intake\n" +
                         "6. Eat regularly, control the portion size\n" +
                         "7. Drink plenty of fluids\n" +
                         "8. Maintain a healthy body weight\n" +
                         "9. Get on the move, make it a habit\n" +
                         "10. Start now! And keep changing gradually.");
        JScrollPane tipsScrollPane = new JScrollPane(tipsArea);
        tipsScrollPane.setPreferredSize(new Dimension(400, 200));
        panel.add(tipsScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createClassScheduleSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Class Schedule", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JTextArea scheduleArea = new JTextArea();
        scheduleArea.setEditable(false);
        scheduleArea.setLineWrap(true);
        scheduleArea.setWrapStyleWord(true);
        scheduleArea.setText("Monday:\n" +
                             " - 9:00 AM: Yoga\n" +
                             " - 11:00 AM: HIIT\n" +
                             " - 6:00 PM: Zumba\n\n" +
                             "Tuesday:\n" +
                             " - 9:00 AM: Pilates\n" +
                             " - 11:00 AM: Strength Training\n" +
                             " - 6:00 PM: Cardio\n\n" +
                             "Wednesday:\n" +
                             " - 9:00 AM: Yoga\n" +
                             " - 11:00 AM: HIIT\n" +
                             " - 6:00 PM: Zumba\n\n" +
                             "Thursday:\n" +
                             " - 9:00 AM: Pilates\n" +
                             " - 11:00 AM: Strength Training\n" +
                             " - 6:00 PM: Cardio\n\n" +
                             "Friday:\n" +
                             " - 9:00 AM: Yoga\n" +
                             " - 11:00 AM: HIIT\n" +
                             " - 6:00 PM: Zumba\n\n" +
                             "Saturday:\n" +
                             " - 9:00 AM: Pilates\n" +
                             " - 11:00 AM: Strength Training\n" +
                             " - 6:00 PM: Cardio\n\n" +
                             "Sunday:\n" +
                             " - Rest Day");
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleArea);
        scheduleScrollPane.setPreferredSize(new Dimension(400, 200));
        panel.add(scheduleScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 73, 79));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "NavigationMenu"));

        // Hover effect for back button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(90, 93, 99));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(70, 73, 79));
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(backButton);
        panel.add(buttonPanel);

        return panel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gym Fitness Tracker Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        DashboardMainInterface dashboardMainInterface = new DashboardMainInterface();
        frame.add(dashboardMainInterface.getMainPanel(), BorderLayout.CENTER);

        frame.setVisible(true);
    }
}