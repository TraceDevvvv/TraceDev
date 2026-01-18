/**
 * This is the main entry point of the entire application.
 * It creates the necessary service objects and initializes the first GUI frame (LoginFrame).
 * It ensures that GUI operations are handled on the Event Dispatch Thread (EDT) for thread safety.
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure that GUI creation and updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the TeachingService which will manage data and simulated server connection
                TeachingService teachingService = new TeachingService();
                // Start the application by displaying the Login frame
                // This frame requires the teachingService to simulate SMOS connection upon login
                LoginFrame loginFrame = new LoginFrame(teachingService);
                loginFrame.setVisible(true);
            }
        });
    }
}