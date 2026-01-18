'''
Main Application entry point for the Student Notes System
This application allows administrators to view student notes
'''
package studentnotessystem;
import javax.swing.*;
public class MainApplication {
    /**
     * Main method - entry point of the application
     * Sets look and feel and starts the login process
     */
    public static void main(String[] args) {
        // Set the look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Start with login screen
            new LoginFrame();
        });
    }
}