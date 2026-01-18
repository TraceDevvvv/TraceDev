/*
 Login interface for the system
 Validates administrator credentials before granting access
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private SMOSServerConnection serverConnection;
    public LoginFrame() {
        serverConnection = new SMOSServerConnection();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Address Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center on screen
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        // Username field
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        // Password field
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        formPanel.add(loginButton);
        // Status label
        JLabel statusLabel = new JLabel("Enter admin credentials");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(statusLabel);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add server status label
        JLabel serverStatusLabel = new JLabel(serverConnection.getServerStatus());
        serverStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serverStatusLabel.setForeground(Color.BLUE);
        mainPanel.add(serverStatusLabel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Validate administrator credentials (hardcoded for demo)
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(LoginFrame.this, 
                    "Login successful! Welcome Administrator.", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                // Open address manager frame
                AddressManagerFrame addressManager = new AddressManagerFrame(serverConnection);
                addressManager.setVisible(true);
                // Close login window
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, 
                    "Invalid credentials. Only administrators can access this system.", 
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}