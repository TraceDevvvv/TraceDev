'''
LoginPanel provides the interface for user login.
It simulates the login process required before logout.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginPanel extends JPanel {
    private MainFrame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    /**
     * Constructs a LoginPanel.
     * @param parentFrame The main application frame for switching panels
     */
    public LoginPanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    /**
     * Initializes all UI components for the login panel.
     */
    private void initializeComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        statusLabel = new JLabel("Please login to access logout functionality");
        // Set some visual properties
        statusLabel.setForeground(Color.BLUE);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
    }
    /**
     * Sets up the layout of the login panel.
     */
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Login System", JLabel.CENTER), gbc);
        // Username row
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        // Password row
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        // Login button
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(loginButton, gbc);
        // Status label
        gbc.gridy = 4;
        add(statusLabel, gbc);
        // Instructions
        gbc.gridy = 5;
        JLabel instructionLabel = new JLabel("<html><center>For demo purposes:<br>Use any username/password combination</center></html>");
        instructionLabel.setForeground(Color.DARK_GRAY);
        add(instructionLabel, gbc);
    }
    /**
     * Sets up event handlers for login button.
     */
    private void setupEventHandlers() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        // Allow login by pressing Enter in password field
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }
    /**
     * Performs the login operation and switches to logout panel on success.
     */
    private void performLogin() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();
        // Basic validation
        if (username.isEmpty()) {
            statusLabel.setText("Username cannot be empty!");
            statusLabel.setForeground(Color.RED);
            return;
        }
        // In a real application, you would validate credentials here
        // For demo purposes, we accept any non-empty username
        UserSession session = UserSession.getInstance();
        session.login(username);
        // Clear password for security
        passwordField.setText("");
        statusLabel.setText("Login successful! Redirecting...");
        statusLabel.setForeground(Color.GREEN);
        // Switch to logout panel after a brief delay
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToLogoutPanel();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    /**
     * Resets the login form fields and status.
     * Called when switching back to the login panel from logout.
     */
    public void resetForm() {
        usernameField.setText("");
        passwordField.setText("");
        statusLabel.setText("Please login to access logout functionality");
        statusLabel.setForeground(Color.BLUE);
        usernameField.requestFocusInWindow(); // Set focus to username field for convenience
    }
}