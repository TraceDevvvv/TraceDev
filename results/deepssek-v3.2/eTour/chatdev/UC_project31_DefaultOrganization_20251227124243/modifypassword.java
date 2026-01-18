/**
 * A runnable GUI application implementing the ModifyPassword use case for Agency Operators.
 * This program allows a logged-in agency operator to change their password securely,
 * following the steps: choose to change password, upload form, enter new password, confirm, and save.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class ModifyPassword {
  // Simulated database of agency operators: username -> [password, agency_name]
  private static Map<String, String[]> users = new HashMap<>();
  static {
    // Initialize with a sample agency operator (default credentials)
    users.put("agency_op", new String[]{"old_pass123", "Global Logistics"});
  }
  /**
   * Validates the current password for a given username.
   * @param username the agency operator's username
   * @param currentPassword the entered current password
   * @return true if the password matches the stored value, false otherwise
   */
  public static boolean validateCurrentPassword(String username, String currentPassword) {
    if (!users.containsKey(username)) {
      return false;
    }
    return users.get(username)[0].equals(currentPassword);
  }
  /**
   * Validates the new password against security rules: at least 8 characters,
   * containing both a letter and a digit.
   * @param newPassword the proposed new password
   * @return true if the password meets requirements, false otherwise
   */
  public static boolean validateNewPassword(String newPassword) {
    if (newPassword == null || newPassword.length() < 8) {
      return false;
    }
    boolean hasDigit = false;
    boolean hasLetter = false;
    for (char c : newPassword.toCharArray()) {
      if (Character.isDigit(c)) hasDigit = true;
      if (Character.isLetter(c)) hasLetter = true;
      if (hasDigit && hasLetter) break;
    }
    return hasDigit && hasLetter;
  }
  /**
   * Updates the password for the given username in the simulated database.
   * Simulates saving changes; includes a random chance of server interruption.
   * @param username the username whose password is to be updated
   * @param newPassword the new password to store
   * @return true if update succeeds, false on server interruption
   */
  public static boolean updatePassword(String username, String newPassword) {
    // Simulate server interruption (10% chance) as per quality requirement
    if (Math.random() < 0.1) {
      return false;
    }
    if (!users.containsKey(username)) {
      return false;
    }
    String[] userData = users.get(username);
    userData[0] = newPassword;
    users.put(username, userData);
    return true;
  }
  /**
   * Creates and displays the GUI for password modification.
   * Implements the flow: press button, fill form, enter new password, confirm, save.
   */
  private static void createAndShowGUI() {
    JFrame frame = new JFrame("Modify Password - Agency Operator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 350);
    frame.setLayout(new BorderLayout());
    // Form panel with GridBagLayout for organized input fields
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    // Username field
    gbc.gridx = 0; gbc.gridy = 0;
    formPanel.add(new JLabel("Username:"), gbc);
    gbc.gridx = 1;
    JTextField usernameField = new JTextField(20);
    usernameField.setText("agency_op"); // Default for testing
    formPanel.add(usernameField, gbc);
    // Current password field
    gbc.gridx = 0; gbc.gridy = 1;
    formPanel.add(new JLabel("Current Password:"), gbc);
    gbc.gridx = 1;
    JPasswordField currentPasswordField = new JPasswordField(20);
    currentPasswordField.setText("old_pass123"); // Default for testing
    formPanel.add(currentPasswordField, gbc);
    // New password field
    gbc.gridx = 0; gbc.gridy = 2;
    formPanel.add(new JLabel("New Password:"), gbc);
    gbc.gridx = 1;
    JPasswordField newPasswordField = new JPasswordField(20);
    formPanel.add(newPasswordField, gbc);
    // Confirm new password field
    gbc.gridx = 0; gbc.gridy = 3;
    formPanel.add(new JLabel("Confirm New Password:"), gbc);
    gbc.gridx = 1;
    JPasswordField confirmPasswordField = new JPasswordField(20);
    formPanel.add(confirmPasswordField, gbc);
    // Buttons panel (Step 5: press appropriate button to change password)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton changeButton = new JButton("Change Password");
    JButton cancelButton = new JButton("Cancel");
    buttonPanel.add(changeButton);
    buttonPanel.add(cancelButton);
    // Status label for operation feedback
    JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);
    statusLabel.setForeground(Color.BLUE);
    // Assemble frame
    frame.add(formPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.add(statusLabel, BorderLayout.NORTH);
    // Action for Change Password button (Steps 6-8)
    changeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        // Step 6 & 7: upload form and validate inputs
        if (!validateCurrentPassword(username, currentPassword)) {
          statusLabel.setForeground(Color.RED);
          statusLabel.setText("Error: Incorrect current password.");
          return;
        }
        if (!validateNewPassword(newPassword)) {
          statusLabel.setForeground(Color.RED);
          statusLabel.setText("Error: New password must be at least 8 characters with a letter and a digit.");
          return;
        }
        if (!newPassword.equals(confirmPassword)) {
          statusLabel.setForeground(Color.RED);
          statusLabel.setText("Error: New password and confirmation do not match.");
          return;
        }
        // Step 8: save changes; handle server interruption exit condition
        boolean success = updatePassword(username, newPassword);
        if (success) {
          statusLabel.setForeground(Color.GREEN);
          statusLabel.setText("Success: Password updated successfully!");
          // Clear sensitive fields
          currentPasswordField.setText("");
          newPasswordField.setText("");
          confirmPasswordField.setText("");
        } else {
          statusLabel.setForeground(Color.RED);
          statusLabel.setText("Error: Server interruption. Password not updated.");
        }
      }
    });
    // Cancel button exits the application
    cancelButton.addActionListener(e -> System.exit(0));
    // Center and show the window
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  /**
   * Main method: entry point of the program.
   * Assumes the agency operator is already logged in (entry condition).
   * Launches the GUI on the Event Dispatch Thread.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> createAndShowGUI());
  }
}