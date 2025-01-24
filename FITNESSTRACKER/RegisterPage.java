// package FITNESSTRACKER;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class RegisterPage extends JPanel{
//     private JFrame frame;
//     private Container container;
//     private JTextField fullnamefield;
//     private JTextField mobilenumberfield;
//     private JTextField email_addressfield;
//     private JPasswordField passwordfield;

//     // Database configuration
//     private static final String DB_URL = "jdbc:mysql://localhost:3306/Fitness_tracker";
//     private static final String DB_USER = "root";
//     private static final String DB_PASSWORD = "password";

//     public RegisterPage(JFrame mainFrame) {
//         this.frame = mainFrame;
//         // initializeFrame();
//         setupContainer();
//         addRegisterImage();
//         addHeadingLabels();
//         addInputFields();
//         addPasswordToggle();
//         addNavigationButtons();
//         frame.setVisible(true);
//     }

//     private void initializeFrame() {
//         frame = new JFrame();
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setBounds(300, 150, 1000, 600);
//         frame.setTitle("Register Page");

//         ImageIcon icon = new ImageIcon("logo.png");
//         if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
//             System.out.println("Logo image loaded successfully.");
//         } else {
//             System.out.println("Failed to load logo image.");
//         }
//         frame.setIconImage(icon.getImage());
//     }

//     private void setupContainer() {
//         container = this;
//         container.setLayout(null);
//     }

//     private void addRegisterImage() {
//         ImageIcon originalIcon = new ImageIcon("gym_photo.jpg");
//         Image originalImage = originalIcon.getImage();

//         int desiredWidth = 400;
//         int desiredHeight = 350;

//         Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
//         ImageIcon resizedIcon = new ImageIcon(resizedImage);

//         JLabel imageLabel = new JLabel(resizedIcon);
//         container.add(imageLabel);
//         imageLabel.setBounds(50, 80, desiredWidth, desiredHeight);
//     }

//     private void addHeadingLabels() {
//         JLabel registerLabel = new JLabel("Register here now");
//         container.add(registerLabel);
//         registerLabel.setBounds(610, 30, 400, 80);
//         registerLabel.setFont(new Font("Arial", Font.BOLD, 30));
//         registerLabel.setForeground(new Color(173, 216, 230));

//         JLabel welcomeLabel = new JLabel("Welcome to our gym apps");
//         container.add(welcomeLabel);
//         welcomeLabel.setBounds(550, 100, 400, 80);
//         welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
//     }

//     private void addInputFields() {
//         addFormField("Full Name", 160, fullnamefield = new JTextField());
//         addFormField("Mobile Number", 238, mobilenumberfield = new JTextField());
//         addFormField("Email Address", 320, email_addressfield = new JTextField());
//         addFormField("Password:", 400, passwordfield = new JPasswordField());
//     }

//     private void addFormField(String labelText, int yPosition, JTextField field) {
//         JLabel label = new JLabel(labelText);
//         container.add(label);
//         label.setBounds(550, yPosition, 200, 80);
//         label.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));

//         field.setBounds(550, yPosition + 60, 300, 30);
//         container.add(field);
//         field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
//     }

//     private void addPasswordToggle() {
//         JButton showPassword = new JButton("Show");
//         showPassword.setBounds(862, 462, 70, 25);
//         container.add(showPassword);

//         showPassword.addActionListener(e -> {
//             if (showPassword.getText().equals("Show")) {
//                 passwordfield.setEchoChar((char) 0);
//                 showPassword.setText("Hide");
//             } else {
//                 passwordfield.setEchoChar('*');
//                 showPassword.setText("Show");
//             }
//         });
//     }

//     private void addNavigationButtons() {
//         JButton backButton = new JButton("Back");
//         backButton.setBounds(550, 500, 100, 30);
//         container.add(backButton);
//         backButton.addActionListener(e -> {
//             frame.dispose();
//             new LoginPage(); // Ensure LoginPage class is implemented
//         });

//         JButton registerButton = new JButton("Register");
//         registerButton.setBounds(700, 500, 100, 30);
//         container.add(registerButton);
//         registerButton.addActionListener(e -> handleRegistration());
//     }

//     private void handleRegistration() {
//         String fullname = fullnamefield.getText().trim();
//         String mobile_number = mobilenumberfield.getText().trim();
//         String email_address = email_addressfield.getText().trim();
//         String password = new String(passwordfield.getPassword()).trim();

