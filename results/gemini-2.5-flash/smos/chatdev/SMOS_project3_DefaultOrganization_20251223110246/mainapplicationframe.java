/*
 * Represents the main application window displayed after a successful login.
 * Contains a welcome message and a logout button.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainApplicationFrame extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;
    /**
     * Constructor for the MainApplicationFrame.
     * Initializes the GUI components for the main application view.
     */
    public MainApplicationFrame() {
        setTitle("Main Application - Logged In"); // Set the window title
        setSize(400, 300); // Set initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define behavior on close
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Make the window non-resizable
        // Set up the main panel with a BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        add(panel);
        // Welcome Label
        // Retrieve the current username from the UserSession to personalize the welcome message
        welcomeLabel = new JLabel("Welcome, " + UserSession.getUsername() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font for welcome message
        panel.add(welcomeLabel, BorderLayout.CENTER);
        // Logout Button
        logoutButton = new JButton("Logout");
        panel.add(logoutButton, BorderLayout.SOUTH);
        // Add action listener to the logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogout(); // Call the method to handle logout
            }
        });
    }
    /**
     * Handles the logout process.
     * Updates the UserSession status, disposes of this frame, and displays the LoginFrame.
     */
    private void performLogout() {
        // Only perform logout if a user is actually logged in.
        // This is a safety check; ideally, this frame would only be accessible when logged in.
        if (UserSession.isLoggedIn()) {
            UserSession.logout(); // Update the user session status to logged out
            JOptionPane.showMessageDialog(this, "You have been successfully logged out.", "Logged Out", JOptionPane.INFORMATION_MESSAGE);
            // Dispose of the current MainApplicationFrame to close it
            this.dispose();
            // Create and display a new LoginFrame, satisfying the requirement to "display the login form"
            new LoginFrame().setVisible(true);
        } else {
            // This case should theoretically not be reached if application flow is correct.
            // It means logout was attempted when no user was marked as logged in.
            JOptionPane.showMessageDialog(this, "No user is currently logged in to log out.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}