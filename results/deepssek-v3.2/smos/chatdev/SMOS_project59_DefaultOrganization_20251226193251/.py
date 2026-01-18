import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * Main GUI frame for displaying student report cards.
 * This implements the use case steps: displaying archived reports, selecting one, showing details.
 */
class ReportDisplayFrame extends JFrame {
    private Student currentStudent;
    private ReportService reportService;
    private JList<ReportCard> reportList;
    private DefaultListModel<ReportCard> listModel;
    private JTextArea detailsArea;
    private JButton viewDetailsButton;
    private JLabel statusLabel;
    public ReportDisplayFrame(Student student) {
        this.currentStudent = student;
        this.reportService = new ReportService();
        // Setup the main window
        setTitle("Student Report Display - " + student.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));
        // Initialize UI components
        initUI();
        // Step 1: Display the student's reports from the archive (Events sequence step 1)
        loadStudentReports();
        // Step 3 (simulated postcondition): Simulate SMOS server connection interruption after details are shown
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                reportService.simulateSMOSConnectionInterrupt();
            }
        });
    }
    /**
     * Initializes all GUI components and layouts.
     */
    private void initUI() {
        // North panel: Title and welcome
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Online Report System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(titleLabel, BorderLayout.NORTH);
        JLabel welcomeLabel = new JLabel("Welcome, " + currentStudent.getName() + " (" + currentStudent.getId() + ")");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        northPanel.add(welcomeLabel, BorderLayout.SOUTH);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(northPanel, BorderLayout.NORTH);
        // Center panel: List of reports and details
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);
        // Left panel: Report list
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Your Report Cards (Step 1: System displays archive)"));
        listModel = new DefaultListModel<>();
        reportList = new JList<>(listModel);
        reportList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reportList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane listScroll = new JScrollPane(reportList);
        leftPanel.add(listScroll, BorderLayout.CENTER);
        // Button to view details (Events sequence step 2: user selects report card, then clicks to view)
        viewDetailsButton = new JButton("View Selected Report Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedReport();
            }
        });
        leftPanel.add(viewDetailsButton, BorderLayout.SOUTH);
        splitPane.setLeftComponent(leftPanel);
        // Right panel: Details display
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Report Details (Step 3: Displays details)"));
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        rightPanel.add(detailsScroll, BorderLayout.CENTER);
        splitPane.setRightComponent(rightPanel);
        add(splitPane, BorderLayout.CENTER);
        // South panel: Status bar
        statusLabel = new JLabel("Ready. Click 'Online report' to begin.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        add(statusLabel, BorderLayout.SOUTH);
        // Simulate the user clicking "Online report" as per preconditions
        JButton onlineReportButton = new JButton("Online Report");
        onlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Loading your reports...");
                loadStudentReports();  // This fulfills the user action of clicking "Online report"
                statusLabel.setText("Reports loaded. Select a report card and click 'View Selected Report Details'.");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(onlineReportButton);
        add(buttonPanel, BorderLayout.WEST);
    }
    /**
     * Loads the current student's report cards from the service and populates the list.
     * This corresponds to Events sequence step 1.
     */
    private void loadStudentReports() {
        listModel.clear();
        List<ReportCard> reports = reportService.getReportsForStudent(currentStudent.getId());
        if (reports.isEmpty()) {
            listModel.addElement(new ReportCard("No reports", "", "N/A", new String[]{}, new double[]{}, "No data found."));
            viewDetailsButton.setEnabled(false);
            statusLabel.setText("No report cards found for you.");
        } else {
            for (ReportCard report : reports) {
                listModel.addElement(report);
            }
            viewDetailsButton.setEnabled(true);
            statusLabel.setText("Found " + reports.size() + " report(s). Select one to view details.");
        }
    }
    /**
     * Displays the details of the currently selected report card in the details area.
     * This corresponds to Events sequence step 3.
     */
    private void displaySelectedReport() {
        ReportCard selected = reportList.getSelectedValue();
        if (selected == null || selected.getReportId().equals("No reports")) {
            JOptionPane.showMessageDialog(this, "Please select a valid report card from the list.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Build a detailed string representation
        StringBuilder details = new StringBuilder();
        details.append("Report ID: ").append(selected.getReportId()).append("\n");
        details.append("Student ID: ").append(selected.getStudentId()).append("\n");
        details.append("Semester: ").append(selected.getSemester()).append("\n");
        details.append("----------------------------------------\n");
        details.append("Subjects and Grades:\n");
        String[] subjects = selected.getSubjects();
        double[] grades = selected.getGrades();
        for (int i = 0; i < subjects.length; i++) {
            details.append(String.format("  %-15s : %.2f\n", subjects[i], grades[i]));
        }
        details.append("----------------------------------------\n");
        details.append(String.format("Average Grade: %.2f\n", selected.calculateAverage()));
        details.append("Teacher Comments: ").append(selected.getTeacherComments()).append("\n");
        details.append("----------------------------------------\n");
        details.append("(Postcondition: Report displayed successfully.)");
        detailsArea.setText(details.toString());
        statusLabel.setText("Displaying details for " + selected.getReportId());
    }
}