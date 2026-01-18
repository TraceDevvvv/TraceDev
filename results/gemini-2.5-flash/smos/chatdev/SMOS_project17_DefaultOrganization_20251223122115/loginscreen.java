'''
Represents the login screen of the application.
Users must log in with appropriate credentials to access the system.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginScreen extends JFrame {
    private static final String ADMIN_USERNAME = "admin";
    private static final char[] ADMIN_PASSWORD = {'a', 'd', 'm', 'i', 'n'};
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    /**
     * Constructs a new LoginScreen.
     * Initializes the GUI components and sets up the login window.
     */
    public LoginScreen() {
        // Set up the JFrame properties
        setTitle("Login - Address Management System");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);
        // Create a panel to hold the login components with a GridBagLayout for flexible positioning
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow field to expand horizontally
        usernameField = new JTextField(15);
        usernameField.setText(ADMIN_USERNAME); // Pre-fill for convenience
        panel.add(usernameField, gbc);
        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Reset weight
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(15);
        passwordField.setText(new String(ADMIN_PASSWORD)); // Pre-fill for convenience
        panel.add(passwordField, gbc);
        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button if space allows
        gbc.weightx = 0; // No horizontal expansion
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);
        // Message Label for feedback
        gbc.gridx = 0;
        gbc.gridy = 3;
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED); // Error messages in red
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, gbc);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        // Add panel to the frame and make it visible
        add(panel);
        setVisible(true);
    }
    /**
     * Attempts to authenticate the user based on the entered username and password.
     * If successful and the role is "administrator", it opens the AdminDashboard.
     * Otherwise, it displays an error message.
     * This method handles the precondition: "The user is logged in to the system with the administrator role".
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        // Simulate authentication
        if (username.equals(ADMIN_USERNAME) && java.util.Arrays.equals(password, ADMIN_PASSWORD)) {
            // Authentication successful, assume administrator role
            messageLabel.setForeground(new Color(0, 100, 0)); // Dark green for success
            messageLabel.setText("Login successful! Redirecting...");
            // In a real system, you might have a UserSession or SecurityContext here.
            // Close the login screen and open the admin dashboard
            dispose(); // Close current frame
            new AdminDashboard(); // Open the main application window
        } else {
            // Authentication failed
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }
        // Clear password field for security after attempt
        java.util.Arrays.fill(password, '0');
    }
}