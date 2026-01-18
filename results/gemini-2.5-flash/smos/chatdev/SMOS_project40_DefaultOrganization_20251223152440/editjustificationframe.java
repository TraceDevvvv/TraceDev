'''
Provides the graphical interface for editing a specific Justification.
Users (Administrators) can modify the justification's date and description.
'''
package gui;
import model.Justification;
import service.JustificationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate; // For basic date validation
import java.time.format.DateTimeParseException;
/**
 * This JDialog allows an administrator to edit the details of a single Justification.
 * It displays fields for the justification's ID (read-only), date, and description.
 * Upon saving, it updates the justification via the JustificationService and notifies a listener.
 * Implemented as a modal JDialog to ensure focused user interaction.
 */
public class EditJustificationFrame extends JDialog { // CHANGED from JFrame to JDialog
    private final JustificationService justificationService;
    private final Justification originalJustification;
    private final JustificationUpdateListener listener; // Callback for when update is complete
    private JTextField idField;
    private JTextField dateField;
    private JTextArea descriptionArea;
    private JButton saveButton;
    private JButton cancelButton;
    /**
     * Constructs a new EditJustificationFrame.
     * @param owner The parent Frame of this dialog (e.g., JustificationListFrame).
     * @param justificationService The service used to perform the update.
     * @param justification The Justification object to be edited.
     * @param listener The listener to notify upon successful update.
     */
    public EditJustificationFrame(Frame owner, JustificationService justificationService, Justification justification, JustificationUpdateListener listener) { // Added Frame owner
        super(owner, "Edit Justification (ID: " + justification.getId() + ")", true); // Call JDialog constructor, set title and modality true
        this.justificationService = justificationService;
        this.originalJustification = justification; // Store the original to pre-fill fields
        this.listener = listener;
        // setTitle("Edit Justification (ID: " + justification.getId() + ")"); // Title is set in super constructor
        setSize(500, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Changed from JFrame.DISPOSE_ON_CLOSE to JDialog.DISPOSE_ON_CLOSE
        setLocationRelativeTo(owner); // Center the window relative to its parent
        setResizable(false); // Make frame fixed size for simplicity
        initComponents(); // Initialize GUI components
        populateFields(); // Populate fields with original justification data
    }
    /**
     * Initializes all GUI components within the frame.
     * Sets up text fields, text area, and buttons, along with their layout.
     */
    private void initComponents() {
        // Use JPanel with BorderLayout for overall structure
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Add padding to the main panel
        // Form Panel using GridBagLayout for flexible alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontally
        // ID Field (read-only)
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.EAST; // Label alignment
        formPanel.add(new JLabel("Justification ID:"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.weightx = 1.0; // Field takes extra horizontal space
        idField = new JTextField(String.valueOf(originalJustification.getId()));
        idField.setEditable(false); // ID cannot be changed
        idField.setBackground(Color.LIGHT_GRAY); // Visually indicate it's read-only
        formPanel.add(idField, gbc);
        // Date Field
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        dateField = new JTextField(20); // Width hint
        formPanel.add(dateField, gbc);
        // Description/Justification Area
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.NORTHEAST; // Label aligned top-right
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        gbc.weighty = 1.0; // Text area takes extra vertical space
        gbc.fill = GridBagConstraints.BOTH; // Fills both vertically and horizontally
        descriptionArea = new JTextArea(5, 20); // Rows, columns hint
        descriptionArea.setLineWrap(true); // Wrap text to next line
        descriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Add scroll capability
        formPanel.add(scrollPane, gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to center of main panel
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Align buttons to the right
        saveButton = new JButton("Save Changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 12));
        buttonPanel.add(saveButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to south of main panel
        add(mainPanel); // Add the main panel to the frame's content pane
        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame without saving
            }
        });
    }
    /**
     * Populates the form fields with the data from the original Justification object.
     * This prepares the form for the administrator to make changes.
     */
    private void populateFields() {
        if (originalJustification != null) {
            idField.setText(String.valueOf(originalJustification.getId()));
            dateField.setText(originalJustification.getJustificationDate());
            descriptionArea.setText(originalJustification.getDescription());
        }
    }
    /**
     * Validates user input and processes the saving of changes.
     * This method handles the "Click on 'Save'" event and subsequent "Change the justification" system action.
     */
    private void saveChanges() {
        // 1. Change one or more fields of the displayed form: date justification.
        // This step is already done by the user interacting with fields.
        String newDate = dateField.getText().trim();
        String newDescription = descriptionArea.getText().trim();
        // Basic input validation
        if (newDate.isEmpty() || newDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date and Description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validate date format (simple YYYY-MM-DD check)
        if (!isValidDateFormat(newDate)) {
             JOptionPane.showMessageDialog(this, "Please enter a valid date format (YYYY-MM-DD).", "Input Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        // Create a temporary Justification object with updated details to pass to the service
        // The ID remains the same, as we are updating an existing record.
        Justification updatedJustification = new Justification(originalJustification.getId(), newDate, newDescription);
        // 2. Click on "Save" (triggered by this method call)
        // 3. Change the justification. (System action, handled by JustificationService)
        boolean success = justificationService.updateJustification(updatedJustification);
        if (success) {
            // Postcondition: The justification has been modified.
            // Notify the listener (JustificationListFrame) that an update occurred.
            // This allows the list frame to refresh its data.
            if (listener != null) {
                listener.onJustificationUpdated();
            }
            dispose(); // Close the edit frame upon successful save
        } else {
            // This case should ideally not happen if originalJustification is valid,
            // but good for robustness if the ID suddenly became invalid.
            JOptionPane.showMessageDialog(this, "Failed to update justification. It might no longer exist.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Performs a basic validation of the date string to ensure it's in YYYY-MM-DD format
     * and represents a valid calendar date.
     * @param dateString The string to validate.
     * @return true if the date string is in YYYY-MM-DD format and represents a valid date, false otherwise.
     */
    private boolean isValidDateFormat(String dateString) {
        try {
            // Attempt to parse the date using LocalDate.parse.
            // This method inherently performs strict parsing for both format (YYYY-MM-DD)
            // and calendar date validity (e.g., it will not allow 'February 30th').
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}