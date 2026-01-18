/**
 * PasswordChangeApp class provides a graphical user interface for users to change their password.
 * It simulates a password change process, specifically highlighting the error scenario
 * where the new password and its confirmation do not match, as described in the ERROREMODIFICAPASSWORD use case.
 * The application allows users to input a new password and confirm it.
 * If the passwords do not match, an error message is displayed, and the user remains
 * on the password change screen to correct their input.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
public class PasswordChangeApp extends JFrame {
    // Text field for the new password input
    private JPasswordField newPasswordField;
    // Text field for confirming the new password
    private JPasswordField confirmPasswordField;
    // Button to trigger the password change attempt
    private JButton confirmButton;
    /**
     * Constructor for the PasswordChangeApp.
     * Initializes the JFrame, sets up its layout, and adds necessary components
     * like labels, password fields, and the confirm button.
     */
    public PasswordChangeApp() {
        // Set the title of the JFrame
        setTitle("Password Change");
        // Ensure the application exits when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the preferred size of the window
        setPreferredSize(new Dimension(400, 200));
        // Set the layout manager for the frame's content pane. GridBagLayout is chosen for flexible component placement.
        setLayout(new GridBagLayout());
        // Create GridBagConstraints object for managing component layout
        GridBagConstraints gbc = new GridBagConstraints();
        // Add padding around components
        gbc.insets = new Insets(5, 5, 5, 5);
        // --- New Password Label and Field ---
        JLabel newPasswordLabel = new JLabel("New Password:");
        // Position the label in the grid
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST; // Align to west (left)
        add(newPasswordLabel, gbc);
        newPasswordField = new JPasswordField(20); // Password field with 20 columns width
        // Position the password field
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make component fill available horizontal space
        add(newPasswordField, gbc);
        // --- Confirm Password Label and Field ---
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        // Position the label
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.WEST; // Align to west (left)
        add(confirmPasswordLabel, gbc);
        confirmPasswordField = new JPasswordField(20); // Password field with 20 columns width
        // Position the password field
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make component fill available horizontal space
        add(confirmPasswordField, gbc);
        // --- Confirm Button ---
        confirmButton = new JButton("Confirm Change");
        // Add an ActionListener to the button to handle clicks
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to handle password change logic
                handlePasswordChange();
            }
        });
        // Position the button
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.EAST; // Align to east (right)
        gbc.fill = GridBagConstraints.NONE; // Do not fill horizontal space
        add(confirmButton, gbc);
        // Pack the components to their preferred sizes and make the window visible
        pack();
        // Center the window on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Handles the logic for attempting a password change.
     * This method is triggered when the "Confirm Change" button is pressed.
     * It retrieves the entered passwords, validates them, and provides feedback to the user.
     * This method directly implements the "Flow of events" from the use case.
     */
    private void handlePasswordChange() {
        // Get the character arrays from the password fields for security (avoids String immutability issues)
        char[] newPass = newPasswordField.getPassword();
        char[] confirmPass = confirmPasswordField.getPassword();
        // Check if either password field is empty
        if (newPass.length == 0 || confirmPass.length == 0) {
            // Notify an error message (Flow of event 4)
            JOptionPane.showMessageDialog(this,
                    "Password fields cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            // Confirmation of reading (Flow of event 5) - User clicks OK.
            // Back to change your password (Flow of event 6) - Fields remain populated for correction.
        }
        // Check if the new password and confirmation password do not match as per use case 'ERROREMODIFICAPASSWORD'
        else if (!Arrays.equals(newPass, confirmPass)) {
            // Notify an error message as per the use case (Flow of event 4)
            JOptionPane.showMessageDialog(this,
                    "The new password and confirmation do not match. Please try again.",
                    "Password Mismatch Error",
                    JOptionPane.ERROR_MESSAGE);
            // Confirmation of reading (Flow of event 5) - User clicks OK.
            // Back to change your password (Flow of event 6) - Fields remain populated for correction.
        }
        // If passwords match (this scenario is outside the "ERROREMODIFICAPASSWORD" use case,
        // but included for completeness of a real application flow simulation)
        else {
            // In a real application, here you would send the new password to a backend service
            // for actual update logic. For this simulation, we just show a success message.
            JOptionPane.showMessageDialog(this,
                    "Password successfully changed!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            // Clear the password fields after a successful change
            newPasswordField.setText("");
            confirmPasswordField.setText("");
        }
        // It is crucial to clear the 'char' arrays from memory after use for security reasons,
        // preventing them from lingering in memory where they could be exposed.
        Arrays.fill(newPass, ' '); // Overwrite array with spaces
        Arrays.fill(confirmPass, ' '); // Overwrite array with spaces
    }
    /**
     * Main method to launch the PasswordChangeApp.
     * Ensures the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordChangeApp();
            }
        });
    }
}