'''
Entry point of the application.
Initializes the serv and launches the main GUI frame.
'''
import gui.JustificationListFrame;
import service.JustificationService;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Justification; // <--- ADDED THIS IMPORT
public class MainApplication {
    /**
     * Main method to start the application.
     * Sets up the look and feel, initializes data, and displays the main list frame.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Set system look and feel for better integration with OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
            // Continue with default look and feel if setting fails
        }
        // Run GUI updates on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize the JustificationService with some sample data
            JustificationService justificationService = JustificationService.getInstance();
            justificationService.addJustification(new Justification(1, "2023-10-26", "Original justification for annual leave."));
            justificationService.addJustification(new Justification(2, "2023-11-01", "Request for project extension due to unforeseen delays."));
            justificationService.addJustification(new Justification(3, "2023-09-15", "Justification for expenses incurred during business trip."));
            // Create and display the main JustificationListFrame
            // This frame will allow viewing and selecting justifications for editing.
            new JustificationListFrame(justificationService).setVisible(true);
        });
    }
}