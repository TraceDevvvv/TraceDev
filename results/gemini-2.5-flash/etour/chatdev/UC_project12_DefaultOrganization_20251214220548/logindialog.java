/**
 * A modal dialog for the Agency Operator to log in.
 * It prompts for username and password and validates credentials using AgencyOperatorSession.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AgencyOperatorSession session;
    private boolean loginSuccessful = false;
    /**
     * Constructs a new LoginDialog.
     * @param parent The parent frame for this dialog.
     * @param session The AgencyOperatorSession instance to use for login validation.
     */
    public LoginDialog(Frame parent, AgencyOperatorSession session) {
        super(parent, "Operator Login", true); // Modal dialog
        this.session = session;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        pack(); // Adjusts the dialog size to fit its components
        setLocationRelativeTo(parent); // Center the dialog relative to its parent
    }
    /**
     * Initializes and lays out the GUI components of the login dialog.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setText("admin"); // Default for convenience
        panel.add(usernameField, gbc);
        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setText("admin"); // Default for convenience
        panel.add(passwordField, gbc);
        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        panel.add(loginButton, gbc);
        // Allows hitting Enter to attempt login
        getRootPane().setDefaultButton(loginButton);
        add(panel);
    }
    /**
     * Attempts to log in using the credentials entered in the fields.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (session.login(username, password)) {
            loginSuccessful = true;
            dispose(); // Close the dialog on successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password field on failure
        }
    }
    /**
     * Checks if the login attempt was successful.
     * @return True if login was successful, false otherwise.
     */
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}