//         if (fullname.isEmpty()) {
//             showError("Full Name is required.", fullnamefield);
//             return;
//         }
//         if (mobile_number.isEmpty()) {
//             showError("Mobile number is required.", mobilenumberfield);
//             return;
//         }
//         if (!isValidEmail(email_address)) {
//             showError("Invalid email address. It should contain '@' and be alphanumeric.", email_addressfield);
//             return;
//         }
//         if (!isStrongPassword(password)) {
//             showError("Password must be at least 8 characters long, containing letters, numbers, and special characters.", passwordfield);
//             return;
//         }

//         try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//             String query = "INSERT INTO details(fullname, mobile_number, email_address, passwords) VALUES (?, ?, ?, ?)";

//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setString(1, fullname);
//                 preparedStatement.setString(2, mobile_number);
//                 preparedStatement.setString(3, email_address);
//                 preparedStatement.setString(4, password);

//                 int rowsInserted = preparedStatement.executeUpdate();
//                 if (rowsInserted > 0) {
//                     JOptionPane.showMessageDialog(frame, "Registration successful!");
//                     frame.dispose();
//                     new LoginPage(); // Ensure LoginPage class is implemented
//                 } else {
//                     showError("Registration failed.", null);
//                 }
//             }
//         } catch (SQLException ex) {
//             showError("Database error: " + ex.getMessage(), null);
//             ex.printStackTrace();
//         }
//     }

//     private boolean isValidEmail(String email) {
//         return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
//     }

//     private boolean isStrongPassword(String password) {
//         return password.length() >= 8 &&
//                 password.matches(".*[A-Za-z].*") &&
//                 password.matches(".*[0-9].*") &&
//                 password.matches(".*[!@#$%^&*()-+=<>?].*");
//     }

//     private void showError(String message, JComponent component) {
//         JOptionPane.showMessageDialog(frame, message);
//         if (component != null) {
//             component.requestFocus();
//         }
//     }

//     public static void main(String[] args) {
//         // new RegisterPage();
//     }
// }

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

    public RegisterPage(JFrame frame) {
        this.frame = frame;
        setupContainer();
        addRegisterImage();
        addHeadingLabels();
        addInputFields();
        addPasswordToggle();
        addNavigationButtons();

    }

    private void setupContainer() {
        container = this;
        container.setLayout(null);
    }

    private void addRegisterImage() {
        ImageIcon originalIcon = new ImageIcon("/Users/admin/Desktop/FITNESSTRACKER/FITNESSTRACKER/gym_photo.jpg");
        Image originalImage = originalIcon.getImage();

        int desiredWidth = 400;
        int desiredHeight = 350;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(50, 50, desiredWidth, desiredHeight);
        container.add(imageLabel);
    }

    private void addHeadingLabels() {
        JLabel registerLabel = new JLabel("Register here now");
        container.add(registerLabel);
        registerLabel.setBounds(610, 30, 400, 80);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        registerLabel.setForeground(new Color(173, 216, 230));

        JLabel welcomeLabel = new JLabel("Welcome to our gym apps");
        container.add(welcomeLabel);
        welcomeLabel.setBounds(550, 100, 400, 80);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
    }

    private void addInputFields() {
        addFormField("Full Name", 160, fullnamefield = new JTextField());
        addFormField("Username", 238, usernamefield = new JTextField());
        addFormField("Email Address", 320, email_addressfield = new JTextField());
        addFormField("Password:", 400, passwordfield = new JPasswordField());
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

    private void addPasswordToggle() {
        JButton showPassword = new JButton("Show");
        showPassword.setBounds(862, 462, 70, 25);
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

    private void addNavigationButtons() {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(550, 500, 100, 30);
        container.add(loginButton);
        loginButton.addActionListener(e -> {
            FitnessTrackerApp.showLoginPage();
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(700, 500, 100, 30);
        container.add(registerButton);
        registerButton.addActionListener(e -> handleRegistration());
    }

    private void handleRegistration() {
        String fullname = fullnamefield.getText().trim();
        String username = usernamefield.getText().trim();
        String email_address = email_addressfield.getText().trim();
        String password = new String(passwordfield.getPassword()).trim();

        if (!isValidEmail(email_address)) {
            showError("Invalid email address.", email_addressfield);
            return;
        }

        if (!isStrongPassword(password)) {
            showError("Password must be at least 8 characters long and include letters, numbers, and special characters.", passwordfield);
            return;
        }

        RegistrationHandler registrationHandler = new RegistrationHandler();
        boolean success = registrationHandler.registerUser(fullname, username, email_address, password);

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