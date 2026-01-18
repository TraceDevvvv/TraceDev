'''
Main entry point for the ViewOnePage report card viewer.
This application follows the provided use case exactly, including the initial "Online reports" button click.
It simulates a simple educational system with a GUI built using Java Swing.
The application implements the exact sequence from the use case:
Precondition: Administrator logged in and clicks "Online reports" button
1. User selects academic year → system displays classes with report card buttons
2. User chooses class → system displays student list
3. User selects student and months → system displays report
For demonstration, it uses hard-coded sample data.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ViewOnePageApp {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create the main application frame
                JFrame frame = new JFrame("Student Report System - Administrator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null); // Center on screen
                // Start with the login panel that includes the "Online reports" button
                LoginPanel loginPanel = new LoginPanel(frame);
                frame.getContentPane().add(loginPanel);
                frame.setVisible(true);
            }
        });
    }
}
/**
 * Initial panel that simulates administrator login and includes the
 * "Online reports" button as specified in the use case preconditions.
 * This panel must be shown first, and the main report viewer is only
 * accessible after clicking the "Online reports" button.
 */
class LoginPanel extends JPanel {
    private JFrame parentFrame;
    public LoginPanel(JFrame frame) {
        this.parentFrame = frame;
        setupUI();
    }
    private void setupUI() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Administrator Portal - SMOS System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        // Login simulation panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login Simulation"));
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField("admin");
        userField.setEditable(false);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField("********");
        passField.setEditable(false);
        JLabel statusLabel = new JLabel("Status:");
        JLabel statusValue = new JLabel("Logged in as Administrator");
        statusValue.setForeground(Color.GREEN.darker());
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(statusLabel);
        loginPanel.add(statusValue);
        // "Online reports" button panel as specified in precondition
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton onlineReportsButton = new JButton("Online Reports");
        onlineReportsButton.setFont(new Font("Arial", Font.BOLD, 16));
        onlineReportsButton.setPreferredSize(new Dimension(200, 50));
        // Tooltip as per use case
        onlineReportsButton.setToolTipText("Click to access student report cards");
        onlineReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the main report card viewer as specified
                parentFrame.getContentPane().removeAll();
                ReportCardViewer reportViewer = new ReportCardViewer();
                parentFrame.getContentPane().add(reportViewer);
                parentFrame.setTitle("Administrator Report Card Viewer");
                parentFrame.setSize(900, 700);
                parentFrame.revalidate();
                parentFrame.repaint();
                // Log the action as per use case flow
                System.out.println("[SYSTEM] Administrator clicked 'Online Reports' button. Loading report viewer...");
            }
        });
        buttonPanel.add(onlineReportsButton);
        // Instruction panel
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JLabel instruction1 = new JLabel("Use Case Precondition Met:");
        instruction1.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel instruction2 = new JLabel("✓ User logged in as administrator");
        instruction2.setForeground(new Color(0, 128, 0));
        JLabel instruction3 = new JLabel("Next: Click 'Online Reports' button to continue");
        instruction3.setFont(new Font("Arial", Font.ITALIC, 12));
        instructionPanel.add(instruction1);
        instructionPanel.add(Box.createVerticalStrut(5));
        instructionPanel.add(instruction2);
        instructionPanel.add(Box.createVerticalStrut(5));
        instructionPanel.add(instruction3);
        // Add all panels to main layout
        add(titlePanel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(instructionPanel, BorderLayout.EAST);
    }
}
/**
 * Main GUI frame that guides the administrator through the report viewing process.
 * The steps correspond exactly to the events sequence in the use case.
 * This is only shown after the "Online reports" button is clicked.
 */
class ReportCardViewer extends JPanel {
    private JComboBox<String> yearComboBox;
    private JPanel classButtonPanel;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> monthsComboBox;
    private JButton viewReportButton;
    // Hard-coded sample data for demonstration
    private final String[] academicYears = {"2022-2023", "2023-2024"};
    // Simulate classes available per selected year
    private final String[] classes = {"Class A", "Class B", "Class C"};
    // Simulate students per class
    private final String[] students = {"Alice Smith", "Bob Johnson", "Charlie Brown"};
    // Four-month periods (like quarters) for report selection
    private final String[] fourMonthPeriods = {"Sep-Dec", "Jan-Apr", "May-Aug"};
    // Panel to display the final report
    private ReportDisplayPanel reportPanel;
    // Track selections
    private String selectedYear = null;
    private String selectedClass = null;
    private String selectedStudent = null;
    private String selectedPeriod = null;
    public ReportCardViewer() {
        setupGUI();
        loadInitialData();
    }
    private void setupGUI() {
        setLayout(new BorderLayout(10, 10));
        // Top panel for welcome and instructions
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel welcomeLabel = new JLabel("Administrator: View Student Report Card", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(welcomeLabel, BorderLayout.NORTH);
        JLabel instructionLabel = new JLabel("Follow the exact sequence: 1. Select year → 2. Choose class → 3. Select student and months → 4. View report", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(instructionLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        // Central panel for sequential steps
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Step 1: Academic Year Selection (User step 1)
        JPanel yearPanel = createStepPanel("1. Select Academic Year:");
        yearComboBox = new JComboBox<>();
        yearPanel.add(yearComboBox);
        centerPanel.add(yearPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        // Step 2: Class List with Report Card Buttons (System step 2)
        JPanel classPanel = createStepPanel("2. Classes with Report Card buttons:");
        classButtonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        classButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JScrollPane classScrollPane = new JScrollPane(classButtonPanel);
        classScrollPane.setPreferredSize(new Dimension(500, 150));
        classPanel.add(classScrollPane, BorderLayout.CENTER);
        centerPanel.add(classPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        // Step 3: Student List (System step 4) - shown after class selection
        JPanel studentPanel = createStepPanel("3. Select Student:");
        studentComboBox = new JComboBox<>();
        studentPanel.add(studentComboBox);
        studentPanel.setVisible(false); // Initially hidden
        centerPanel.add(studentPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        // Step 4: Four-month period and View Report (User step 5)
        JPanel reportSelectionPanel = new JPanel(new BorderLayout());
        reportSelectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel periodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        periodPanel.add(new JLabel("4. Select Four-Month Period: "));
        monthsComboBox = new JComboBox<>();
        periodPanel.add(monthsComboBox);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewReportButton = new JButton("View Report Card");
        viewReportButton.setEnabled(false);
        buttonPanel.add(viewReportButton);
        reportSelectionPanel.add(periodPanel, BorderLayout.WEST);
        reportSelectionPanel.add(buttonPanel, BorderLayout.EAST);
        reportSelectionPanel.setVisible(false); // Initially hidden
        centerPanel.add(reportSelectionPanel);
        add(centerPanel, BorderLayout.CENTER);
        // Bottom panel for report display
        reportPanel = new ReportDisplayPanel();
        add(reportPanel, BorderLayout.SOUTH);
        // Setup event listeners
        setupEventListeners();
    }
    private JPanel createStepPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);
        return panel;
    }
    private void setupEventListeners() {
        // Year selection triggers class list display with buttons (System step 2)
        yearComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (yearComboBox.getSelectedItem() != null) {
                    selectedYear = yearComboBox.getSelectedItem().toString();
                    displayClassButtons(); // System step 2
                }
            }
        });
        // Student selection enables report viewing
        studentComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (studentComboBox.getSelectedItem() != null) {
                    selectedStudent = studentComboBox.getSelectedItem().toString();
                    enableReportSelection();
                }
            }
        });
        // Month selection updates selected period
        monthsComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (monthsComboBox.getSelectedItem() != null) {
                    selectedPeriod = monthsComboBox.getSelectedItem().toString();
                    viewReportButton.setEnabled(selectedStudent != null);
                }
            }
        });
        // View report button action
        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayReport(); // System step 6
            }
        });
    }
    private void loadInitialData() {
        // Populate academic year dropdown
        for (String year : academicYears) {
            yearComboBox.addItem(year);
        }
        // Populate four-month periods dropdown
        for (String period : fourMonthPeriods) {
            monthsComboBox.addItem(period);
        }
        // Initialize with no selection
        selectedPeriod = null;
    }
    /**
     * System step 2: Display the list of classes with "report cards" button next to each class
     */
    private void displayClassButtons() {
        classButtonPanel.removeAll();
        if (selectedYear == null) {
            classButtonPanel.add(new JLabel("Please select an academic year first"));
            classButtonPanel.revalidate();
            classButtonPanel.repaint();
            return;
        }
        // Display classes for the selected year
        for (final String className : classes) {
            JPanel classRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel classLabel = new JLabel(className + " (" + selectedYear + ")");
            classLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            classRow.add(classLabel);
            JButton reportCardButton = new JButton("View Report Cards");
            reportCardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedClass = className;
                    displayStudentsForClass(); // System step 4
                }
            });
            classRow.add(reportCardButton);
            classButtonPanel.add(classRow);
        }
        classButtonPanel.revalidate();
        classButtonPanel.repaint();
        // Reset subsequent steps
        resetSubsequentSteps();
    }
    /**
     * System step 4: Display the list of class students chosen by the user
     */
    private void displayStudentsForClass() {
        // Show student selection panel
        Container parent = studentComboBox.getParent();
        if (parent != null) {
            parent.setVisible(true);
        }
        // Clear and populate student dropdown
        studentComboBox.removeAllItems();
        studentComboBox.addItem("-- Select a Student --");
        for (String student : students) {
            studentComboBox.addItem(student);
        }
        // Reset report selection
        Container reportParent = viewReportButton.getParent();
        if (reportParent != null) {
            reportParent.setVisible(false);
        }
        // Disable report button until student is selected
        viewReportButton.setEnabled(false);
        reportPanel.clearReport();
        // Update UI
        revalidate();
        repaint();
    }
    /**
     * Enable report selection after student is chosen (prepares for User step 5)
     */
    private void enableReportSelection() {
        if (selectedStudent != null && !selectedStudent.equals("-- Select a Student --")) {
            Container reportParent = viewReportButton.getParent();
            if (reportParent != null) {
                reportParent.setVisible(true);
            }
            viewReportButton.setEnabled(selectedPeriod != null);
        }
    }
    /**
     * System step 6: Display the chosen student report referred to the selected four months
     */
    private void displayReport() {
        // Validate all selections
        if (selectedYear == null || selectedClass == null || 
            selectedStudent == null || selectedStudent.equals("-- Select a Student --") || 
            selectedPeriod == null) {
            JOptionPane.showMessageDialog(this, 
                "Please complete all selection steps:\n" +
                "1. Select academic year\n" +
                "2. Choose a class\n" +
                "3. Select a student\n" +
                "4. Select a four-month period", 
                "Incomplete Data", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Fetch or generate report for the selected data
        String reportContent = generateReport(selectedYear, selectedClass, selectedStudent, selectedPeriod);
        // Display the chosen student report
        reportPanel.displayReport(selectedYear, selectedClass, selectedStudent, selectedPeriod, reportContent);
        // Simulate postcondition: connection to server interrupted after display
        interruptServerConnection();
    }
    private void resetSubsequentSteps() {
        // Hide student selection
        Container studentParent = studentComboBox.getParent();
        if (studentParent != null) {
            studentParent.setVisible(false);
        }
        // Hide report selection
        Container reportParent = viewReportButton.getParent();
        if (reportParent != null) {
            reportParent.setVisible(false);
        }
        // Clear selections and reset comboboxes
        studentComboBox.removeAllItems();
        selectedClass = null;
        selectedStudent = null;
        selectedPeriod = null;
        viewReportButton.setEnabled(false);
        // Clear any existing report
        reportPanel.clearReport();
    }
    private String generateReport(String year, String className, String studentName, String period) {
        // In a real system, this would fetch data from a database or external service.
        // For demonstration, we return a hard-coded sample report.
        StringBuilder report = new StringBuilder();
        report.append("REPORT CARD\n");
        report.append("===========\n");
        report.append("Academic Year: ").append(year).append("\n");
        report.append("Class: ").append(className).append("\n");
        report.append("Student: ").append(studentName).append("\n");
        report.append("Period: ").append(period).append("\n");
        report.append("Report Date: ").append(new Date()).append("\n\n");
        report.append("SUBJECTS AND GRADES:\n");
        report.append("--------------------\n");
        // Generate sample grades - different for each student for realism
        Map<String, String> grades = new LinkedHashMap<>();
        grades.put("Mathematics", getStudentGrade(studentName, "Mathematics"));
        grades.put("Science", getStudentGrade(studentName, "Science"));
        grades.put("English", getStudentGrade(studentName, "English"));
        grades.put("History", getStudentGrade(studentName, "History"));
        grades.put("Physical Education", getStudentGrade(studentName, "Physical Education"));
        for (Map.Entry<String, String> entry : grades.entrySet()) {
            report.append(String.format("%-20s: %s\n", entry.getKey(), entry.getValue()));
        }
        report.append("\nATTENDANCE AND BEHAVIOR:\n");
        report.append("------------------------\n");
        report.append("Attendance: ").append(getAttendanceRate(studentName)).append("%\n");
        report.append("Participation: ").append(getParticipationLevel(studentName)).append("\n");
        report.append("Homework Completion: ").append(getHomeworkCompletion(studentName)).append("%\n");
        report.append("\nTEACHER COMMENTS:\n");
        report.append("-----------------\n");
        report.append(getTeacherComments(studentName, period)).append("\n");
        report.append("\nOVERALL ASSESSMENT:\n");
        report.append("------------------\n");
        report.append("Overall Grade: ").append(getOverallGrade(studentName)).append("\n");
        report.append("Status: ").append(getStudentStatus(studentName)).append("\n");
        return report.toString();
    }
    // Helper methods for generating realistic report data
    private String getStudentGrade(String studentName, String subject) {
        // Generate consistent but varied grades per student
        int hash = Math.abs((studentName + subject).hashCode());
        String[] grades = {"A", "A-", "B+", "B", "B-", "C+", "C"};
        return grades[hash % grades.length];
    }
    private int getAttendanceRate(String studentName) {
        int hash = Math.abs(studentName.hashCode());
        return 85 + (hash % 16); // 85-100%
    }
    private String getParticipationLevel(String studentName) {
        int hash = Math.abs(studentName.hashCode());
        String[] levels = {"Excellent", "Good", "Satisfactory", "Needs Improvement"};
        return levels[hash % levels.length];
    }
    private int getHomeworkCompletion(String studentName) {
        int hash = Math.abs(studentName.hashCode());
        return 75 + (hash % 26); // 75-100%
    }
    private String getTeacherComments(String studentName, String period) {
        String[] comments = {
            studentName + " shows excellent progress in most subjects. Continues to demonstrate strong analytical skills.",
            studentName + " has made significant improvements this period. Shows increased engagement in class discussions.",
            studentName + " maintains consistent performance. Could benefit from additional practice in challenging areas.",
            studentName + " is a diligent student who completes assignments on time. Shows good understanding of core concepts."
        };
        int hash = Math.abs((studentName + period).hashCode());
        return comments[hash % comments.length];
    }
    private String getOverallGrade(String studentName) {
        int hash = Math.abs(studentName.hashCode());
        String[] grades = {"A", "A-", "B+", "B", "B-"};
        return grades[hash % grades.length];
    }
    private String getStudentStatus(String studentName) {
        int hash = Math.abs(studentName.hashCode());
        String[] statuses = {"Passing", "Excellent Standing", "Good Standing", "Satisfactory"};
        return statuses[hash % statuses.length];
    }
    private void interruptServerConnection() {
        // Simulate postcondition: "The user stops the connection to the SMOS server interrupted"
        // For demo, show a message and log
        JOptionPane.showMessageDialog(this,
            "Report displayed successfully.\n" +
            "Connection to SMOS server has been terminated as per security protocol.",
            "Connection Status",
            JOptionPane.INFORMATION_MESSAGE);
        System.out.println("[INFO] Connection to SMOS server terminated after report display.");
    }
}
/**
 * Panel that displays the generated report card.
 * It includes a text area and a button to close/clear the report.
 */
class ReportDisplayPanel extends JPanel {
    private JTextArea reportTextArea;
    private JButton clearButton;
    private JButton printButton;
    public ReportDisplayPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Student Report Card"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        reportTextArea = new JTextArea(15, 70);
        reportTextArea.setEditable(false);
        reportTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportTextArea.setLineWrap(true);
        reportTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        clearButton = new JButton("Clear Report");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearReport();
            }
        });
        printButton = new JButton("Print Report");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printReport();
            }
        });
        buttonPanel.add(printButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Initially hide the panel until a report is generated
        setVisible(false);
    }
    public void displayReport(String year, String className, String studentName, String period, String content) {
        // Build a header for the displayed report
        String header = String.format("REPORT CARD VIEWER\n" +
                                     "==================\n" +
                                     "Generated for: %s\n" +
                                     "Class: %s | Year: %s | Period: %s\n" +
                                     "Generated on: %s\n\n",
                                     studentName, className, year, period, new Date());
        reportTextArea.setText(header + content);
        setVisible(true);
        // Scroll to top
        reportTextArea.setCaretPosition(0);
    }
    public void clearReport() {
        reportTextArea.setText("");
        setVisible(false);
    }
    private void printReport() {
        try {
            if (reportTextArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No report to print.", 
                    "Print Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean print = reportTextArea.print();
            if (print) {
                JOptionPane.showMessageDialog(this, 
                    "Report sent to printer successfully.", 
                    "Print Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error printing report: " + ex.getMessage(), 
                "Print Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}