/**
 * Main class to start the Admin Dashboard application.
 * It initializes the GUI on the Event Dispatch Thread (EDT).
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create a new instance of the registration service
            RegistrationService registrationService = new RegistrationService();
            // Populate with some dummy data for demonstration
            registrationService.addRegistration(new StudentRegistration("S001", "Alice Smith"));
            registrationService.addRegistration(new StudentRegistration("S002", "Bob Johnson"));
            registrationService.addRegistration(new StudentRegistration("S003", "Charlie Brown"));
            // Create and display the administrator dashboard frame
            AdminDashboardFrame adminFrame = new AdminDashboardFrame(registrationService);
            adminFrame.setVisible(true);
        });
    }
}