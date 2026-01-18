'''
Graphical User Interface (GUI) for the Refreshment Point Editor application.
Allows an operator to load, view, modify, and save Refreshment Point data.
Implements the user interaction flow as described in the use case.
'''
package gui;
import service.RefreshmentPointService;
import model.RefreshmentPoint;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class RefreshmentPointEditorGUI extends JFrame {
    private final RefreshmentPointService service;
    // GUI Components
    private JComboBox<String> idComboBox;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextArea descriptionArea;
    private JButton loadButton;
    private JButton saveButton;
    private JButton cancelButton;
    // Stores the ID of the point currently loaded/edited
    private String currentPointId = null;
    /**
     * Constructs the GUI for the Refreshment Point Editor.
     * This also covers the "Entry Conditions: The Point Restaurant Operator has successfully authenticated to the system."
     * (implicitly, as the GUI is accessible after successful authentication).
     *
     * @param service The RefreshmentPointService to interact with for business logic.
     */
    public RefreshmentPointEditorGUI(RefreshmentPointService service) {
        this.service = service;
        setTitle("MODIFICADATIPUNTODIRISTORO - Change Refreshment Point Data");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initializeUI();
        populateIdComboBox(); // Load available IDs into the combo box on startup
    }
    /**
     * Initializes and arranges all Swing GUI components.
     * This forms the "form" mentioned in the use case for displaying and changing data.
     * It also enables the functionality required by the point of rest (Flow of events, step 1).
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // North Panel for ID selection and Load button
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        idPanel.add(new JLabel("Select Point ID:"));
        idComboBox = new JComboBox<>();
        idComboBox.setPreferredSize(new Dimension(150, 25));
        idPanel.add(idComboBox);
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadRefreshmentPoint());
        idPanel.add(loadButton);
        add(idPanel, BorderLayout.NORTH);
        // Center Panel for data input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        nameField = new JTextField(25);
        formPanel.add(nameField, gbc);
        // Address
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        addressField = new JTextField(25);
        formPanel.add(addressField, gbc);
        // Contact Phone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Contact Phone:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        phoneField = new JTextField(25);
        formPanel.add(phoneField, gbc);
        // Description (uses JTextArea for multi-line input)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Align label to top-right
        gbc.weightx = 0;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.gridheight = 2; // Make description span two rows visually
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        descriptionArea = new JTextArea(5, 25); // 5 rows, 25 columns
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Add scrollbar for description
        formPanel.add(scrollPane, gbc);
        add(formPanel, BorderLayout.CENTER);
        // South Panel for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        saveButton = new JButton("Save Changes");
        saveButton.setEnabled(false); // Disable until a point is loaded
        saveButton.addActionListener(e -> saveRefreshmentPoint());
        buttonPanel.add(saveButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false); // Disable until a point is loaded
        cancelButton.addActionListener(e -> cancelOperation());
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the ID combo box with all available refreshment point IDs from the service.
     * This ensures the functionality for information is present.
     */
    private void populateIdComboBox() {
        try {
            List<String> ids = service.getAllPointIds();
            idComboBox.removeAllItems(); // Clear existing items
            if (ids.isEmpty()) {
                idComboBox.addItem("No Points Available");
                loadButton.setEnabled(false);
            } else {
                ids.forEach(idComboBox::addItem);
                loadButton.setEnabled(true);
            }
        } catch (RuntimeException e) {
            displayErrorMessage("Failed to load point IDs: " + e.getMessage());
            idComboBox.removeAllItems();
            idComboBox.addItem("Error loading IDs");
            loadButton.setEnabled(false);
        }
    }
    /**
     * Loads the data for the selected refreshment point and displays it in the form.
     * This covers step 2 of the flow of events: "Upload data point Refreshments and displays them in a form."
     */
    private void loadRefreshmentPoint() {
        String selectedId = (String) idComboBox.getSelectedItem();
        if (selectedId == null || selectedId.equals("No Points Available") || selectedId.equals("Error loading IDs")) {
            displayErrorMessage("Please select a valid Refreshment Point ID to load.");
            clearForm(); // Clear form if an invalid selection is made
            setEditingState(false);
            return;
        }
        try {
            RefreshmentPoint point = service.getRefreshmentPoint(selectedId);
            if (point != null) {
                currentPointId = point.getId();
                nameField.setText(point.getName());
                addressField.setText(point.getAddress());
                phoneField.setText(point.getContactPhone());
                descriptionArea.setText(point.getDescription());
                setEditingState(true); // Enable save/cancel buttons
                System.out.println("GUI: Loaded data for point: " + currentPointId);
            } else {
                displayErrorMessage("Refreshment Point with ID '" + selectedId + "' not found.");
                clearForm();
                setEditingState(false);
                currentPointId = null;
            }
        } catch (RuntimeException e) {
            displayErrorMessage("Error loading refreshment point: " + e.getMessage());
            clearForm();
            setEditingState(false);
            currentPointId = null;
        }
    }
    /**
     * Gathers data from the form fields, validates it, and attempts to save the changes.
     * This covers steps 3, 4, 5, 6, and exit condition 1.
     */
    private void saveRefreshmentPoint() {
        if (currentPointId == null) {
            displayErrorMessage("No refreshment point loaded to save.");
            return;
        }
        // Step 3: Change data in the form and submit. (Implicitly done by user editing fields)
        // Create a new RefreshmentPoint object with data from the form
        RefreshmentPoint updatedPoint = new RefreshmentPoint(
            currentPointId, // ID should not be changed by the user in this use case
            nameField.getText(),
            addressField.getText(),
            phoneField.getText(),
            descriptionArea.getText()
        );
        // Step 4: Verify the data entered and asks for confirmation of the change.
        List<String> validationErrors = service.validateRefreshmentPoint(updatedPoint);
        if (!validationErrors.isEmpty()) {
            // Where the data is invalid or insufficient, the system activates the use case Errored.
            displayErrorMessage("Validation Error(s):\n" + String.join("\n", validationErrors));
            return;
        }
        // Step 4 (cont.): Asks for confirmation of the change.
        int confirm = displayConfirmationMessage("Are you sure you want to save changes for ID: " + currentPointId + "?");
        if (confirm == JOptionPane.YES_OPTION) {
            // Step 5: Confirm the operation.
            try {
                // Step 6: Stores the modified data of the point of rest.
                List<String> serviceErrors = service.updateRefreshmentPoint(updatedPoint);
                if (!serviceErrors.isEmpty()) {
                    // This path is usually not taken if validateRefreshmentPoint is called first,
                    // but it's a fallback for any service-level validation issues.
                    displayErrorMessage("Failed to save changes due to service-level validation:\n" + String.join("\n", serviceErrors));
                } else {
                    displayInformationMessage("Refreshment Point " + currentPointId + " data successfully updated.");
                    // Exit condition: The notification system has been changing the data point selected restaurants.
                    resetFormState();
                    populateIdComboBox(); // Refresh the list in case of changes (though not strictly necessary for update)
                }
            } catch (RuntimeException e) {
                // Catches errors like "ETOUR connection interruption" from the service layer (Exit condition: Interruption of the connection to the server ETOUR).
                // This also activates the "use case Errored" implicitly due to system error.
                displayErrorMessage("Failed to save changes: " + e.getMessage());
            }
        } else {
            System.out.println("GUI: Save operation cancelled by user.");
        }
    }
    /**
     * Clears the form fields and resets the state.
     * This covers exit condition 2: "Restaurant Point Of Operator cancels the operation."
     * It also helps prepare the form for a new load or after a successful save.
     */
    private void cancelOperation() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel? All unsaved changes will be lost.",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("GUI: Operation cancelled by operator.");
            resetFormState(); // Reset form and button states
            // If there were valid items initially, try to re-select the first one or clear.
            populateIdComboBox(); // Re-populate to ensure current state reflecting data.
            if (idComboBox.getItemCount() > 0) {
                idComboBox.setSelectedIndex(0); // Select the first item by default
            }
        }
    }
    /**
     * Resets the form fields and button states to their initial, unarmed state.
     */
    private void resetFormState() {
        clearForm();
        currentPointId = null;
        setEditingState(false);
    }
    /**
     * Sets the enabled state of save/cancel buttons and input fields.
     * @param editing true to enable editing controls, false to disable.
     */
    private void setEditingState(boolean editing) {
        nameField.setEnabled(editing);
        addressField.setEnabled(editing);
        phoneField.setEnabled(editing);
        descriptionArea.setEnabled(editing);
        saveButton.setEnabled(editing);
        cancelButton.setEnabled(editing);
        // Optionally disable ID selection while editing, though current logic handles it
        // idComboBox.setEnabled(!editing);
        // loadButton.setEnabled(!editing);
    }
    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        descriptionArea.setText("");
    }
    /**
     * Displays an error message dialog to the user.
     * This is triggered when validation fails ("use case Errored") or a system error occurs.
     *
     * @param message The error message to display.
     */
    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays an informational message dialog to the user.
     *
     * @param message The information message to display.
     */
    private void displayInformationMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Displays a confirmation dialog to the user.
     *
     * @param message The confirmation message to display.
     * @return The user's choice (YES_OPTION, NO_OPTION).
     */
    private int displayConfirmationMessage(String message) {
        return JOptionPane.showConfirmDialog(this,
                                             message,
                                             "Confirm Changes",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE);
    }
}