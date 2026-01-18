/**
 * Main entry point for the Address Management System application.
 * Launches the AdministratorConsole GUI.
 */
import javax.swing.SwingUtilities;
public class AddressApp {
    /**
     * Main method to start the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            AdministratorConsole app = new AdministratorConsole();
            app.setVisible(true);
        });
    }
}