'''
Login frame for authenticating the user as Direction.
Precondition: User must be logged in as Direction to access the main functionality.
This is a simulation - in a real system, authentication would connect to a server/database.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    public LoginFrame() {
        setTitle("Digital Register - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title label
        JLabel titleLabel = new JLabel("Please login as Direction", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Role:"));
        roleComboBox = new JComboBox<>(new String[]{"Select role", "Teacher", "Student", "Admin", "Direction"});
        formPanel.add(roleComboBox);
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
        formPanel.add(new JLabel()); // placeholder
        formPanel.add(loginButton);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add informational label
        JLabel infoLabel = new JLabel("Note: This is a simulation. Default role is 'Direction'", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        infoLabel.setForeground(Color.GRAY);
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Authenticates the user. This is a simulation - in a real application, this would connect to a server.
     * For this simulation, we verify that the user has selected the "Direction" role.
     */
    private void authenticate() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();
        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if role is selected
        if (selectedRole == null || selectedRole.equals("Select role")) {
            JOptionPane.showMessageDialog(this, "Please select a user role.",
                    "Role Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verify user has "Direction" role as specified in the preconditions
        if (!selectedRole.equals("Direction")) {
            JOptionPane.showMessageDialog(this, 
                    "Access denied. This application requires 'Direction' role.\n" +
                    "Selected role: " + selectedRole,
                    "Authorization Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate successful authentication for Direction role
        JOptionPane.showMessageDialog(this, 
                "Login successful as Direction user: " + username,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // close login window
        new YearSelectionFrame().setVisible(true); // open year selection
    }
}