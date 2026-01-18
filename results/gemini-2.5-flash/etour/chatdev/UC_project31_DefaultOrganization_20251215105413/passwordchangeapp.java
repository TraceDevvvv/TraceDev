/**
 * The main application frame for the password change system.
 * It uses a CardLayout to switch between the LoginPanel and ChangePasswordPanel.
 */
import javax.swing.*;
import java.awt.*;
public class PasswordChangeApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserService userService;
    private String loggedInUser; // Stores the username of the currently logged-in user
    private LoginPanel loginPanel;
    private ChangePasswordPanel changePasswordPanel;
    /**
     * Constructs the main application window.
     * Initializes the UserService, sets up the CardLayout, and adds the panels.
     */
    public PasswordChangeApp() {
        super("Agency Password Management System"); // Set window title
        userService = new UserService(); // Initialize the service layer
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout); // Panel to hold other panels, managed by CardLayout
        // Initialize panels
        loginPanel = new LoginPanel(this);
        // changePasswordPanel is initialized with an empty username; it will be set dynamically upon login.
        changePasswordPanel = new ChangePasswordPanel(this, userService, ""); 
        // Add panels to the card panel with unique names
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(changePasswordPanel, "ChangePassword");
        // Add a checkbox to simulate server connection issues
        JCheckBox simulateConnectionLoss = new JCheckBox("Simulate Server Disconnection");
        // Initial state of the checkbox reflects the UserService's initial state (connected by default)
        simulateConnectionLoss.setSelected(!userService.isServerConnected()); 
        simulateConnectionLoss.addActionListener(e -> {
            userService.setServerConnected(!simulateConnectionLoss.isSelected());
            System.out.println("Server connection status: " + (userService.isServerConnected() ? "Connected" : "Disconnected (Simulated)"));
            if (simulateConnectionLoss.isSelected()) {
                JOptionPane.showMessageDialog(this, "Server connection set to DISCONNECTED.", "Simulated Connection Loss", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Server connection set to CONNECTED.", "Simulated Connection Restored", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Use BorderLayout to place the cardPanel in the center and the checkbox at the bottom
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(simulateConnectionLoss);
        add(footerPanel, BorderLayout.SOUTH);
        // Configure JFrame properties
        setSize(450, 400); // Set initial window size, adjusted for the checkbox
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
        showLoginPanel(); // Start by showing the login panel
    }
    /**
     * Displays the login panel.
     */
    public void showLoginPanel() {
        cardLayout.show(cardPanel, "Login");
        loggedInUser = null; // Clear logged-in user info
    }
    /**
     * Displays the password change panel for a specific user.
     * @param username The username of the operator who has logged in.
     */
    public void showChangePasswordPanel(String username) {
        loggedInUser = username;
        changePasswordPanel.setUsername(username); // Update the username displayed in the panel
        cardLayout.show(cardPanel, "ChangePassword");
    }
    /**
     * Gets the UserService instance used by the application.
     * @return The UserService instance.
     */
    public UserService getUserService() {
        return userService;
    }
    /**
     * Gets the username of the user currently logged into the system.
     * @return The username of the logged-in user, or null if no user is logged in.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }
    /**
     * The main entry point for the application.
     * Creates and runs the PasswordChangeApp on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordChangeApp();
            }
        });
    }
}