/**
 * This panel simulates a login screen for administrators.
 * It is not directly required by the use case, but it is included to satisfy preconditions.
 * Precondition: The user is logged in as an administrator.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    // Reference to the main application frame for switching panels after login.
    private ApplicationFrame appFrame;
    public LoginPanel(ApplicationFrame appFrame) {
        this.appFrame = appFrame;
        setLayout(new GridLayout(3, 2, 5, 5));
        // Create GUI components.
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        // Add action listener to the login button.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Simple authentication: For demo, accept any non-empty credentials.
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Assume login is successful and user is an administrator.
                    JOptionPane.showMessageDialog(LoginPanel.this,
                            "Login successful! You are now logged in as an administrator.",
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // In a real system, you would switch to the main application view.
                    // For simplicity, we just enable the request viewing functionality.
                    // This panel could be removed and replaced with the main view.
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this,
                            "Invalid credentials. Please try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Add components to the panel.
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel()); // empty cell for layout
        add(loginButton);
    }
}