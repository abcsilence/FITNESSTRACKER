package FITNESSTRACKER;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private JFrame frame;
    private Container container;
    private JTextField usernamefield;
    private JPasswordField passwordfield;

    public LoginPage(JFrame frame) {
        this.frame = frame;
        setupContainer();
        addLoginComponents();
        addNavigationButtons();
    }

    private void setupContainer() {
        container = this;
        container.setLayout(null);
    }

    private void addLoginComponents() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(610, 30, 400, 80);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        container.add(loginLabel);

        addFormField("Username", 160, usernamefield = new JTextField());
        addFormField("Password", 238, passwordfield = new JPasswordField());
    }

    private void addFormField(String labelText, int yPosition, JTextField field) {
        JLabel label = new JLabel(labelText);
        container.add(label);
        label.setBounds(550, yPosition, 200, 80);
        label.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));

        field.setBounds(550, yPosition + 60, 300, 30);
        container.add(field);
        field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
    }

    private void addNavigationButtons() {
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(550, 320, 100, 30);
        container.add(registerButton);
        registerButton.addActionListener(e -> {
            FitnessTrackerApp.showSignupPage();
        });

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(700, 320, 100, 30);
        container.add(loginButton);
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernamefield.getText().trim();
        String password = new String(passwordfield.getPassword()).trim();

        // Add login validation logic here
        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and password are required.", null);
            return;
        }

        // Perform login validation (this is just a placeholder)
        if (username.equals("roshan") && password.equals("123")) {
         JOptionPane.showMessageDialog(frame, "Login successful!");
         FitnessTrackerApp.showDashboard(username);
     } else {
         showError("Invalid username or password.", null);
     }
    }

    private void showError(String message, JComponent component) {
        JOptionPane.showMessageDialog(frame, message);
        if (component != null) {
            component.requestFocus();
        }
    }
}