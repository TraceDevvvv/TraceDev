/**
 * Panel for editing student report card.
 * Corresponds to form where administrator enters new data.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditFormPanel extends JPanel {
    private StudentDataManager dataManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Student currentStudent;
    // Form components
    private JTextField idField;
    private JTextField nameField;
    private JTextField gradeField;
    private JTextField mathField;
    private JTextField scienceField;
    private JTextField englishField;
    private JTextField historyField;
    private JTextField artField;
    private JButton saveButton;
    private JButton cancelButton;
    public EditFormPanel(StudentDataManager dataManager, CardLayout cardLayout, JPanel mainPanel) {
        this.dataManager = dataManager;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initializeUI();
    }
    /**
     * Builds edit form UI.
     */
    private void initializeUI() {
        // Title
        JLabel titleLabel = new JLabel("Edit Student Report Card", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        // Form panel with subject grades
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        idField = new JTextField();
        nameField = new JTextField();
        gradeField = new JTextField();
        mathField = new JTextField();
        scienceField = new JTextField();
        englishField = new JTextField();
        historyField = new JTextField();
        artField = new JTextField();
        // ID field non-editable
        idField.setEditable(false);
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Grade Level:"));
        formPanel.add(gradeField);
        formPanel.add(new JLabel("Math Grade:"));
        formPanel.add(mathField);
        formPanel.add(new JLabel("Science Grade:"));
        formPanel.add(scienceField);
        formPanel.add(new JLabel("English Grade:"));
        formPanel.add(englishField);
        formPanel.add(new JLabel("History Grade:"));
        formPanel.add(historyField);
        formPanel.add(new JLabel("Art Grade:"));
        formPanel.add(artField);
        add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelEdit();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates form with student data.
     * Called when switching from list view.
     * @param student student to edit
     */
    public void setStudent(Student student) {
        currentStudent = student;
        idField.setText(student.getId());
        nameField.setText(student.getName());
        gradeField.setText(student.getGrade());
        mathField.setText(student.getMathGrade());
        scienceField.setText(student.getScienceGrade());
        englishField.setText(student.getEnglishGrade());
        historyField.setText(student.getHistoryGrade());
        artField.setText(student.getArtGrade());
    }
    /**
     * Validates input fields with comprehensive checks.
     * @return true if validation passes
     */
    private boolean validateInput() {
        // Check for empty fields
        if (nameField.getText().trim().isEmpty() ||
            gradeField.getText().trim().isEmpty() ||
            mathField.getText().trim().isEmpty() ||
            scienceField.getText().trim().isEmpty() ||
            englishField.getText().trim().isEmpty() ||
            historyField.getText().trim().isEmpty() ||
            artField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "All fields are required.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Validate grade level (should be numeric 1-12)
        String gradeLevel = gradeField.getText().trim();
        try {
            int gradeNum = Integer.parseInt(gradeLevel);
            if (gradeNum < 1 || gradeNum > 12) {
                JOptionPane.showMessageDialog(this,
                    "Grade level must be between 1 and 12.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Grade level must be a number (1-12).",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Validate subject grades (common grade formats)
        String[] grades = {
            mathField.getText().trim(),
            scienceField.getText().trim(),
            englishField.getText().trim(),
            historyField.getText().trim(),
            artField.getText().trim()
        };
        // Common valid grade patterns: A-F, +/- modifiers, percentages 0-100
        String gradePattern = "^([A-F][+-]?|\\d{1,3}%?)$";
        for (int i = 0; i < grades.length; i++) {
            if (!grades[i].matches(gradePattern) && grades[i].length() > 3) {
                JOptionPane.showMessageDialog(this,
                    "Invalid grade format for subject. Use letter grades (A-F) with optional +/- or percentages (0-100).",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    /**
     * Saves changes and returns to list view.
     * Implements events sequence steps 2 and 3.
     */
    private void saveChanges() {
        if (!validateInput()) {
            return;
        }
        // Create updated student object
        Student updatedStudent = new Student(
            currentStudent.getId(),
            nameField.getText().trim(),
            gradeField.getText().trim(),
            mathField.getText().trim(),
            scienceField.getText().trim(),
            englishField.getText().trim(),
            historyField.getText().trim(),
            artField.getText().trim()
        );
        // Persist change
        boolean success = dataManager.updateStudent(updatedStudent);
        if (success) {
            // Show confirmation message (event sequence step 3)
            JOptionPane.showMessageDialog(this, 
                "Report card updated successfully!\nStudent: " + updatedStudent.getName(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            // Return to list view (post-condition: displays form with list)
            cardLayout.show(mainPanel, "LIST");
            // Refresh list view
            StudentListPanel listPanel = (StudentListPanel) mainPanel.getComponent(0);
            listPanel.refreshTable();
        } else {
            JOptionPane.showMessageDialog(this,
                "Error updating student. Student not found in database.",
                "Update Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Cancels editing and returns to list view.
     */
    private void cancelEdit() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Discard changes and return to student list?",
                "Cancel Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "LIST");
        }
    }
}