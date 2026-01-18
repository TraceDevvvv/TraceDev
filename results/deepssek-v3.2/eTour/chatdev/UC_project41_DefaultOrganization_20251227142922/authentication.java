/**
 * Authentication: Provides login functionality for restaurant operators.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Authentication extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static final String VALID_USERNAME = "operator";
    private static final String VALID_PASSWORD = "admin123";
    public Authentication() {
        setTitle("Restaurant System Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        loginButton.addActionListener(e -> authenticate());
        cancelButton.addActionListener(e -> System.exit(0));
        panel.add(loginButton);
        panel.add(cancelButton);
        add(panel);
    }
    private void authenticate() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Simple authentication check
        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            JOptionPane.showMessageDialog(this,
                "Authentication successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close login window
            SwingUtilities.invokeLater(() -> {
                new MenuEditorFrame().setVisible(true); // Open menu editor
            });
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid credentials. Please try again.",
                "Authentication Failed",
                JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Authentication().setVisible(true);
        });
    }
}