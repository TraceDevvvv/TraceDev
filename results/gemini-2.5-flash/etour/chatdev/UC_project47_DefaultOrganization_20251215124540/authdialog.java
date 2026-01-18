'''
A modal dialog for user authentication to simulate the entry condition:
"Tourism has successfully authenticated to the system."
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AuthDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private TouristService touristService;
    private boolean authenticated = false;
    private String authenticatedUsername = null; // Storing the authenticated username
    /**
     * Constructs an AuthDialog.
     *
     * @param parent The parent JFrame for this dialog.
     */
    public AuthDialog(JFrame parent) {
        super(parent, "Login", true); // Modal dialog
        this.touristService = new TouristService();
        initComponents();
        setupLayout();
        addListeners();
        pack();
        setLocationRelativeTo(parent); // Center relative to parent
    }
    /**
     * Initializes the GUI components for the login dialog.
     */
    private void initComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
    }
    /**
     * Sets up the layout of the login dialog.
     */
    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        // Username Field
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        // Password Field
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        add(panel);
    }
    /**
     * Adds action listeners to the login and cancel buttons.
     */
    private void addListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticated = false;
                dispose(); // Close the dialog
            }
        });
        // Allow pressing Enter in password field to trigger login
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }
    /**
     * Handles the login attempt.
     * Uses TouristService to authenticate user.
     * Displays error messages or sets authentication status.
     */
    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (touristService.authenticate(username, password)) {
                authenticated = true;
                authenticatedUsername = username; // Store the authenticated username
                dispose(); // Close dialog on successful login
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                passwordField.setText("");
            }
        } catch (TouristService.TouristServiceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Displays the login dialog and returns the authentication status.
     *
     * @return true if the user successfully authenticated, false otherwise.
     */
    public boolean showLogin() {
        setVisible(true); // Make the dialog visible
        return authenticated;
    }
    /**
     * Get the username of the tourist who successfully authenticated.
     * This username is used as the key to retrieve/update tourist data in TouristService.
     *
     * @return The authenticated tourist's username, or null if not authenticated.
     */
    public String getAuthenticatedUsername() {
        return authenticatedUsername;
    }
}