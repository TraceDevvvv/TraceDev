'''
Login interface for agency operator authentication
Satisfies the entry condition "The agency has logged"
'''
package com.chatdev.newsapp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginGUI extends JFrame {
    private DatabaseManager dbManager;
    private AuthenticationService authService;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    public LoginGUI(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.authService = new AuthenticationService();
        initializeUI();
    }
    /**
     * Initializes the login user interface
     */
    private void initializeUI() {
        setTitle("Agency Operator Login - News System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        // Add title
        JLabel titleLabel = new JLabel("Agency Operator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create form panel
        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Creates the login form panel
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);
        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);
        // Status label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        statusLabel = new JLabel("Enter agency operator credentials", SwingConstants.CENTER);
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(statusLabel, gbc);
        return panel;
    }
    /**
     * Creates the button panel with login and cancel actions
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelLogin();
            }
        });
        panel.add(loginButton);
        panel.add(cancelButton);
        return panel;
    }
    /**
     * Performs the login authentication process
     */
    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Validate input
        String validationMessage = authService.validateCredentials(username, password);
        if (!validationMessage.isEmpty()) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Validation error: " + validationMessage);
            return;
        }
        // Authenticate
        if (authService.authenticateAgencyOperator(username, password)) {
            // Create session
            SessionManager session = SessionManager.getInstance();
            if (session.login(username)) {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Login successful! Redirecting...");
                // Proceed to news deletion system
                openNewsDeletionSystem();
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Session already active. Please logout first.");
            }
        } else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Invalid username or password. Please try again.");
        }
    }
    /**
     * Opens the news deletion system after successful login
     */
    private void openNewsDeletionSystem() {
        // Close login window
        this.dispose();
        // Open news deletion GUI with shared database manager
        NewsDeletionGUI newsDeletionGUI = new NewsDeletionGUI(dbManager);
        newsDeletionGUI.setVisible(true);
    }
    /**
     * Cancels the login operation
     */
    private void cancelLogin() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel login?\nThe application will exit.",
            "Cancel Login",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}