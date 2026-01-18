/**
 * Main.java
 *
 * This is the entry point for the InsertDelayAdmin application.
 * It displays a login dialog for authentication, and upon successful
 * login as an administrator, it launches the main GUI frame.
 */
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            // Create and display login dialog
            LoginDialog loginDialog = new LoginDialog(null);
            loginDialog.setVisible(true);
            // Check if login was successful and user is administrator
            if (loginDialog.isAuthenticated() && loginDialog.isAdministrator()) {
                // Login successful, create and show main application frame
                MainFrame frame = new MainFrame("Insert Delay Admin");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setVisible(true);
            } else {
                // Login failed or user is not administrator, exit application
                JOptionPane.showMessageDialog(null,
                    "Authentication failed or insufficient privileges.\nApplication will exit.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        });
    }
}