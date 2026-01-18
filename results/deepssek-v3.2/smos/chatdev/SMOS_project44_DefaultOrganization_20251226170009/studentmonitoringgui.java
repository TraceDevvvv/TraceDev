import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Main GUI class for the Student Monitoring System
 * Provides interface for administrators to monitor student absences and grades
 */
public class StudentMonitoringGUI extends JFrame {
    private StudentDatabase database;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField absenceThresholdField;
    private JTextField gradeThresholdField;
    private JLabel statusLabel;
    /**
     * Constructor sets up the main GUI interface
     */
    public StudentMonitoringGUI() {
        database = new StudentDatabase();
        initializeUI();
        loadAllStudents();
    }
    /**
     * Initialize all UI components and layout
     */
    private void initializeUI() {
        // Set up main window
        setTitle("Student Monitoring System - Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create top panel for controls
        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        // Create center panel for student table
        JPanel tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        // Create bottom panel for status
        JPanel statusPanel = createStatusPanel();
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Simulate server connection attempt
        checkServerConnection();
    }
    /**
     * Create control panel with threshold inputs and search button
     * @return Configured control panel
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createTitledBorder("Student Monitoring Controls"));
        // Labels and fields for thresholds
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Absence threshold
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Max Absence Threshold:"), gbc);
        gbc.gridx = 1;
        absenceThresholdField = new JTextField("10", 10);
        panel.add(absenceThresholdField, gbc);
        // Grade threshold
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Min Grade Threshold (0-100):"), gbc);
        gbc.gridx = 1;
        gradeThresholdField = new JTextField("75", 10);
        panel.add(gradeThresholdField, gbc);
        // Search button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        JButton searchButton = new JButton("Search Students");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudents();
            }
        });
        panel.add(searchButton, gbc);
        // Show all button
        gbc.gridx = 3;
        JButton showAllButton = new JButton("Show All Students");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllStudents();
            }
        });
        panel.add(showAllButton, gbc);
        return panel;
    }
    /**
     * Create panel with table to display student data
     * @return Configured table panel
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Student Data"));
        // Create table model with columns
        String[] columns = { "Student ID", "Name", "Absences", "Average Grade", "Status" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    /**
     * Create status panel at the bottom
     * @return Configured status panel
     */
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready");
        panel.add(statusLabel);
        return panel;
    }
    /**
     * Search students based on threshold inputs
     * Validates input and displays results
     */
    private void searchStudents() {
        try {
            // Parse threshold values
            int absenceThreshold = Integer.parseInt(absenceThresholdField.getText().trim());
            double gradeThreshold = Double.parseDouble(gradeThresholdField.getText().trim());
            // Validate inputs
            if (absenceThreshold < 0) {
                JOptionPane.showMessageDialog(this, "Absence threshold cannot be negative",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gradeThreshold < 0 || gradeThreshold > 100) {
                JOptionPane.showMessageDialog(this, "Grade threshold must be between 0 and 100",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Search for students exceeding thresholds
            List<Student> students = database.searchStudentsAboveThreshold(absenceThreshold, gradeThreshold);
            // Update table with results
            updateTable(students);
            // Update status
            statusLabel.setText(String.format("Found %d students requiring monitoring (Absences > %d OR Grade < %.1f)",
                    students.size(), absenceThreshold, gradeThreshold));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for thresholds",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Load all students from database into table
     */
    private void loadAllStudents() {
        List<Student> students = database.getAllStudents();
        updateTable(students);
        statusLabel.setText("Displaying all " + students.size() + " students");
    }
    /**
     * Update the table with student data
     * @param students List of students to display
     */
    private void updateTable(List<Student> students) {
        // Clear table
        tableModel.setRowCount(0);
        // Add students to table
        for (Student student : students) {
            Object[] row = {
                    student.getId(),
                    student.getName(),
                    student.getAbsences(),
                    student.getAverageGrade(),
                    getStudentStatus(student)
            };
            tableModel.addRow(row);
        }
    }
    /**
     * Determine status of student based on current thresholds
     * @param student Student to evaluate
     * @return Status string
     */
    private String getStudentStatus(Student student) {
        try {
            int absenceThreshold = Integer.parseInt(absenceThresholdField.getText().trim());
            double gradeThreshold = Double.parseDouble(gradeThresholdField.getText().trim());
            boolean highAbsences = student.getAbsences() > absenceThreshold;
            boolean lowGrade = student.getAverageGrade() < gradeThreshold;
            if (highAbsences && lowGrade) {
                return "High Risk - Poor Attendance & Low Grades";
            } else if (highAbsences) {
                return "Monitor - Poor Attendance";
            } else if (lowGrade) {
                return "Monitor - Low Grades";
            } else {
                return "Normal";
            }
        } catch (NumberFormatException ex) {
            // Fallback to default thresholds if current input is invalid
            int defaultAbsenceThreshold = 10;
            double defaultGradeThreshold = 75.0;
            boolean highAbsences = student.getAbsences() > defaultAbsenceThreshold;
            boolean lowGrade = student.getAverageGrade() < defaultGradeThreshold;
            if (highAbsences && lowGrade) {
                return "High Risk - Poor Attendance & Low Grades";
            } else if (highAbsences) {
                return "Monitor - Poor Attendance";
            } else if (lowGrade) {
                return "Monitor - Low Grades";
            } else {
                return "Normal";
            }
        }
    }
    /**
     * Check server connection and update status
     */
    private void checkServerConnection() {
        boolean connected = database.connectToSMOSServer();
        if (!connected) {
            statusLabel.setText("Warning: Connection to SMOS server interrupted. Using local data.");
        } else {
            statusLabel.setText("Connected to SMOS server. Ready for monitoring.");
        }
    }
}