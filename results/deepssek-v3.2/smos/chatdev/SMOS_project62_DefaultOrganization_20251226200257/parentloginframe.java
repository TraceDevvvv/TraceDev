'''
ParentLoginFrame.java
GUI for parent login to the system.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class ParentLoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseSimulator database;
    public ParentLoginFrame() {
        database = new DatabaseSimulator();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Student Report System - Parent Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        // Username label and field
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        // Password label and field
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        // Login button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        panel.add(loginButton, gbc);
        add(panel);
    }
    /**
     * Attempts to log in the parent with provided credentials.
     */
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", 
                "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (database.validateParent(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            // Open the student selection frame
            new StudentSelectionFrame(username, database).setVisible(true);
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", 
                "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password field
        }
    }
}