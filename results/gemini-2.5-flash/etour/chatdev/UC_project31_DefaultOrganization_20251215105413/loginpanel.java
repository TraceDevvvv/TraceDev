/**
 * A JPanel that simulates a login screen for the Agency Operator.
 * Upon successful "login," it notifies the main application to display the password change form.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // Added for secure password handling
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private PasswordChangeApp parentApp; // Reference to the main application frame
    /**
     * Constructs a LoginPanel.
     * @param parentApp The main application frame to interact with.
     */
    public LoginPanel(PasswordChangeApp parentApp) {
        this.parentApp = parentApp;
        setLayout(new GridBagLayout()); // Using GridBagLayout for better control over component placement
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        JLabel titleLabel = new JLabel("Agency Operator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        // Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);
        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);
        // Login Button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
        // Message Label for feedback
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);
        addLoginActionListener(); // Add action listener to the login button
    }
    /**
     * Adds an ActionListener to the login button.
     * When the button is pressed, it attempts to authenticate the user
     * and, if successful, switches to the password change panel.
     */
    private void addLoginActionListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword(); // Retrieve password as char[]
                messageLabel.setText(""); // Clear previous messages
                if (username.isEmpty() || passwordChars.length == 0) {
                    messageLabel.setText("Username and password cannot be empty.");
                    // Clear password field immediately if validation fails
                    Arrays.fill(passwordChars, ' ');
                    passwordField.setText("");
                    return;
                }
                try {
                    // Attempt to authenticate using the UserService with char[]
                    User authenticatedUser = parentApp.getUserService().authenticate(username, passwordChars);
                    if (authenticatedUser != null) {
                        messageLabel.setForeground(Color.BLUE);
                        messageLabel.setText("Login successful! Redirecting...");
                        // Simulate a small delay before redirecting for better UX
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Navigate to the password change panel
                                parentApp.showChangePasswordPanel(authenticatedUser.getUsername());
                            }
                        });
                        timer.setRepeats(false); // Only run once
                        timer.start();
                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Invalid username or password.");
                    }
                } catch (UserService.ConnectionInterruptionException ex) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Connection error: " + ex.getMessage());
                    JOptionPane.showMessageDialog(parentApp, "Error: " + ex.getMessage() + "\nCannot perform login.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Clear the password char array from memory immediately after use
                    Arrays.fill(passwordChars, ' ');
                    passwordField.setText(""); // Clear password field in GUI
                }
            }
        });
    }
}