/*
Main GUI application for modifying cultural object data.
Implements the MODIFICABENECULTURALE use case using Java Swing.
Handles user selection, data loading, editing, validation, confirmation, and saving.
Simulates connection interruptions (ETOUR) and invalid data (Errored use case).
Uses SwingWorker for background operations to maintain UI responsiveness.
*/
package com.chatdev.modifybeneconaturale.gui;
import com.chatdev.modifybeneconaturale.dao.CulturalObjectDAO;
import com.chatdev.modifybeneconaturale.model.CulturalObject;
import com.chatdev.modifybeneconaturale.service.CulturalObjectService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException; // For SwingWorker exceptions
public class ModifyCulturalObjectApp extends JFrame {
    private final CulturalObjectService culturalObjectService;
    private final CulturalObjectDAO culturalObjectDAO; // For setting ETOUR simulation
    private JList<CulturalObject> culturalObjectList;
    private DefaultListModel<CulturalObject> listModel;
    private CulturalObject selectedCulturalObject; // Stores the object currently selected and being edited
    // Form fields
    private JTextField txtId;
    private JTextField txtName;
    private JTextArea txtDescription;
    private JTextField txtLocation;
    private JFormattedTextField txtValue; // For numerical input
    // Buttons
    private JButton btnReloadList;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JCheckBox chkSimulateETOUR; // To simulate connection interruption
    // Status message for user feedback
    private JLabel statusLabel;
    /**
     * Constructor for the ModifyCulturalObjectApp GUI.
     * Initializes components and sets up event listeners.
     */
    public ModifyCulturalObjectApp() {
        super("Modifica Bene Culturale (Cultural Object Modification)");
        // Initialize DAO and Service
        this.culturalObjectDAO = new CulturalObjectDAO();
        this.culturalObjectService = new CulturalObjectService(culturalObjectDAO);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents();
        setupLayout();
        addEventHandlers();
        loadCulturalObjects(); // Load initial data on startup
        // Initially, form fields are disabled until an item is selected from the list
        enableFormFields(false);
        // Ensure app is not in a busy state initially
        setAppBusy(false);
    }
    /**
     * Initializes all GUI components.
     * Sets up their initial properties like text, editability, and formatting.
     */
    private void initComponents() {
        // Cultural Object List Component
        listModel = new DefaultListModel<>();
        culturalObjectList = new JList<>(listModel);
        culturalObjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        culturalObjectList.setBorder(BorderFactory.createTitledBorder("Select Cultural Object"));
        // Form Fields for Cultural Object Details
        txtId = new JTextField(20);
        txtId.setEditable(false); // ID should not be editable by the user as it's a primary key
        txtName = new JTextField(30);
        txtDescription = new JTextArea(5, 30); // 5 rows, 30 columns as preferred size
        txtDescription.setLineWrap(true); // Wrap lines if they exceed text area's width
        txtDescription.setWrapStyleWord(true); // Wrap at word boundaries
        txtLocation = new JTextField(30);
        // JFormattedTextField for 'Value' to handle numerical input with locale formatting
        NumberFormat amountFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        txtValue = new JFormattedTextField(amountFormat);
        txtValue.setColumns(15); // Preferred width for value input
        txtValue.setValue(0.0); // Default value
        // Action Buttons
        btnReloadList = new JButton("Reload List");
        btnSubmit = new JButton("Submit Changes");
        btnCancel = new JButton("Cancel");
        // Simulation Checkbox
        chkSimulateETOUR = new JCheckBox("Simulate Connection Interruption (ETOUR)");
        // Status Label at the bottom for user feedback
        statusLabel = new JLabel("Application Ready");
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setBorder(BorderFactory.createEtchedBorder()); // Adds a simple border
    }
    /**
     * Sets up the layout of the GUI components using BorderLayout and GridBagLayout.
     * Organizes elements into distinct panels for clarity.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10)); // Outer layout for main JFrame
        // Left Panel: Contains the list of cultural objects and reload button
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(culturalObjectList), BorderLayout.CENTER); // Add scroll pane for the list
        JPanel reloadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reloadPanel.add(btnReloadList);
        leftPanel.add(reloadPanel, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension(250, getHeight())); // Give list a preferred width
        add(leftPanel, BorderLayout.WEST);
        // Center Panel: Contains the form for editing details and control buttons
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the central content
        // Form Panel: Uses GridBagLayout for flexible alignment of labels and input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Cultural Object Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill their display area horizontally
        int row = 0; // Row counter for GridBagLayout
        // Add ID field
        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.EAST; formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST; formPanel.add(txtId, gbc);
        // Add Name field
        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.EAST; formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST; formPanel.add(txtName, gbc);
        // Add Description field (with JScrollPane for multi-line text)
        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.NORTHEAST; formPanel.add(new JLabel("Description:"), gbc); // Align label to top
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH; // Allow JTextArea to expand both horizontally and vertically
        gbc.weighty = 0.5; // Give some vertical weight for description area
        formPanel.add(new JScrollPane(txtDescription), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Reset fill for subsequent components
        gbc.weighty = 0.0; // Reset vertical weight
        // Add Location field
        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.EAST; formPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST; formPanel.add(txtLocation, gbc);
        // Add Value field
        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.EAST; formPanel.add(new JLabel("Value:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST; formPanel.add(txtValue, gbc);
        centerPanel.add(formPanel, BorderLayout.CENTER);
        // Control buttons panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        controlPanel.add(chkSimulateETOUR);
        controlPanel.add(btnCancel);
        controlPanel.add(btnSubmit);
        centerPanel.add(controlPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        // Status bar at the bottom
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Adds event listeners to GUI components.
     * Links user actions to specific methods for logic execution.
     */
    private void addEventHandlers() {
        // Listener for cultural object list selection changes
        culturalObjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Ensure the event is not an intermediate selection change
                if (!e.getValueIsAdjusting()) {
                    selectedItemChanged();
                }
            }
        });
        // Listener for the "Reload List" button
        btnReloadList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCulturalObjects(); // Reload all objects
            }
        });
        // Listener for the "Submit Changes" button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitChanges(); // Initiate the update process
            }
        });
        // Listener for the "Cancel" button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOperation(); // Cancel the current editing operation
            }
        });
        // Listener for the "Simulate ETOUR" checkbox
        chkSimulateETOUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle ETOUR simulation in the DAO
                culturalObjectDAO.setSimulateETOUR(chkSimulateETOUR.isSelected());
                statusLabel.setText("ETOUR simulation " + (chkSimulateETOUR.isSelected() ? "ACTIVE" : "INACTIVE"));
                statusLabel.setForeground(chkSimulateETOUR.isSelected() ? Color.ORANGE : Color.BLUE);
            }
        });
    }
    /**
     * Helper method to manage the application's busy state, disabling/enabling relevant UI components.
     * This ensures the user cannot interact with the form or trigger new operations during a background task
     * (e.g., loading data or submitting changes), maintaining UI responsiveness and preventing race conditions.
     *
     * @param busy True to set the app to busy state (disable global UI elements), false to set to idle state (enable them).
     */
    private void setAppBusy(boolean busy) {
        // Disable/enable global controls
        culturalObjectList.setEnabled(!busy);
        btnReloadList.setEnabled(!busy);
        chkSimulateETOUR.setEnabled(!busy);
        // Change cursor to indicate loading/processing
        setCursor(Cursor.getPredefinedCursor(busy ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        // Note: btnSubmit and btnCancel are managed by enableFormFields which is called based on selection
        // and also explicitly just before/after the SwingWorker for submission.
    }
    /**
     * Loads cultural objects into the JList.
     * This simulates the "RicercaBeneCulturale" use case.
     * The operation is performed in a background thread using a SwingWorker to prevent UI freezing.
     */
    private void loadCulturalObjects() {
        listModel.clear(); // Clear existing list items
        culturalObjectList.clearSelection(); // Deselect any item
        selectedCulturalObject = null; // Clear selected object
        clearForm(); // Clear the form fields
        enableFormFields(false); // Disable form fields until an item is successfully selected
        statusLabel.setText("Loading cultural objects...");
        statusLabel.setForeground(Color.ORANGE);
        setAppBusy(true); // Disable global UI during loading
        new SwingWorker<List<CulturalObject>, Void>() {
            @Override
            protected List<CulturalObject> doInBackground() throws Exception {
                // This runs on a background thread.
                // It calls the service to retrieve all cultural objects.
                return culturalObjectService.getAllCulturalObjects();
            }
            @Override
            protected void done() {
                // This runs back on the Event Dispatch Thread (EDT) after doInBackground completes.
                setAppBusy(false); // Re-enable global UI elements.
                try {
                    List<CulturalObject> objects = get(); // Get result from background thread
                    if (objects.isEmpty()) {
                        statusLabel.setText("No cultural objects found.");
                        statusLabel.setForeground(Color.RED);
                    } else {
                        for (CulturalObject obj : objects) {
                            listModel.addElement(obj); // Add objects to the list model
                        }
                        statusLabel.setText("Cultural objects loaded successfully.");
                        statusLabel.setForeground(Color.BLUE);
                    }
                } catch (InterruptedException e) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                    showErrorMessage("Loading of cultural objects was interrupted.");
                    statusLabel.setText("Loading interrupted.");
                    statusLabel.setForeground(Color.RED);
                } catch (ExecutionException e) {
                    // Handle exceptions that occurred in doInBackground (e.g., ConnectionInterruptionException)
                    Throwable cause = e.getCause();
                    if (cause instanceof CulturalObjectDAO.ConnectionInterruptionException) {
                        showErrorMessage("Error loading cultural objects: " + cause.getMessage() + "\nETOUR simulation might be active.");
                        statusLabel.setText("Failed to load objects due to connection error.");
                        statusLabel.setForeground(Color.RED);
                    } else {
                        showErrorMessage("An unexpected error occurred while loading cultural objects: " + cause.getMessage());
                        statusLabel.setText("Failed to load objects due to an unexpected error.");
                        statusLabel.setForeground(Color.RED);
                        e.printStackTrace(); // Log full stack trace for unexpected errors
                    }
                }
                // If an object was previously selected and is still in the list, re-select it
                // and enable the form fields. Otherwise, ensure they are disabled.
                if (culturalObjectList.getSelectedValue() != null) {
                    enableFormFields(true);
                } else {
                    enableFormFields(false);
                }
            }
        }.execute(); // Start the SwingWorker
    }
    /**
     * Handles selection change in the cultural object list.
     * Loads the data of the selected object into the form for editing (Step 2 of use case).
     */
    private void selectedItemChanged() {
        selectedCulturalObject = culturalObjectList.getSelectedValue();
        if (selectedCulturalObject != null) {
            displayCulturalObjectData(selectedCulturalObject); // Populate form with selected object's data
            enableFormFields(true); // Enable form for editing
            statusLabel.setText("Cultural object '" + selectedCulturalObject.getName() + "' selected for editing.");
            statusLabel.setForeground(Color.BLUE);
        } else {
            clearForm(); // Clear form if no item is selected
            enableFormFields(false); // Disable form if no item is selected
            statusLabel.setText("No cultural object selected.");
            statusLabel.setForeground(Color.BLACK);
        }
    }
    /**
     * Displays the data of a given CulturalObject in the form fields.
     *
     * @param obj The CulturalObject whose data is to be displayed.
     */
    private void displayCulturalObjectData(CulturalObject obj) {
        if (obj != null) {
            txtId.setText(obj.getId());
            txtName.setText(obj.getName());
            txtDescription.setText(obj.getDescription());
            txtLocation.setText(obj.getLocation());
            txtValue.setValue(obj.getValue()); // Set value using JFormattedTextField's setValue method
        }
    }
    /**
     * Clears all form fields by setting their text to empty or their default initial state.
     */
    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtDescription.setText("");
        txtLocation.setText("");
        txtValue.setValue(0.0); // Reset numerical field to default value
    }
    /**
     * Enables or disables the core form input fields and the submit/cancel buttons.
     * This is distinct from `setAppBusy` which controls global application interactivity.
     *
     * @param enable True to enable the form controls, false to disable them.
     */
    private void enableFormFields(boolean enable) {
        txtName.setEnabled(enable);
        txtDescription.setEnabled(enable);
        txtLocation.setEnabled(enable);
        txtValue.setEnabled(enable);
        btnSubmit.setEnabled(enable);
        btnCancel.setEnabled(enable);
    }
    /**
     * Collects data from the form fields and creates a new CulturalObject instance.
     * This object represents the user's proposed changes.
     *
     * @return A new CulturalObject with data from the form, or null if a critical input
     * (like a number format) is invalid.
     */
    private CulturalObject createCulturalObjectFromForm() {
        // A selected object must exist to get its ID for update
        if (selectedCulturalObject == null) {
            showErrorMessage("No cultural object selected for modification. This should not happen.");
            return null; // Should be prevented by UI state (disabled form)
        }
        String id = txtId.getText(); // ID is from the non-editable field
        String name = txtName.getText();
        String description = txtDescription.getText();
        String location = txtLocation.getText();
        double value;
        try {
            // Use the formatter associated with JFormattedTextField to parse correctly based on its setup
            NumberFormat formatter = (NumberFormat) txtValue.getFormatter();
            if (formatter != null) {
                // Parse the text from the field into a Number, then get its double value
                Number parsedNumber = formatter.parse(txtValue.getText());
                value = parsedNumber.doubleValue();
            } else {
                 // Fallback if formatter is unexpectedly null (unlikely but defensive)
                value = Double.parseDouble(txtValue.getText());
            }
        } catch (ParseException | NumberFormatException e) {
            showErrorMessage("Invalid numerical value entered for 'Value'. Please enter a valid number (e.g., 1234.56).");
            return null; // Indicate parsing failure
        }
        // Return a NEW CulturalObject with the updated data, retaining the original ID.
        // This ensures immutability of the selectedCulturalObject until an actual update occurs.
        return new CulturalObject(id, name, description, location, value);
    }
    /**
     * Handles the submission of changes to the cultural object.
     * This method orchestrates client-side validation, user confirmation, and
     * the actual update process asynchronously using a SwingWorker to keep the UI responsive.
     * Corresponds to Steps 3-6 of the use case.
     */
    private void submitChanges() {
        // Step 3: Collect data from the form.
        final CulturalObject updatedObject = createCulturalObjectFromForm();
        if (updatedObject == null) {
            // Error message already shown by createCulturalObjectFromForm if parsing failed.
            return;
        }
        // Step 4a: Client-side validation of the data entered.
        List<String> validationErrors = culturalObjectService.validateCulturalObject(updatedObject);
        if (!validationErrors.isEmpty()) {
            // Data is invalid; activate the "Errored" use case simulation.
            showErroredDialog(validationErrors);
            statusLabel.setText("Validation failed. Please correct the errors.");
            statusLabel.setForeground(Color.RED);
            // Form fields remain enabled so the user can correct them.
            return;
        }
        // Step 4b: Ask for confirmation of the transaction before starting the background update.
        int confirmResult = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save these changes for cultural object:\nID: " + updatedObject.getId() + "\nName: " + updatedObject.getName(),
                "Confirm Update",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmResult == JOptionPane.YES_OPTION) {
            // Step 5: User confirmed the operation.
            statusLabel.setText("Saving changes...");
            statusLabel.setForeground(Color.ORANGE);
            // Quality requirement: Block input controls in the form to avoid multiple submissions
            // before the end of the operation.
            enableFormFields(false); // Disable form fields and submit/cancel buttons
            setAppBusy(true);  // Disable other interactive elements like reload/list
            // Execute the update operation in a background thread using SwingWorker
            new SwingWorker<CulturalObjectService.UpdateResult, Void>() {
                @Override
                protected CulturalObjectService.UpdateResult doInBackground() throws Exception {
                    // This code runs in a separate thread.
                    // Step 6: Stores the modified data of the cultural using the service.
                    return culturalObjectService.updateCulturalObject(updatedObject);
                }
                @Override
                protected void done() {
                    // This code runs back on the EDT once doInBackground is complete.
                    setAppBusy(false); // Re-enable global app interactivity
                    try {
                        CulturalObjectService.UpdateResult result = get(); // Get the result from doInBackground()
                        switch (result) {
                            case SUCCESS:
                                // Exit condition: The notification system has been changing the data of the cultural.
                                showSuccessMessage("Cultural object '" + updatedObject.getName() + "' updated successfully!");
                                statusLabel.setText("Update successful!");
                                statusLabel.setForeground(Color.GREEN.darker());
                                // Reload objects to refresh the list and clear/disable the form for new selection
                                loadCulturalObjects();
                                break;
                            case VALIDATION_ERROR:
                                // Server-side or deeper business rule validation failed (e.g., in service)
                                showErroredDialog(culturalObjectService.validateCulturalObject(updatedObject)); // Show specific errors
                                statusLabel.setText("Update failed: Validation errors.");
                                statusLabel.setForeground(Color.RED);
                                enableFormFields(true); // Re-enable form fields for user to correct
                                break;
                            case CONNECTION_ERROR:
                                // Exit condition: Interruption of the connection to the server ETOUR.
                                showErrorMessage("Update failed due to connection interruption (ETOUR). Please try again.");
                                statusLabel.setText("Update failed: Connection error.");
                                statusLabel.setForeground(Color.RED);
                                enableFormFields(true); // Re-enable form for potential retry
                                break;
                            case NOT_FOUND_ERROR:
                                showErrorMessage("Update failed: Cultural object not found in the system. It may have been deleted by another operator.");
                                statusLabel.setText("Update failed: Object not found.");
                                statusLabel.setForeground(Color.RED);
                                loadCulturalObjects(); // Reload to reflect actual data (object might be gone)
                                break;
                            case UNKNOWN_ERROR:
                                showErrorMessage("An unexpected error occurred during the update process. Please contact support.");
                                statusLabel.setText("Update failed: Unknown error.");
                                statusLabel.setForeground(Color.RED);
                                enableFormFields(true); // Re-enable for potential retry
                                break;
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                        showErrorMessage("The update operation was interrupted.");
                        statusLabel.setText("Update interrupted.");
                        statusLabel.setForeground(Color.RED);
                        enableFormFields(true); // Re-enable in case of interruption
                    } catch (ExecutionException e) {
                        // Handle exceptions thrown directly in doInBackground() (e.g., from service or DAO)
                        Throwable cause = e.getCause();
                        String errorMsg;
                        if (cause instanceof CulturalObjectDAO.ConnectionInterruptionException) {
                            errorMsg = "Connection Interruption: " + cause.getMessage();
                            statusLabel.setText("Update failed: " + errorMsg);
                            statusLabel.setForeground(Color.RED);
                        } else if (cause instanceof CulturalObjectDAO.CulturalObjectNotFoundException) {
                             errorMsg = "Object Not Found: " + cause.getMessage();
                             statusLabel.setText("Update failed: " + errorMsg);
                             statusLabel.setForeground(Color.RED);
                             loadCulturalObjects(); // Reload in case object was deleted
                        } else {
                            errorMsg = (cause != null) ? cause.getMessage() : "An unknown error occurred.";
                            statusLabel.setText("Update failed: " + errorMsg);
                            statusLabel.setForeground(Color.RED);
                            e.printStackTrace(); // Log full stack trace for debugging
                        }
                        showErrorMessage("An error occurred during update: " + errorMsg);
                        enableFormFields(true); // Re-enable form
                    }
                }
            }.execute(); // Start the background task
        } else {
            // User cancelled the confirmation ("No" clicked or dialog closed).
            // Exit condition: Operator Agency cancels the operation.
            // Treat this as a full cancellation of the current editing session.
            cancelOperation(); // Calls the central cancellation logic
        }
    }
    /**
     * Handles the cancellation of the modification operation.
     * This acts as an "Exit condition: Operator Agency cancels the operation."
     * It resets the UI to a neutral state, clearing the form and disabling editing fields.
     */
    private void cancelOperation() {
        selectedCulturalObject = null; // Clear the reference to the selected object
        culturalObjectList.clearSelection(); // Clear selection in the list
        clearForm(); // Clear all data from the form fields
        enableFormFields(false); // Disable form fields
        setAppBusy(false); // Ensure general app controls are interactive
        statusLabel.setText("Operation cancelled by operator. Form cleared.");
        statusLabel.setForeground(Color.BLACK);
        // Optionally, one might reload the list here to ensure its state is fully consistent
        // with the server if changes were made elsewhere. For this use case, we just clear the form.
    }
    /**
     * Displays an error message dialog to the user.
     *
     * @param message The error message to display.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays a success message dialog to the user.
     *
     * @param message The success message to display.
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Simulates the "Errored" use case by displaying validation errors in a dialog.
     *
     * @param errors A list of validation error messages to present to the user.
     */
    private void showErroredDialog(List<String> errors) {
        StringBuilder errorMessage = new StringBuilder("The following errors were found:\n");
        for (String error : errors) {
            errorMessage.append("- ").append(error).append("\n");
        }
        JOptionPane.showMessageDialog(this, errorMessage.toString(), "Invalid Data - Errored", JOptionPane.WARNING_MESSAGE);
    }
    /**
     * Main method to run the application.
     * Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set look and feel to system default for better integration with OS
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.println("Could not set system look and feel: " + e.getMessage());
                    // Fallback to default Metal L&F if system L&F is not available
                }
                new ModifyCulturalObjectApp().setVisible(true); // Make the application window visible
            }
        });
    }
}