/**
 * LoginWindow provides authentication interface for tourists.
 * Entry condition: Tourist must successfully authenticate to access the system.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginWindow() {
        super("ETOUR System - Login");
        initializeUI();
    }
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Tourist Authentication", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        formPanel.add(userLabel);
        formPanel.add(usernameField);
        formPanel.add(passLabel);
        formPanel.add(passwordField);
        // Placeholder for spacing
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        // Login button action - simplified authentication for simulation
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getPassword();
            // Simple validation - in real system, this would connect to authentication server
            if (username.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Simulate successful authentication
            dispose();
            new AdvancedSearchForm().setVisible(true);
        });
        cancelButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
}