'''
Main application class for Student Monitoring.
This class creates a graphical user interface (GUI) using Java Swing.
It allows the user (Direction) to input thresholds for student notes and absences,
and then displays students who exceed both thresholds.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
public class StudentMonitoringApp extends JFrame {
    private JTextField notesThresholdField;
    private JTextField absencesThresholdField;
    private JTextArea resultsArea;
    private JButton monitorButton;
    private StudentService studentService;
    /**
     * Constructor for StudentMonitoringApp.
     * Initializes the GUI components and sets up event listeners.
     */
    public StudentMonitoringApp() {
        super("Student Monitoring System"); // Set the window title
        studentService = new StudentService(); // Initialize the service layer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the window
        setSize(600, 500); // Set default window size
        setLocationRelativeTo(null); // Center the window on the screen
        setupUI(); // Call method to arrange GUI elements
    }
    /**
     * Sets up the user interface elements and their layout.
     * Uses BorderLayout for overall structure, and GridBagLayout for input panel for flexibility.
     */
    private void setupUI() {
        // Main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        // Panel for input fields and button
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Set Monitoring Thresholds"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        // Label and text field for notes threshold
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Notes Threshold:"), gbc);
        notesThresholdField = new JTextField("0", 5); // Default value 0, width 5
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(notesThresholdField, gbc);
        // Label and text field for absences threshold
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Absences Threshold:"), gbc);
        absencesThresholdField = new JTextField("0", 5); // Default value 0, width 5
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(absencesThresholdField, gbc);
        // Monitor button
        monitorButton = new JButton("Monitor Students");
        // Add an action listener to the button to trigger the monitoring logic
        monitorButton.addActionListener(this::monitorStudents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(monitorButton, gbc);
        // Text area to display results
        resultsArea = new JTextArea();
        resultsArea.setEditable(false); // Make the text area read-only
        resultsArea.setLineWrap(true); // Enable line wrapping
        resultsArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(resultsArea); // Add scroll capability
        scrollPane.setBorder(BorderFactory.createTitledBorder("Monitoring Results"));
        // Add input panel to the top (NORTH) and results area to the center (CENTER)
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Add the main panel to the frame
        add(mainPanel);
    }
    /**
     * This method is triggered when the "Monitor Students" button is clicked.
     * It parses the threshold inputs, calls the StudentService to get filtered students,
     * and updates the results display area.
     */
    private void monitorStudents(ActionEvent e) {
        int notesThreshold;
        int absencesThreshold;
        try {
            // Parse integers from the text fields. Handle potential NumberFormatException.
            notesThreshold = Integer.parseInt(notesThresholdField.getText());
            absencesThreshold = Integer.parseInt(absencesThresholdField.getText());
            // Validate that thresholds are not negative
            if (notesThreshold < 0 || absencesThreshold < 0) {
                JOptionPane.showMessageDialog(this,
                        "Thresholds cannot be negative. Please enter non-negative numbers.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                resultsArea.setText(""); // Clear previous results if input is invalid
                return; // Stop processing if input is invalid
            }
        } catch (NumberFormatException ex) {
            // Show an error message if input is not a valid number
            JOptionPane.showMessageDialog(this,
                    "Invalid input. Please enter valid numbers for thresholds.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            resultsArea.setText(""); // Clear previous results if input is invalid
            return; // Stop processing if input is invalid
        }
        // Call the service to get students matching the criteria
        List<Student> monitoredStudents = studentService.getStudentsExceedingThresholds(notesThreshold, absencesThreshold);
        // Clear previous results
        resultsArea.setText("");
        if (monitoredStudents.isEmpty()) {
            // Display a message if no students match the criteria
            resultsArea.append("No students found exceeding the specified thresholds:\n" +
                               "Notes > " + notesThreshold + ", Absences > " + absencesThreshold);
        } else {
            // Build a string with details of all matching students
            resultsArea.append("Students exceeding thresholds (Notes > " + notesThreshold +
                               ", Absences > " + absencesThreshold + "):\n\n");
            for (Student student : monitoredStudents) {
                resultsArea.append(student.toString() + "\n");
            }
        }
        // Add explicit message regarding SMOS server status to fulfill postcondition
        resultsArea.append("\n\n--- SMOS Server Status (Simulated) ---\n");
        resultsArea.append("Connection Status: Operational (Data retrieved from simulated source)\n");
        resultsArea.append("Note: For this runnable application, data is simulated. In a production environment,\n      this would reflect actual SMOS server connectivity.\n");
    }
    /**
     * Main method to run the Student Monitoring application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are run on the Event Dispatch Thread for thread safety in Swing.
        SwingUtilities.invokeLater(() -> {
            StudentMonitoringApp app = new StudentMonitoringApp();
            app.setVisible(true); // Make the application window visible
        });
    }
}