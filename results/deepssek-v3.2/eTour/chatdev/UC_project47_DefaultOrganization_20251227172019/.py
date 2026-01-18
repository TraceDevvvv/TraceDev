/**
 * ModifyTouristDataGUI.java
 * 
 * This class provides the graphical user interface for the Modify Tourist Data use case.
 * It uses Java Swing to create a form for viewing and editing tourist data.
 * It interacts with DataManager to load and save data.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ModifyTouristDataGUI extends JFrame {
    private Tourist tourist;
    private DataManager dataManager;
    // UI Components
    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JButton loadButton;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    public ModifyTouristDataGUI() {
        dataManager = new DataManager();
        initializeUI();
    }
    /**
     * Initializes the user interface components and layout.
     */
    private void initializeUI() {
        setTitle("Modify Tourist Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);  // Center the window
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Form panel with GridBagLayout for flexible layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Username row
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(20);
        usernameField.setEditable(false);  // Username cannot be changed
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        // Load button
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadTouristData());
        gbc.gridx = 2;
        formPanel.add(loadButton, gbc);
        // First name row
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(firstNameField, gbc);
        // Last name row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(lastNameField, gbc);
        // Email row
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(emailField, gbc);
        // Phone number row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(phoneField, gbc);
        // Address row
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Address:"), gbc);
        addressField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(addressField, gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);  // Disabled until data is loaded
        submitButton.addActionListener(e -> submitChanges());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancelOperation());
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Status label at the top
        statusLabel = new JLabel("Enter username and click 'Load Data' to start.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        add(mainPanel);
    }
    /**
     * Loads tourist data from the server and populates the form.
     * Corresponds to step 2 in the flow of events.
     */
    private void loadTouristData() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Step 2: Load data from server
            tourist = dataManager.loadTouristData(username);
            if (tourist == null) {
                statusLabel.setText("Tourist not found for username: " + username);
                clearForm();
                submitButton.setEnabled(false);
                return;
            }
            // Populate form fields with loaded data
            firstNameField.setText(tourist.getFirstName());
            lastNameField.setText(tourist.getLastName());
            emailField.setText(tourist.getEmail());
            phoneField.setText(tourist.getPhoneNumber());
            addressField.setText(tourist.getAddress());
            statusLabel.setText("Data loaded successfully. You can now edit the fields.");
            submitButton.setEnabled(true);  // Enable submit button
        } catch (RuntimeException e) {
            // Handle server connection interruption
            statusLabel.setText("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
            clearForm();
            submitButton.setEnabled(false);
        }
    }
    /**
     * Handles the submission of modified data.
     * Implements steps 3, 4, 5, and 6 from the flow of events.
     */
    private void submitChanges() {
        // Step 3: Get edited fields from form
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        // Step 4: Check modified information
        Tourist modifiedTourist = new Tourist(tourist.getUsername(), firstName, lastName, email, phone, address);
        if (!modifiedTourist.validateFields()) {
            // Activate the "Errored" use case (simulated with error dialog)
            statusLabel.setText("Invalid or insufficient data. Please correct the errors.");
            JOptionPane.showMessageDialog(this, 
                "Please ensure all fields are filled correctly.\n" +
                "Email and phone number must be valid.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Step 5: Ask for confirmation
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to save the changes?\n" +
            "First Name: " + firstName + "\n" +
            "Last Name: " + lastName + "\n" +
            "Email: " + email + "\n" +
            "Phone: " + phone + "\n" +
            "Address: " + address,
            "Confirm Changes", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) {
            statusLabel.setText("Changes cancelled by user.");
            return;  // User cancelled the operation
        }
        // Step 6: Store the modified data
        try {
            boolean success = dataManager.saveTouristData(modifiedTourist);
            if (success) {
                tourist = modifiedTourist;  // Update local tourist object
                statusLabel.setText("Data modified successfully!");
                JOptionPane.showMessageDialog(this, 
                    "Your data has been updated successfully.", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText("Failed to save data. Please try again.");
                JOptionPane.showMessageDialog(this, 
                    "Failed to save data. Please try again.", 
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException e) {
            // Handle server connection interruption during save
            statusLabel.setText("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Cancels the current operation and resets the form.
     * Corresponds to one of the exit conditions.
     */
    private void cancelOperation() {
        clearForm();
        statusLabel.setText("Operation cancelled. Enter username and click 'Load Data' to start.");
        submitButton.setEnabled(false);
    }
    /**
     * Clears all form fields.
     */
    private void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }
    // Main method for standalone testing (optional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifyTouristDataGUI gui = new ModifyTouristDataGUI();
            gui.setVisible(true);
        });
    }
}