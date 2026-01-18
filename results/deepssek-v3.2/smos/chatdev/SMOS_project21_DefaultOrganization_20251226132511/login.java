/**
 * LoginDialog provides authentication for administrators before accessing the system
 * This satisfies the precondition: "The user is logged in to the system as an administrator"
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean authenticated;
    /**
     * Constructor creates the login dialog
     * @param parent Parent frame (can be null for standalone dialog)
     */
    public LoginDialog(JFrame parent) {
        super(parent, "Administrator Login", true);
        this.authenticated = false;
        initializeComponents();
        layoutComponents();
        setupEventHandlers();
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    /**
     * Initialize all GUI components for the login dialog
     */
    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        // Set mnemonics for accessibility
        loginButton.setMnemonic(KeyEvent.VK_L);
        cancelButton.setMnemonic(KeyEvent.VK_C);
    }
    /**
     * Layout the components in the dialog
     */
    private void layoutComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        JLabel titleLabel = new JLabel("Administrator Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        // Username label and field
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        // Password label and field
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        // Add padding around the panel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        wrapperPanel.add(panel, BorderLayout.CENTER);
        setContentPane(wrapperPanel);
    }
    /**
     * Setup event handlers for buttons and fields
     */
    private void setupEventHandlers() {
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticated = false;
                dispose();
            }
        });
        // Allow pressing Enter in password field to submit login
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    authenticateUser();
                }
            }
        });
        // Allow pressing Enter in username field to submit login
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    authenticateUser();
                }
            }
        });
    }
    /**
     * Authenticate the user with provided credentials
     * For simulation purposes, uses hardcoded admin credentials
     */
    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Simulate authentication - in a real system this would check against a database
        if (username.equals("admin") && password.equals("password")) {
            authenticated = true;
            dispose();
            JOptionPane.showMessageDialog(this, 
                "Login successful!\nWelcome, Administrator.", 
                "Authentication Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Clear fields and show error
            passwordField.setText("");
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password.\nPlease try again.", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
            usernameField.requestFocus();
            usernameField.selectAll();
        }
    }
    /**
     * Check if user is authenticated
     * @return true if authentication was successful
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
}