'''
Main class for the Login system.
This class sets up the GUI and handles the login logic.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class app {
    private static JFrame frame;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JLabel messageLabel;
    // In-memory user database (for demo purposes)
    private static Map<String, User> userDatabase = new HashMap<>();
    public static void main(String[] args) {
        // Populate the user database with some sample users
        initializeUserDatabase();
        // Set up the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    /**
     * Initializes the in-memory user database with sample credentials.
     */
    private static void initializeUserDatabase() {
        // Adding sample users with hashed passwords
        try {
            userDatabase.put("admin", new User("admin", PasswordHasher.hashPassword("admin123"), false));
            userDatabase.put("user1", new User("user1", PasswordHasher.hashPassword("password1"), false));
            userDatabase.put("john_doe", new User("john_doe", PasswordHasher.hashPassword("securepass"), false));
        } catch (java.security.NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error initializing database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Creates and displays the login GUI.
     */
    private static void createAndShowGUI() {
        frame = new JFrame("Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to the Login System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        // Message label for feedback
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setForeground(Color.RED);
        // Add components to frame
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(messageLabel, BorderLayout.AFTER_LAST_LINE);
        // Action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancel();
            }
        });
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Handles the login action when the login button is clicked.
     * Validates the username and password using LoginService.
     */
    private static void handleLogin() {
        String username = usernameField.getText().trim();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        // Clear password array for security
        java.util.Arrays.fill(passwordChars, '0');
        try {
            // Use LoginService to authenticate user
            LoginService loginService = LoginService.getInstance();
            User authenticatedUser = loginService.authenticate(username, password, userDatabase);
            // Successful login: display work area and close login window
            messageLabel.setText("Login successful! Redirecting...");
            // Close login window after a short delay and show work area on EDT
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            showWorkArea(authenticatedUser.getUsername());
                        }
                    });
                }
            });
            timer.setRepeats(false);
            timer.start();
        } catch (LoginException e) {
            // Handle different login error scenarios based on error type
            switch(e.getErrorType()) {
                case EMPTY_FIELDS:
                    messageLabel.setText("Please fill in both username and password.");
                    break;
                case INVALID_INPUT_FORMAT:
                    messageLabel.setText(e.getMessage());
                    break;
                case INVALID_CREDENTIALS:
                    messageLabel.setText("Invalid username or password. Please try again.");
                    passwordField.setText("");
                    break;
                case SERVER_CONNECTION_FAILED:
                    messageLabel.setText("Error: Connection to server interrupted (ETOUR).");
                    break;
                case ACCOUNT_LOCKED:
                    messageLabel.setText("Account is locked. Please contact administrator.");
                    break;
                default:
                    messageLabel.setText("Login failed: " + e.getMessage());
            }
        }
    }
    /**
     * Handles the cancel action when the cancel button is clicked.
     * Clears the fields and resets the message label.
     */
    private static void handleCancel() {
        usernameField.setText("");
        passwordField.setText("");
        messageLabel.setText("Operation canceled.");
    }
    /**
     * Displays the work area (main application) after successful login.
     * @param username The username of the logged-in user.
     */
    private static void showWorkArea(String username) {
        JFrame workFrame = new JFrame("Work Area - Welcome " + username);
        workFrame.setSize(600, 400);
        workFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("You are now in the work area. User: " + username, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        workFrame.add(welcomeLabel, BorderLayout.CENTER);
        workFrame.setLocationRelativeTo(null);
        workFrame.setVisible(true);
    }
}