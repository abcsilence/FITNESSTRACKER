package FITNESSTRACKER;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TrainerDashboard extends JPanel {
    private JPanel contentPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String username;

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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome, " + username + "! This is the Home page.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createProfilePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("This is the Profile page.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRoutinePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("This is the Routine page.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
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
