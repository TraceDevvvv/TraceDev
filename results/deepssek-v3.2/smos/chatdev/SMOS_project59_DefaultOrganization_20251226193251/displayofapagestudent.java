/**
 * Main class to run the Student Report Display application.
 * This application implements the use case "DisplayOfAPageStudent" where a student
 * can view their report cards after logging in and clicking "Online report".
 * The system displays archived reports, allows selection of a report card, 
 * and shows detailed information. Postconditions include report display and 
 * simulated SMOS server connection interruption.
 * 
 * Classes in this file:
 * - DisplayOfAPageStudent: Main entry point
 * - Student: Represents a student actor with ID and name
 * - ReportCard: Represents a report card with details for a student
 * - ReportService: Service class to simulate report retrieval and SMOS server interaction
 * - ReportDisplayFrame: Main GUI frame for displaying student report cards
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class DisplayOfAPageStudent {
    /**
     * Main entry point for the Student Report Display application.
     * Simulates a logged-in student (precondition: user logged in as student)
     * and launches the GUI application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Simulate a logged-in student (precondition: user logged in as student)
        Student student = new Student("S12345", "John Doe");
        // Launch GUI on Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            ReportDisplayFrame frame = new ReportDisplayFrame(student);
            frame.setVisible(true);
        });
    }
}
/**
 * Represents a student actor with ID and name.
 * This class models the student user who is interacting with the system.
 */
