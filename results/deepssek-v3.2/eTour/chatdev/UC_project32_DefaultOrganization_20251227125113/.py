/**
 * DOCSTRING
 *
 * ModifyPasswordGUI.java
 * This class provides the graphical user interface (GUI) for the password modification process.
 * It includes text fields for entering new password and confirmation, and buttons to confirm or cancel.
 * The GUI handles the password change flow as per the use case, including error notification and user interaction.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                // Step 4: Notify an error message if passwords do not match.
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                // Validate: check if passwords match and are not empty.
                if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    showNotification("Error: Password fields cannot be empty.", true);
                } else if (!newPassword.equals(confirmPassword)) {
                    showNotification("Error: Passwords do not match. Please try again.", true);
                } else {
                    // In a real application, you would update the password here.
                    // For this demo, we assume success and show a success message.
                    showNotification("Password changed successfully!", false);
                    // Reset the fields for next operation.
                    newPasswordField.setText("");
                    confirmPasswordField.setText("");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 6: Back to change your password.
                // In this demo, going back means clearing the fields and notification.
                newPasswordField.setText("");
                confirmPasswordField.setText("");
                notificationLabel.setText(" ");
            }
        });
    }
    /**
     * Displays a notification message to the user.
     * @param message The message to display.
     * @param isError If true, the message is displayed in red (error), otherwise in green (success).
     */
    private void showNotification(String message, boolean isError) {
        notificationLabel.setText(message);
        if (isError) {
            notificationLabel.setForeground(Color.RED);
        } else {
            notificationLabel.setForeground(Color.GREEN);
        }
        // Step 5: Confirmation of the reading of the notification.
        // We assume the user reads the notification. In a more complex system,
        // you might require an explicit confirmation (e.g., a pop-up dialog).
        // For now, we simply display the notification in the GUI.
    }
    /**
     * Provides access to the main JPanel for integration into a JFrame.
     * @return The main JPanel containing the GUI.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
}