'''
This is the main entry point for the User Management application.
It initializes the UserManager and UserValidationService, and then
creates and displays the NewUserForm on the Event Dispatch Thread (EDT)
to ensure thread-safe GUI operations.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize the user manager and validation service
            // These instances can be shared across different GUI components if needed.
            UserManager userManager = new UserManager();
            UserValidationService validationService = new UserValidationService(userManager);
            // Create and display the new user form
            NewUserForm form = new NewUserForm(userManager, validationService);
            form.setVisible(true);
        });
    }
}