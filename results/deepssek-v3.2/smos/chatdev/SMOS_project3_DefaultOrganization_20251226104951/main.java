'''
Main class for the Logout use case GUI application.
This program simulates a simple login/logout system with a graphical interface.
A user can log in with a username and password, and after logging in, can log out,
which returns to the login form.
The program uses Java Swing for the GUI.
Supports multiple registered users with proper authentication.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class main {
    private JFrame frame;
    private JPanel loginPanel;
    private JPanel dashboardPanel;
    private JLabel statusLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel welcomeLabel; // Instance variable for direct reference
    private Map<String, String> registeredUsers;
    private String currentUser;
    public main() {
        registeredUsers = new HashMap<>();
        registeredUsers.put("admin", "password123");
        registeredUsers.put("alice", "alice123");
        registeredUsers.put("bob", "bob456");
        registeredUsers.put("charlie", "charlie789");
        frame = new JFrame("Login/Logout System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new CardLayout());
        loginPanel = createLoginPanel();
        dashboardPanel = createDashboardPanel();
        frame.add(loginPanel, "LOGIN");
        frame.add(dashboardPanel, "DASHBOARD");
        showLoginPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameField.setToolTipText("Enter your username");
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setToolTipText("Enter your password");
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        // Bind Enter key to trigger login button
        usernameField.addActionListener(e -> loginButton.doClick());
        passwordField.addActionListener(e -> loginButton.doClick());
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel infoLabel = new JLabel("Registered users: admin, alice, bob, charlie");
        infoLabel.setForeground(Color.BLUE);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(statusLabel);
        panel.add(new JLabel());
        panel.add(infoLabel);
        return panel;
    }
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        welcomeLabel = new JLabel("", SwingConstants.CENTER); // Initialize instance variable
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
        // Add keyboard shortcut (Alt+L) for logout
        logoutButton.setMnemonic('L');
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(logoutButton);
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Clear any previous status messages
        statusLabel.setText(" ");
        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Username and password cannot be empty.");
            return;
        }
        if (!registeredUsers.containsKey(username)) {
            statusLabel.setText("Username not found. Please use a registered username.");
            return;
        }
        String storedPassword = registeredUsers.get(username);
        if (!storedPassword.equals(password)) {
            statusLabel.setText("Invalid password for user: " + username);
            return;
        }
        // Store current user for logout acknowledgement
        currentUser = username;
        usernameField.setText("");
        passwordField.setText("");
        statusLabel.setText(" ");
        showDashboardPanel();
        updateWelcomeMessage(username);
    }
    private void updateWelcomeMessage(String username) {
        // Directly update the welcome label using the instance variable
        welcomeLabel.setText("Welcome " + username + "! You are logged in.");
    }
    private void handleLogout() {
        // Show confirmation dialog before logging out
        int choice = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            // Show logout success message
            JOptionPane.showMessageDialog(frame,
                    "Goodbye " + currentUser + "! You have been logged out successfully.",
                    "Logout Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            currentUser = null;
            showLoginPanel();
            clearWelcomeMessage();
            usernameField.requestFocusInWindow(); // Set focus to username field
        }
        // If NO option is selected, stay on dashboard
    }
    private void clearWelcomeMessage() {
        // Directly clear the welcome label using the instance variable
        welcomeLabel.setText("");
    }
    private void showLoginPanel() {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), "LOGIN");
    }
    private void showDashboardPanel() {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), "DASHBOARD");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main();
            }
        });
    }
}