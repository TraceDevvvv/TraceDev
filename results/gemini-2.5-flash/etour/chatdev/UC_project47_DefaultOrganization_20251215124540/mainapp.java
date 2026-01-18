'''
The main entry point for the application.
It simulates the overall flow: authentication, then launching the data modification feature.
'''
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainApp extends JFrame {
    private TouristDataModificationFrame modificationFrame;
    /**
     * Constructor for the MainApp.
     * Sets up the initial application window and handles the authentication process.
     */
    public MainApp() {
        setTitle("ChatDev Tourist Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits the application when this frame is closed
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window
        // Simulate main application presence, though it's mostly a container here
        JLabel welcomeLabel = new JLabel("Welcome to ETOUR Tourist Service!", SwingConstants.CENTER);
        add(welcomeLabel);
        setVisible(true); // Show the main app frame initially
        // 1. Access to functionality for the modification of data (triggered after auth)
        // Entry Condition: Tourism has successfully authenticated to the system.
        AuthDialog authDialog = new AuthDialog(this);
        if (authDialog.showLogin()) {
            String authenticatedUsername = authDialog.getAuthenticatedUsername();
            JOptionPane.showMessageDialog(this, "Authentication successful for " + authenticatedUsername + "!",
                    "Login Success", JOptionPane.INFORMATION_MESSAGE);
            // If authenticated, open the data modification frame
            showModifyDataScreen(authenticatedUsername);
        } else {
            JOptionPane.showMessageDialog(this, "Authentication failed or cancelled. Exiting application.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            System.exit(0); // Exit if authentication fails or is cancelled
        }
    }
    /**
     * Displays the TouristDataModificationFrame for the authenticated tourist.
     *
     * @param username The username of the tourist who has authenticated.
     */
    private void showModifyDataScreen(String username) {
        // Hide the main app frame as the modification frame will be primary
        this.setVisible(false);
        modificationFrame = new TouristDataModificationFrame(this, username);
        modificationFrame.setVisible(true);
    }
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp();
            }
        });
    }
}