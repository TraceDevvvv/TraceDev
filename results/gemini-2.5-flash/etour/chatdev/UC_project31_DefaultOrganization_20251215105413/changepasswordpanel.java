/**
 * A JPanel providing the user interface for changing the account password.
 * It includes fields for current password, new password, and confirmation,
 * and interacts with the UserService to update the password.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // For secure password handling
public class ChangePasswordPanel extends JPanel {
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changePasswordButton;
    private JButton backToLoginButton; // Button to go back to login (or logout)
    private JLabel messageLabel;
    private PasswordChangeApp parentApp;
    private UserService userService;
    private String currentUser; // The username of the logged-in operator
    /**
     * Constructs a ChangePasswordPanel.
     * @param parentApp The main application frame.
     * @param service The UserService instance for password updates.
     * @param username The username of the currently logged-in operator.
     */
    public ChangePasswordPanel(PasswordChangeApp parentApp, UserService service, String username) {
        this.parentApp = parentApp;
        this.userService = service;
        this.currentUser = username;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        JLabel titleLabel = new JLabel("Change Password for " + currentUser, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        // Current Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Current Password:"), gbc);
        currentPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(currentPasswordField, gbc);
        // New Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("New Password:"), gbc);
        newPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(newPasswordField, gbc);
        // Confirm New Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Confirm New Password:"), gbc);
        confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(confirmPasswordField, gbc);
        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(changePasswordButton, gbc);
        // Back to Login/Logout Button
        backToLoginButton = new JButton("Logout");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(backToLoginButton, gbc);
        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);
        addChangePasswordActionListener();
        addBackToLoginActionListener();
    }
    /**
     * Adds an ActionListener to the "Change Password" button.
     * This listener validates input, calls the UserService to update the password,
     * and provides user feedback through dialogs and a message label.
     */
    private void addChangePasswordActionListener() {
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText(""); // Clear previous messages
                char[] currentPassChars = currentPasswordField.getPassword();
                char[] newPassChars = newPasswordField.getPassword();
                char[] confirmPassChars = confirmPasswordField.getPassword();
                try {
                    // --- Input Validation ---
                    if (currentPassChars.length == 0 || newPassChars.length == 0 || confirmPassChars.length == 0) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("All password fields must be filled.");
                        return;
                    }
                    if (!Arrays.equals(newPassChars, confirmPassChars)) { // Securely compare char arrays
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("New password and confirm password do not match.");
                        return;
                    }
                    if (Arrays.equals(newPassChars, currentPassChars)) { // Securely compare char arrays
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("New password cannot be the same as the current password.");
                        return;
                    }
                    // Basic password strength check (can be expanded)
                    if (newPassChars.length < 6) { 
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("New password must be at least 6 characters long.");
                        return;
                    }
                    // --- Attempt to change password via UserService ---
                    boolean success = userService.changePassword(currentUser, currentPassChars, newPassChars);
                    if (success) {
                        messageLabel.setForeground(new Color(0, 150, 0)); // Dark green
                        messageLabel.setText("Password changed successfully!");
                        JOptionPane.showMessageDialog(parentApp, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Optionally, redirect to login or clear fields after success
                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Failed to change password. Current password may be incorrect.");
                        JOptionPane.showMessageDialog(parentApp, "Failed to change password.\nPlease check your current password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (UserService.ConnectionInterruptionException ex) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Connection error: " + ex.getMessage());
                    JOptionPane.showMessageDialog(parentApp, "Error: " + ex.getMessage() + "\nCannot save changes.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Clear password fields immediately and securely
                    Arrays.fill(currentPassChars, ' ');
                    Arrays.fill(newPassChars, ' ');
                    Arrays.fill(confirmPassChars, ' ');
                    currentPasswordField.setText("");
                    newPasswordField.setText("");
                    confirmPasswordField.setText("");
                }
            }
        });
    }
    /**
     * Adds an ActionListener to the "Logout" button.
     * This simply navigates the user back to the login screen.
     */
    private void addBackToLoginActionListener() {
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear fields and go back to login panel
                char[] current = currentPasswordField.getPassword();
                char[] newP = newPasswordField.getPassword();
                char[] confirmP = confirmPasswordField.getPassword();
                if (current != null) Arrays.fill(current, ' ');
                if (newP != null) Arrays.fill(newP, ' ');
                if (confirmP != null) Arrays.fill(confirmP, ' ');
                currentPasswordField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");
                messageLabel.setText("");
                parentApp.showLoginPanel();
            }
        });
    }
    /**
     * Updates the username displayed in the title of the panel.
     * @param username The new username to display.
     */
    public void setUsername(String username) {
        this.currentUser = username;
        // Find and update the title label
        for (Component comp : getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().startsWith("Change Password for")) {
                ((JLabel) comp).setText("Change Password for " + currentUser);
                break;
            }
        }
    }
}