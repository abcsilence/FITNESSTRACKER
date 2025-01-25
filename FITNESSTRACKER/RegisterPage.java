package FITNESSTRACKER;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JPanel {
    private JFrame frame;
    private Container container;
    private JTextField fullnamefield;
    private JTextField usernamefield;
    private JTextField email_addressfield;
    private JPasswordField passwordfield;
    private JCheckBox trainerCheckBox;

    public RegisterPage(JFrame frame) {
        this.frame = frame;
        setupContainer();
        addRegisterImage();
        addHeadingLabels();
        addInputFields();
        addPasswordToggle();
        addTrainerCheckBox();
        addNavigationButtons();
    }

    private void setupContainer() {
        container = this;
        container.setLayout(null);
        container.setBackground(Color.decode("#152630")); // Set background color to #152630

    }

    private void addRegisterImage() {
        ImageIcon originalIcon = new ImageIcon("/Users/admin/Desktop/FITNESSTRACKER/FITNESSTRACKER/logo.png");
        Image originalImage = originalIcon.getImage();

        int desiredWidth = 400;
        int desiredHeight = 400;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(50, 50, desiredWidth, desiredHeight);
        container.add(imageLabel);
    }

    private void addHeadingLabels() {
        JLabel registerLabel = new JLabel("Welcome to Fitness App");
        registerLabel.setForeground(Color.LIGHT_GRAY); // Set text color to light gray
        container.add(registerLabel);
        registerLabel.setBounds(580, 30, 400, 80);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 30));
    }

    private void addInputFields() {
        addFormField("Full Name", 80, fullnamefield = new JTextField());
        addFormField("Username", 160, usernamefield = new JTextField());
        addFormField("Email Address", 230, email_addressfield = new JTextField());
        addFormField("Password:", 300, passwordfield = new JPasswordField());
    }

    private void addFormField(String labelText, int yPosition, JTextField field) {
        JLabel label = new JLabel(labelText);
        container.add(label);
        label.setBounds(550, yPosition, 200, 80);
        label.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        label.setForeground(Color.LIGHT_GRAY); // Set text color to light gray

        field.setBounds(550, yPosition + 60, 300, 30);
        container.add(field);
        field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
    }

    private void addPasswordToggle() {
        JButton showPassword = new JButton("Show");
        showPassword.setBounds(862, 360, 70, 25);
        container.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.getText().equals("Show")) {
                passwordfield.setEchoChar((char) 0);
                showPassword.setText("Hide");
            } else {
                passwordfield.setEchoChar('*');
                showPassword.setText("Show");
            }
        });
    }

    private void addTrainerCheckBox() {
        trainerCheckBox = new JCheckBox("I am a trainer");
        trainerCheckBox.setBounds(550, 400, 300, 30);
        trainerCheckBox.setBackground(new Color(240, 240, 240));
        trainerCheckBox.setForeground(Color.LIGHT_GRAY); // Set text color to light gray

        container.add(trainerCheckBox);
    }

    private void addNavigationButtons() {
        JButton backButton = new JButton("Login");
        backButton.setBounds(550, 450, 100, 30);
        container.add(backButton);
        backButton.addActionListener(e -> {
            FitnessTrackerApp.showLoginPage();
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(700, 450, 100, 30);
        container.add(registerButton);
        registerButton.addActionListener(e -> handleRegistration());
    }

    private void handleRegistration() {
        String fullname = fullnamefield.getText().trim();
        String username = usernamefield.getText().trim();
        String email_address = email_addressfield.getText().trim();
        String password = new String(passwordfield.getPassword()).trim();
        boolean IStrainer = trainerCheckBox.isSelected();

        if (fullname.isEmpty() || username.isEmpty() || email_address.isEmpty() || password.isEmpty()) {
            showError("All fields must be filled out.", null);
            return;
        }

        if (!isValidEmail(email_address)) {
            showError("Invalid email address.", email_addressfield);
            return;
        }

        if (!isStrongPassword(password)) {
            showError("Password must be at least 8 characters long and include letters, numbers, and special characters.", passwordfield);
            return;
        }

        RegistrationHandler registrationHandler = new RegistrationHandler();
        if (registrationHandler.usernameExists(username)) {
            showError("Username already exists.", usernamefield);
            return;
        }

        boolean success = registrationHandler.registerUser(fullname, username, email_address, password, IStrainer);

        if (success) {
            JOptionPane.showMessageDialog(frame, "Registration successful!");
        } else {
            showError("Registration failed.", null);
        }
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Za-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()-+=<>?].*");
    }

    private void showError(String message, JComponent component) {
        JOptionPane.showMessageDialog(frame, message);
        if (component != null) {
            component.requestFocus();
        }
    }
}