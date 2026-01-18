/**
 * This class represents the GUI form for viewing, modifying, and deleting
 * justification details. It interacts with the JustificationService to
 * perform data operations.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
public class JustificationDetailsForm extends JFrame {
    private JustificationService justificationService;
    private Justification currentJustification;
    // GUI Components
    private JLabel       idLabel;
    private JTextField   employeeNameField;
    private JTextField   absenceDateField; // For LocalDate input
    private JTextArea    reasonArea;
    private JTextField   statusField;
    private JButton      modifyButton;
    private JButton      deleteButton;
    private JButton      saveButton;
    private JButton      cancelButton;
    // DateTimeFormatter for consistent date parsing and formatting
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    /**
     * Constructor for the JustificationDetailsForm.
     *
     * @param service The JustificationService instance for data operations.
     * @param justification The Justification object to display and manage.
     */
    public JustificationDetailsForm(JustificationService service, Justification justification) {
        this.justificationService = service;
        this.currentJustification = justification;
        // Basic JFrame setup
        setTitle("Justification Details (Administrator)");
        setSize(500, 400); // Set a reasonable size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize and set up the GUI components
        initializeComponents();
        setupLayout();
        addEventHandlers();
        // Display the initial justification details and set to view-only mode
        displayJustificationDetails();
        setEditable(false);
    }
    /**
     * Initializes all the GUI components (labels, text fields, buttons).
     */
    private void initializeComponents() {
        // Labels and fields for justification details
        idLabel = new JLabel(); // ID is usually display-only or set by the system
        employeeNameField = new JTextField(20);
        absenceDateField = new JTextField(20);
        reasonArea = new JTextArea(5, 20); // 5 rows, 20 columns for the text area
        reasonArea.setLineWrap(true); // Wrap lines if text exceeds bounds
        reasonArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane reasonScrollPane = new JScrollPane(reasonArea); // Add scroll for reason area
        statusField = new JTextField(20); // Status might be a dropdown in a real app
        // Action buttons
        modifyButton = new JButton("Modify");
        deleteButton = new JButton("Delete");
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
    }
    /**
     * Sets up the layout of the form using GridBagLayout for flexible positioning.
     */
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontally
        // Row 0: Justification ID (Display only)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(idLabel, gbc);
        // Row 1: Employee Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Employee Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(employeeNameField, gbc);
        // Row 2: Absence Date
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Absence Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(absenceDateField, gbc);
        // Row 3: Reason (JTextArea with JScrollPane)
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(new JLabel("Reason:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weighty = 0.5; // Allow textArea to expand vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        mainPanel.add(new JScrollPane(reasonArea), gbc);
        gbc.weighty = 0; // Reset weight after reasonArea
        // Row 4: Status
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Reset fill for single line components
        mainPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(statusField, gbc);
        // Buttons Panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        // Add main panel and button panel to the JFrame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Adds action listeners to the buttons to handle user interactions.
     * This includes modify, delete, save, and cancel actions.
     */
    private void addEventHandlers() {
        // Action for Modify Button: Enable editing fields and show Save/Cancel
        modifyButton.addActionListener(e -> {
            setEditable(true);
            modifyButton.setVisible(false);
            deleteButton.setVisible(false);
            saveButton.setVisible(true);
            cancelButton.setVisible(true);
        });
        // Action for Delete Button: Show confirmation dialog and then delete
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this justification?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (justificationService.deleteJustification(currentJustification.getId())) {
                    JOptionPane.showMessageDialog(this, "Justification deleted successfully.");
                    // After deletion, close the form (simulates admin interrupting connection)
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete justification.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Action for Save Button: Validate inputs, update justification, update service, then go back to view mode
        saveButton.addActionListener(e -> {
            if (validateInput()) { // Perform input validation
                try {
                    // Create a new Justification object with updated data (or modify current one)
                    currentJustification.setEmployeeName(employeeNameField.getText().trim());
                    currentJustification.setAbsenceDate(LocalDate.parse(absenceDateField.getText().trim(), DATE_FORMATTER));
                    currentJustification.setReason(reasonArea.getText().trim());
                    currentJustification.setStatus(statusField.getText().trim());
                    if (justificationService.updateJustification(currentJustification)) {
                        JOptionPane.showMessageDialog(this, "Justification updated successfully.");
                        // Switch back to view mode
                        setEditable(false);
                        modifyButton.setVisible(true);
                        deleteButton.setVisible(true);
                        saveButton.setVisible(false);
                        cancelButton.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update justification.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Action for Cancel Button: Revert changes, go back to view mode
        cancelButton.addActionListener(e -> {
            // Revert fields to original justification details
            displayJustificationDetails();
            setEditable(false);
            modifyButton.setVisible(true);
            deleteButton.setVisible(true);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
        });
    }
    /**
     * Populates the form fields with the details of the currentJustification object.
     */
    private void displayJustificationDetails() {
        if (currentJustification != null) {
            idLabel.setText(currentJustification.getId());
            employeeNameField.setText(currentJustification.getEmployeeName());
            // Format LocalDate to String for display, handling null gracefully
            absenceDateField.setText(currentJustification.getAbsenceDate() != null ?
                                     currentJustification.getAbsenceDate().format(DATE_FORMATTER) : "");
            reasonArea.setText(currentJustification.getReason());
            statusField.setText(currentJustification.getStatus());
        } else {
            // Clear fields if no justification is set
            idLabel.setText("N/A");
            employeeNameField.setText("");
            absenceDateField.setText("");
            reasonArea.setText("");
            statusField.setText("");
            JOptionPane.showMessageDialog(this, "No justification details to display.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Sets the editability of the input fields.
     * @param editable True to make fields editable, false for view-only.
     */
    private void setEditable(boolean editable) {
        employeeNameField.setEditable(editable);
        absenceDateField.setEditable(editable);
        reasonArea.setEditable(editable);
        statusField.setEditable(editable);
    }
    /**
     * Validates the input fields before saving.
     * @return True if all inputs are valid, false otherwise.
     */
    private boolean validateInput() {
        String empName = employeeNameField.getText().trim();
        String absenceDateStr = absenceDateField.getText().trim();
        String reason = reasonArea.getText().trim();
        String status = statusField.getText().trim();
        if (empName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Employee Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (absenceDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Absence Date cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalDate.parse(absenceDateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date format for Absence Date. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Reason cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Status cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}