'''
This JFrame provides the GUI for "MODIFICADATITURISTA" use case.
It allows an authenticated tourist to view and modify their account data.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TouristDataModificationFrame extends JFrame {
    private MainApp parentApp; // Reference to the main application frame
    private String authenticatedUsername; // The username of the currently authenticated tourist
    private TouristService touristService;
    // GUI components
    private JLabel usernameLabel; // Displays the authenticated username (read-only)
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton submitButton;
    private JButton cancelButton;
    private JCheckBox simulateErrorCheckBox; // To simulate ETOUR connection interruption
    /**
     * Constructs the TouristDataModificationFrame.
     *
     * @param parentApp The main application instance.
     * @param authenticatedUsername The username of the currently authenticated tourist.
     */
    public TouristDataModificationFrame(MainApp parentApp, String authenticatedUsername) {
        super("Modify Tourist Data - " + authenticatedUsername);
        this.parentApp = parentApp;
        this.authenticatedUsername = authenticatedUsername;
        this.touristService = new TouristService();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Custom handling for closing
        setSize(400, 350); // Adjusted size to fit new elements
        setLocationRelativeTo(null); // Center on screen
        initComponents();
        setupLayout();
        addListeners();
        loadTouristData(authenticatedUsername); // Load and display data on startup
        // Handle window closing event as a cancel operation
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cancelModification();
            }
        });
    }
    /**
     * Initializes all GUI components.
     */
    private void initComponents() {
        usernameLabel = new JLabel(); // This will display static username, value set in loadTouristData
        emailField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        submitButton = new JButton("Submit Changes");
        cancelButton = new JButton("Cancel");
        simulateErrorCheckBox = new JCheckBox("Simulate ETOUR Connection Interruption");
    }
    /**
     * Sets up the layout of the frame using GridBagLayout.
     * Loads the data and displays them in a form (Use Case Step 2).
     */
    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        // Username (read-only label)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow horizontal expansion
        panel.add(usernameLabel, gbc);
        // First Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Reset weight
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(firstNameField, gbc);
        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(lastNameField, gbc);
        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(emailField, gbc);
        // Simulate Error CheckBox
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span two columns
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(simulateErrorCheckBox, gbc);
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.EAST; // Align buttons to the right
        gbc.fill = GridBagConstraints.NONE; // Don't fill horizontally
        panel.add(buttonPanel, gbc);
        add(panel, BorderLayout.CENTER);
    }
    /**
     * Adds action listeners to the buttons and checkbox.
     */
    private void addListeners() {
        // Edit the fields in the form and submit (Use Case Step 3)
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitModification();
            }
        });
        // Tourist cancels the operation (Exit condition)
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelModification();
            }
        });
        simulateErrorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                touristService.setSimulateConnectionInterruption(simulateErrorCheckBox.isSelected());
                if (simulateErrorCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(TouristDataModificationFrame.this,
                            "ETOUR connection interruption simulation is ON. Updates may fail randomly.",
                            "Simulation Enabled", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TouristDataModificationFrame.this,
                            "ETOUR connection interruption simulation is OFF.",
                            "Simulation Disabled", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    /**
     * Loads the authenticated tourist's data from the service and populates the form fields.
     * (Part of Use Case Step 2).
     *
     * @param username The username of the tourist whose data to load.
     */
    private void loadTouristData(String username) {
        try {
            // Fetch tourist data using the authenticated username
            Tourist tourist = touristService.getTouristData(username);
            if (tourist != null) {
                usernameLabel.setText(tourist.getUsername()); // Display the username
                emailField.setText(tourist.getEmail());
                firstNameField.setText(tourist.getFirstName());
                lastNameField.setText(tourist.getLastName());
                setFieldsEditable(true); // Enable fields if data loaded successfully
            } else {
                displayErrorMessage("Data Load Error", "Could not load data for user: " + username);
                // Optionally disable form fields if data cannot be loaded
                setFieldsEditable(false);
            }
        } catch (TouristService.TouristServiceException e) {
            displayErrorMessage("Connection Error", e.getMessage());
            setFieldsEditable(false); // Disable editing if connection fails
        }
    }
    /**
     * Helper method to enable/disable form fields.
     * @param editable true to make fields editable, false otherwise.
     */
    private void setFieldsEditable(boolean editable) {
        emailField.setEditable(editable);
        firstNameField.setEditable(editable);
        lastNameField.setEditable(editable);
        submitButton.setEnabled(editable);
        // Cancel button should always be enabled to allow user to back out
    }
    /**
     * Validates the input fields (Part of Use Case Step 4).
     *
     * @param firstName The first name entered by the user.
     * @param lastName The last name entered by the user.
     * @param email The email entered by the user.
     * @return An empty string if validation passes, otherwise an error message.
     */
    private String validateInput(String firstName, String lastName, String email) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return "First Name cannot be empty.";
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            return "Last Name cannot be empty.";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }
        // Basic email validation
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Please enter a valid email address.";
        }
        return ""; // No error
    }
    /**
     * Handles the submission of modified data.
     * Includes validation, confirmation, and saving.
     * (Use Case Steps 3, 4, 5, 6).
     */
    private void submitModification() {
        String newFirstName = firstNameField.getText();
        String newLastName = lastNameField.getText();
        String newEmail = emailField.getText();
        // 4. Check the modified information
        String validationError = validateInput(newFirstName, newLastName, newEmail);
        if (!validationError.isEmpty()) {
            // Where the data is invalid or insufficient, the system activates the use case Errored.
            displayErrorMessage("Validation Error", validationError);
            return; // Stop processing if validation fails
        }
        // Retrieve current tourist data to ensure ID is correct and other fields are consistent
        Tourist currentTourist;
        try {
            currentTourist = touristService.getTouristData(authenticatedUsername);
            if (currentTourist == null) {
                displayErrorMessage("Error", "Authenticated tourist data not found.");
                return;
            }
        } catch (TouristService.TouristServiceException e) {
            displayErrorMessage("Connection Error", e.getMessage());
            return;
        }
        // Create a temporary Tourist object with new data for confirmation/update
        // The ID of the tourist is preserved from the currentTourist object.
        // The username is the authenticatedUsername.
        Tourist updatedTourist = new Tourist(
                currentTourist.getId(), 
                authenticatedUsername, 
                newEmail,
                newFirstName,
                newLastName
        );
        // 4. Asks for confirmation of the change.
        if (showConfirmationDialog(updatedTourist)) { // Use Case Step 5: Confirmation of the transaction change.
            try {
                // 6. Stores the modified data.
                boolean success = touristService.updateTouristData(updatedTourist);
                if (success) {
                    handleUpdateResult(true, "Data successfully updated!");
                    // Refresh displayed data to reflect changes
                    loadTouristData(authenticatedUsername);
                } else {
                    handleUpdateResult(false, "Failed to update data: Tourist not found or other internal error.");
                }
            } catch (TouristService.TouristServiceException e) {
                // Interruption of the connection to the server ETOUR (Exit condition)
                handleUpdateResult(false, e.getMessage());
            }
        } else {
            // Tourist cancels the operation (Exit condition) by not confirming
            JOptionPane.showMessageDialog(this, "Data modification cancelled by user.",
                    "Modification Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Displays a confirmation dialog before applying changes.
     *
     * @param newTouristData The Tourist object with changes pending.
     * @return true if the user confirms, false otherwise.
     */
    private boolean showConfirmationDialog(Tourist newTouristData) {
        int response = JOptionPane.showConfirmDialog(
                this,
                "Do you want to confirm these changes?\n\n" + newTouristData.toString(),
                "Confirm Data Modification",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return response == JOptionPane.YES_OPTION;
    }
    /**
     * Displays the result of the update operation (success or failure).
     *
     * @param success true if the update was successful, false otherwise.
     * @param message The message to display to the user.
     */
    private void handleUpdateResult(boolean success, String message) {
        if (success) {
            // The system shall notify the successful modification of data (Exit condition)
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // This covers various error scenarios including "Errored" use case
            displayErrorMessage("Update Failed", message);
        }
    }
    /**
     * Displays an error message dialog. (Activates "Errored" use case implicitly).
     *
     * @param title The title of the error dialog.
     * @param message The error message to display.
     */
    private void displayErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Handles the cancellation of the modification operation.
     * Closes the modification frame and returns control to the main application.
     * (Exit condition: The Tourist cancels the operation).
     */
    private void cancelModification() {
        setVisible(false); // Hide the current frame
        dispose();         // Release resources for this frame
        JOptionPane.showMessageDialog(parentApp, "Modification operation cancelled.",
                "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        // Make the parent application frame visible again and bring it to the front.
        // This allows the user to return to the main application interface.
        parentApp.setVisible(true);
        parentApp.toFront(); // Bring the parent application window to the foreground
    }
}