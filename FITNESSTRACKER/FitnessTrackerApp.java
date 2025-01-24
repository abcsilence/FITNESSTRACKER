package FITNESSTRACKER;

import javax.swing.*;

public class FitnessTrackerApp {
    private static JFrame mainFrame;

    public static void main(String[] args) {
        // Initialize the application
        SwingUtilities.invokeLater(() -> {
            mainFrame = new JFrame("Fitness Tracker");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(1000, 600);
            mainFrame.setLocationRelativeTo(null);

            // Start with the Signup page
            showSignupPage();
        });
    }

    public static void showSignupPage() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new RegisterPage(mainFrame));
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public static void showLoginPage() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new LoginPage(mainFrame));
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
    // public static void showDashboard(String username) {
    //     mainFrame.getContentPane().removeAll();
    //     mainFrame.add(new DashboardMainInterface(username).getMainPanel());
    //     mainFrame.revalidate();
    //     mainFrame.repaint();
    //     mainFrame.setVisible(true);
    // }
}