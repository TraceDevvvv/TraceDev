/**
 * Provides a form for the administrator to insert or modify a student's report card grades.
 * (Corresponds to System event 5 and User event 6 in the use case).
 * Handles saving the grades and returning to the student view.
 * (Corresponds to System event 7 and Postcondition: Report added).
 * Also handles user interruption (Postcondition: User interrupts).
 * Simulates "Connection to the SMOS server interrupted" with a message.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class ReportCardInputPanel extends JPanel {
    private Main mainFrame;
    private ReportCardSystem system;
    private String currentStudentId;
    private JLabel studentNameLabel;
    private Map<String, JTextField> gradeFields; // To easily access grade input fields
    // Define subjects for simplicity
    private final String[] SUBJECTS = {"Math", "Science", "History", "English", "Art"};
    public ReportCardInputPanel(Main mainFrame, ReportCardSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout(10, 10));
        // Top Panel for student name and title
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Insert/Edit Student Report Card", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        northPanel.add(titleLabel, BorderLayout.NORTH);
        studentNameLabel = new JLabel("Student: ", SwingConstants.CENTER);
        studentNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        northPanel.add(studentNameLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        // Center Panel for grade inputs
        JPanel gradesPanel = new JPanel(new GridLayout(SUBJECTS.length, 2, 10, 10));
        gradesPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        gradeFields = new HashMap<>();
        for (String subject : SUBJECTS) {
            gradesPanel.add(new JLabel(subject + ":"));
            JTextField gradeField = new JTextField(5);
            gradeFields.put(subject, gradeField);
            gradesPanel.add(gradeField);
        }
        add(gradesPanel, BorderLayout.CENTER);
        // Bottom Panel for Save and Cancel buttons
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton saveButton = new JButton("Save Report Card");
        JButton cancelButton = new JButton("Cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReportCard();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // User interrupts the operation
                System.out.println("User interrupted report card insertion/editing for student: " + currentStudentId);
                // Return to the student selection page for the previously selected class
                if (mainFrame.getSelectedClassId() != null) {
                   mainFrame.showStudentSelection(mainFrame.getSelectedClassId());
                } else {
                    mainFrame.showClassSelection(); // Fallback if no class was selected
                }
            }
        });
        JButton simulateServerErrorButton = new JButton("Simulate SMOS Server Error");
        simulateServerErrorButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Connection to the SMOS server interrupted. Please try again later.", "Server Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Postcondition: Connection to the SMOS server interrupted simulated.");
        });
        southPanel.add(simulateServerErrorButton);
        southPanel.add(cancelButton);
        southPanel.add(saveButton);
        add(southPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    /**
     * Loads the existing report card data for the given student ID into the form fields.
     * If no report card exists, initializes empty fields.
     * @param studentId The ID of the student.
     */
    public void loadReportCard(String studentId) {
        currentStudentId = studentId;
        Student student = system.getStudentById(studentId);
        if (student != null) {
            studentNameLabel.setText("Student: " + student.getName() + " (ID: " + student.getId() + ")");
        } else {
            studentNameLabel.setText("Student ID: " + studentId + " (Unknown Student)");
            JOptionPane.showMessageDialog(this, "Student with ID " + studentId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            // If student not found, disable input or navigate back.
            // For now, it will just show the error and keep the panel, but with no student info.
            // A more robust application might immediately go back to the previous screen.
            return;
        }
        ReportCard reportCard = system.getReportCard(studentId); // This will create if not exists or return null if student/class error
        if (reportCard == null) {
            JOptionPane.showMessageDialog(this, "Could not load/create report card for student " + studentId + ". Check console for details.", "Error", JOptionPane.ERROR_MESSAGE);
            // If report card cannot be loaded/created, it's a critical error for this panel's function.
            // Navigate back to prevent users from saving invalid data.
            if (mainFrame.getSelectedClassId() != null) {
               mainFrame.showStudentSelection(mainFrame.getSelectedClassId());
            } else {
                mainFrame.showClassSelection();
            }
            return;
        }
        // Populate grade fields with existing data or clear them
        for (String subject : SUBJECTS) {
            JTextField field = gradeFields.get(subject);
            Double grade = reportCard.getGrade(subject);
            if (grade != null) {
                field.setText(String.valueOf(grade));
            } else {
                field.setText(""); // Clear if no grade exists
            }
        }
    }
    /**
     * Collects grades from the input fields, validates them, and saves the report card.
     * Then navigates back to the student selection page.
     */
    private void saveReportCard() {
        if (currentStudentId == null) {
            JOptionPane.showMessageDialog(this, "No student selected to save report card.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ReportCard existingReportCard = system.getReportCard(currentStudentId);
        if (existingReportCard == null) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve existing report card for saving. This should not happen after load. Please re-select student.", "Error", JOptionPane.ERROR_MESSAGE);
            // This case implies a deeper issue if getReportCard failed during save but not during load.
            return;
        }
        // Prepare to store grades, validating them as we go
        // No need for a separate Map<String, Double> newGrades for intermediate storage
        // directly update existingReportCard after validation
        boolean hasErrors = false;
        for (String subject : SUBJECTS) {
            JTextField field = gradeFields.get(subject);
            String gradeText = field.getText().trim();
            if (!gradeText.isEmpty()) {
                try {
                    double grade = Double.parseDouble(gradeText);
                    if (grade < 0 || grade > 100) { // Basic validation
                        JOptionPane.showMessageDialog(this, "Grade for " + subject + " must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        hasErrors = true;
                        break; // Stop on first error
                    }
                    existingReportCard.setGrade(subject, grade); // Update directly
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid grade for " + subject + ". Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    hasErrors = true;
                    break; // Stop on first error
                }
            } else {
                // If the field is left empty, it means the grade is not provided or explicitly removed (partial insert).
                // We might want to remove the grade from the report card or keep it as null/empty.
                // For this U.C., we assume empty means "no grade provided" or "clear existing grade",
                // so we don't set it, effectively keeping the previous grade or leaving it unset if it was already unset.
                // If a grade was previously set and the user clears the field,
                // we should explicitly remove it from the report card.
                existingReportCard.setGrade(subject, null); // Clear the grade for this subject
            }
        }
        if (!hasErrors) {
            system.saveReportCard(existingReportCard);
            JOptionPane.showMessageDialog(this, "Report card saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Postcondition: The report relative to a student was added/updated to the system.
            System.out.println("Postcondition: Report card for student " + currentStudentId + " updated/added.");
            // Return to the student selection page for the previously selected class
            if (mainFrame.getSelectedClassId() != null) {
               mainFrame.showStudentSelection(mainFrame.getSelectedClassId());
            } else {
                mainFrame.showClassSelection(); // Fallback
            }
        }
    }
}