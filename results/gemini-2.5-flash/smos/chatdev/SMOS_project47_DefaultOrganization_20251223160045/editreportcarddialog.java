/*
editreportcarddialog.java
This class provides the graphical user interface for an administrator to
edit a student's report card. It displays existing grades and allows input
for new ones, handling validation and update operations through the StudentService.
*/
package gui;
import model.Student;
import model.SubjectGrade;
import service.StudentService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A dialog window for editing a student's report card.
 * Allows administrators to modify grades for various subjects and add new subjects.
 */
public class EditReportCardDialog extends JDialog {
    private Student studentToEdit;        // The student whose report card is being edited
    private StudentService studentService; // Service to interact with student data
    private boolean updated = false;      // Flag to indicate if the report card was updated successfully
    private JLabel studentIdLabel;
    private JLabel studentNameLabel;
    private JPanel gradesPanel;                 // Panel to dynamically add subject grade input fields
    private Map<String, JTextField> gradeFields; // Map to hold JTextFields for each subject's grade
    // New fields for adding subjects dynamically to the form
    private JTextField newSubjectNameField;
    private JTextField newSubjectGradeInputField;
    // Stores subjects added via "Add Subject" button before final save.
    // Changed from List<SubjectGrade> to Map<String, SubjectGrade> to enforce uniqueness by subject name.
    private Map<String, SubjectGrade> temporaryNewGrades;
    /**
     * Constructs an EditReportCardDialog.
     * @param owner The parent frame of this dialog.
     * @param student The Student object whose report card is to be edited.
     * @param studentService The StudentService instance to handle data operations.
     */
    public EditReportCardDialog(JFrame owner, Student student, StudentService studentService) {
        super(owner, "Edit Report Card for " + student.getName(), true); // Modal dialog
        this.studentToEdit = student;
        this.studentService = studentService;
        this.gradeFields = new HashMap<>();
        this.temporaryNewGrades = new HashMap<>(); // Initialize temporary storage as a HashMap
        // Set layout for the dialog
        setLayout(new BorderLayout(10, 10));
        setSize(500, 600); // Default size, slightly larger to accommodate new subject input
        setLocationRelativeTo(owner); // Center dialog over parent frame
        initComponents(); // Initialize all GUI components
        populateStudentData(); // Fill the form with current student and grade data
    }
    /**
     * Initializes the GUI components of the dialog.
     */
    private void initComponents() {
        // --- Student Info Panel ---
        JPanel studentInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        studentInfoPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        studentInfoPanel.add(new JLabel("Student ID:"));
        studentIdLabel = new JLabel();
        studentInfoPanel.add(studentIdLabel);
        studentInfoPanel.add(new JLabel("Student Name:"));
        studentNameLabel = new JLabel();
        studentInfoPanel.add(studentNameLabel);
        add(studentInfoPanel, BorderLayout.NORTH);
        // --- Main Content Panel (Grades and New Subject Input) ---
        JPanel mainContentPanel = new JPanel(new BorderLayout(10, 10));
        // Grades Panel (Scrollable)
        gradesPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for flexible form
        gradesPanel.setBorder(BorderFactory.createTitledBorder("Existing and Added Subject Grades"));
        JScrollPane scrollPane = new JScrollPane(gradesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainContentPanel.add(scrollPane, BorderLayout.CENTER);
        // New Subject Input Panel
        JPanel newSubjectInputPanel = new JPanel(new GridBagLayout());
        newSubjectInputPanel.setBorder(BorderFactory.createTitledBorder("Add New Subject"));
        GridBagConstraints gbcNew = new GridBagConstraints();
        gbcNew.insets = new Insets(5, 5, 5, 5); // Padding
        gbcNew.fill = GridBagConstraints.HORIZONTAL;
        gbcNew.gridx = 0; gbcNew.gridy = 0; gbcNew.weightx = 0.3;
        newSubjectInputPanel.add(new JLabel("Subject Name:"), gbcNew);
        gbcNew.gridx = 1; gbcNew.gridy = 0; gbcNew.weightx = 0.7;
        newSubjectNameField = new JTextField(15);
        newSubjectInputPanel.add(newSubjectNameField, gbcNew);
        gbcNew.gridx = 0; gbcNew.gridy = 1; gbcNew.weightx = 0.3;
        newSubjectInputPanel.add(new JLabel("Grade (0-100):"), gbcNew);
        gbcNew.gridx = 1; gbcNew.gridy = 1; gbcNew.weightx = 0.7;
        newSubjectGradeInputField = new JTextField(15);
        newSubjectInputPanel.add(newSubjectGradeInputField, gbcNew);
        JButton addSubjectButton = new JButton("Add Subject to Form");
        gbcNew.gridx = 0; gbcNew.gridy = 2; gbcNew.gridwidth = 2; gbcNew.weightx = 1.0;
        newSubjectInputPanel.add(addSubjectButton, gbcNew);
        addSubjectButton.addActionListener(e -> addNewSubjectToForm());
        mainContentPanel.add(newSubjectInputPanel, BorderLayout.SOUTH);
        add(mainContentPanel, BorderLayout.CENTER);
        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");
        saveButton.addActionListener(e -> saveChanges());
        cancelButton.addActionListener(e -> dispose()); // Close the dialog without saving
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the dialog fields with the current data of the student's report card.
     * Dynamically creates text fields for each subject, including temporarily added ones.
     */
    private void populateStudentData() {
        studentIdLabel.setText(studentToEdit.getStudentId());
        studentNameLabel.setText(studentToEdit.getName());
        gradesPanel.removeAll(); // Clear previous components
        gradeFields.clear();     // Clear previous text field references
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Combine existing grades and temporarily added new grades for display
        Map<String, SubjectGrade> gradesToDisplay = new HashMap<>();
        // Add existing grades first
        for (SubjectGrade sg : studentToEdit.getReportCard().getGrades()) {
            gradesToDisplay.put(sg.getSubjectName(), sg);
        }
        // Add temporary new grades, overwriting existing if subject name matches
        gradesToDisplay.putAll(temporaryNewGrades); // Directly add all from the temporary map
        int row = 0;
        // Iterate through the combined and unique set of grades to create input fields
        for (SubjectGrade sg : gradesToDisplay.values()) {
            // Subject Name Label
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0.3;
            gradesPanel.add(new JLabel(sg.getSubjectName() + ":"), gbc);
            // Grade Input Field
            JTextField gradeField = new JTextField(String.valueOf(sg.getGrade()));
            gradeField.setColumns(10); // Standard column width
            gradeFields.put(sg.getSubjectName(), gradeField); // Store reference for later retrieval
            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.weightx = 0.7;
            gradesPanel.add(gradeField, gbc);
            row++;
        }
        // Add a vertical glue to push components to the top if there aren't many
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gradesPanel.add(Box.createVerticalGlue(), gbc);
        gradesPanel.revalidate(); // Re-layout the grades panel
        gradesPanel.repaint();    // Repaint to show changes
    }
    /**
     * Handles adding a new subject with a grade to the form's display.
     * This action only adds the subject to a temporary list and updates the UI;
     * it does not save to the backend until the "Save Changes" button is clicked.
     */
    private void addNewSubjectToForm() {
        String subjectName = newSubjectNameField.getText().trim();
        String gradeText = newSubjectGradeInputField.getText().trim();
        if (subjectName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Grade for " + subjectName + " cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double grade = Double.parseDouble(gradeText);
            if (grade < 0 || grade > 100) { // Basic grade validation
                JOptionPane.showMessageDialog(this, "Grade for " + subjectName + " must be between 0 and 100.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Add the new subject grade to the temporary map.
            // If a subject with the same name already exists, its value will be updated.
            temporaryNewGrades.put(subjectName, new SubjectGrade(subjectName, grade));
            // Refresh the grades display to include the newly added subject (or updated value if it was a duplicate)
            populateStudentData();
            // Clear input fields for the next new subject entry
            newSubjectNameField.setText("");
            newSubjectGradeInputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid grade for " + subjectName + ". Please enter a valid number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Handles the "Save Changes" action.
     * It reads the new grades from all displayed fields, validates them, and attempts to update the student's report card
     * via the StudentService. Displays appropriate feedback messages.
     */
    private void saveChanges() {
        List<SubjectGrade> gradesToSave = new ArrayList<>();
        boolean inputError = false;
        // Collect grades from all currently displayed JTextFields (both original and temporarily added)
        for (Map.Entry<String, JTextField> entry : gradeFields.entrySet()) {
            String subject = entry.getKey();
            String gradeText = entry.getValue().getText().trim();
            if (gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Grade for " + subject + " cannot be empty.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                inputError = true;
                break;
            }
            try {
                double grade = Double.parseDouble(gradeText);
                if (grade < 0 || grade > 100) { // Basic grade validation
                    JOptionPane.showMessageDialog(this, "Grade for " + subject + " must be between 0 and 100.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    inputError = true;
                    break;
                }
                gradesToSave.add(new SubjectGrade(subject, grade));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid grade for " + subject + ". Please enter a valid number.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                inputError = true;
                break;
            }
        }
        if (!inputError) {
            // Attempt to update the report card using the service
            // The service method will clear existing grades and then add/update with `gradesToSave`.
            boolean success = studentService.updateStudentReportCard(studentToEdit.getStudentId(), gradesToSave);
            if (success) {
                updated = true; // Set flag to true on successful update
                JOptionPane.showMessageDialog(this,
                        "Report card for " + studentToEdit.getName() + " updated successfully!",
                        "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                // Clear temporary new grades as they are now persisted in the studentService
                temporaryNewGrades.clear();
                dispose(); // Close dialog on success
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to update report card. Student not found or other error occurred.",
                        "Update Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Checks if the report card was successfully updated.
     * @return true if the report card was updated, false otherwise.
     */
    public boolean isUpdated() {
        return updated;
    }
}