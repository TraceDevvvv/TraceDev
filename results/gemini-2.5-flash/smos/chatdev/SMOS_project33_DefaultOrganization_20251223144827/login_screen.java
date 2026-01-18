/*
LoginScreen.java
 DOCSTRING
    This class provides a simple graphical user interface for administrator login.
    Upon successful "login" (simulated by clicking a button with predefined credentials),
    it initiates the main AdminDelayManagementApp.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public LoginScreen() {
        // Set up the JFrame properties
        setTitle("Administrator Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Create UI components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        // Set up layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(loginButton, gbc);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate login functionality
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // For this example, we'll use a very simple validation
                // In a real application, this would involve database checks or authentication serv
                if ("admin".equals(username) && "admin123".equals(password)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Open the main application window
                    AdminDelayManagementApp app = new AdminDelayManagementApp();
                    app.setVisible(true);
                    // Close the login window
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
}