/*
 * Represents the login window of the application.
 * Allows users to enter credentials and attempt to log in.
 * Upon successful login, it hides itself and opens the MainApplicationFrame.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private JButton loginButton;
    /**
     * Constructor for the LoginFrame.
     * Initializes the GUI components and sets up the layout and listeners.
     */
    public LoginFrame() {
        setTitle("Login to System"); // Set the window title
        setSize(350, 250); // Set initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define behavior on close
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Make the window non-resizable
        // Set up the main panel with a GridBagLayout for flexible component placement
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        add(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components should fill their display area horizontally
        // Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);
        // Message Label (for login feedback)
        gbc.gridx = 0;
        gbc.gridy = 3;
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED); // Set text color to red for error messages
        panel.add(messageLabel, gbc);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin(); // Call the method to handle login attempt
            }
        });
        // Allow pressing Enter key to trigger login from password field
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        // Allow pressing Enter key to trigger login from username field
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
    }
    /**
     * Attempts to log in the user based on the entered credentials.
     * This method contains dummy logic for demonstration purposes.
     * In a real application, it would interact with a backend authentication service.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        // Get password as a char array for security, then convert to String for comparison here.
        // In a real application, the char array should be used directly for comparison and then cleared.
        String password = new String(passwordField.getPassword());
        // Dummy authentication logic: user "user" with password "password" is valid
        if (username.equals("user") && password.equals("password")) {
            UserSession.login(username); // Update the user session status
            messageLabel.setText(""); // Clear any previous error messages
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Dispose of the current login frame and show the main application frame
            // This prevents an accumulation of hidden LoginFrame instances in memory.
            this.dispose(); // Dispose the current login window
            new MainApplicationFrame().setVisible(true); // Create and display the main application window
        } else {
            messageLabel.setText("Invalid Username or Password!"); // Display error message
            passwordField.setText(""); // Clear password field for security
        }
        // Clear the password char array after use for security
        java.util.Arrays.fill(passwordField.getPassword(), ' ');
    }
}