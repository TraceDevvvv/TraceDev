/**
The main graphical user interface for the AcceptEnrollmentStudent application.
Displays a table of pending registration requests with Accept button functionality.
Handles user interactions and updates the GUI accordingly.
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class EnrollmentGUI extends JFrame {
    private JTable requestTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private RegistrationManager manager;
    // Column names for the table
    private final String[] columnNames = {"Student ID", "Name", "Email", "Status"};
    /**
     * Constructor sets up the GUI components and initializes the RegistrationManager.
     */
    public EnrollmentGUI() {
        // Set up the main window
        setTitle("Accept Enrollment Student - Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // Center the window on screen
        manager = new RegistrationManager();
        // Create and arrange GUI components
        initComponents();
        // Load initial data into the table
        loadPendingRequests();
    }
    /**
     * Initializes and arranges all GUI components using BorderLayout.
     */
    private void initComponents() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Title label at the top
        JLabel titleLabel = new JLabel("Pending Student Registration Requests");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Table to display pending requests (center)
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        requestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(requestTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Lower panel with buttons and status label (south)
        JPanel lowerPanel = new JPanel(new BorderLayout());
        // Button panel (west)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton acceptButton = new JButton("Accept Enrollment");
        JButton refreshButton = new JButton("Refresh List");
        // Set button actions
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptEnrollment();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPendingRequests();
            }
        });
        buttonPanel.add(acceptButton);
        buttonPanel.add(refreshButton);
        lowerPanel.add(buttonPanel, BorderLayout.WEST);
        // Status label (east)
        statusLabel = new JLabel("Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lowerPanel.add(statusLabel, BorderLayout.EAST);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        // Add main panel to frame
        add(mainPanel);
    }
    /**
     * Loads pending registration requests from RegistrationManager and updates the table.
     */
    private void loadPendingRequests() {
        // Clear existing table data
        tableModel.setRowCount(0);
        // Get pending requests from manager
        List<RegistrationRequest> pendingRequests = manager.getPendingRequests();
        // Add each request as a row in the table
        for (RegistrationRequest request : pendingRequests) {
            Object[] rowData = {
                request.getStudentId(),
                request.getName(),
                request.getEmail(),
                request.getStatus()
            };
            tableModel.addRow(rowData);
        }
        // Update status label based on number of pending requests
        if (pendingRequests.isEmpty()) {
            statusLabel.setText("No pending requests");
        } else {
            statusLabel.setText("Found " + pendingRequests.size() + " pending request(s)");
        }
    }
    /**
     * Handles the acceptance of a selected enrollment request.
     * Includes validation, confirmation, and error handling.
     */
    private void acceptEnrollment() {
        // Check if a row is selected
        int selectedRow = requestTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student from the table first.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get student ID from selected row
        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        String studentName = (String) tableModel.getValueAt(selectedRow, 1);
        // Confirm acceptance with the administrator
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to accept enrollment for " + studentName + " (ID: " + studentId + ")?",
            "Confirm Enrollment Acceptance",
            JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // User cancelled
        }
        try {
            // Attempt to accept the student
            boolean success = manager.acceptStudent(studentId);
            if (success) {
                // Success: show message and refresh table
                JOptionPane.showMessageDialog(this,
                    "Successfully accepted enrollment for " + studentName,
                    "Enrollment Accepted",
                    JOptionPane.INFORMATION_MESSAGE);
                // Update the table row to reflect the new status
                tableModel.setValueAt("ACTIVATED", selectedRow, 3);
                statusLabel.setText("Activated student: " + studentName);
                // Note: In a real application, you might want to refresh the entire list
                // loadPendingRequests();
            } else {
                // Simulated server interruption
                JOptionPane.showMessageDialog(this,
                    "Failed to accept enrollment due to SMOS server interruption.\nPlease try again later.",
                    "Server Interrupted",
                    JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Server interrupted - operation failed");
            }
        } catch (IllegalArgumentException e) {
            // Handle case where student ID is not found (edge case)
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Student Not Found",
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error: Student not found");
        }
    }
}