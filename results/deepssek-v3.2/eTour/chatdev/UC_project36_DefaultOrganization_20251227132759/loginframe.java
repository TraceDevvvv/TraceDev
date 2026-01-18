/**
 * Main GUI frame for the login application.
 * Provides user interface for entering login credentials and handles
 * the login attempt flow including error scenarios.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginErrorHandler errorHandler;
    private AuthService authService;
    public LoginFrame() {
        // Initialize components
        errorHandler = new LoginErrorHandler();
        authService = new AuthService();
        // Set up the frame
        setTitle("Login System - LoginError Use Case Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        // Create UI components
        createUIComponents();
    }
    private void createUIComponents() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title label
        JLabel titleLabel = new JLabel("Login System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(usernameLabel, gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameField, gbc);
        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passwordField, gbc);
        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);
        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);
        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
    }
    /**
     * Handles the login attempt process.
     * Validates input and either grants access or initiates error handling flow.
     */
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Check if credentials are empty
        if (username.isEmpty() || password.isEmpty()) {
            handleLoginError("Username and password cannot be empty.");
            return;
        }
        // Attempt authentication
        boolean isValid = authService.authenticate(username, password);
        if (!isValid) {
            handleLoginError("Invalid username or password. Please try again.");
        } else {
            // Successful login
            JOptionPane.showMessageDialog(this, 
                "Login successful! Welcome, " + username + ".",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            // In a real application, you would navigate to another screen here
        }
    }
    /**
     * Unified error handling method that follows the use case flow exactly.
     * Implements Steps 1-3 of the error handling flow as specified in the use case.
     * 
     * @param errorMessage Specific error message to display
     */
    private void handleLoginError(String errorMessage) {
        // Step 1 & 2 combined: Notify invalid data and request confirmation in one dialog
        boolean confirmed = errorHandler.requestConfirmationForInvalidData(errorMessage);
        if (confirmed) {
            // Step 3: Initiate recovery through error handler
            errorHandler.initiateRecovery();
            // Execute the actual recovery
            recoverPreviousState();
            // Exit condition: Return control to user interaction
            System.out.println("Control returned to user interaction after error recovery.");
        }
    }
    /**
     * Recovers the previous state (clears the form for new attempt).
     * Implements Step 3 of the error handling flow.
     */
    private void recoverPreviousState() {
        // Clear the input fields
        usernameField.setText("");
        passwordField.setText("");
        // Set focus back to username field
        usernameField.requestFocus();
        // Enable login button if it was disabled during error flow
        loginButton.setEnabled(true);
    }
}