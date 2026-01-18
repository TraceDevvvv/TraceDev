/*
 * The main entry point for the Logout application.
 * Initializes and displays the LoginFrame to start the user interaction.
 */
import javax.swing.SwingUtilities;
public class LogoutApp {
    /**
     * The main method to start the application.
     * It ensures the GUI is created and run on the Event-Dispatching Thread (EDT) as required by Swing.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure that GUI creation and updates
        // are performed on the Event-Dispatching Thread for thread safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the LoginFrame
                LoginFrame loginFrame = new LoginFrame();
                // Make the login frame visible to the user, starting the application flow
                loginFrame.setVisible(true);
            }
        });
    }
}