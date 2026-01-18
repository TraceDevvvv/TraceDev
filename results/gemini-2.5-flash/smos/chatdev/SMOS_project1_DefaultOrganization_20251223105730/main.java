'''
This is the main entry point for the Login application.
It follows the standard Java Swing application pattern: create the GUI on the Event Dispatch Thread (EDT).
'''
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method to start the Login application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the authentication service.
            AuthService authService = new AuthService();
            // Pre-populate with some sample loggable users.
            // Pass passwords as char arrays directly.
            authService.addUser("admin", "password".toCharArray());
            authService.addUser("user123", "securepass".toCharArray());
            authService.addUser("testuser", "test12345".toCharArray());
            // Create and display the login frame, passing the auth service.
            LoginFrame loginFrame = new LoginFrame(authService);
            loginFrame.setVisible(true);
        });
    }
}