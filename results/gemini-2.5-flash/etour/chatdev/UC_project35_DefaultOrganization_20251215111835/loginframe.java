import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays; // Added for secure password clearing
/**
 * {@code LoginFrame} represents the graphical user interface for the login functionality.
 * It allows registered users to input their credentials to access the system.
 * The frame handles user input, communicates with the {@code AuthService} for authentication,
 * and manages different outcomes such as successful login, incorrect credentials,
 * or connection errors.
 * @author Programmer
 * @version 1.0
 * @since 2023-10-27
 */
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private AuthService authService;
    /**
     * Constructs the LoginFrame, setting up the GUI elements and
     * initializing the authentication service.
     */
    public LoginFrame() {
        super("User Login - ChatDev"); // Set window title
        authService = new AuthService(); // Initialize authentication service
        // Set up the frame properties
        // Change default close operation to DO_NOTHING_ON_CLOSE
        // to handle closing consistently via the WindowListener.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(350, 200); // Set initial frame size
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false); // Prevent resizing of the frame
        // Use a JPanel with a GridBagLayout for flexible component placement
        JPanel panel = new JPanel(new GridBagLayout());
        // Set a border for some padding
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel); // Add the panel to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        // --- Username Label and Field ---
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        panel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15); // 15 columns wide
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        panel.add(usernameField, gbc);
        // --- Password Label and Field ---
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15); // 15 columns wide
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        panel.add(passwordField, gbc);
        // --- Login Button ---
        loginButton = new JButton("Login");
        // Add an ActionListener to the Login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin(); // Call method to handle login attempt
            }
        });
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        gbc.gridwidth = 1; // Spans 1 column
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.fill = GridBagConstraints.NONE; // Do not fill horizontally
        panel.add(loginButton, gbc);
        // --- Cancel Button ---
        cancelButton = new JButton("Cancel");
        // Add an ActionListener to the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancellation: The user cancels the operation.
                handleCancellation();
            }
        });
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        gbc.gridwidth = 1; // Spans 1 column
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.fill = GridBagConstraints.NONE; // Do not fill horizontally
        panel.add(cancelButton, gbc);
        // Add a WindowListener to handle the window closing event via the 'X' button.
        // This ensures the same cancellation logic as the "Cancel" button.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleCancellation();
            }
        });
    }
    /**
     * Handles the login attempt when the login button is pressed.
     * Retrieves credentials, calls the authentication service, and
     * handles success, incorrect credentials, or connection interruptions.
     */
    private void attemptLogin() {
        String username = usernameField.getText(); // Get username from field
        // Get password from field as char array directly for security.
        // This prevents the password from existing as an immutable String in memory.
        char[] passwordChars = passwordField.getPassword();
        // Pre-check for empty fields to provide immediate feedback without hitting AuthService
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username cannot be empty.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            // Clear the sensitive password data immediately if validation fails
            Arrays.fill(passwordChars, '\0');
            return;
        }
        if (passwordChars.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Password cannot be empty.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            // Clear the sensitive password data immediately if validation fails
            Arrays.fill(passwordChars, '\0');
            return;
        }
        loginButton.setEnabled(false); // Disable buttons during login process to prevent multiple submissions
        cancelButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Change cursor to indicate waiting state
        // Perform authentication in a separate SwingWorker thread to keep the GUI responsive
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            private String errorMessage = null;
            private boolean isConnectionError = false;
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    // Call the authentication service with the char array password
                    return authService.authenticate(username, passwordChars);
                } catch (ConnectionInterruptionException cie) {
                    // Handle simulated connection interruption (ETOUR) as per use case
                    errorMessage = cie.getMessage();
                    isConnectionError = true;
                    return false; // Authentication failed due to connection
                } catch (IllegalArgumentException iae) {
                    // Handle input validation errors from AuthService (e.g., empty string, though checked locally too)
                    // This can catch cases where AuthService has stricter validation.
                    errorMessage = iae.getMessage();
                    return false;
                } finally {
                    // Always clear the sensitive password data from memory after use, regardless of outcome of authentication attempt
                    Arrays.fill(passwordChars, '\0');
                }
            }
            @Override
            protected void done() {
                // This block runs on the EDT after doInBackground completes.
                // Re-enable buttons and restore cursor regardless of the outcome.
                loginButton.setEnabled(true);
                cancelButton.setEnabled(true);
                setCursor(Cursor.getDefaultCursor());
                try {
                    boolean authenticated = get(); // Retrieve the result from doInBackground
                    if (isConnectionError) {
                        // Display error for simulated connection interruption
                        handleConnectionError(errorMessage);
                    } else if (authenticated) {
                        // If authentication is successful, proceed to the main application area
                        handleLoginSuccess(username); // Fulfills exit condition: "The system displays the area of work registered."
                    } else {
                        // If authentication fails due to incorrect credentials ("LoginErrato" use case)
                        handleLoginFailure(); // Displays error message and allows user to retry
                    }
                } catch (Exception ex) {
                    // Catch any other unexpected exceptions from SwingWorker.get() or doInBackground
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "An unexpected error occurred during login: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Execute the SwingWorker to start the authentication process
    }
    /**
     * Handles successful login: disposes the login frame and opens the main application frame.
     * This fulfills the exit condition: "The system displays the area of work registered."
     *
     * @param username The username of the successfully logged-in user.
     */
    private void handleLoginSuccess(String username) {
        JOptionPane.showMessageDialog(this,
                "Welcome, " + username + "!",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);
        // Hide the login frame as it's no longer needed
        this.dispose();
        // Create and show the main application window (work area)
        MainApplicationFrame mainFrame = new MainApplicationFrame(username);
        mainFrame.setVisible(true);
    }
    /**
     * Handles login failure due to incorrect credentials.
     * Displays an error message and keeps the login form active for retry.
     * This corresponds to the "LoginErrato" use case path.
     */
    private void handleLoginFailure() {
        JOptionPane.showMessageDialog(this,
                "Incorrect username or password. Please try again.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        // Clear password field for security and to encourage re-entry of credentials
        passwordField.setText("");
        // Optionally, focus the username field to quickly allow re-entry
        usernameField.requestFocusInWindow();
    }
    /**
     * Handles a simulated connection interruption error.
     * Displays an error message to inform the user about the issue, then allows retry.
     * This fulfills the exit condition: "Interruption of the connection to the server ETOUR .."
     *
     * @param message The detailed error message from the ConnectionInterruptionException.
     */
    private void handleConnectionError(String message) {
        JOptionPane.showMessageDialog(this,
                message + "\nPlease check your connection and try again.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        // Clear password field after error
        passwordField.setText("");
    }
    /**
     * Handles the user canceling the login operation or closing the window via 'X'.
     * Prompts for confirmation and then exits the application if confirmed.
     * This fulfills the exit condition: "The registered user cancels the operation."
     */
    private void handleCancellation() {
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel login and exit the application?",
                "Cancel Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0); // Terminate the application gracefully
        }
    }
}