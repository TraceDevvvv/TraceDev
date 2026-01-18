/**
 * This is the main application class for the Cultural Heritage Management System.
 * It sets up the main JFrame and integrates the CulturalHeritageFormPanel and CulturalHeritageService.
 * This class serves as the entry point for the GUI application.
 */
import javax.swing.*;
import java.awt.*;
public class CulturalHeritageApp extends JFrame {
    private final CulturalHeritageService culturalHeritageService;
    /**
     * Constructor for the CulturalHeritageApp.
     * Initializes the CulturalHeritageService and sets up the main application window.
     */
    public CulturalHeritageApp() {
        // Initialize the service layer for managing cultural heritage objects.
        culturalHeritageService = new CulturalHeritageService();
        // Setup the main frame properties (title, size, close operation).
        setTitle("Cultural Heritage Insertion System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Create and add the form panel to the main frame.
        // The form panel is responsible for collecting user input and interacting with the service.
        CulturalHeritageFormPanel formPanel = new CulturalHeritageFormPanel(culturalHeritageService);
        add(formPanel, BorderLayout.CENTER);
    }
    /**
     * Main method to start the Cultural Heritage application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            // Step: Simulate Operator login condition as per use case entry condition.
            // This simple dialog acts as a placeholder for a more complex login system.
            // Requirement: The agency has logged.
            int loginResult = JOptionPane.showConfirmDialog(null,
                    "Simulate agency operator login?\n(Selecting 'No' will exit the application)",
                    "Operator Login Required",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (loginResult == JOptionPane.YES_OPTION) {
                CulturalHeritageApp app = new CulturalHeritageApp();
                app.setVisible(true); // Make the main window visible.
            } else {
                // Operator decided not to "log in" or cancelled.
                JOptionPane.showMessageDialog(null,
                        "Login cancelled. Application will exit.",
                        "Application Exit",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Exit the application if login is not simulated successfully.
            }
        });
    }
}