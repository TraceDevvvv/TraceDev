/**
Authentication window that verifies user credentials before granting access
to the search preference modification functionality.
Simulates authentication to meet the entry condition requirement.
*/
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AuthenticationFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseSimulator dbSimulator;
    private JButton retryButton;
    private JPanel statusPanel;
    public AuthenticationFrame() {
        dbSimulator = DatabaseSimulator.getInstance();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("ETOUR System - Authentication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title label
        JLabel titleLabel = new JLabel("ETOUR Authentication", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> authenticateUser());
        formPanel.add(loginButton);
        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> System.exit(0));
        formPanel.add(cancelButton);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Status panel with retry button (initially hidden)
        statusPanel = new JPanel(new BorderLayout(10, 5));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statusPanel.setVisible(false);
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        retryButton = new JButton("Retry Connection");
        retryButton.addActionListener(e -> {
            // Attempt to reconnect
            dbSimulator.setServerConnected(true);
            statusPanel.setVisible(false);
            usernameField.setEnabled(true);
            passwordField.setEnabled(true);
            loginButton.setEnabled(true);
            cancelButton.setEnabled(true);
        });
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.add(retryButton, BorderLayout.EAST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        // Enter key support for password field
        passwordField.addActionListener(e -> authenticateUser());
        add(mainPanel);
        setVisible(true);
    }
    private void authenticateUser() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();
        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password", 
                "Authentication Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate authentication
        try {
            boolean authenticated = dbSimulator.authenticateUser(username, new String(password));
            if (authenticated) {
                JOptionPane.showMessageDialog(this, 
                    "Authentication successful! Loading preferences...", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Close authentication window and open preference manager
                dispose();
                new SearchPreferenceManager(username);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid credentials. Please try again.", 
                    "Authentication Failed", 
                    JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        } catch (RuntimeException e) {
            // Handle server connection interruption specifically
            if (e.getMessage() != null && e.getMessage().contains("ETOUR Server connection interrupted")) {
                // Disable form fields and show retry option
                usernameField.setEnabled(false);
                passwordField.setEnabled(false);
                JButton loginButton = (JButton) ((JPanel) getContentPane().getComponent(0).getComponent(1)).getComponent(4);
                JButton cancelButton = (JButton) ((JPanel) getContentPane().getComponent(0).getComponent(1)).getComponent(5);
                loginButton.setEnabled(false);
                cancelButton.setEnabled(false);
                // Update status panel
                JLabel statusLabel = (JLabel) statusPanel.getComponent(0);
                statusLabel.setText("ETOUR Server connection interrupted. Click 'Retry Connection' to attempt reconnection.");
                statusPanel.setVisible(true);
                pack();
                setSize(400, 300);
            } else {
                // Handle other exceptions
                JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage() + "\n\nPlease check your connection and try again.",
                    "ETOUR Server Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Unexpected error occurred: " + e.getMessage(),
                "System Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}