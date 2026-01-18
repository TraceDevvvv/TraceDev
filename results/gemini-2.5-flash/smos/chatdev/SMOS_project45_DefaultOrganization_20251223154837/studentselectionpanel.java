/*
 * GUI panel for displaying a list of students for a selected class and allowing
 * the administrator to choose a student and four months to view their report.
 */
package gui;
import data.DataStore;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
// Import the new ReportRequest DTO
import gui.ReportRequest;
public class StudentSelectionPanel extends JPanel {
    private JList<Student> studentList;
    private DefaultListModel<Student> studentListModel;
    private JCheckBox[] monthCheckBoxes;
    private JButton viewReportButton;
    private String currentAcademicYear;
    private String currentClassName;
    // Callback: (ReportRequest) -> void
    private Consumer<ReportRequest> onReportViewRequested;
    private DataStore dataStore; // Add DataStore instance
    private static final String[] ALL_MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    /**
     * Constructs a new StudentSelectionPanel.
     * @param dataStore The DataStore instance for retrieving student data.
     * @param onReportViewRequested A callback function to be executed when the "View Report" button is clicked.
     *                              It receives a {@link ReportRequest} object containing all necessary parameters.
     */
    public StudentSelectionPanel(DataStore dataStore, Consumer<ReportRequest> onReportViewRequested) { // Modify constructor
        this.dataStore = dataStore; // Assign dataStore
        this.onReportViewRequested = onReportViewRequested;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("<html><h2>Select Student and Months</h2></html>"));
        add(titlePanel, BorderLayout.NORTH);
        // Center Panel for Student List and Month Selection
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        // Student Section
        JPanel studentSection = new JPanel(new BorderLayout());
        studentSection.setBorder(BorderFactory.createTitledBorder("Select Student"));
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane studentScrollPane = new JScrollPane(studentList);
        studentSection.add(studentScrollPane, BorderLayout.CENTER);
        centerPanel.add(studentSection);
        // Month Selection Section
        JPanel monthSection = new JPanel(new BorderLayout());
        monthSection.setBorder(BorderFactory.createTitledBorder("Select Four Months"));
        JPanel monthCheckBoxPanel = new JPanel(new GridLayout(6, 2)); // 12 months, 6 rows, 2 columns
        monthCheckBoxes = new JCheckBox[ALL_MONTHS.length];
        for (int i = 0; i < ALL_MONTHS.length; i++) {
            monthCheckBoxes[i] = new JCheckBox(ALL_MONTHS[i]);
            monthCheckBoxPanel.add(monthCheckBoxes[i]);
        }
        monthSection.add(monthCheckBoxPanel, BorderLayout.CENTER);
        centerPanel.add(monthSection);
        add(centerPanel, BorderLayout.CENTER);
        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        viewReportButton = new JButton("View Report");
        viewReportButton.addActionListener(this::handleViewReportClick);
        viewReportButton.setEnabled(false); // Initially disabled
        controlPanel.add(viewReportButton);
        add(controlPanel, BorderLayout.SOUTH);
        // Enable/disable button based on selections
        studentList.addListSelectionListener(e -> updateViewReportButtonState());
        for (JCheckBox checkBox : monthCheckBoxes) {
            checkBox.addActionListener(e -> updateViewReportButtonState());
        }
    }
    /**
     * Updates the state of the "View Report" button based on whether a student
     * is selected and exactly four months are checked.
     */
    private void updateViewReportButtonState() {
        boolean studentSelected = studentList.getSelectedIndex() != -1;
        long selectedMonthsCount = Arrays.stream(monthCheckBoxes)
                                        .filter(JCheckBox::isSelected)
                                        .count();
        viewReportButton.setEnabled(studentSelected && selectedMonthsCount == 4);
    }
    /**
     * Sets the academic year and class name, then populates the student list for that class.
     * Disables month checkboxes if no students are available.
     * @param academicYear The selected academic year.
     * @param className The selected class name.
     */
    public void setClassDetails(String academicYear, String className) {
        this.currentAcademicYear = academicYear;
        this.currentClassName = className;
        studentListModel.clear(); // Clear previous students
        // Use the injected DataStore instance
        List<Student> students = dataStore.getStudentsForClass(academicYear, className);
        if (students.isEmpty()) {
            // Add a placeholder message if no students
            studentListModel.addElement(new Student("", "No students available for this class"));
            studentList.setEnabled(false);
            // Disable month selection as there are no students to report on
            for(JCheckBox cb : monthCheckBoxes) cb.setEnabled(false);
        } else {
            studentList.setEnabled(true);
            for (Student student : students) {
                studentListModel.addElement(student);
            }
             // Enable month selection if students are available
            for(JCheckBox cb : monthCheckBoxes) cb.setEnabled(true);
        }
        // Reset selections and button state
        studentList.clearSelection();
        for (JCheckBox cb : monthCheckBoxes) {
            cb.setSelected(false);
        }
        updateViewReportButtonState();
    }
    /**
     * Handles the click event for the "View Report" button.
     * Validates selections and invokes the callback with the chosen details.
     * @param e The ActionEvent generated by the button click.
     */
    private void handleViewReportClick(ActionEvent e) {
        Student selectedStudent = studentList.getSelectedValue();
        List<String> selectedMonths = new ArrayList<>();
        for (JCheckBox checkBox : monthCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedMonths.add(checkBox.getText());
            }
        }
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check for placeholder student
        if (selectedStudent.getId().isEmpty() && selectedStudent.getName().equals("No students available for this class")) {
             JOptionPane.showMessageDialog(this, "No valid student selected.", "Selection Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
        if (selectedMonths.size() != 4) {
            JOptionPane.showMessageDialog(this, "Please select exactly four months.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (onReportViewRequested != null) {
            // Create a ReportRequest object and pass it to the consumer
            ReportRequest request = new ReportRequest(currentAcademicYear, currentClassName, selectedStudent, selectedMonths);
            onReportViewRequested.accept(request);
        }
    }
}