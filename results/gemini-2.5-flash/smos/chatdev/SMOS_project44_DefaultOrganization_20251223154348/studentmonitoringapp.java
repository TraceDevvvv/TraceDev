'''
Main application class for Student Monitoring. This class sets up the GUI
using Swing, allowing an administrator to input thresholds for absences and notes,
search for students meeting these criteria, and display the results in a table.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
public class StudentMonitoringApp extends JFrame {
    // UI Components
    private JTextField absenceThresholdField;
    private JTextField noteThresholdField;
    private JButton searchButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel statusMessageLabel; // Label for status messages
    // Service layer for business logic
    private StudentMonitorService studentService;
    /**
     * Constructor for the StudentMonitoringApp.
     * Initializes the service and sets up the graphical user interface.
     */
    public StudentMonitoringApp() {
        studentService = new StudentMonitorService();
        initializeUI();
    }
    /**
     * Initializes all GUI components and lays them out in the JFrame.
     */
    private void initializeUI() {
        // Set up the main window properties
        setTitle("Student Monitoring System - Administrator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        // --- Input Panel for Thresholds ---
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered flow layout
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        absenceThresholdField = new JTextField("5", 5); // Default value 5 for absences
        noteThresholdField = new JTextField("10", 5);  // Default value 10 for note length
        inputPanel.add(new JLabel("Absence Threshold (Absences > X):"));
        inputPanel.add(absenceThresholdField);
        inputPanel.add(new JLabel("Note Length Threshold (Note length > Y):"));
        inputPanel.add(noteThresholdField);
        searchButton = new JButton("Search Students");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch(); // Call the search logic when button is clicked
            }
        });
        inputPanel.add(searchButton);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        // --- Results Table Panel ---
        // Column headers for the table
        String[] columnNames = {"ID", "Name", "Absences", "Notes"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setFillsViewportHeight(true); // Fills the entire height of the scroll pane
        // Add table to a scroll pane for scrollability
        JScrollPane scrollPane = new JScrollPane(studentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Initialize status message label
        statusMessageLabel = new JLabel("Click 'Search Students' to begin.", SwingConstants.CENTER);
        statusMessageLabel.setForeground(Color.GRAY);
        statusMessageLabel.setFont(statusMessageLabel.getFont().deriveFont(Font.ITALIC, 14f));
        statusMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add some padding
        mainPanel.add(statusMessageLabel, BorderLayout.SOUTH); // Place below the table
        // Display the initial empty table (no rows) and initial message
        tableModel.setRowCount(0); // Ensure table is empty
        statusMessageLabel.setVisible(true);
    }
    /**
     * Executes the student search based on the provided thresholds.
     * Retrieves all student data, filters it, and updates the display table.
     */
    private void performSearch() {
        int absenceThreshold;
        int noteThreshold;
        // Try to parse threshold values from text fields
        try {
            absenceThreshold = Integer.parseInt(absenceThresholdField.getText().trim());
            noteThreshold = Integer.parseInt(noteThresholdField.getText().trim());
        } catch (NumberFormatException ex) {
            // Handle invalid input (non-numeric)
            JOptionPane.showMessageDialog(this,
                    "Please enter valid integer values for thresholds.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            // After an error, set a general message rather than no message.
            statusMessageLabel.setText("Please correct input errors.");
            statusMessageLabel.setVisible(true);
            return; // Exit method if input is invalid
        }
        // Ensure thresholds are non-negative, though the use case implies >= 0 values.
        // The current filtering logic uses strictly greater than (>) so 0 or positive is fine.
        if (absenceThreshold < 0 || noteThreshold < 0) {
            JOptionPane.showMessageDialog(this,
                    "Thresholds cannot be negative. Please enter positive integers or zero.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            // After an error, set a general message rather than no message.
            statusMessageLabel.setText("Please correct input errors.");
            statusMessageLabel.setVisible(true);
            return;
        }
        // Save current message visibility state before performing search,
        // then update it. This helps if the table is updated to be empty
        // after a previous successful search.
        boolean wasStatusVisible = statusMessageLabel.isVisible();
        // 1. Search in the archive all students
        List<Student> allStudents = studentService.getStudentsForCurrentSchoolYear();
        // 2. Filter students based on thresholds
        List<Student> filteredStudents = studentService.filterStudentsByThreshold(
                allStudents, absenceThreshold, noteThreshold
        );
        // 3. Displays data presentation screen (by updating the JTable and status label)
        updateTable(filteredStudents);
        // Postcondition: Information about absences and notes of the students is displayed.
        // Postcondition: Connection to the simulated SMOS server is implicitly "interrupted"
        // by the completion of data retrieval, as it's a discrete operation.
        // Add explicit UI feedback for the SMOS server postcondition
        // Only update this message if the table wasn't empty after updateTable().
        // If updateTable() set a "No students found" message, we don't want to override it immediately.
        if (filteredStudents.isEmpty()) {
            // updateTable already set "No students found..." message
            // or we might want to append the server message to it.
            statusMessageLabel.setText(statusMessageLabel.getText() + " Simulated SMOS server interaction finished.");
        } else {
            statusMessageLabel.setText("Search complete. Simulated SMOS server interaction finished.");
            statusMessageLabel.setVisible(true); // Ensure the message is visible after search
        }
    }
    /**
     * Updates the JTable with the provided list of students.
     * Clears existing table data and adds new rows for each student.
     * Also manages the visibility of the status message label.
     *
     * @param students The list of students to display in the table.
     */
    private void updateTable(List<Student> students) {
        tableModel.setRowCount(0); // Clear existing rows
        if (students.isEmpty()) {
            statusMessageLabel.setText("No students found matching criteria.");
            statusMessageLabel.setVisible(true); // Show the message
        } else {
            // statusMessageLabel.setVisible(false); // Let performSearch handle visibility for server message
            // Add each student as a new row to the table model
            for (Student student : students) {
                Object[] rowData = {
                        student.getId(),
                        student.getName(),
                        student.getAbsences(),
                        student.getNotes()
                };
                tableModel.addRow(rowData);
            }
        }
    }
    /**
     * The main entry point for the Student Monitoring application.
     * Creates and displays the GUI.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentMonitoringApp().setVisible(true);
            }
        });
    }
}