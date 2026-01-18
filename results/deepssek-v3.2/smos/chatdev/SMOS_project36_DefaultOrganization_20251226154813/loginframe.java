/**
 * Login frame for administrator authentication
 * Precondition: User must be logged in as administrator
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginFrame() {
        setTitle("Administrator Login - InsertJustification System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Administrator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Login form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        formPanel.add(new JLabel()); // Empty cell
        formPanel.add(buttonPanel);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    private void authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Simple authentication simulation
        if ("admin".equals(username) && "admin123".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Navigate to registry screen (simulating that user has already performed
            // "SviewTetTingloregister" and "viewellacogiustifies" cases)
            RegistryScreen registryScreen = new RegistryScreen();
            registryScreen.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid credentials. Please try again.", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}