/**
 * Main application class to demonstrate login error handling use case.
 * This application simulates a login system where users can enter credentials.
 * When incorrect data is supplied, it follows the specified error handling flow.
 */
import javax.swing.SwingUtilities;
public class LoginErrorApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for Swing components
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the login frame
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}