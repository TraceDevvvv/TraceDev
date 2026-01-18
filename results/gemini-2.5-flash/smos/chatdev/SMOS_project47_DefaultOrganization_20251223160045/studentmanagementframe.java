/*
studentmanagementframe.java
This is the main application frame for the student management system.
It displays a list of students and provides an interface for an administrator
to select a student and edit their report card.
*/
package gui;
import model.Student;
import service.StudentService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * The main application frame for managing students.
 * Displays a list of students and allows editing their report cards.
 */
public class StudentManagementFrame extends JFrame {
    private StudentService studentService;        // Service to handle student data operations
    private JTable studentTable;                 // Table to display student list
    private DefaultTableModel tableModel;        // Model for the student table
    /**
     * Constructs the StudentManagementFrame.
     * @param studentService The StudentService instance to be used by the frame.
     */
    public StudentManagementFrame(StudentService studentService) {
        this.studentService = studentService;
        setTitle("Student Report Card Management (Admin)");
        setSize(800, 600); // Set initial window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setLocationRelativeTo(null); // Center the window on the screen
        initComponents();   // Initialize GUI components
        loadStudentData();  // Load and display initial student data in the table
    }
    /**
     * Initializes the GUI components of the main frame.
     */
    private void initComponents() {
        // --- Table Panel ---
        // Define initial column names for the student table. These columns are dynamically managed/populated.
        // For dynamic subjects, it's generally better to have core columns and then fetch subject grades as needed.
        // For simplicity and matching current structure, we list some common subjects.
        // The table model will contain basic info and then various subject grades.
        String[] initialColumnNames = {"Student ID", "Name", "Math", "Physics", "Chemistry", "Literature", "History", "Geography"};
        tableModel = new DefaultTableModel(initialColumnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable in the main display table
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one student selection
        JScrollPane scrollPane = new JScrollPane(studentTable); // Make table scrollable
        add(scrollPane, BorderLayout.CENTER); // Add table to the center of the frame
        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center-aligned buttons
        JButton editReportCardButton = new JButton("Edit Report Card");
        buttonPanel.add(editReportCardButton);
        add(buttonPanel, BorderLayout.SOUTH); // Add buttons to the bottom of the frame
        // Add action listener to the "Edit Report Card" button
        editReportCardButton.addActionListener(e -> {
            // Precondition: user click on the edit button
            // Trigger the edit dialog
            openEditReportCardDialog();
        });
    }
    /**
     * Loads student data from the StudentService and populates the JTable.
     * This method is called initially and after any updates to refresh the view.
     * It dynamically handles subject columns to some extent, adding new ones if found.
     */
    private void loadStudentData() {
        // Clear existing data and columns to rebuild the table fresh,
        // which helps in displaying newly added subjects.
        tableModel.setRowCount(0);
        // Reset column structure, keeping basic student info and then adding subjects dynamically
        String[] baseColumns = {"Student ID", "Name"};
        List<String> dynamicSubjectColumns = new java.util.ArrayList<>();
        List<Student> allStudents = studentService.getAllStudents(); // Get all students to determine all subjects
        // Collect all unique subject names present across all students
        for (Student student : allStudents) {
            for (model.SubjectGrade sg : student.getReportCard().getGrades()) {
                if (!dynamicSubjectColumns.contains(sg.getSubjectName())) {
                    dynamicSubjectColumns.add(sg.getSubjectName());
                }
            }
        }
        // Build the final column names array
        String[] columnNames = new String[baseColumns.length + dynamicSubjectColumns.size()];
        System.arraycopy(baseColumns, 0, columnNames, 0, baseColumns.length);
        for (int i = 0; i < dynamicSubjectColumns.size(); i++) {
            columnNames[baseColumns.length + i] = dynamicSubjectColumns.get(i);
        }
        // Update the table model with new column names if they have changed or are more comprehensive
        tableModel.setColumnIdentifiers(columnNames);
        // Now populate the rows
        for (Student student : allStudents) {
            Object[] rowData = new Object[columnNames.length];
            rowData[0] = student.getStudentId();
            rowData[1] = student.getName();
            // Populate subject grades dynamically based on column names
            for (int i = baseColumns.length; i < columnNames.length; i++) {
                String subjectName = columnNames[i];
                rowData[i] = getGradeValue(student, subjectName);
            }
            tableModel.addRow(rowData); // Add the constructed row to the table model
        }
    }
    /**
     * Helper method to get the value of a specific subject grade for a student.
     * Returns "N/A" if the grade is not found.
     * @param student The student object.
     * @param subjectName The name of the subject.
     * @return The grade as a String or "N/A".
     */
    private String getGradeValue(Student student, String subjectName) {
        return student.getReportCard().getGrades().stream()
                .filter(sg -> sg.getSubjectName().equals(subjectName))
                .findFirst()
                .map(sg -> String.format("%.2f", sg.getGrade())) // Format grade to 2 decimal places
                .orElse("N/A"); // Default if grade not found
    }
    /**
     * Opens the EditReportCardDialog when the "Edit Report Card" button is clicked.
     * Handles student selection and refreshes the table upon dialog closure.
     */
    private void openEditReportCardDialog() {
        int selectedRow = studentTable.getSelectedRow(); // Get the index of the selected row
        if (selectedRow == -1) {
            // If no row is selected, display an error message
            JOptionPane.showMessageDialog(this,
                    "Please select a student to edit.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get the student ID from the selected row
        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        // Retrieve the full Student object from the service
        studentService.getStudentById(studentId).ifPresent(student -> {
            // System displays the form with the fields to be modified
            EditReportCardDialog editDialog = new EditReportCardDialog(this, student, studentService);
            editDialog.setVisible(true); // Make the edit dialog visible
            // Post-dialog processing (after user has interacted with the dialog)
            if (editDialog.isUpdated()) {
                // The system displays a confirmation message (handled in dialog) and then displays the form with the list of students.
                // A report relative to a student modified (Postcondition)
                loadStudentData(); // Refresh the table to show updated data, including new subjects if any
            }
        });
    }
}