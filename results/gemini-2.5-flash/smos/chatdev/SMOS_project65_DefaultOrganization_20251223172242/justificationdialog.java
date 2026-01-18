import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
/*
 * A JDialog for managing (adding or editing) a Justification.
 * It provides fields for reason and approval status.
 */
public class JustificationDialog extends JDialog {
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField dateField;
    private JTextArea reasonArea;
    private JCheckBox approvedCheckBox;
    private JButton saveButton;
    private JButton cancelButton;
    private Justification justification; // The justification being edited, or null for a new one
    private String initialStudentId;
    private String initialStudentName;
    private LocalDate initialDate;
    /**
     * Constructs a dialog for adding a new justification.
     * @param owner The parent frame of the dialog.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param date The date for which justification is being added.
     */
    public JustificationDialog(JFrame owner, String studentId, String studentName, LocalDate date) {
        super(owner, "Add Justification", true);
        this.initialStudentId = studentId;
        this.initialStudentName = studentName;
        this.initialDate = date;
        this.justification = null; // Indicating new justification
        initUI();
        populateFields();
    }
    /**
     * Constructs a dialog for editing an existing justification.
     * @param owner The parent frame of the dialog.
     * @param existingJustification The Justification object to edit.
     * @param studentName The name of the student (for display).
     */
    public JustificationDialog(JFrame owner, Justification existingJustification, String studentName) {
        super(owner, "Edit Justification", true);
        this.justification = existingJustification;
        this.initialStudentId = existingJustification.getStudentId();
        this.initialStudentName = studentName;
        this.initialDate = existingJustification.getDate();
        initUI();
        populateFields();
    }
    /**
     * Initializes the user interface components of the dialog.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Student ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        studentIdField = new JTextField(15);
        studentIdField.setEditable(false);
        formPanel.add(studentIdField, gbc);
        // Student Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        studentNameField = new JTextField(15);
        studentNameField.setEditable(false);
        formPanel.add(studentNameField, gbc);
        // Date
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        dateField = new JTextField(15);
        dateField.setEditable(false);
        formPanel.add(dateField, gbc);
        // Reason
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Reason:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0; gbc.weighty = 0.5;
        reasonArea = new JTextArea(5, 20);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        formPanel.add(scrollPane, gbc);
        // Approved Checkbox
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        approvedCheckBox = new JCheckBox("Approved");
        formPanel.add(approvedCheckBox, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Buttons
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(e -> onSave());
        cancelButton.addActionListener(e -> onCancel());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(400, 400);
        setLocationRelativeTo(getOwner());
    }
    /**
     * Populates the form fields with existing justification data if in edit mode,
     * or with initial student/date data if in add mode.
     */
    private void populateFields() {
        studentIdField.setText(initialStudentId);
        studentNameField.setText(initialStudentName);
        dateField.setText(initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        if (justification != null) {
            reasonArea.setText(justification.getReason());
            approvedCheckBox.setSelected(justification.isApproved());
        } else {
            // Default for new justification
            approvedCheckBox.setSelected(false);
        }
    }
    /**
     * Handles the save action. Validates input and sets the justification object.
     */
    private void onSave() {
        String reason = reasonArea.getText().trim();
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Reason cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (justification == null) { // New justification
            justification = new Justification(
                UUID.randomUUID().toString(),
                initialStudentId,
                initialDate,
                reason,
                approvedCheckBox.isSelected()
            );
        } else { // Editing existing justification
            justification.setReason(reason);
            justification.setApproved(approvedCheckBox.isSelected());
        }
        dispose(); // Close the dialog
    }
    /**
     * Handles the cancel action, discarding any changes.
     */
    private void onCancel() {
        justification = null; // Indicate that no justification was saved/updated
        dispose(); // Close the dialog
    }
    /**
     * Returns the Justification object after the dialog is closed.
     * Will be `null` if the dialog was cancelled or if reason was empty.
     * @return The Justification object if saved, otherwise null.
     */
    public Justification getJustification() {
        return justification;
    }
}