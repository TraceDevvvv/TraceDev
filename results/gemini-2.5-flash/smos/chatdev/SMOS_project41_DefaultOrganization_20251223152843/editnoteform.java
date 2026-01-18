/*
A JPanel that provides a graphical user interface for editing details of a DisciplinaryNote.
It includes fields for student name, description, teacher name, and date, pre-populated with data from an existing note.
It validates user input before allowing the data to be extracted.
*/
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * JPanel form for editing a DisciplinaryNote.
 * Provides input fields for student, description, teacher, and date.
 */
public class EditNoteForm extends JPanel {
    private JTextField studentField;
    private JTextArea descriptionField;
    private JTextField teacherField;
    private JTextField dateField; // Using JTextField for date, expecting yyyy-MM-dd format
    private DisciplinaryNote originalNote; // Stores the note being edited
    // Date format for display and parsing
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Constructs an EditNoteForm panel.
     *
     * @param note The DisciplinaryNote object to be edited. Its data will pre-populate the form.
     */
    public EditNoteForm(DisciplinaryNote note) {
        this.originalNote = note;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add some padding
        initComponents(); // Initialize all GUI components
        populateForm(note); // Fill form with the existing note's data
    }
    /**
     * Initializes and lays out the GUI components of the form.
     */
    private void initComponents() {
        // Create a JPanel for the form fields using GridBagLayout for alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components stretch horizontally
        // Label and field for Student Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END; // Align label to the right
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Student field takes extra horizontal space
        gbc.anchor = GridBagConstraints.LINE_START; // Align field to the left
        studentField = new JTextField(25);
        formPanel.add(studentField, gbc);
        // Label and field for Teacher Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Reset weight
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Teacher Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        teacherField = new JTextField(25);
        formPanel.add(teacherField, gbc);
        // Label and field for Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        dateField = new JTextField(15);
        formPanel.add(dateField, gbc);
        // Label and field for Description (TextArea)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END; // Align label to top-right for JTextArea
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Description field takes extra vertical space
        gbc.fill = GridBagConstraints.BOTH; // Description field fills both horizontally and vertically
        descriptionField = new JTextArea(5, 25); // 5 rows, 25 columns
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(descriptionField); // Add scrollbar for long descriptions
        formPanel.add(scrollPane, gbc);
        // Add the form panel to the center of the BorderLayout
        add(formPanel, BorderLayout.CENTER);
    }
    /**
     * Populates the form fields with data from the given DisciplinaryNote.
     *
     * @param note The DisciplinaryNote whose data will fill the form.
     */
    public void populateForm(DisciplinaryNote note) {
        if (note != null) {
            studentField.setText(note.getStudentName());
            descriptionField.setText(note.getDescription());
            teacherField.setText(note.getTeacherName());
            dateField.setText(note.getNoteDate().format(DATE_FORMATTER));
        } else {
            // Clear fields if no note is provided for new entry (though this form is for editing)
            studentField.setText("");
            descriptionField.setText("");
            teacherField.setText("");
            dateField.setText(LocalDate.now().format(DATE_FORMATTER)); // Default to current date
        }
    }
    /**
     * Validates the input in the form fields and returns a DisciplinaryNote object
     * with the updated data. If validation fails, it returns null.
     *
     * @return A new DisciplinaryNote object with updated data if valid, otherwise null.
     */
    public DisciplinaryNote getUpdatedNoteData() {
        String student = studentField.getText().trim();
        String description = descriptionField.getText().trim();
        String teacher = teacherField.getText().trim();
        String dateString = dateField.getText().trim();
        LocalDate noteDate;
        // Basic validation
        if (student.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Description cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (teacher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (dateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            noteDate = LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date format. Please use yyyy-MM-dd.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        // Create a new DisciplinaryNote object with the original ID and updated data
        // The original ID is crucial for the NoteService to find and update the correct record.
        return new DisciplinaryNote(originalNote.getId(), student, description, teacher, noteDate);
    }
}