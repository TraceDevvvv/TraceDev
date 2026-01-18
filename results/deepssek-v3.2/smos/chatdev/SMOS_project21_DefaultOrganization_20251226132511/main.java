/**
 * Main entry point for the AssignRemoveTeachings application
 * This application allows administrators to assign or remove teachings from addresses
 * The application starts with a login screen to satisfy the administrator precondition
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI creation happens on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create login dialog and authenticate user before proceeding
            LoginDialog loginDialog = new LoginDialog(null);
            loginDialog.setVisible(true);
            // Check if login was successful
            if (loginDialog.isAuthenticated()) {
                // Create and display the main GUI
                AddressTeachingManager manager = new AddressTeachingManager();
                AssignRemoveTeachingsGUI gui = new AssignRemoveTeachingsGUI(manager);
                gui.setVisible(true);
            } else {
                // Exit if authentication failed
                System.exit(0);
            }
        });
    }
}