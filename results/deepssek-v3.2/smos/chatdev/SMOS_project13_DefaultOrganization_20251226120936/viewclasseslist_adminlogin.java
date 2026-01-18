'''
Admin Login module for ViewClassesList use case.
Handles authentication with administrator role verification before
proceeding to Class Management screen. Includes simulated credentials
and basic session management.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminLogin {
    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private boolean authenticated = false;
    private String sessionUser = null;
    // Simulated credentials database
    private static final String[][] VALID_USERS = {
        {"admin1", "pass123", "Administrator"},
        {"admin2", "mypass", "Administrator"},
        {"user1", "userpass", "Teacher"}
    };
    public AdminLogin() {
        initializeLogin();
    }
    private void initializeLogin() {
        loginFrame = new JFrame("System Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        centerPanel.add(usernameField, gbc);
        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        centerPanel.add(passwordField, gbc);
        // Role
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        String[] roles = {"Administrator", "Teacher"};
        roleComboBox = new JComboBox<>(roles);
        centerPanel.add(roleComboBox, gbc);
        loginFrame.add(centerPanel, BorderLayout.CENTER);
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        loginFrame.add(buttonPanel, BorderLayout.SOUTH);
        // Event listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Allow Enter key to submit login
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        loginFrame.setVisible(true);
    }
    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String selectedRole = (String) roleComboBox.getSelectedItem();
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, 
                "Please enter both username and password", 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean found = false;
        String userRole = null;
        for (String[] user : VALID_USERS) {
            if (user[0].equals(username) && user[1].equals(password)) {
                found = true;
                userRole = user[2];
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(loginFrame, 
                "Invalid username or password", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!userRole.equals(selectedRole)) {
            JOptionPane.showMessageDialog(loginFrame, 
                "Selected role does not match user credentials", 
                "Role Mismatch", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if Administrator role is selected
        if (!selectedRole.equals("Administrator")) {
            JOptionPane.showMessageDialog(loginFrame, 
                "Access denied: Administrator role required for Class Management", 
                "Access Denied", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        authenticated = true;
        sessionUser = username;
        JOptionPane.showMessageDialog(loginFrame, 
            "Login successful!\nWelcome Administrator: " + username, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        loginFrame.dispose();
        showMainApplication();
    }
    private void showMainApplication() {
        SwingUtilities.invokeLater(() -> {
            final ViewClassesList app = new ViewClassesList(sessionUser);
            app.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    app.shutdown();
                }
            });
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminLogin();
        });
    }
}