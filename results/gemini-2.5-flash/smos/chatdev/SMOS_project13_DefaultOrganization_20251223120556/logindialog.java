'''
A modal dialog box for user login.
This dialog handles the authentication process before the main application frame is displayed,
fulfilling the "user is logged in" precondition.
'''
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private AuthService authService;
    private boolean authenticated = false; // Flag to indicate successful authentication
    /**
     * Constructs a new LoginDialog.
     *
     * @param owner The Frame from which the dialog is displayed. Can be null for an initial dialog.
     */
    public LoginDialog(Frame owner) {
        super(owner, "Administrator Login", true); // Set as modal
        authService = new AuthService(); // Instantiate the authentication service
        initUI();
    }
    /**
     * Initializes the user interface components of the login dialog.
     * Sets up labels, text fields, password field, and buttons.
     */
    private void initUI() {
        // Set layout for the dialog content pane
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Username:"), gbc);
        // Username Field
        usernameField = new JTextField(15);
        usernameField.setText("admin"); // Pre-fill for convenience
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);
        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Password:"), gbc);
        // Password Field
        passwordField = new JPasswordField(15);
        passwordField.setText("adminpass"); // Pre-fill for convenience
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);
        // Login Button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(loginButton, gbc);
        // Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cancelButton, gbc);
        // Make the dialog fit its components
        pack();
        // Center the dialog on the screen
        setLocationRelativeTo(owner);
    }
    /**
     * Attempts to log in the user using the provided credentials.
     * If authentication is successful and the user is an administrator, sets the flag
     * `authenticated` to true and disposes the dialog. Otherwise, shows an error message.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = authService.login(username, password);
        if (user != null && user.isAdmin()) {
            authenticated = true; // Set flag to true on successful admin login
            dispose(); // Close the dialog
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Administrator credentials.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password field for security
        }
    }
    /**
     * Returns whether the user successfully authenticated as an administrator.
     *
     * @return true if authentication was successful, false otherwise.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
}