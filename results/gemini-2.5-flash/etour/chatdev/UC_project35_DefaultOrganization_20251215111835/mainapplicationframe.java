import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * {@code MainApplicationFrame} represents the main application window or "work area"
 * that is displayed to the user after a successful login.
 * It provides a welcome message and a logout option.
 * This class fulfills the use case exit condition: "The system displays the area of work registered."
 * @author Programmer
 * @version 1.0
 * @since 2023-10-27
 */
public class MainApplicationFrame extends JFrame {
    /**
     * Constructs the MainApplicationFrame, which serves as the "work area"
     * displayed after a successful user login. It shows a welcome message.
     *
     * @param username The username of the user who successfully logged in.
     */
    public MainApplicationFrame(String username) {
        super("ChatDev Work Area - Welcome " + username); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close by default
        setSize(600, 400); // Set initial frame size
        setLocationRelativeTo(null); // Center the frame on the screen
        // Use a BorderLayout for general layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the content
        add(panel);
        // Display a welcome message in HTML format for better presentation
        JLabel welcomeLabel = new JLabel("<html><center><h1>Welcome to ChatDev, " + username + "!</h1><br>" +
                "You have successfully accessed the system.<br>This is your personalized work area.</center></html>",
                SwingConstants.CENTER); // Center text horizontally within the label
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size for readability
        panel.add(welcomeLabel, BorderLayout.CENTER); // Add label to the center of the panel
        // Add a logout button for user convenience (optional, but good practice for a full system)
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            // Confirm with the user before logging out
            int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to log out and exit the application?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                handleLogout(); // Call method to handle the logout process
            }
        });
        JPanel southPanel = new JPanel(); // Create a panel for the button to control its placement
        southPanel.add(logoutButton);
        panel.add(southPanel, BorderLayout.SOUTH); // Add the button panel to the bottom of the main panel
        // Add a WindowListener to gracefully handle closing the main application.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // This method is called when the user clicks the 'X' button.
                // The default close operation is already set to EXIT_ON_CLOSE,
                // so no explicit System.exit(0) is needed here unless specific cleanup
                // or confirmation is desired before exiting through the 'X' button.
                System.out.println("Main application window is closing. Performing final cleanup...");
                // In a real application, you might save state, close connections, etc.
                // For this example, we just print a message.
            }
        });
    }
    /**
     * Handles the logout process. Closes the current application frame
     * and terminates the application. In a more complex system, this might
     * involve re-opening the login screen or clearing session data.
     */
    private void handleLogout() {
        this.dispose(); // Close the main application frame
        System.exit(0); // Terminate the entire application after logout
    }
}