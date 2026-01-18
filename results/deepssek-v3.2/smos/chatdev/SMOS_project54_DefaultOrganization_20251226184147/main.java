/**
 * Main entry point for the InsertAbsences application.
 * This application allows ATA staff to record student absences
 * and send email notifications to parents.
 */
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // If system L&F fails, use default Swing L&F
                System.err.println("Failed to set system look and feel, using default.");
            }
            // Initialize serv
            DatabaseService databaseService = new DatabaseService();
            // Use simulation mode for email service by default for safe demonstration
            EmailService emailService = new EmailService(true);
            AbsenceService absenceService = new AbsenceService(databaseService, emailService);
            // Create and display the main application frame
            InsertAbsencesFrame frame = new InsertAbsencesFrame(absenceService);
            frame.setVisible(true);
        });
    }
}