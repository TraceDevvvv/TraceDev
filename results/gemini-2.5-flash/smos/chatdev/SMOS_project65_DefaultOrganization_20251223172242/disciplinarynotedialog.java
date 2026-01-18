import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
/*
 * A JDialog for adding a new Disciplinary Note.
 * It provides fields for description.
 */
public class DisciplinaryNoteDialog extends JDialog {
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField dateField;
    private JTextArea descriptionArea;
    private JButton saveButton;
    private JButton cancelButton;
    private DisciplinaryNote disciplinaryNote; // The note created/edited, or null for a new one
    private String initialStudentId;
    private String initialStudentName;
    private LocalDate initialDate;
    /**
     * Constructs a dialog for adding a new disciplinary note.
     * @param owner The parent frame of the dialog.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param date The date the disciplinary note applies to.
     */
    public DisciplinaryNoteDialog(JFrame owner, String studentId, String studentName, LocalDate date) {
        super(owner, "Add Disciplinary Note", true);
        this.initialStudentId = studentId;
        this.initialStudentName = studentName;
        this.initialDate = date;
        this.disciplinaryNote = null; // Indicating new note
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
        // Description
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0; gbc.weighty = 0.5;
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane, gbc);
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
     * Populates the form fields with initial student/date data.
     */
    private void populateFields() {
        studentIdField.setText(initialStudentId);
        studentNameField.setText(initialStudentName);
        dateField.setText(initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        // No existing note to populate for 'Add' dialog
        descriptionArea.setText("");
    }
    /**
     * Handles the save action. Validates input and creates the disciplinary note object.
     */
    private void onSave() {
        String description = descriptionArea.getText().trim();
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create a new DisciplinaryNote
        disciplinaryNote = new DisciplinaryNote(
            UUID.randomUUID().toString(),
            initialStudentId,
            initialDate,
            description
        );
        dispose(); // Close the dialog
    }
    /**
     * Handles the cancel action, discarding the new note.
     */
    private void onCancel() {
        disciplinaryNote = null; // Indicate that no note was saved
        dispose(); // Close the dialog
    }
    /**
     * Returns the created DisciplinaryNote object after the dialog is closed.
     * Will be `null` if the dialog was cancelled or description was empty.
     * @return The DisciplinaryNote object if saved, otherwise null.
     */
    public DisciplinaryNote getDisciplinaryNote() {
        return disciplinaryNote;
    }
}