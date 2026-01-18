'''
@file LoginFrame.java
@brief Provides mock authentication for Tourist.
@details After successful login, it proceeds to the main application frame.
@version 1.0
@date 2023-10-15
'''
package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    /**
     * Constructor to initialize the login frame.
     */
    public LoginFrame() {
        super("ETOUR Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        // Login button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        // Add ActionListener for login button
        loginButton.addActionListener(e -> attemptLogin());
        panel.add(loginButton, gbc);
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    /**
     * Attempts to authenticate the user with mock validation.
     * @details For demonstration, any non-empty username and password is accepted.
     */
    private void attemptLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();
        // Basic validation
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }
        // Simulate successful authentication
        JOptionPane.showMessageDialog(this, "Login successful!");
        dispose(); // Close login window
        new MainFrame(user); // Open main application window
    }
}