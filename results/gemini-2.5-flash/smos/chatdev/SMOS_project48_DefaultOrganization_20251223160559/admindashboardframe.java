'''
Represents the administrator's dashboard GUI for managing report cards,
specifically providing functionality to delete a report card.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
class AdminDashboardFrame extends JFrame {
    private ReportCardService reportCardService;
    private JTextField reportCardIdField;
    private JTextArea displayArea;
    private JButton deleteButton;
    private JLabel statusLabel; // Added a status label for better feedback
    /**
     * Constructs the AdminDashboardFrame.
     *
     * @param service The ReportCardService instance to use for report card operations.
     */
    public AdminDashboardFrame(ReportCardService service) {
        this.reportCardService = service;
        setTitle("Administrator Dashboard - Delete Report Card");
        setSize(700, 450); // Slightly increased size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10)); // Add some padding
        initComponents();
        refreshDisplayArea(); // Initial display of report cards
    }
    /**
     * Initializes all GUI components and sets up their layout and event listeners.
     */
    private void initComponents() {
        // Panel for input and button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Delete Report Card"));
        topPanel.add(new JLabel("Report Card ID to Delete:"));
        reportCardIdField = new JTextField(10);
        topPanel.add(reportCardIdField);
        deleteButton = new JButton("Delete Report Card");
        deleteButton.addActionListener(new DeleteButtonListener());
        topPanel.add(deleteButton);
        add(topPanel, BorderLayout.NORTH);
        // Text area to display current list of report cards
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Use monospaced font for cleaner display
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Current Report Cards"));
        add(scrollPane, BorderLayout.CENTER);
        // Status bar or message area
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        statusLabel = new JLabel("Welcome, Administrator! Enter ID to delete.");
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    /**
     * Refreshes the display area with the current list of report cards.
     * This simulates "displays the form with the list of classes" post-deletion.
     */
    private void refreshDisplayArea() {
        displayArea.setText(""); // Clear existing text
        List<ReportCard> currentReportCards = reportCardService.getAllReportCards();
        if (currentReportCards.isEmpty()) {
            displayArea.append("No report cards available in the system.");
        } else {
            displayArea.append(String.format("%-5s %-20s %-15s %-8s\n", "ID", "Student Name", "Class Name", "Grade"));
            displayArea.append("------------------------------------------------------------\n");
            for (ReportCard rc : currentReportCards) {
                displayArea.append(String.format("%-5d %-20s %-15s %-8.1f\n", 
                                                rc.getId(), rc.getStudentName(), rc.getClassName(), rc.getGrade()));
            }
        }
    }
    /**
     * ActionListener for the delete button. Handles the sequence of events
     * for deleting a report card including confirmation and status updates.
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Preconditions: Administrator logged in (simulated by running this app),
            // user took "Displaying a report card" (simulated by displayArea),
            // user clicked "Delete" button (this action listener).
            String idText = reportCardIdField.getText().trim();
            statusLabel.setText("Processing...");
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "Please enter a Report Card ID to delete.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Error: No ID entered.");
                return;
            }
            int reportCardId;
            try {
                reportCardId = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "Invalid Report Card ID. Please enter a numerical value.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                reportCardIdField.setText(""); // Clear invalid input
                statusLabel.setText("Error: Invalid ID format.");
                return;
            }
            // Check if the report card exists before prompting for confirmation
            ReportCard cardToDelete = reportCardService.findReportCardById(reportCardId);
            if (cardToDelete == null) {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "Report Card with ID " + reportCardId + " not found.",
                        "Deletion Failed", JOptionPane.INFORMATION_MESSAGE);
                reportCardIdField.setText(""); // Clear the input field
                statusLabel.setText("Deletion failed: Report card not found.");
                return;
            }
            // System event 1: The system displays a form to confirm the cancellation
            int confirmation = JOptionPane.showConfirmDialog(AdminDashboardFrame.this,
                    "Are you sure you want to delete the report card for " +
                            cardToDelete.getStudentName() + " (ID: " + reportCardId + ")?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // User event 2: The user accepts the cancellation by pressing the confirmation key
            if (confirmation == JOptionPane.YES_OPTION) {
                // Perform the deletion
                boolean deleted = reportCardService.deleteReportCard(reportCardId);
                if (deleted) {
                    // System event 3a: Displays a message of correct deletion
                    JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                            "Report card ID " + reportCardId + " deleted successfully!",
                            "Deletion Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Postcondition: A report relative to a student is canceled (handled by service)
                    // System event 3b: Displays the form with the list of classes
                    refreshDisplayArea();
                    reportCardIdField.setText(""); // Clear the input field
                    statusLabel.setText("Report card ID " + reportCardId + " successfully deleted.");
                    // Postcondition: Connection to the interrupted SMOS server (simulated)
                    // In a real application, this would involve closing a connection or flagging a state.
                    System.out.println("LOG: SMOS server connection interrupted (simulated) for report card " + reportCardId + " deletion.");
                } else {
                    // This case should ideally not be reached if cardToDelete was not null and service logic is correct
                    JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                            "Failed to delete report card ID " + reportCardId + " due to an internal error.",
                            "Deletion Failed",
                            JOptionPane.ERROR_MESSAGE);
                    statusLabel.setText("Deletion failed due to internal error.");
                }
            } else {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "Deletion cancelled.",
                        "Cancellation",
                        JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText("Deletion process cancelled by user.");
            }
        }
    }
}