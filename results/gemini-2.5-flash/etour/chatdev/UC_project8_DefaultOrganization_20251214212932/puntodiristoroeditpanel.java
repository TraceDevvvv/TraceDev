'''
A JPanel that allows an agency operator to view and edit the data
of a single PuntoDiRistoro. It handles data validation and submission
according to the use case steps (Steps 2-6).
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Explicitly import DateTimeFormatter
import java.util.Optional;
public class PuntoDiRistoroEditPanel extends JPanel {
    /**
     * Interface to communicate save/cancel actions and errors from this panel
     * to the main application frame (or a higher-level controller).
     */
    public interface PuntoDiRistoroActionListener {
        /**
         * Called when a PuntoDiRistoro is successfully saved.
         * @param updatedPunto The PuntoDiRistoro object with its newly saved data.
         */
        void onPuntoDiRistoroSaved(PuntoDiRistoro updatedPunto);
        /**
         * Called when the user cancels the editing operation.
         */
        void onPuntoDiRistoroCancelled();
        /**
         * Called when a service-related error occurs during operations like saving.
         * @param message The error message to display.
         */
        void onError(String message);
    }
    private final PuntoDiRistoroService service;
    private final PuntoDiRistoroActionListener listener;
    private PuntoDiRistoro originalPuntoDiRistoro; // Holds the original object for potential reference (not directly used for modification)
    private PuntoDiRistoro editablePuntoDiRistoro; // The actual object whose data is being edited (a copy of original)
    // UI Components for displaying and editing PuntoDiRistoro data
    private JLabel idLabel;
    private JTextField nameField;
    private JTextField addressField;
    private JComboBox<String> typeComboBox;
    private JCheckBox activeCbx;
    private JCheckBox functionalCbx;
    private JLabel lastModifiedLabel;
    private JButton saveButton;
    private JButton cancelButton;
    /**
     * Constructs a PuntoDiRistoroEditPanel.
     *
     * @param service The service responsible for validating and saving punto di ristoro data.
     * @param listener The listener to notify about save/cancel actions and errors.
     */
    public PuntoDiRistoroEditPanel(PuntoDiRistoroService service, PuntoDiRistoroActionListener listener) {
        this.service = service;
        this.listener = listener;
        initComponents(); // Initialize the UI components
    }
    /**
     * Initializes and lays out the UI components of the panel.
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        // Title for the editing form
        JLabel titleLabel = new JLabel("Edit Point of Rest Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        // Form Panel using GridBagLayout for flexible and aligned component placement
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component
        gbc.anchor = GridBagConstraints.WEST; // Anchor components to the west
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill available horizontal space
        // Row 0: ID (read-only)
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; // Give horizontal weight to data field
        idLabel = new JLabel(); // Display ID as non-editable text
        formPanel.add(idLabel, gbc);
        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        nameField = new JTextField(20); // Text field for name input
        formPanel.add(nameField, gbc);
        // Row 2: Address
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        addressField = new JTextField(20); // Text field for address input
        formPanel.add(addressField, gbc);
        // Row 3: Type (Dropdown)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        formPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        String[] types = {"Select Type", "Restaurant", "Cafe", "Hotel", "Bar", "Gelateria", "Other"};
        typeComboBox = new JComboBox<>(types); // Dropdown for selecting type
        formPanel.add(typeComboBox, gbc);
        // Row 4: Active Checkbox
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        formPanel.add(new JLabel("Active:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        activeCbx = new JCheckBox(); // Checkbox for active status
        formPanel.add(activeCbx, gbc);
        // Row 5: Functional Checkbox
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        formPanel.add(new JLabel("Functional:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        functionalCbx = new JCheckBox(); // Checkbox for functional status
        formPanel.add(functionalCbx, gbc);
        // Row 6: Last Modified (read-only information)
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        formPanel.add(new JLabel("Last Modified:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6; gbc.weightx = 1.0;
        lastModifiedLabel = new JLabel(); // Label to display last modified timestamp
        lastModifiedLabel.setForeground(Color.GRAY); // A subtle color for informational text
        formPanel.add(lastModifiedLabel, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Buttons Panel for Save and Cancel actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Right-aligned buttons
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // --- Action Listeners for buttons ---
        // ActionListener for the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSave(); // Trigger the save handling logic
            }
        });
        // ActionListener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This fulfills the exit condition: "Operator Agency cancels the operation."
                // Ensure to reset any saving state if it was active before cancelling,
                // although a cancel button wouldn't be enabled if a save operation was in progress.
                listener.onPuntoDiRistoroCancelled(); // Notify listener that operation was canceled
            }
        });
    }
    /**
     * Loads the data of a specified PuntoDiRistoro object into the form for editing.
     * This method creates an editable copy to avoid direct modification of the original
     * data object passed from the list view.
     * (Use Case Step 2: Upload data from the point of rest and displays the form of change.)
     *
     * @param punto The PuntoDiRistoro object to be edited.
     */
    public void loadPuntoDiRistoro(PuntoDiRistoro punto) {
        if (punto == null) {
            // If no data is provided, show an error and return to the previous view.
            ErroredDialog.display(this, "No Point of Rest data provided for editing.");
            listener.onPuntoDiRistoroCancelled(); // Simulate a cancellation if no data
            return;
        }
        this.originalPuntoDiRistoro = punto; // Store original for reference
        this.editablePuntoDiRistoro = new PuntoDiRistoro(punto); // Create a mutable copy for editing
        // Populate UI fields with data from the editable copy
        idLabel.setText(editablePuntoDiRistoro.getId());
        nameField.setText(editablePuntoDiRistoro.getName());
        addressField.setText(editablePuntoDiRistoro.getAddress());
        typeComboBox.setSelectedItem(editablePuntoDiRistoro.getType());
        activeCbx.setSelected(editablePuntoDiRistoro.isActive());
        functionalCbx.setSelected(editablePuntoDiRistoro.isFunctional());
        updateLastModifiedLabel(editablePuntoDiRistoro.getLastModified()); // Update the read-only timestamp
        // Ensure all form components are enabled for editing when loaded.
        setFormEnabled(true); 
        saveButton.setText("Save Changes"); // Reset button text in case it was "Saving..."
        cancelButton.setEnabled(true); // Ensure cancel button is enabled when loading for edit
    }
    /**
     * Updates the 'Last Modified' label with the formatted date and time.
     *
     * @param dateTime The LocalDateTime object to display.
     */
    private void updateLastModifiedLabel(LocalDateTime dateTime) {
        if (dateTime != null) {
            // Format the LocalDateTime to a user-friendly string
            lastModifiedLabel.setText(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            lastModifiedLabel.setText("N/A");
        }
    }
    /**
     * Extracts the current data from the form fields and updates the
     * `editablePuntoDiRistoro` object with these values.
     * (Use Case Step 3: Change data in the form and submit - partial completion here.)
     *
     * @return The `editablePuntoDiRistoro` object, now populated with current form data.
     */
    private PuntoDiRistoro createPuntoDiRistoroFromForm() {
        // Update the editable object's properties from the UI components.
        // The setters within PuntoDiRistoro will automatically update its `lastModified` timestamp.
        editablePuntoDiRistoro.setName(nameField.getText());
        editablePuntoDiRistoro.setAddress(addressField.getText());
        editablePuntoDiRistoro.setType((String) typeComboBox.getSelectedItem());
        editablePuntoDiRistoro.setActive(activeCbx.isSelected());
        editablePuntoDiRistoro.setFunctional(functionalCbx.isSelected());
        return editablePuntoDiRistoro;
    }
    /**
     * Handles the save operation flow:
     * 1. Collects data from the form.
     * 2. Validates the collected data.
     * 3. Asks for user confirmation.
     * 4. If confirmed, blocks form input to prevent multiple submissions.
     * 5. Attempts to save the data via the service.
     * 6. Handles success or failure of the save operation.
     */
    private void handleSave() {
        // Use Case Step 3: Change data in the form and submit.
        // First, update the editable object with current form values for validation.
        PuntoDiRistoro puntoToValidateAndSave = createPuntoDiRistoroFromForm();
        // Use Case Step 4: Verify the data entered in form.
        Optional<String> validationError = service.validatePuntoDiRistoro(puntoToValidateAndSave);
        if (validationError.isPresent()) {
            // If data is invalid or insufficient, activate the use case "Errored".
            ErroredDialog.display(this, "Validation Error: " + validationError.get());
            return; // Stop the save process and allow correction.
        }
        // Use Case Step 4 (cont.): Asks for confirmation of the transaction.
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to save these changes?\n\n" +
                        "ID: " + puntoToValidateAndSave.getId() + "\n" +
                        "Name: " + puntoToValidateAndSave.getName() + "\n" +
                        "Address: " + puntoToValidateAndSave.getAddress() + "\n" +
                        "Type: " + puntoToValidateAndSave.getType() + "\n" +
                        "Active: " + puntoToValidateAndSave.isActive() + "\n" +
                        "Functional: " + puntoToValidateAndSave.isFunctional(),
                "Confirm Save",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        // Use Case Step 5: Confirm the operation.
        if (confirm == JOptionPane.YES_OPTION) {
            // Quality requirement: "The system requirements blocks of input controls in the form
            // once we receive confirmation of the change to avoid multiple submissions before the end of the operation."
            setFormEnabled(false);      // Disable form fields and Save button
            saveButton.setText("Saving..."); // Provide user feedback that save is in progress
            cancelButton.setEnabled(false); // Explicitly disable cancel button during active save operation
            try {
                // Use Case Step 6: Stores the modified data of the point of rest.
                service.savePuntoDiRistoro(puntoToValidateAndSave);
                JOptionPane.showMessageDialog(this, "Point of Rest data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Notify the listener (main app) that the save was successful.
                listener.onPuntoDiRistoroSaved(puntoToValidateAndSave);
                // Exit condition: "The system has been reporting the information required by the point of rest."
                // The main app will switch back to the list view, effectively resetting this panel.
            } catch (ServiceException ex) {
                // Handle service exceptions, including "Interruption of the connection to the server ETOUR."
                listener.onError("Save failed: " + ex.getMessage());
                setFormEnabled(true);        // Re-enable form controls and Save button
                saveButton.setText("Save Changes"); // Reset button text
                cancelButton.setEnabled(true); // Re-enable cancel button on failed save to allow retry or cancel
            }
        }
        // If confirm is NO_OPTION, no action is taken, and the user can continue editing.
        // In this case, the form remains enabled and the buttons retain their original state.
    }
    /**
     * Enables or disables all editable input controls in the form,
     * including the save button. The cancel button's state is managed
     * independently in the handleSave method for specific transaction states.
     *
     * @param enabled True to enable controls for editing, false to disable them.
     */
    public void setFormEnabled(boolean enabled) {
        nameField.setEnabled(enabled);
        addressField.setEnabled(enabled);
        typeComboBox.setEnabled(enabled);
        activeCbx.setEnabled(enabled);
        functionalCbx.setEnabled(enabled);
        saveButton.setEnabled(enabled);
        // The cancelButton is managed explicitly in handleSave() where its state
        // depends on the actual saving transaction progress, not just general form enablement.
        // Do NOT set cancelButton.setEnabled(enabled) here directly.
    }
}