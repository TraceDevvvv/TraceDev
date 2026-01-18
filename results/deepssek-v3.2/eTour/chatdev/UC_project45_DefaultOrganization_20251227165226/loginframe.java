'''
GUI frame for operator authentication.
Implements the entry condition: "The Point Of Restaurant Operator has successfully authenticated to the system."
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private RestaurantOperator operator;
    public LoginFrame() {
        setTitle("Point of Restaurant - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create a sample operator for demonstration
        operator = new RestaurantOperator("operator1", "password123", "City Bistro");
        initComponents();
    }
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Point of Restaurant System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        // Login Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 2;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.gridwidth = 2;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);
        // Demo credentials label
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 3;
        JLabel demoLabel = new JLabel("Demo: operator1 / password123");
        demoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        demoLabel.setForeground(Color.GRAY);
        formPanel.add(demoLabel, gbc);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
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
                System.exit(0);
            }
        });
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        // Add panels to frame
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Authenticates the user using the provided credentials.
     * If successful, opens the main menu.
     */
    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password.", 
                "Missing Information", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (operator.authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, 
                "Login successful!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            // Close login window and open main menu
            this.dispose();
            MainMenuFrame mainMenu = new MainMenuFrame(operator);
            mainMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password.", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
}