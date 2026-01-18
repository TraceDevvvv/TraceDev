'''
The main JFrame for the login interface. It houses the LoginPanel and sets up the window properties.
'''
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
public class LoginFrame extends JFrame {
    /**
     * Constructs a new LoginFrame.
     * Sets up the frame properties and adds a LoginPanel to it.
     *
     * @param authService The authentication service to be used by the LoginPanel.
     */
    public LoginFrame(AuthService authService) {
        // Set the title of the frame
        setTitle("Login System");
        // Set the default close operation, terminates the application on close.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Set preferred size for the frame.
        setPreferredSize(new Dimension(400, 250));
        // Create and add the LoginPanel to this frame.
        // The LoginPanel handles all the input fields and logic.
        LoginPanel loginPanel = new LoginPanel(authService, this);
        add(loginPanel);
        // Pack the components to their preferred sizes and make the frame visible.
        pack();
        // Center the window on the screen.
        setLocationRelativeTo(null);
        // Make the frame non-resizable by the user.
        setResizable(false);
    }
    /**
     * Simulates displaying the user workspace after successful login.
     * In a real application, this would open a new main application window.
     * For this example, it opens a WorkspaceFrame instead of terminating the application.
     *
     * Postconditions:
     * - The system displays the registered user workspace
     * - Interruption of the connection to the SMOS server (simulated by print)
     * @param username The username that successfully logged in.
     */
    public void showUserWorkspace(String username) {
        System.out.println("Login successful for user: " + username);
        System.out.println("Displaying " + username + "'s workspace...");
        System.out.println("Simulating interruption of connection to SMOS server (if applicable)..."); // Fulfills the 'interruption of the connection to the SMOS server' postcondition.
        // Dispose of the current login frame.
        // This closes the login window, typically making way for the main application window.
        dispose();
        // Create and show the new workspace frame.
        // This fulfills the 'system displays the registered user workspace' postcondition.
        WorkspaceFrame workspaceFrame = new WorkspaceFrame(username);
        workspaceFrame.setVisible(true);
    }
}