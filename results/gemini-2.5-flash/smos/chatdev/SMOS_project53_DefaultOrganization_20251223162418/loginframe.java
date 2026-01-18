'''
A JFrame that provides a graphical user interface for user login.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    /**
     * Constructs the login frame setting up all GUI components and listeners.
     */
    public LoginFrame() {
        // Set up the frame properties
        setTitle("ATA Staff Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Set up the main panel with a GridBagLayout for better control over component positioning.
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        add(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components expand horizontally
        // Username label and field
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        // Password label and field
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        // Login button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        gbc.gridwidth = 2; // Span across two columns
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);
        // Status label for messages (e.g., login failed)
        gbc.gridx = 0; // Column 0
        gbc.gridy = 3; // Row 3
        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED); // Make error messages red
        panel.add(statusLabel, gbc);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        // Allow pressing Enter key to trigger login when fields are focused
        ActionListener enterKeyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        };
        usernameField.addActionListener(enterKeyListener);
        passwordField.addActionListener(enterKeyListener);
    }
    /**
     * Attempts to log in the user using the credentials from the input fields.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Get password as char array, convert to String
        // Authenticate using the AuthService
        User authenticatedUser = AuthService.authenticate(username, password);
        if (authenticatedUser != null) {
            // Precondition met: user is logged in as staff
            statusLabel.setText("Login successful!");
            statusLabel.setForeground(Color.BLUE);
            JOptionPane.showMessageDialog(this, "Login successful as staff!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Close login window and open the class list window
            dispose(); // Close this login frame
            SwingUtilities.invokeLater(() -> {
                ClassListFrame classListFrame = new ClassListFrame(); // ClassListFrame name remains as it describes the view
                classListFrame.setVisible(true);
            });
        } else {
            // Login failed
            statusLabel.setText("Invalid username or password, or not a staff account.");
            statusLabel.setForeground(Color.RED);
        }
    }
}