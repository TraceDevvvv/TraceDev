import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
/*
 * Main application class to display student report cards using a Swing GUI.
 * This class orchestrates the interaction between the user, the simulated SMOS server,
 * and the display of report data.
 *
 * Implements the use case: "DisplayOfAPageStudent"
 * Preconditions:
 * - The user has been logged in to the system as a student (simulated by directly accessing student S101 data).
 * - The user clicks on "Online report" (simulated by starting the application).
 *
 * Events sequence:
 * 1. System displays the student's reports logged in in the archive.
 * 2. User selects the report card.
 * 3. Displays the details of the selected report card.
 *
 * Postconditions:
 * - The system displays a student's report.
 * - Connection to the SMOS server interrupted (simulated after application exit).
 */
public class StudentReportApp extends JFrame {
    private SMOSServerSimulator smosServer;
    private String currentStudentId = "S101"; // Simulating a logged-in student for demonstration
    private JList<ReportCard> reportCardList;
    private DefaultListModel<ReportCard> reportCardListModel;
    private JTextArea reportDetailsArea;
    private JButton exitButton;
    /**
     * Constructs the StudentReportApp, setting up the GUI and initializing the simulator.
     */
    public StudentReportApp() {
        super("Student Report Viewer"); // Set the window title
        smosServer = new SMOSServerSimulator();
        // Configure the main window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        // Set up the main panel with a BorderLayout for overall layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the edges
        // --- Left Panel: List of Report Cards ---
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Available Report Cards")); // Add a titled border
        reportCardListModel = new DefaultListModel<>(); // Model to hold ReportCard objects for the JList
        reportCardList = new JList<>(reportCardListModel);
        reportCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one report to be selected at a time
        // Add a ListSelectionListener to handle user selection of a report card
        reportCardList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Ensure this event is not an "adjusting" event (multiple events fire during a single selection gesture)
                // and that a valid item is actually selected.
                if (!e.getValueIsAdjusting() && reportCardList.getSelectedIndex() != -1) {
                    displaySelectedReportCardDetails();
                }
            }
        });
        leftPanel.add(new JScrollPane(reportCardList), BorderLayout.CENTER); // Wrap list in JScrollPane for scrollability
        // --- Center Panel: Report Details Area ---
        reportDetailsArea = new JTextArea();
        reportDetailsArea.setEditable(false); // Make text area read-only for displaying information
        reportDetailsArea.setLineWrap(true);   // Enable automatic line wrapping
        reportDetailsArea.setWrapStyleWord(true); // Wrap at word boundaries for better readability
        reportDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use a monospaced font for formatted details
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Report Details")); // Add a titled border
        centerPanel.add(new JScrollPane(reportDetailsArea), BorderLayout.CENTER); // Wrap text area in JScrollPane
        // --- Bottom Panel: Exit Button ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align button to the right
        exitButton = new JButton("Exit");
        // Add an action listener to the exit button. This is where the SMOS server connection is interrupted.
        exitButton.addActionListener(e -> {
            smosServer.interruptConnection(); // Fulfills postcondition: "Connection to the SMOS server interrupted"
            System.exit(0); // Terminate the application
        });
        bottomPanel.add(exitButton);
        // Add panels to the main frame using JSplitPane for resizable division
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
        splitPane.setDividerLocation(250); // Set initial divider position
        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Load reports for the simulated "logged-in" student once the GUI is set up.
        // This simulates the system displaying reports upon "Online report" click.
        loadStudentReports(currentStudentId);
    }
    /**
     * Loads the report cards for the given student ID from the simulated SMOS server
     * and populates the JList. This operation runs in a background thread to keep the UI responsive.
     * @param studentId The ID of the student whose reports are to be loaded.
     */
    private void loadStudentReports(String studentId) {
        reportCardListModel.clear(); // Clear any existing reports from the list
        reportDetailsArea.setText("Loading student reports..."); // Provide immediate user feedback
        // Use SwingWorker to perform the potentially long-running operation (simulated network call)
        // on a background thread, preventing the UI from freezing.
        SwingWorker<List<ReportCard>, Void> worker = new SwingWorker<List<ReportCard>, Void>() {
            @Override
            protected List<ReportCard> doInBackground() throws Exception {
                // Simulate fetching data from the SMOS server
                return smosServer.getStudentReports(studentId);
            }
            @Override
            protected void done() {
                // This method runs on the Event Dispatch Thread (EDT) after doInBackground completes.
                try {
                    List<ReportCard> reports = get(); // Retrieve the result from the background task
                    if (reports != null && !reports.isEmpty()) {
                        for (ReportCard rc : reports) {
                            reportCardListModel.addElement(rc); // Add each report card to the list model
                        }
                        reportDetailsArea.setText("Select a report card from the list to view its details.");
                        // Automatically select the first item if reports were loaded, triggering display of details
                        if (!reportCardListModel.isEmpty()) {
                            reportCardList.setSelectedIndex(0);
                        }
                    } else {
                        // Handle case where no reports are found or connection was interrupted during fetch
                        reportDetailsArea.setText("No report cards found for student " + studentId +
                                ".\n(Server connection status: " + (smosServer.isConnectionActive() ? "Active" : "Interrupted") + ")");
                    }
                } catch (Exception e) {
                    // Handle any exceptions that occurred during the background task
                    reportDetailsArea.setText("Error loading reports: " + e.getMessage());
                    e.printStackTrace(); // Log the full stack trace for debugging
                }
            }
        };
        worker.execute(); // Start the SwingWorker
    }
    /**
     * Displays the detailed information of the currently selected report card
     * in the JTextArea. Fetches details from the simulated SMOS server in a background thread.
     */
    private void displaySelectedReportCardDetails() {
        ReportCard selectedReport = reportCardList.getSelectedValue();
        if (selectedReport == null) {
            reportDetailsArea.setText("No report card selected.");
            return;
        }
        reportDetailsArea.setText("Loading details for " + selectedReport.getReportId() + "..."); // User feedback
        // Use SwingWorker again for fetching specific report details to maintain UI responsiveness
        SwingWorker<ReportCard, Void> worker = new SwingWorker<ReportCard, Void>() {
            @Override
            protected ReportCard doInBackground() throws Exception {
                // Fetch report details. The SMOS server connection is active until the user explicitly exits.
                return smosServer.getReportCardDetails(selectedReport.getReportId());
            }
            @Override
            protected void done() {
                // This method runs on the Event Dispatch Thread (EDT)
                try {
                   ReportCard detailedReport = get(); // Retrieve the detailed report
                    if (detailedReport != null) {
                        reportDetailsArea.setText(detailedReport.toDetailedString()); // Display full details
                    } else {
                        // This case handles situations where details could not be retrieved, e.g., connection lost
                        reportDetailsArea.setText("Failed to retrieve details for report: " + selectedReport.getReportId() +
                                "\n(Server connection status: " + (smosServer.isConnectionActive() ? "Active" : "Interrupted") + ")" +
                                "\nIt might have been removed or connection interrupted.");
                    }
                } catch (Exception e) {
                    // Handle any exceptions from the background task
                    reportDetailsArea.setText("Error retrieving report details: " + e.getMessage());
                    e.printStackTrace(); // Log the full stack trace for debugging
                }
            }
        };
        worker.execute(); // Start the SwingWorker
    }
    /**
     * Main method to run the Student Report Viewer application.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure that all Swing GUI updates and creation occur on the Event Dispatch Thread (EDT)
        // This is crucial for Swing applications to prevent deadlocks and ensure thread safety.
        SwingUtilities.invokeLater(() -> {
            StudentReportApp app = new StudentReportApp();
            app.setVisible(true); // Make the main application window visible
        });
    }
}