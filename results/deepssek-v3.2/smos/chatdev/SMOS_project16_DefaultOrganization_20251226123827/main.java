'''
Main entry point for the Class Management System.
This program implements the "DeleteClass" use case for administrators.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Start the application on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            // Initialize the database simulator with sample data
            DatabaseSimulator db = new DatabaseSimulator();
            db.initializeSampleData();
            // Start with login screen
            LoginFrame loginFrame = new LoginFrame(db);
            loginFrame.setVisible(true);
        });
    }
}