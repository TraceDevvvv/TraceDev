/**
 * Main class for Student Monitoring System - Direction.
 * This GUI application allows Direction actors to input thresholds for student notes and absences,
 * then displays students whose counts exceed both thresholds.
 * 
 * Key features:
 * - GUI built with Java Swing
 * - Input validation for thresholds
 * - Simulated server connection with error handling
 * - Dummy data generation for demonstration
 * - Proper edge case handling
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class Main extends JFrame {
    private JTextField notesThresholdField;
    private JTextField absencesThresholdField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private StudentDataService dataService;
    public Main() {
        super("Student Monitoring System - Direction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Initialize data service (simulated with dummy data)
        dataService = new StudentDataService();
        // Create GUI components
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Notes Threshold (≥):"));
        notesThresholdField = new JTextField("5");
        inputPanel.add(notesThresholdField);
        inputPanel.add(new JLabel("Absences Threshold (≥):"));
        absencesThresholdField = new JTextField("3");
        inputPanel.add(absencesThresholdField);
        JButton searchButton = new JButton("Search Students");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudents();
            }
        });
        // Table setup
        String[] columns = {"ID", "Name", "Notes Count", "Absences Count"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        statusLabel = new JLabel("Enter thresholds and click 'Search Students'.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Layout arrangement
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);
        mainPanel.add(statusLabel, BorderLayout.PAGE_END);
        add(mainPanel);
    }
    /**
     * Search students based on user-input thresholds.
     * Handles input validation and simulated server connection.
     * Implements the core functionality from the use case.
     */
    private void searchStudents() {
        int notesThreshold, absencesThreshold;
        try {
            notesThreshold = Integer.parseInt(notesThresholdField.getText().trim());
            absencesThreshold = Integer.parseInt(absencesThresholdField.getText().trim());
            if (notesThreshold < 0 || absencesThreshold < 0) {
                JOptionPane.showMessageDialog(this, "Thresholds cannot be negative.", 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid integers for thresholds.", 
                                          "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Attempt to fetch data with simulated server check
        List<Student> students;
        try {
            students = dataService.fetchStudentsAboveThreshold(notesThreshold, absencesThreshold);
        } catch (RuntimeException e) {
            // Simulate SMOS server interruption as per postconditions
            statusLabel.setText("Error: Failed to connect to SMOS server. Data may be incomplete.");
            JOptionPane.showMessageDialog(this, "Could not connect to SMOS server. Using dummy data.", 
                                          "Server Error", JOptionPane.WARNING_MESSAGE);
            students = dataService.getDummyStudents(); // Fallback to dummy data
        }
        // Update table with recovered information
        tableModel.setRowCount(0);
        if (students.isEmpty()) {
            statusLabel.setText("No students found exceeding both thresholds.");
        } else {
            for (Student student : students) {
                tableModel.addRow(new Object[]{
                    student.getId(), 
                    student.getName(), 
                    student.getNotesCount(), 
                    student.getAbsencesCount()
                });
            }
            statusLabel.setText("Found " + students.size() + " student(s) exceeding thresholds.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}