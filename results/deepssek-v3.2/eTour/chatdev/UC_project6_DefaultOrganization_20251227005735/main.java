'''
Main entry point for the Refreshment Point Deletion Application.
This application allows agency operators to view and delete refreshment points
from the system after proper authentication and confirmation.
'''
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Create and display login dialog first
            LoginDialog loginDialog = new LoginDialog(null);
            loginDialog.setVisible(true);
            // If login succeeded, show main application window
            if (loginDialog.isSucceeded()) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } else {
                // Exit if login cancelled or failed
                System.exit(0);
            }
        });
    }
}