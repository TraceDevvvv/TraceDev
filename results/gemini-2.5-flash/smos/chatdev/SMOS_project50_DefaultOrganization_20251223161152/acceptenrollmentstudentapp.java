/**
 * This is the main application class for the AcceptEnrollmentStudent program.
 * It initializes the RegistrationService and the EnrollmentView GUI.
 */
import javax.swing.SwingUtilities;
public class AcceptEnrollmentStudentApp {
    /**
     * Main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            // Instantiate the service layer that manages student registrations
            RegistrationService registrationService = new RegistrationService();
            // Instantiate the main GUI window for enrollment management, passing the service
            EnrollmentView enrollmentView = new EnrollmentView(registrationService);
            // Make the GUI window visible to the user
            enrollmentView.setVisible(true);
        });
    }
}