/**
 * A JPanel for user login. It provides input fields for username and password
 * and a login button. It notifies a `LoginListener` upon successful login.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    // Listener interface for login events
    public interface LoginListener {
        void onLoginSuccess(String username);
        void onLoginFailure();
    }
    private LoginListener loginListener;
    /**
     * Constructs a new LoginPanel.
     */
    public LoginPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.anchor = GridBagConstraints.WEST;
        // Title
        JLabel titleLabel = new JLabel("Administrator Login");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);
        // Username Label
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Username:"), gbc);
        // Username Field
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);
        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);
        // Password Field
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);
        // Login Button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
        // Message Label (for login feedback)
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(messageLabel, gbc);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
    }
    /**
     * Sets the listener for login events.
     * @param listener The object that will receive login success/failure notifications.
     */
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
    /**
     * Attempts to authenticate the user based on the entered username and password.
     * For this simulation, valid credentials are "admin" / "password".
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Get password as String safely
        // Simulate administrator login credentials
        if ("admin".equals(username) && "password".equals(password)) {
            messageLabel.setText(""); // Clear any previous error message
            if (loginListener != null) {
                loginListener.onLoginSuccess(username);
            }
        } else {
            messageLabel.setText("Invalid username or password.");
            if (loginListener != null) {
                loginListener.onLoginFailure();
            }
        }
    }
}