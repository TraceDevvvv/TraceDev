/*
 Main entry point for the Address Management System
 Creates and displays the login interface for administrators
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI creation
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}