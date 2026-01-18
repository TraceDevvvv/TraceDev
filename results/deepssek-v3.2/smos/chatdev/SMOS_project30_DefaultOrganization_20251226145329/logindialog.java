/**
 * LoginDialog.java
 *
 * This class implements a login dialog for user authentication.
 * It verifies user credentials and checks if the user has administrator privileges
 * before allowing access to the main application.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean authenticated;
    private boolean administrator;
    public LoginDialog(JFrame parent) {
        super(parent, "Administrator Login", true);
        authenticated = false;
        administrator = false;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        usernameField = new JTextField(15);
        add(usernameField, gbc);
        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);
        // Login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        add(loginButton, gbc);
        // Cancel button
        gbc.gridx = 2;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton, gbc);
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    /**
     * Authenticates the user by checking credentials.
     * For demonstration purposes, uses hardcoded admin credentials.
     * In a real application, this would connect to an authentication server.
     */
    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Check for empty fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password.",
                "Login Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // In a real application, this would be a database or LDAP query
        // For this demo, we'll use hardcoded administrator credentials
        if (username.equals("admin") && password.equals("admin123")) {
            authenticated = true;
            administrator = true;
            JOptionPane.showMessageDialog(this,
                "Login successful! Welcome, Administrator.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the login dialog
        } else {
            authenticated = false;
            administrator = false;
            JOptionPane.showMessageDialog(this,
                "Invalid username or password. Please try again.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
            // Clear password field for retry
            passwordField.setText("");
        }
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public boolean isAdministrator() {
        return administrator;
    }
}