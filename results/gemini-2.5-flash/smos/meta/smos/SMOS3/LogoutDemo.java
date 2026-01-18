/*
 * LogoutDemo.java
 *
 * A fully runnable Java Swing application demonstrating the 'Logout' use case.
 * A registered user can log in, log out by clicking the logout button, and re-log in.
 * All necessary classes, imports, and comments are included.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class to manage user session state
class UserSession {
    private static boolean loggedIn = false;
    private static String username = null;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void login(String user) {
        loggedIn = true;
        username = user;
    }

    public static void logout() {
        loggedIn = false;
        username = null;
    }

    public static String getUsername() {
        return username;
    }
}

// Login form UI
class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginForm() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        panel.add(messageLabel);

        add(panel, BorderLayout.CENTER);

        // Action listener for login button
        loginButton.addActionListener(e -> handleLogin());

        // Pressing Enter in password field triggers login
        passwordField.addActionListener(e -> handleLogin());
    }

    // Simulate authentication (accepts any non-empty username/password)
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username and password required.");
            return;
        }
        // In real system, check credentials here
        UserSession.login(username);
        // Open main app and close login form
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
        dispose();
    }
}

// Main application UI after login
class MainApp extends JFrame {
    public MainApp() {
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + UserSession.getUsername() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        add(logoutButton, BorderLayout.SOUTH);

        // Action listener for logout button
        logoutButton.addActionListener(e -> handleLogout());
    }

    // Handles logout logic
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            UserSession.logout();
            // Show login form again
            SwingUtilities.invokeLater(() -> {
                new LoginForm().setVisible(true);
            });
            dispose();
        }
    }
}

// Main entry point
public class LogoutDemo {
    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Show login form at startup
            new LoginForm().setVisible(true);
        });
    }
}
