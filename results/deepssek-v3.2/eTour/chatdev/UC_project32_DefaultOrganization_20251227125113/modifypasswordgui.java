/**
 * This class provides a complete GUI implementation for password modification
 * that follows the specific use case flow from entry conditions to exit conditions.
 * It includes error notification with user confirmation and proper reset functionality.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ModifyPasswordGUI.java
 * This class provides the graphical user interface (GUI) for the password modification process.
 * It includes text fields for entering new password and confirmation, and buttons to confirm or cancel.
 * The GUI handles the password change flow as per the use case, including error notification and user interaction.
 * It also includes password validation and proper exception handling.
 */
public class ModifyPasswordGUI {
    private JPanel mainPanel;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel notificationLabel;
    /**
     * Constructor for ModifyPasswordGUI.
     * Initializes the GUI components and sets up event listeners.
     */
    public ModifyPasswordGUI() {
        initializeComponents();
        setupListeners();
    }
    /**
     * Initializes the GUI components (JPanel, text fields, buttons, labels).
     */
    private void initializeComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title label.
        JLabel titleLabel = new JLabel("Change Your Password", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        // New password label.
        JLabel newPasswordLabel = new JLabel("New Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(newPasswordLabel, gbc);
        // New password field.
        newPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(newPasswordField, gbc);
        // Confirm password label.
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(confirmPasswordLabel, gbc);
        // Confirm password field.
        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(confirmPasswordField, gbc);
        // Notification label (initially empty, used for error/success messages).
        notificationLabel = new JLabel(" ");
        notificationLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(notificationLabel, gbc);
        // Button panel for Confirm and Back buttons.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        confirmButton = new JButton("Confirm");
        backButton = new JButton("Back");
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
    }
    /**
     * Sets up action listeners for the Confirm and Back buttons.
     * Implements the flow of events as per the use case.
     */
    private void setupListeners() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePasswordChange();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 6: Back to change your password
                resetForm();
            }
        });
        // Add Enter key support for form submission
        newPasswordField.addActionListener(e -> handlePasswordChange());
        confirmPasswordField.addActionListener(e -> handlePasswordChange());
    }
    /**
     * Handles the password change logic when the Confirm button is clicked.
     * Implements the flow: validate, check password confirmation, and notify error/success.
     */
    private void handlePasswordChange() {
        String newPassword;
        String confirmPassword;
        try {
            newPassword = new String(newPasswordField.getPassword());
            confirmPassword = new String(confirmPasswordField.getPassword());
        } catch (Exception ex) {
            showNotification("Error: Unable to read password input.", true, true);
            return;
        }
        // Validate: check if passwords match and are not empty.
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showNotification("Error: Password fields cannot be empty.", true, true);
        } else if (!validatePassword(newPassword)) {
            // Error message already shown in validatePassword
            return;
        } else if (!newPassword.equals(confirmPassword)) {
            showNotification("Error: Passwords do not match. Please try again.", true, true);
        } else {
            // Simulate password change process
            boolean passwordChanged = changePassword(newPassword);
            if (passwordChanged) {
                showNotification("Password changed successfully!", false, false);
                // Reset the fields for next operation
                resetForm();
            } else {
                showNotification("Error: Failed to change password. Please try again.", true, true);
            }
        }
    }
    /**
     * Validates the password against security requirements.
     * @param password The password to validate.
     * @return true if the password meets all requirements, false otherwise.
     */
    private boolean validatePassword(String password) {
        if (password.length() < 8) {
            showNotification("Error: Password must be at least 8 characters.", true, true);
            return false;
        }
        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            showNotification("Error: Password must contain at least one uppercase letter.", true, true);
            return false;
        }
        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            showNotification("Error: Password must contain at least one lowercase letter.", true, true);
            return false;
        }
        // Check for at least one digit
        if (!password.matches(".*\\d.*")) {
            showNotification("Error: Password must contain at least one digit.", true, true);
            return false;
        }
        // Check for at least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            showNotification("Error: Password must contain at least one special character.", true, true);
            return false;
        }
        return true;
    }
    /**
     * Simulates the actual password change process.
     * In a real application, this would connect to a database or authentication service.
     * @param newPassword The new password to set.
     * @return true if the password was successfully changed, false otherwise.
     */
    private boolean changePassword(String newPassword) {
        try {
            // Simulate password change logic
            // In a real application, you would:
            // 1. Connect to authentication service
            // 2. Encrypt/hash the password
            // 3. Update the user's password in the database
            // 4. Return success/failure based on the operation result
            // For demonstration purposes, simulate a successful change
            Thread.sleep(500); // Simulate network delay
            return true;
        } catch (Exception e) {
            // Log the error in a real application
            return false;
        }
    }
    /**
     * Resets the form to its initial state, clearing all fields and notifications.
     * This implements Step 6 of the use case: "Back to change your password."
     */
    private void resetForm() {
        newPasswordField.setText("");
        confirmPasswordField.setText("");
        notificationLabel.setText(" ");
        newPasswordField.requestFocusInWindow(); // Give focus back to first field
    }
    /**
     * Displays a notification message to the user.
     * @param message The message to display.
     * @param isError If true, the message is displayed in red (error), otherwise in green (success).
     * @param requireConfirmation If true, shows a confirmation dialog for error messages (Step 5).
     */
    private void showNotification(String message, boolean isError, boolean requireConfirmation) {
        SwingUtilities.invokeLater(() -> {
            notificationLabel.setText(message);
            if (isError) {
                notificationLabel.setForeground(Color.RED);
                if (requireConfirmation) {
                    // Step 5: Confirmation of reading the notification
                    int result = JOptionPane.showConfirmDialog(mainPanel, 
                        message + "\n\nClick OK to continue.", 
                        "Password Change Error", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.ERROR_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        // Step 6: Back to change password after confirmation
                        resetForm();
                    }
                }
            } else {
                notificationLabel.setForeground(Color.GREEN);
            }
        });
    }
    /**
     * Provides access to the main JPanel for integration into a JFrame.
     * @return The main JPanel containing the GUI.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    /**
     * Main method to launch the GUI application.
     * Creates a JFrame to host the GUI and makes it visible.
     * Uses SwingUtilities.invokeLater to ensure thread safety.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure GUI creation happens on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main application window
            JFrame frame = new JFrame("Password Modification");
            // Set the close operation to exit the application when window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Create an instance of ModifyPasswordGUI and set it as the content pane
            frame.setContentPane(new ModifyPasswordGUI().getMainPanel());
            // Automatically size the window to fit its contents
            frame.pack();
            // Center the window on the screen
            frame.setLocationRelativeTo(null);
            // Make the window visible
            frame.setVisible(true);
        });
    }
}