"""
Login frame for operator authentication
Handles the entry condition: operator must successfully authenticate
"""
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    public LoginFrame() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Refreshment Point Management - Login");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create header label
        JLabel headerLabel = new JLabel("Operator Authentication", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // Add empty cells for alignment
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateOperator();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit application when cancelled
            }
        });
        // Add main panel to frame
        add(mainPanel);
    }
    private void authenticateOperator() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Simple authentication logic
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password", 
                "Authentication Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // In a real application, this would validate against a database
        // For demonstration, accept any non-empty credentials
        if (!username.isEmpty() && !password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Authentication successful!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            // Close login frame and open main application
            this.dispose();
            openModifyRefreshmentPointFrame();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid credentials", 
                "Authentication Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openModifyRefreshmentPointFrame() {
        ModifyRefreshmentPointFrame mainFrame = new ModifyRefreshmentPointFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}