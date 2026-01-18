/**
 * Main class to run the Convention History application.
 * This class initializes the MVC components and starts the application.
 * It ensures the application is fully runnable without external database setup.
 */
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // Initialize MVC components
                ConventionHistoryModel model = new ConventionHistoryModel();
                ConventionHistoryView view = new ConventionHistoryView();
                ConventionHistoryController controller = new ConventionHistoryController(model, view);
                // Display the view
                view.setVisible(true);
                // Show startup message
                JOptionPane.showMessageDialog(view,
                    "Welcome to Convention History Viewer!\n\n" +
                    "This application uses H2 in-memory database for demo purposes.\n" +
                    "Sample data has been loaded automatically.\n\n" +
                    "Try entering 'The Gourmet Hub' in the restaurant field and click 'Load Conventions'.",
                    "Welcome",
                    JOptionPane.INFORMATION_MESSAGE);
                // Try to connect to database automatically on startup
                controller.handleConnectToServer();
            } catch (Exception e) {
                // Handle any initialization errors gracefully
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
                // Show error message to user
                JOptionPane.showMessageDialog(null,
                    "Failed to start application: " + e.getMessage() + "\n" +
                    "Please ensure you have Java 8 or higher installed.",
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}