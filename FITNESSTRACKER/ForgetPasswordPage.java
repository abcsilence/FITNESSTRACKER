package FITNESSTRACKER;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForgetPasswordPage {
    private JFrame frame;
    private Container container;
    private JTextField emailField;
    private JPasswordField newPasswordField, confirmPasswordField;
    private JButton showNewPasswordButton, showConfirmPasswordButton;

    public ForgetPasswordPage() {
        initializeFrame();
        setupContainer();
        addLabels();
        addInputFields();
        addPasswordToggle();
        addSubmitButton();
        addForgetImage();
        addBackToLogin();
        frame.setVisible(true);
    }

    private void addForgetImage() {
        ImageIcon originalIcon = new ImageIcon("forgetpassword.jpg");
        Image originalImage = originalIcon.getImage();

        int desiredWidth = 400;
        int desiredHeight = 350;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        container.add(imageLabel);
        imageLabel.setBounds(50, 80, desiredWidth, desiredHeight);
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 150, 1000, 600);
        frame.setTitle("Forget Password");

        // Set icon
        ImageIcon icon = new ImageIcon("imageico.png");
        frame.setIconImage(icon.getImage());
    }

    private void setupContainer() {
        container = frame.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.white);
    }

    private void addLabels() {
        JLabel headline = new JLabel("Forgot Your Password?");
        container.add(headline);
        headline.setBounds(550, 50, 400, 80);
        headline.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel emailLabel = new JLabel("Email Address");
        container.add(emailLabel);
        emailLabel.setBounds(550, 160, 200, 80);
        emailLabel.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));

        JLabel newPasswordLabel = new JLabel("New Password");
        container.add(newPasswordLabel);
        newPasswordLabel.setBounds(550, 238, 200, 80);
        newPasswordLabel.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        container.add(confirmPasswordLabel);
        confirmPasswordLabel.setBounds(550, 318, 200, 80);
        confirmPasswordLabel.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
    }

    private void addInputFields() {
        emailField = new JTextField();
        emailField.setBounds(550, 220, 300, 30);
        container.add(emailField);
        emailField.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(550, 300, 300, 30);
        container.add(newPasswordField);
        newPasswordField.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(550, 380, 300, 30);
        container.add(confirmPasswordField);
        confirmPasswordField.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
    }

    private void addPasswordToggle() {
        showNewPasswordButton = new JButton("Show");
        showNewPasswordButton.setBounds(865, 302, 70, 25);
        container.add(showNewPasswordButton);

        showNewPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showNewPasswordButton.getText().equals("Show")) {
                    newPasswordField.setEchoChar((char) 0);
                    showNewPasswordButton.setText("Hide");
                } else {
                    newPasswordField.setEchoChar('*');
                    showNewPasswordButton.setText("Show");
                }
            }
        });

        showConfirmPasswordButton = new JButton("Show");
        showConfirmPasswordButton.setBounds(865, 382, 70, 25);
        container.add(showConfirmPasswordButton);

        showConfirmPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showConfirmPasswordButton.getText().equals("Show")) {
                    confirmPasswordField.setEchoChar((char) 0);
                    showConfirmPasswordButton.setText("Hide");
                } else {
                    confirmPasswordField.setEchoChar('*');
                    showConfirmPasswordButton.setText("Show");
                }
            }
        });
    }

    private void addSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(550, 450, 300, 30);
        container.add(submitButton);
        submitButton.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (newPassword.equals(confirmPassword)) {
                    updatePassword(email, newPassword);
                } else {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match.");
                }
            }
        });
    }

    private void addBackToLogin() {
        JLabel backText = new JLabel("Back to the login page? ");
        backText.setBounds(550, 490, 300, 30);
        container.add(backText);
        backText.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));

        JButton backButton = new JButton("Back");
        backButton.setBounds(710, 495, 50, 20);
        container.add(backButton);
        backButton.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(173, 216, 230));
        backButton.setBorder(null);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginPage();
            }
        });
    }

    private void updatePassword(String email, String newPassword) {
        String DB_URL = "jdbc:mysql://localhost:3306/Details";
        String DB_USER = "root";
        String DB_PASSWORD = "admin123";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE details SET passwords = ? WHERE email_address = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newPassword);
                statement.setString(2, email);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Password updated successfully!");
                    frame.dispose();
                    new LoginPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Email address not found.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error updating password: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ForgetPasswordPage();
    }
}
