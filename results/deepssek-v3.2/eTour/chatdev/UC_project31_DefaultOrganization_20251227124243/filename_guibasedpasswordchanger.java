/**
 * A GUI-based implementation of the ModifyPassword use case using Java Swing.
 * This provides a graphical interface for the agency operator to change their password.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class GuiBasedPasswordChanger {
    // Mock database similar to the console version.
    private static Map<String, String[]> users = new HashMap<>();
    static {
        users.put("agency_op", new String[]{"old_pass123", "Global Logistics"});
    }
    /**
     * Validates the current password for a given user.
     */
    public static boolean validateCurrentPassword(String username, String currentPassword) {
        if (!users.containsKey(username)) {
            return false;
        }
        return users.get(username)[0].equals(currentPassword);
    }
    /**
     * Validates the new password with security rules.
     */
    public static boolean validateNewPassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasLetter = false;
        for (char c : newPassword.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (hasDigit && hasLetter) {
                break;
            }
        }
        return hasDigit && hasLetter;
    }
    /**
     * Updates the password in the mock database.
     */
    public static boolean updatePassword(String username, String newPassword) {
        if (!users.containsKey(username)) {
            return false;
        }
        String[] userData = users.get(username);
        userData[0] = newPassword;
        users.put(username, userData);
        return true;
    }
    /**
     * Creates and displays the GUI for password change.
     */
    public static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Modify Password - Agency Operator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());
        // Create a panel for the form with GridBagLayout for flexibility
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);
        // Current password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Current Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField currentPasswordField = new JPasswordField(20);
        formPanel.add(currentPasswordField, gbc);
        // New password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField newPasswordField = new JPasswordField(20);
        formPanel.add(newPasswordField, gbc);
        // Confirm password field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Confirm New Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        formPanel.add(confirmPasswordField, gbc);
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton changeButton = new JButton("Change Password");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        // Status label for feedback
        JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
        // Add components to frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);
        // Action listener for Change Password button (Step 5, 6, 7, 8)
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String currentPassword = new String(currentPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                // Validate current password
                if (!validateCurrentPassword(username, currentPassword)) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error: Incorrect current password.");
                    return;
                }
                // Validate new password rules
                if (!validateNewPassword(newPassword)) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error: New password must be at least 8 characters and contain a letter and a digit.");
                    return;
                }
                // Confirm new password matches
                if (!newPassword.equals(confirmPassword)) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error: New password and confirmation do not match.");
                    return;
                }
                // Update password (save changes)
                boolean success = updatePassword(username, newPassword);
                if (success) {
                    statusLabel.setForeground(Color.GREEN);
                    statusLabel.setText("Success: Password updated successfully!");
                    // Clear fields
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    confirmPasswordField.setText("");
                } else {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error: Server interruption. Password not updated.");
                }
            }
        });
        // Cancel button: close the application
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Display the frame
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // Schedule GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}