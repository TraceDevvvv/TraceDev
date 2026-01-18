'''
This class provides a graphical user interface (GUI) for the "Edit User" functionality.
It allows an administrator to view and modify user details.
It interacts with the UserManager for data operations and validation.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditUserGUI extends JFrame {
    private UserManager userManager;
    private User currentUser; // The user currently being edited
    // GUI Components
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField roleField; // Assuming role can also be edited, or displayed
    private JButton editButton;
    private JButton searchButton; // Added to simulate viewing another user
    // Added component for simulating admin login status
    private JCheckBox adminLoginStatusCheckbox; 
    /**
     * Constructs the EditUserGUI.
     * @param userManager The manager responsible for user data operations.
     */
    public EditUserGUI(UserManager userManager) {
        this.userManager = userManager;
        initUI();
        // For the sake of the use case, let's pre-load U001 details if available.
        // In a real app, this would be passed from a "viewdetTailsente" screen.
        loadUserById("U001");
    }
    /**
     * Initializes all graphical user interface components.
     */
    private void initUI() {
        setTitle("Edit User Details");
        setSize(400, 350); // Increased height to accommodate new component
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // User ID (for searching/identifying current user)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        userIdField = new JTextField(15);
        panel.add(userIdField, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        searchButton = new JButton("Load User");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserById(userIdField.getText());
            }
        });
        panel.add(searchButton, gbc);
        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span two columns
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        // Role
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        roleField = new JTextField(20);
        roleField.setEditable(true); // Role could be editable or fixed based on requirements
        panel.add(roleField, gbc);
        // Add JCheckBox for simulating admin login
        gbc.gridx = 0;
        gbc.gridy = 4; // New row for checkbox
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST; // Align checkbox to the left
        adminLoginStatusCheckbox = new JCheckBox("Simulate Admin Login", userManager.isLoggedInAsAdmin());
        adminLoginStatusCheckbox.addActionListener(e -> {
            userManager.setAdminLoggedIn(adminLoginStatusCheckbox.isSelected());
        });
        panel.add(adminLoginStatusCheckbox, gbc);
        // Edit Button
        gbc.gridx = 0;
        gbc.gridy = 5; // Adjusted row for edit button
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        editButton = new JButton("Edit User");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserAction();
            }
        });
        panel.add(editButton, gbc);
        add(panel);
    }
    /**
     * Loads the details of a user into the form fields based on their ID.
     * Simulates the "viewdetTailsente" precondition.
     * @param userId The ID of the user to load.
     */
    private void loadUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            showError("Please enter a User ID to load.");
            return;
        }
        currentUser = userManager.viewUserDetails(userId);
        if (currentUser != null) {
            userIdField.setText(currentUser.getId());
            nameField.setText(currentUser.getName());
            emailField.setText(currentUser.getEmail());
            roleField.setText(currentUser.getRole());
            setTitle("Edit User Details - " + currentUser.getName());
        } else {
            showError("User with ID '" + userId + "' not found.");
            // Clear fields if user not found
            nameField.setText("");
            emailField.setText("");
            roleField.setText("");
            // Do not clear userIdField, let user try another ID
            setTitle("Edit User Details");
        }
    }
    /**
     * Handles the action when the "Edit" button is clicked.
     * This is the core logic described in the use case.
     */
    private void editUserAction() {
        // Precondition 1: Admin login (checked by UserManager behind the scenes)
        if (!userManager.isLoggedInAsAdmin()) {
            showError("Authentication Error: Only administrators can edit users.");
            return;
        }
        // Precondition 2: User details are being displayed
        if (currentUser == null) {
            showError("No user loaded for editing. Please load a user first.");
            return;
        }
        // Get changed data from form
        String newName = nameField.getText();
        String newEmail = emailField.getText();
        String newRole = roleField.getText(); // Get the new role from the text field
        // Step 2: System checks on the validity of the data entered
        // Updated to include role validation based on the comment feedback.
        if (!userManager.isValidUserData(newName, newEmail, newRole)) {
            // If data is not valid, activate "Errodati" use case
            showError("Errodati: Invalid data entered. Please check name (cannot be empty), email format, and role (cannot be empty/must be valid).");
            userManager.interruptSMOSConnection(); // Postcondition: Administrator interrupts connection
            return;
        }
        // Create a new User object with updated details
        User updatedUser = new User(currentUser.getId(), newName, newEmail, newRole);
        // Attempt to update the user
        if (userManager.updateUser(updatedUser)) {
            currentUser = updatedUser; // Update the current user in GUI cache
            showSuccess("User '" + currentUser.getName() + "' (ID: " + currentUser.getId() + ") updated successfully!");
        } else {
            showError("Failed to update user. User ID not found or an unexpected error occurred.");
            userManager.interruptSMOSConnection(); // Postcondition: Administrator interrupts connection
        }
    }
    /**
     * Displays an error message in a dialog box.
     * @param message The error message to display.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays a success message in a dialog box.
     * @param message The success message to display.
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}