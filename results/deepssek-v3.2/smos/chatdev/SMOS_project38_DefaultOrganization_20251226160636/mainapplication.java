/**
 * MainApplication is the entry point of the Justice Management System.
 * It handles administrator login and navigation to justice viewing functionality.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainApplication extends JFrame {
    private Authentication auth;
    private JusticeService justiceService;
    private JTabbedPane tabbedPane; // Store reference for easy access
    // Login components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    // Justice viewing components
    private JTextField justiceIdField;
    private JButton viewJusticeButton;
    private JButton logoutButton;
    /**
     * Constructor initializes the main application.
     */
    public MainApplication() {
        auth = new Authentication();
        justiceService = new JusticeService();
        initializeUI();
    }
    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        setTitle("Justice Management System - Administrator Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 300);
        // Title panel
        JLabel titleLabel = new JLabel("Administrator Justice Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel, BorderLayout.NORTH);
        // Main panel with tabbed interface
        tabbedPane = new JTabbedPane();
        // Login tab
        JPanel loginPanel = createLoginPanel();
        tabbedPane.addTab("Administrator Login", loginPanel);
        // Justice View tab (initially disabled)
        JPanel justicePanel = createJusticePanel();
        tabbedPane.addTab("View Justice Details", justicePanel);
        tabbedPane.setEnabledAt(1, false); // Disabled until login
        add(tabbedPane, BorderLayout.CENTER);
        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Status: Not logged in");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
        // Center window on screen
        setLocationRelativeTo(null);
    }
    /**
     * Creates the login panel with username/password fields.
     *
     * @return JPanel containing login components.
     */
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setText("admin"); // Default for demo
        panel.add(usernameField, gbc);
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setText("admin123"); // Default for demo
        panel.add(passwordField, gbc);
        // Login button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login as Administrator");
        loginButton.addActionListener(new LoginButtonListener());
        panel.add(loginButton, gbc);
        return panel;
    }
    /**
     * Creates the justice viewing panel.
     *
     * @return JPanel containing justice viewing components.
     */
    private JPanel createJusticePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Instructions
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel instructionLabel = new JLabel("<html>Enter Justice ID to view details.<br>"
            + "Sample IDs: J001, J002, J003</html>");
        panel.add(instructionLabel, gbc);
        // Justice ID input
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Justice ID:"), gbc);
        gbc.gridx = 1;
        justiceIdField = new JTextField(10);
        justiceIdField.setText("J001"); // Default for demo
        panel.add(justiceIdField, gbc);
        // View Justice button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        viewJusticeButton = new JButton("View Justice Details");
        viewJusticeButton.addActionListener(new ViewJusticeButtonListener());
        panel.add(viewJusticeButton, gbc);
        // Logout button
        gbc.gridy = 3;
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new LogoutButtonListener());
        panel.add(logoutButton, gbc);
        return panel;
    }
    /**
     * Action listener for the Login button.
     */
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            boolean success = auth.login(username, password);
            if (success) {
                statusLabel.setText("Status: Logged in as " + username);
                statusLabel.setForeground(Color.GREEN.darker());
                // Enable justice viewing tab
                tabbedPane.setEnabledAt(1, true);
                tabbedPane.setSelectedIndex(1); // Switch to justice tab
                JOptionPane.showMessageDialog(MainApplication.this, 
                    "Login successful! You can now view justice details.");
            } else {
                statusLabel.setText("Status: Login failed");
                statusLabel.setForeground(Color.RED);
            }
        }
    }
    /**
     * Action listener for the View Justice button.
     * Implements the use case: "ViewJustificationDetails"
     */
    private class ViewJusticeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!auth.isLoggedIn()) {
                JOptionPane.showMessageDialog(MainApplication.this, 
                    "You must be logged in as administrator to view justice details.",
                    "Authentication Required",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String justiceId = justiceIdField.getText().trim();
            if (justiceId.isEmpty()) {
                JOptionPane.showMessageDialog(MainApplication.this, 
                    "Please enter a Justice ID.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Open the justice details form
            SwingUtilities.invokeLater(() -> {
                JusticeForm form = new JusticeForm(justiceService, auth, justiceId);
                form.setVisible(true);
            });
        }
    }
    /**
     * Action listener for the Logout button.
     */
    private class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            auth.logout();
            statusLabel.setText("Status: Logged out");
            statusLabel.setForeground(Color.BLACK);
            // Disable justice viewing tab
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setSelectedIndex(0); // Switch back to login tab
            JOptionPane.showMessageDialog(MainApplication.this, 
                "You have been logged out successfully.");
        }
    }
    /**
     * Main method to launch the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
            // Display welcome message
            JOptionPane.showMessageDialog(app, 
                "Welcome to the Justice Management System\n\n" +
                "Default Login Credentials:\n" +
                "Username: admin\n" +
                "Password: admin123\n\n" +
                "Sample Justice IDs: J001, J002, J003",
                "System Information",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
}