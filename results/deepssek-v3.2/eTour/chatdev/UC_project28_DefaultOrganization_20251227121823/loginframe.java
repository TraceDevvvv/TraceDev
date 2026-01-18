/**
 * Login frame for agency operator authentication
 * Implements entry condition: agency has logged in
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthService authService;
    public LoginFrame() {
        setTitle("Agency Login - DeleteTag System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center window
        authService = new AuthService();
        initComponents();
    }
    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Agency Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, gbc);
        // Username
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);
        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);
        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        mainPanel.add(loginButton, gbc);
        add(mainPanel);
    }
    private void authenticateUser() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();
        // Validate input
        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Authenticate using service
        try {
            if (authService.authenticate(username, new String(password))) {
                JOptionPane.showMessageDialog(this, "Login successful!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear password from memory
                Arrays.fill(password, ' ');
                // Open DeleteTagFrame
                DeleteTagFrame deleteTagFrame = new DeleteTagFrame();
                deleteTagFrame.setVisible(true);
                dispose(); // Close login window
            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid username or password",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
                // Clear password field
                passwordField.setText("");
                usernameField.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Authentication error: " + e.getMessage(),
                "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
/**
 * Mock authentication service that simulates secure authentication
 * In a real application, this would connect to an authentication server
 */
class AuthService {
    private Map<String, String> userCredentials; // username -> hashed password
    public AuthService() {
        userCredentials = new HashMap<>();
        // Simulated user database with hashed passwords
        userCredentials.put("agency", hashPassword("securePassword123")); // Hashed password
        userCredentials.put("admin", hashPassword("adminPass456"));
        userCredentials.put("operator", hashPassword("operatorPass789"));
    }
    /**
     * Authenticate user with credentials
     * @param username The username to authenticate
     * @param password The password to verify
     * @return true if authentication successful
     */
    public boolean authenticate(String username, String password) {
        // Check if username exists
        if (!userCredentials.containsKey(username)) {
            return false;
        }
        // Get stored hashed password
        String storedHash = userCredentials.get(username);
        // Hash the provided password and compare
        String providedHash = hashPassword(password);
        // Secure comparison to prevent timing attacks
        return MessageDigest.isEqual(
            storedHash.getBytes(),
            providedHash.getBytes()
        );
    }
    /**
     * Hash password using SHA-256 for demonstration
     * In production, use stronger algorithms like bcrypt or Argon2
     * @param password The password to hash
     * @return Hashed password as hex string
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not available", e);
        }
    }
    /**
     * Add a new user (for demonstration purposes)
     * @param username The username
     * @param password The password (will be hashed)
     */
    public void addUser(String username, String password) {
        userCredentials.put(username, hashPassword(password));
    }
    /**
     * Remove a user from the system
     * @param username The username to remove
     */
    public void removeUser(String username) {
        userCredentials.remove(username);
    }
}