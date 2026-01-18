'''
This is the main application class for the Change Password feature.
It provides a graphical user interface (GUI) allowing a registered user
to change their password.
'''
package chatdev.app; // Added package for organization
import chatdev.service.UserService; // Import the UserService class from its package
import chatdev.user.User;       // Import the User class from its package
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // Import for clearing char arrays
public class ChangePasswordApp extends JFrame {
    private UserService userService;
    private User loggedInUser; // The user whose password we are changing
    // GUI components
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton changePasswordButton;
    private JLabel messageLabel;
    /**
     * Constructs the ChangePasswordApp.
     * @param userService The service responsible for user management.
     * @param loggedInUser The user who is currently logged in and wants to change their password.
     */
    public ChangePasswordApp(UserService userService, User loggedInUser) {
        this.userService = userService;
        this.loggedInUser = loggedInUser;
        initializeUI();
    }
    /**
     * Initializes and sets up the graphical user interface.
     */
    private void initializeUI() {
        setTitle("Change Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        // Use GridBagLayout for a more flexible and robust form layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        // Title Label
        JLabel titleLabel = new JLabel("Change Your Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(titleLabel, gbc);
        // Current User Label
        JLabel currentUserLabel = new JLabel("Logged in as: " + loggedInUser.getUsername());
        currentUserLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 1;
        panel.add(currentUserLabel, gbc);
        // Reset gridwidth to 1 for subsequent components
        gbc.gridwidth = 1;
        // Old Password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; // Align label to the west
        panel.add(new JLabel("Old Password:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make text field fill horizontally
        oldPasswordField = new JPasswordField(20);
        panel.add(oldPasswordField, gbc);
        // New Password field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        newPasswordField = new JPasswordField(20);
        panel.add(newPasswordField, gbc);
        // Confirm New Password field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Confirm New Password:"), gbc);
        gbc.gridx = 1;
        confirmNewPasswordField = new JPasswordField(20);
        panel.add(confirmNewPasswordField, gbc);
        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Button spans two columns
        gbc.fill = GridBagConstraints.NONE; // Don't stretch button
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(changePasswordButton, gbc);
        // Message Label for feedback
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridy = 6;
        panel.add(messageLabel, gbc);
        // Add action listener to the button
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangePassword();
            }
        });
        add(panel);
        setVisible(true);
    }
    /**
     * Handles the "Change Password" button click event.
     * Gathers input, calls the UserService, and provides feedback to the user.
     */
    private void handleChangePassword() {
        // Retrieve passwords as char arrays for better security.
        char[] oldPasswordChars = oldPasswordField.getPassword();
        char[] newPasswordChars = newPasswordField.getPassword();
        char[] confirmNewPasswordChars = confirmNewPasswordField.getPassword();
        // Convert char arrays to String for hashing and comparison.
        String oldPassword = new String(oldPasswordChars);
        String newPassword = new String(newPasswordChars);
        String confirmNewPassword = new String(confirmNewPasswordChars);
        // Explicitly clear the char arrays immediately after conversion for security.
        // This reduces the window of vulnerability for password data lingering in memory.
        Arrays.fill(oldPasswordChars, ' ');
        Arrays.fill(newPasswordChars, ' ');
        Arrays.fill(confirmNewPasswordChars, ' ');
        // Note: The String objects created above will still exist until garbage collected,
        // but clearing the char arrays reduces the risk associated with memory dumps.
        // Perform basic client-side input validation
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            displayMessage("Error: All password fields must be filled.", Color.RED);
            return;
        }
        // Call the UserService to attempt the password change
        String result = userService.changePassword(loggedInUser, oldPassword, newPassword, confirmNewPassword);
        // Display the result to the user
        if (result.startsWith("Success")) {
            displayMessage(result, Color.GREEN.darker());
            // Optionally clear fields on success
            oldPasswordField.setText("");
            newPasswordField.setText("");
            confirmNewPasswordField.setText("");
        } else {
            displayMessage(result, Color.RED);
        }
    }
    /**
     * Displays a message in the messageLabel with a specified color.
     * @param message The message to display.
     * @param color The color of the message text.
     */
    private void displayMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }
    /**
     * Main method to run the application.
     * It sets up a simulated logged-in user and then launches the GUI.
     */
    public static void main(String[] args) {
        // Create a UserService instance
        UserService userService = new UserService();
        // Simulate a logged-in user.
        // In a real application, this user would come from a prior login screen.
        // The original password "oldPassword123" is used here for authentication.
        User userToChangePassword = userService.authenticate("testuser", "oldPassword123");
        // Precondition: The user is logged in.
        if (userToChangePassword == null) {
            JOptionPane.showMessageDialog(null,
                    "Error: No user is logged in. Please log in first with username 'testuser' and password 'oldPassword123'.",
                    "Authentication Required", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return;
        }
        // This ensures the GUI is created and updated on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            new ChangePasswordApp(userService, userToChangePassword);
        });
    }
}