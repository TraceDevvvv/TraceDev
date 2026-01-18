/**
 * Represents a mock login screen for the ATA staff.
 * This screen fulfills the precondition that "The user must be logged in to the system as the ATA staff".
 * Upon successful (mock) login, it proceeds to the Class Selection Screen.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // Added import for Arrays utility class
public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    /**
     * Constructs the LoginScreen.
     * Sets up the GUI components for username, password, and a login button.
     */
    public LoginScreen() {
        setTitle("ATA Staff Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField("ata_staff");
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("password");
        panel.add(passwordField);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        panel.add(loginButton);
        // Add an empty label for spacing in the grid
        panel.add(new JLabel("")); 
        add(panel);
        setVisible(true);
    }
    /**
     * Simulates an attempt to log in.
     * In a real system, this would validate credentials against a database.
     * For this mock, any non-empty username/password will be considered successful.
     * On success, opens the ClassSelectionScreen and disposes of the LoginScreen.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        // Mock login logic: For simplicity, any non-empty fields count as successful.
        if (!username.isEmpty() && password.length > 0) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to the class selection screen
            new ClassSelectionScreen();
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Clear password for security
        Arrays.fill(password, '0');
    }
}