class Student {
    private String id;
    private String name;
    /**
     * Constructs a Student object with the given ID and name.
     * 
     * @param id Unique identifier for the student
     * @param name Full name of the student
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
}
/**
 * Represents a report card with details for a student.
 * Contains academic performance data including subjects, grades, and teacher comments.
 */
class ReportCard {
    private String reportId;
    private String studentId;
    private String semester;
    private String[] subjects;
    private double[] grades;
    private String teacherComments;
    /**
     * Constructs a ReportCard with all necessary details.
     * 
     * @param reportId Unique identifier for the report
     * @param studentId ID of the student this report belongs to
     * @param semester Academic semester (e.g., "Fall 2023")
     * @param subjects Array of subject names
     * @param grades Array of grades corresponding to subjects
     * @param teacherComments Comments from the teacher
     * @throws IllegalArgumentException if subjects and grades arrays have different lengths
     */
    public ReportCard(String reportId, String studentId, String semester, 
                      String[] subjects, double[] grades, String teacherComments) {
        this.reportId = reportId;
        this.studentId = studentId;
        this.semester = semester;
        this.subjects = subjects != null ? subjects : new String[0];
        this.grades = grades != null ? grades : new double[0];
        this.teacherComments = teacherComments != null ? teacherComments : "";
        // Validate that subjects and grades arrays have the same length
        if (this.subjects.length != this.grades.length) {
            throw new IllegalArgumentException("Subjects and grades arrays must have the same length");
        }
    }
    public String getReportId() { return reportId; }
    public String getStudentId() { return studentId; }
    public String getSemester() { return semester; }
    public String[] getSubjects() { return subjects; }
    public double[] getGrades() { return grades; }
    public String getTeacherComments() { return teacherComments; }
    /**
     * Calculates the average grade for this report card.
     * Returns 0.0 if there are no grades.
     * 
     * @return Average of all grades, or 0.0 if no grades exist
     */
    public double calculateAverage() {
        if (grades.length == 0) return 0.0;
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
    /**
     * Returns a string representation suitable for display in the report list.
     * Format: "ReportID - Semester (Avg: X.XX)"
     * 
     * @return Formatted string representation of the report card
     */
    @Override
    public String toString() {
        return reportId + " - " + semester + " (Avg: " + String.format("%.2f", calculateAverage()) + ")";
    }
}
/**
 * Service class to simulate report retrieval and SMOS server interaction.
 * This class simulates backend serv including database access and server connections.
 */
class ReportService {
    /**
     * Retrieves all report cards for a given student from the archive.
     * Simulates Events sequence step 1: system displays student's reports logged in the archive.
     * 
     * @param studentId The ID of the student whose reports are requested
     * @return List of ReportCard objects for the specified student
     */
    public List<ReportCard> getReportsForStudent(String studentId) {
        List<ReportCard> reports = new ArrayList<>();
        // Validate input
        if (studentId == null || studentId.trim().isEmpty()) {
            return reports; // Return empty list for invalid student ID
        }
        // Simulate fetching from archive (Events sequence step 1)
        // In a real application, this would query a database or external service
        if (studentId.equals("S12345")) {
            reports.add(new ReportCard("RPT001", studentId, "Fall 2023",
                    new String[]{"Math", "Science", "History", "English"},
                    new double[]{85.5, 92.0, 78.5, 88.0},
                    "Good performance overall. Keep up the good work in Science."));
            reports.add(new ReportCard("RPT002", studentId, "Spring 2024",
                    new String[]{"Math", "Science", "History", "English", "Art"},
                    new double[]{90.0, 87.5, 82.0, 91.5, 95.0},
                    "Excellent improvement in Art. Continue focusing on History."));
        }
        // For other students, return empty list (simulating no reports)
        return reports;
    }
    /**
     * Simulates the postcondition: connection to the SMOS server interrupted.
     * In a real application, this would handle actual server disconnection logic.
     */
    public void simulateSMOSConnectionInterrupt() {
        // In a production environment, this would log the interruption and handle reconnection logic
        System.out.println("SMOS server connection interrupted (postcondition simulated).");
    }
}
/**
 * Main GUI frame for displaying student report cards.
 * Implements the use case: displaying archived reports, selecting one, showing details.
 * Handles all user interactions and implements the complete event sequence.
 */
class ReportDisplayFrame extends JFrame {
    private Student currentStudent;
    private ReportService reportService;
    private JList<ReportCard> reportList;
    private DefaultListModel<ReportCard> listModel;
    private JTextArea detailsArea;
    private JButton viewDetailsButton;
    private JLabel statusLabel;
    /**
     * Constructs the main GUI frame for displaying student reports.
     * 
     * @param student The logged-in student (precondition met)
     */
    public ReportDisplayFrame(Student student) {
        // Validate student parameter
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        this.currentStudent = student;
        this.reportService = new ReportService();
        // Setup the main window
        setTitle("Student Report Display - " + student.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the window on screen
        setLayout(new BorderLayout(10, 10));
        // Initialize UI components
        initUI();
        // Step 1: Display the student's reports from the archive (Events sequence step 1)
        // This happens automatically when the frame is created
        loadStudentReports();
        // Step 3 (simulated postcondition): Simulate SMOS server connection interruption when window closes
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Simulate the postcondition: connection to SMOS server interrupted
                reportService.simulateSMOSConnectionInterrupt();
            }
        });
    }
    /**
     * Initializes all GUI components and layouts.
     * Creates the complete user interface with panels, buttons, and display areas.
     */
    private void initUI() {
        // North panel: Title and welcome message
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Online Report System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(titleLabel, BorderLayout.NORTH);
        JLabel welcomeLabel = new JLabel("Welcome, " + currentStudent.getName() + " (" + currentStudent.getId() + ")");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        northPanel.add(welcomeLabel, BorderLayout.SOUTH);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(northPanel, BorderLayout.NORTH);
        // Center panel: List of reports and details displayed in a split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.5);
        // Left panel: Report list (Events sequence step 1: system displays reports)
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Your Report Cards (Step 1: System displays archive)"));
        listModel = new DefaultListModel<>();
        reportList = new JList<>(listModel);
        reportList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reportList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportList.addListSelectionListener(e -> {
            // Optional: Could enable/disable view button based on selection
            if (!e.getValueIsAdjusting()) {
                viewDetailsButton.setEnabled(reportList.getSelectedIndex() >= 0);
            }
        });
        JScrollPane listScroll = new JScrollPane(reportList);
        listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.add(listScroll, BorderLayout.CENTER);
        // Button to view details (Events sequence step 2 & 3: user selects and views details)
        viewDetailsButton = new JButton("View Selected Report Details");
        viewDetailsButton.setEnabled(false); // Initially disabled until a report is selected
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedReport();
            }
        });
        leftPanel.add(viewDetailsButton, BorderLayout.SOUTH);
        splitPane.setLeftComponent(leftPanel);
        // Right panel: Details display area (Events sequence step 3: displays details)
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Report Details (Step 3: Displays details)"));
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        detailsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(detailsScroll, BorderLayout.CENTER);
        splitPane.setRightComponent(rightPanel);
        add(splitPane, BorderLayout.CENTER);
        // South panel: Status bar for user feedback
        statusLabel = new JLabel("Ready. Click 'Online report' to begin.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        add(statusLabel, BorderLayout.SOUTH);
        // Simulate the user clicking "Online report" as per preconditions
        JButton onlineReportButton = new JButton("Online Report");
        onlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Precondition: user clicks on "Online report"
                statusLabel.setText("Loading your reports...");
                loadStudentReports();  // Fulfills user action of clicking "Online report"
                statusLabel.setText("Reports loaded. Select a report card and click 'View Selected Report Details'.");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPanel.add(onlineReportButton);
        add(buttonPanel, BorderLayout.WEST);
    }
    /**
     * Loads the current student's report cards from the service and populates the list.
     * Corresponds to Events sequence step 1: system displays student's reports logged in in the archive.
     */
    private void loadStudentReports() {
        listModel.clear();
        detailsArea.setText(""); // Clear previous details
        try {
            List<ReportCard> reports = reportService.getReportsForStudent(currentStudent.getId());
            if (reports.isEmpty()) {
                // Handle case where no reports are found
                listModel.addElement(new ReportCard("No reports", "", "N/A", 
                    new String[]{}, new double[]{}, "No data found."));
                viewDetailsButton.setEnabled(false);
                statusLabel.setText("No report cards found for you.");
            } else {
                for (ReportCard report : reports) {
                    listModel.addElement(report);
                }
                viewDetailsButton.setEnabled(false); // Reset until selection is made
                statusLabel.setText("Found " + reports.size() + " report(s). Select one to view details.");
                // Auto-select the first report if available
                if (reports.size() > 0) {
                    reportList.setSelectedIndex(0);
                    viewDetailsButton.setEnabled(true);
                }
            }
        } catch (Exception e) {
            // Handle any errors during report loading
            JOptionPane.showMessageDialog(this, 
                "Error loading reports: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error loading reports.");
        }
    }
    /**
     * Displays the details of the currently selected report card in the details area.
     * Corresponds to Events sequence step 3: displays the details of the selected report card.
     */
    private void displaySelectedReport() {
        ReportCard selected = reportList.getSelectedValue();
        // Validate selection
        if (selected == null || selected.getReportId().equals("No reports")) {
            JOptionPane.showMessageDialog(this, 
                "Please select a valid report card from the list.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Build a detailed string representation of the selected report
            StringBuilder details = new StringBuilder();
            details.append("Report ID: ").append(selected.getReportId()).append("\n");
            details.append("Student ID: ").append(selected.getStudentId()).append("\n");
            details.append("Semester: ").append(selected.getSemester()).append("\n");
            details.append("----------------------------------------\n");
            details.append("Subjects and Grades:\n");
            String[] subjects = selected.getSubjects();
            double[] grades = selected.getGrades();
            // Validate that subjects and grades arrays are consistent
            if (subjects.length != grades.length) {
                throw new IllegalStateException("Invalid report data: subjects and grades count mismatch");
            }
            // Display each subject with its corresponding grade
            for (int i = 0; i < subjects.length; i++) {
                // Format each line with proper alignment
                details.append(String.format("  %-15s : %.2f\n", subjects[i], grades[i]));
            }
            details.append("----------------------------------------\n");
            details.append(String.format("Average Grade: %.2f\n", selected.calculateAverage()));
            details.append("Teacher Comments: ").append(selected.getTeacherComments()).append("\n");
            details.append("----------------------------------------\n");
            details.append("(Postcondition: Report displayed successfully.)");
            // Update the display area
            detailsArea.setText(details.toString());
            detailsArea.setCaretPosition(0); // Scroll to top
            // Update status
            statusLabel.setText("Displaying details for " + selected.getReportId());
        } catch (Exception e) {
            // Handle any errors during detail display
            JOptionPane.showMessageDialog(this, 
                "Error displaying report details: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error displaying report details.");
        }
    }
}