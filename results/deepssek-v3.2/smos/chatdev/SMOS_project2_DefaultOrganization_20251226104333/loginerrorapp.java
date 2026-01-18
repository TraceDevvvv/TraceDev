/**
 * LoginError Application - Demonstrates the LoginError use case.
 * This complete runnable Java program provides a GUI login form.
 * When users enter incorrect authentication data, it displays an error message
 * and allows them to try again (postcondition).
 * Compile and run with: javac LoginErrorApp.java && java LoginErrorApp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
public class LoginErrorApp {
    /**
     * Main entry point for the LoginError application.
     * Launches the Swing GUI on the Event Dispatch Thread (EDT).
     */
    public static void main(String[] args) {
        // Schedule GUI creation on the EDT (best practice for Swing)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginErrorFrame();
            }
        });
    }
}
/**
 * Main GUI frame for the login form.
 * Displays a login form and shows an error when incorrect authentication data is entered.
 * This simulates the "LoginError" use case exactly as specified.
 */
class LoginErrorFrame extends JFrame {
    // Hardcoded credentials for demonstration (in production, validate against a database)
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "password123";
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private int failedAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;
    public LoginErrorFrame() {
        // Frame setup
        setTitle("Login Form - LoginError Use Case Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // center on screen
        // Main panel with GridBagLayout for precise component placement
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);
        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);
        // Attempts label to display failed login attempts
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel attemptsLabel = new JLabel("Failed attempts: 0/" + MAX_ATTEMPTS);
        mainPanel.add(attemptsLabel, gbc);
        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        mainPanel.add(loginButton, gbc);
        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin(attemptsLabel);
            }
        });
        // Key listener to allow login submission with Enter key
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    attemptLogin(attemptsLabel);
                }
            }
        };
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
        // Add main panel to frame and make visible
        add(mainPanel);
        setVisible(true);
    }
    /**
     * Validates entered credentials against hardcoded correct values.
     * If incorrect: shows "Login Error" dialog and allows retry (postcondition).
     * If correct: shows success message and exits (for demo purposes).
     * Includes validation for empty fields and tracks failed attempts.
     */
    private void attemptLogin(JLabel attemptsLabel) {
        String enteredUsername = usernameField.getText().trim();
        // Note: getPassword returns char[] for security; convert to String for comparison
        String enteredPassword = new String(passwordField.getPassword());
        // Basic validation: ensure both fields are non-empty
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Validate credentials
        if (enteredUsername.equals(CORRECT_USERNAME) && enteredPassword.equals(CORRECT_PASSWORD)) {
            JOptionPane.showMessageDialog(this,
                    "Login successful! Welcome, " + enteredUsername + ".",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            // In a real application, navigate to main window; here we exit for demo
            System.exit(0);
        } else {
            // Increment failed attempts and update UI
            failedAttempts++;
            attemptsLabel.setText("Failed attempts: " + failedAttempts + "/" + MAX_ATTEMPTS);
            // Check if maximum attempts reached
            if (failedAttempts >= MAX_ATTEMPTS) {
                JOptionPane.showMessageDialog(this,
                        "Maximum login attempts exceeded. Please contact administrator.",
                        "Account Locked",
                        JOptionPane.ERROR_MESSAGE);
                // Disable UI components to prevent further attempts
                loginButton.setEnabled(false);
                usernameField.setEnabled(false);
                passwordField.setEnabled(false);
                return;
            }
            // This is the core LoginError use case: incorrect authentication data
            JOptionPane.showMessageDialog(this,
                    "Incorrect username or password. Please try again. (" + 
                    (MAX_ATTEMPTS - failedAttempts) + " attempts remaining)",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            // Clear password field for security and refocus username for quick retry
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }
}