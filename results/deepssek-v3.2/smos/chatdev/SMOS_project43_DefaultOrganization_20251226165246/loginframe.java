'''
Login screen for administrator authentication.
Implements the login precondition for DeleteNote use case.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame {
    private NoteSystem system;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    /**
     * Constructor sets up the login interface.
     * 
     * @param system the NoteSystem instance
     */
    public LoginFrame(NoteSystem system) {
        this.system = system;
        setTitle("Administrator Login - Disciplinary System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Username field
        mainPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        mainPanel.add(usernameField);
        // Password field
        mainPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);
        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        mainPanel.add(new JLabel()); // Empty cell
        mainPanel.add(loginButton);
        add(mainPanel);
    }
    /**
     * Handles administrator login.
     * Validates credentials and opens registry screen if successful.
     */
    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean success = system.loginAdmin(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Login successful. Welcome Administrator.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            RegistryFrame registryFrame = new RegistryFrame(system);
            registryFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
}