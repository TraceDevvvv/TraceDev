package com.example.ui;

import com.example.model.UserCredentials;
import com.example.service.PasswordChangeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI screen for changing password.
 * Implements the use case: handle password modification error when confirmation is incorrect.
 */
public class PasswordChangeScreen extends JFrame {
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JLabel messageLabel;
    private PasswordChangeService passwordChangeService;
    private UserCredentials currentUser;

    public PasswordChangeScreen(UserCredentials user) {
        this.currentUser = user;
        this.passwordChangeService = new PasswordChangeService();

        setTitle("Change Password");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        initUI();
    }

    private void initUI() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // New password label and field
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(newPasswordLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(newPasswordField, gbc);

        // Confirm password label and field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(confirmPasswordField, gbc);

        // Message label for error/status
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(messageLabel, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton changeButton = new JButton("Change Password");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);

        // Add action listeners
        changeButton.addActionListener(new ChangeButtonListener());
        cancelButton.addActionListener(e -> {
            // In a real app, you might navigate back to the previous screen.
            // Here we just close the application.
            System.exit(0);
        });

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Listener for the change password button.
     * Implements the flow of events described in the use case.
     */
    private class ChangeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Step 1: User does not confirm password in a correct way (or does).
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            try {
                boolean success = passwordChangeService.changePassword(currentUser, newPassword, confirmPassword);

                if (success) {
                    // Successful password change (outside the error case)
                    JOptionPane.showMessageDialog(PasswordChangeScreen.this,
                            "Password changed successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // In a real app, navigate to another screen or close this one.
                    System.exit(0);
                } else {
                    // Step 2: System notifies an error message.
                    showErrorMessage("Passwords do not match. Please re-enter.");
                }
            } catch (IllegalArgumentException ex) {
                showErrorMessage("Error: " + ex.getMessage());
            }
        }

        private void showErrorMessage(String message) {
            // Step 2: System notifies an error message.
            messageLabel.setText(message);

            // Step 3: User confirms the reading of the notification.
            // We use a JOptionPane to ensure the user acknowledges the error.
            JOptionPane.showMessageDialog(PasswordChangeScreen.this,
                    message,
                    "Password Change Error",
                    JOptionPane.ERROR_MESSAGE);

            // Step 4: System returns to change password screen.
            // Already on the same screen, just clear fields and reset message.
            newPasswordField.setText("");
            confirmPasswordField.setText("");
            messageLabel.setText(" ");
        }
    }

    public static void main(String[] args) {
        // Simulate a logged-in user (for demonstration)
        UserCredentials user = new UserCredentials("john_doe", "oldPassword123");

        // Start the password change screen
        SwingUtilities.invokeLater(() -> {
            PasswordChangeScreen screen = new PasswordChangeScreen(user);
            screen.setVisible(true);
        });
    }
}