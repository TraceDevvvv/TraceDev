/**
 * This is the main entry point for the Report Card Viewer application.
 * It initializes the LoginFrame, which acts as a gateway to the main application
 * as per the use case precondition that the user is already logged in as 'direction'.
 */
import gui.LoginFrame;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create and display the login frame
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}