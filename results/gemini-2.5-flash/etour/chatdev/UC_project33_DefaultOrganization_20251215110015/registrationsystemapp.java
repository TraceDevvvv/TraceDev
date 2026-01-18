/*
Main entry point for the Registration System application.
Sets up the main JFrame and integrates the RegistrationFormPanel.
*/
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
public class RegistrationSystemApp {
    private static final Logger logger = RegistrationLogger.getLogger();
    public static void main(String[] args) {
        // Step 1: Enable the logging feature.
        // This is handled by RegistrationLogger.getLogger() being called at the start.
        logger.info("Application starting up.");
        // Ensure Swing operations are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    /*
     Creates and displays the main application window (JFrame).
     Initializes the UserService and RegistrationFormPanel.
    */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("User Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window
        UserService userService = new UserService();
        RegistrationFormPanel registrationFormPanel = new RegistrationFormPanel(userService);
        frame.add(registrationFormPanel);
        frame.setVisible(true);
        logger.info("Registration form displayed to Guest User.");
    }
}