'''
Handles administrator login as per precondition:
"The user is logged in to the system as an administrator"
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private DatabaseSimulator db;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginFrame(DatabaseSimulator db) {
        this.db = db;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Class Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title panel
        JLabel titleLabel = new JLabel("Administrator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        JButton loginButton = new JButton("Login");
        formPanel.add(loginButton);
        JButton cancelButton = new JButton("Cancel");
        formPanel.add(cancelButton);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(mainPanel);
    }
    /**
     * Attempts to log in as administrator.
     * For simplicity, using hardcoded credentials.
     * In real application, this would validate against a user database.
     */
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Simple authentication (in real app, use secure authentication)
        if (username.equals("admin") && password.equals("admin123")) {
            // Login successful - proceed to class list
            dispose(); // Close login window
            showClassList();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password. Please try again.\n" +
                "Use: admin / admin123",
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Shows the class list after successful login.
     */
    private void showClassList() {
        // Start with class details view (simulating the precondition:
        // "the user has made the case of use 'viewdettagliSlasse'")
        // We'll show the first class by default for demonstration
        if (!db.getAllClasses().isEmpty()) {
            int firstClassId = db.getAllClasses().get(0).getId();
            ClassDetailsFrame detailsFrame = new ClassDetailsFrame(db, firstClassId);
            detailsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "No classes available in the system.",
                "No Data", 
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}