'''
A JPanel that provides a user interface for logging into the system.
It takes a username and password and uses the AuthService to authenticate.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * JPanel representing the login screen of the application.
 * Allows users to enter credentials and attempt to log in.
 */
class LoginScreen extends JPanel {
    private MainApplication mainApp;
    private AuthService authService;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    /**
     * Constructs a LoginScreen.
     *
     * @param mainApp   The main application instance for screen navigation.
     * @param authService The authentication service to use for login.
     */
    public LoginScreen(MainApplication mainApp, AuthService authService) {
        this.mainApp = mainApp;
        this.authService = authService;
        initializeUI();
    }
    /**
     * Initializes the user interface components for the login screen.
     */
    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(usernameLabel, gbc);
        usernameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(usernameField, gbc);
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(passwordLabel, gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        add(passwordField, gbc);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
        // Pre-fill for convenience during testing
        usernameField.setText("admin");
        passwordField.setText("admin");
    }
    /**
     * Attempts to log in using the credentials entered by the user.
     * If successful and the user is an administrator, it navigates to the registry screen.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        AuthService.LoginResult result = authService.login(username, password);
        switch (result) {
            case SUCCESS_ADMIN:
                // Preconditions met: user must be logged in as an administrator
                mainApp.showScreen(MainApplication.REGISTRY_SCREEN);
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, Administrator.", "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            case SUCCESS_NON_ADMIN:
                JOptionPane.showMessageDialog(this, "Login successful, but only administrators are allowed to perform this operation.", "Access Denied", JOptionPane.WARNING_MESSAGE);
                // Explicitly log out users who are valid but not administrators for this system's workflow
                authService.logout();
                break;
            case INVALID_CREDENTIALS:
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                break;
        }
        // Clear password field after attempt
        passwordField.setText("");
    }
}