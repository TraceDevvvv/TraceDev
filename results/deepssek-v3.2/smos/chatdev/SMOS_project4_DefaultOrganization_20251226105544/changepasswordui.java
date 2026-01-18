/**
 * Main GUI class for the Change Password application.
 * Provides a form for users to change their password.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChangePasswordUI extends JFrame {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton submitButton;
    private JButton cancelButton;
    private UserSession userSession;
    /**
     * Constructor to initialize the Change Password UI.
     * @param session The current user session containing user information.
     */
    public ChangePasswordUI(UserSession session) {
        this.userSession = session;
        initializeUI();
    }
    /**
     * Initializes the UI components and layout.
     */
    private void initializeUI() {
        setTitle("Change Password");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Old password label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Old Password:"), gbc);
        gbc.gridx = 1;
        oldPasswordField = new JPasswordField(20);
        mainPanel.add(oldPasswordField, gbc);
        // New password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        newPasswordField = new JPasswordField(20);
        mainPanel.add(newPasswordField, gbc);
        // Confirm new password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Confirm New Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20);
        mainPanel.add(confirmPasswordField, gbc);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        submitButton.addActionListener(new SubmitButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
        add(mainPanel);
    }
    /**
     * Action listener for the submit button.
     * Validates inputs and attempts to change the password.
     */
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            // Validate that all fields are filled
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(ChangePasswordUI.this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate new password and confirmation match
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(ChangePasswordUI.this, "New password and confirmation do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate new password is different from old password
            if (newPassword.equals(oldPassword)) {
                JOptionPane.showMessageDialog(ChangePasswordUI.this, "New password must be different from old password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate password strength (minimum 8 characters, at least one letter and one number)
            if (!isValidPassword(newPassword)) {
                JOptionPane.showMessageDialog(ChangePasswordUI.this, "New password must be at least 8 characters long and contain at least one letter and one number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                boolean success = userSession.changePassword(oldPassword, newPassword);
                if (success) {
                    JOptionPane.showMessageDialog(ChangePasswordUI.this, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ChangePasswordUI.this, "Old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ServerConnectionException ex) {
                JOptionPane.showMessageDialog(ChangePasswordUI.this, "Connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        /**
         * Validates password strength.
         * @param password The password to validate.
         * @return true if password meets strength requirements, false otherwise.
         */
        private boolean isValidPassword(String password) {
            if (password.length() < 8) {
                return false;
            }
            boolean hasLetter = false;
            boolean hasDigit = false;
            for (char c : password.toCharArray()) {
                if (Character.isLetter(c)) {
                    hasLetter = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit) {
                    break;
                }
            }
            return hasLetter && hasDigit;
        }
    }
    /**
     * Action listener for the cancel button.
     * Closes the form without making changes.
     */
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
    /**
     * Main method to launch the Change Password UI (for testing purposes).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a mock user session for demonstration
                UserSession mockSession = new UserSession("testUser", "oldPass123");
                new ChangePasswordUI(mockSession).setVisible(true);
            }
        });
    }
}