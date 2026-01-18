'''
ParentViewApp.java
This is the main application class for the Parent's Student Report Viewer.
It provides a graphical user interface (GUI) for a parent to view their children's report cards.
The application simulates interactions with a School Management Online System (SMOS).
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * ParentViewApp.java
 * This is the main application class for the Parent's Student Report Viewer.
 * It provides a graphical user interface (GUI) for a parent to view their children's report cards.
 * The application simulates interactions with a School Management Online System (SMOS).
 */
public class ParentViewApp extends JFrame {
    private String currentParentId = "parent123"; // Simulating a parent already logged in.
    private SMOSServer smosServer; // Instance of the mock SMOS server.
    // GUI components
    private JList<Student> studentList;
    private DefaultListModel<Student> studentListModel;
    private JList<ReportCard> reportCardList;
    private DefaultListModel<ReportCard> reportCardListModel;
    private JButton viewReportDetailsButton;
    private JLabel statusLabel; // Displays application status messages.
    /**
     * Constructs the main ParentViewApp frame.
     * Initializes the SMOS server and sets up the GUI.
     */
    public ParentViewApp() {
        super("Parent's Student Report Viewer"); // Set window title.
        smosServer = new SMOSServer(); // Initialize our mock server.
        initUI(); // Set up all Swing components.
        loadStudents(); // Load students for the current parent upon startup.
    }
    /**
     * Initializes all the user interface components of the application.
     * This includes panels, lists, buttons, and their layout.
     */
    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the frame.
        setSize(800, 600); // Set preferred window size.
        setLocationRelativeTo(null); // Center the window on the screen.
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps for main layout.
        // --- Student Selection Panel (LEFT side) ---
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBorder(BorderFactory.createTitledBorder("Your Children")); // Title border for aesthetics.
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one student to be selected at a time.
        // Add a listener to handle student selection (simulates "Magine button" click).
        studentList.addListSelectionListener(e -> {
            // Ensure this event is triggered only once when selection stabilizes.
            if (!e.getValueIsAdjusting() && studentList.getSelectedValue() != null) {
                // When a student is selected, load their report cards.
                loadReportCardsForSelectedStudent(studentList.getSelectedValue());
                viewReportDetailsButton.setEnabled(false); // Disable report details button until a report card is selected.
            }
        });
        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER); // Add scroll pane for student list.
        add(studentPanel, BorderLayout.WEST);
        // --- Report Card Selection Panel (CENTER) ---
        JPanel reportCardPanel = new JPanel(new BorderLayout());
        reportCardPanel.setBorder(BorderFactory.createTitledBorder("Report Cards")); // Title border for aesthetics.
        reportCardListModel = new DefaultListModel<>();
        reportCardList = new JList<>(reportCardListModel);
        reportCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one report card to be selected.
        // Add a listener to enable/disable the "View Report Card Details" button.
        reportCardList.addListSelectionListener(e -> {
            // Enable view details button if a report card is selected.
            viewReportDetailsButton.setEnabled(reportCardList.getSelectedValue() != null && !reportCardList.getSelectedValue().isPlaceholder());
        });
        reportCardPanel.add(new JScrollPane(reportCardList), BorderLayout.CENTER); // Add scroll pane for report card list.
        add(reportCardPanel, BorderLayout.CENTER);
        // --- Actions and Status Panel (BOTTOM) ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // FlowLayout for right alignment with padding.
        viewReportDetailsButton = new JButton("View Report Card Details");
        viewReportDetailsButton.setEnabled(false); // Initially disabled until a report card is selected.
        // Add action listener to display report card details.
        viewReportDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedReportCardDetails();
            }
        });
        bottomPanel.add(viewReportDetailsButton);
        statusLabel = new JLabel("Status: Ready"); // Initialize status label.
        bottomPanel.add(statusLabel); // Add status label to the bottom panel.
        add(bottomPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads the children associated with the logged-in parent from the SMOS server.
     * This method is called once upon application startup.
     */
    private void loadStudents() {
        statusLabel.setText("Status: Connecting to SMOS server to fetch students...");
        // Execute heavy network operation in a separate thread to keep UI responsive.
        new SwingWorker<List<Student>, Void>() {
            @Override
            protected List<Student> doInBackground() throws Exception {
                // Simulate a potential delay or error as part of network operation.
                try {
                    Thread.sleep(500); // Simulate network latency
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Fetching students interrupted", ex);
                }
                return smosServer.fetchStudentsForParent(currentParentId);
            }
            @Override
            protected void done() {
                try {
                    List<Student> students = get();
                    studentListModel.clear();
                    reportCardListModel.clear(); // Clear report cards when new students are loaded/reloaded.
                    viewReportDetailsButton.setEnabled(false); // Disable details button.
                    if (students.isEmpty()) {
                        studentListModel.addElement(new Student("N/A", "No children found for this parent."));
                    } else {
                        for (Student s : students) {
                            studentListModel.addElement(s);
                        }
                    }
                    statusLabel.setText("Status: Students loaded.");
                } catch (Exception e) {
                    statusLabel.setText("Status: Error loading students. " + e.getMessage());
                    JOptionPane.showMessageDialog(ParentViewApp.this,
                            "Failed to load students: " + e.getMessage(),
                            "Server Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    /**
     * Loads report cards for a selected student from the SMOS server.
     * This method simulates the "click on the Magine button" -> "View selected student report cards".
     *
     * @param student The Student object for whom to load report cards.
     */
    private void loadReportCardsForSelectedStudent(Student student) {
        reportCardListModel.clear(); // Clear existing report cards.
        viewReportDetailsButton.setEnabled(false); // Disable button immediately when student selection changes.
        // Handle case where no student is effectively selected (e.g., list cleared).
        if (student == null || "N/A".equals(student.getId())) {
            return;
        }
        statusLabel.setText("Status: Fetching report cards for " + student.getName() + "...");
        // Execute heavy network operation in a separate thread to keep UI responsive.
        new SwingWorker<List<ReportCard>, Void>() {
            @Override
            protected List<ReportCard> doInBackground() throws Exception {
                // Simulate a potential delay or error as part of network operation.
                try {
                    Thread.sleep(700); // Simulate network latency
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Fetching report cards interrupted", ex);
                }
                // Check for a specific student ID to simulate a server error/no reports
                // For demonstration, let's say S003 has no reports.
                if ("S003".equals(student.getId())) { // Charlie Brown
                     return new java.util.ArrayList<>(); // Explicitly return empty list
                }
                return smosServer.fetchReportCardsForStudent(student.getId());
            }
            @Override
            protected void done() {
                try {
                    List<ReportCard> reports = get();
                    if (reports.isEmpty()) {
                        // Add a placeholder message if no reports are found.
                        // Create a ReportCard object specifically marked as a placeholder.
                        reportCardListModel.addElement(new ReportCard(student.getId(), "No reports available for " + student.getName(), new HashMap<>(), true)); // MODIFIED LINE
                    } else {
                        for (ReportCard rc : reports) {
                            reportCardListModel.addElement(rc);
                        }
                    }
                    statusLabel.setText("Status: Report cards loaded for " + student.getName() + ".");
                } catch (Exception e) {
                    statusLabel.setText("Status: Error loading report cards. " + e.getMessage());
                    JOptionPane.showMessageDialog(ParentViewApp.this,
                            "Failed to load report cards: " + e.getMessage(),
                            "Server Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    /**
     * Displays the details of the selected report card in a separate dialog.
     * This handles the "Displays the details of the selected report card" event.
     * It also simulates the postcondition "Connection to the interrupted SMOS server".
     */
    private void viewSelectedReportCardDetails() {
        Student selectedStudent = studentList.getSelectedValue();
        ReportCard selectedReportCard = reportCardList.getSelectedValue();
        // Validate that a student is selected.
        if (selectedStudent == null || "N/A".equals(selectedStudent.getId())) {
            JOptionPane.showMessageDialog(this, "Please select a child first.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return; // Exit if no student is selected or it's the placeholder student.
        }
        // Validate that a report card is selected.
        if (selectedReportCard == null) {
            JOptionPane.showMessageDialog(this, "Please select a report card to view its details.", "No Report Card Selected", JOptionPane.WARNING_MESSAGE);
            return; // Exit if no report card is selected.
        }
        // NEW: Check if the selected report card is a placeholder.
        if (selectedReportCard.isPlaceholder()) {
            JOptionPane.showMessageDialog(this, "This is an informational message. No detailed report is available.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return; // Exit if it's a placeholder.
        }
        // If a valid student and a non-placeholder report card are selected:
        statusLabel.setText("Status: Displaying details for report card " + selectedReportCard.getDate() + "...");
        // Create and show the modal dialog for report card details.
        ReportCardViewerDialog viewer = new ReportCardViewerDialog(this, selectedStudent, selectedReportCard);
        viewer.setVisible(true); // This call blocks until the dialog is closed.
        // Simulate the postcondition: SMOS connection interrupted after viewing.
        statusLabel.setText("Status: Report card details viewed. SMOS connection interrupted.");
        smosServer.disconnect();
    }
    /**
     * Main method to run the application.
     * Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            new ParentViewApp().setVisible(true);
        });
    }
}