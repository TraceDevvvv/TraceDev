/**
 * AdminDashboardFrame class provides the graphical user interface for administrators
 * to view and reject student registration requests.
 * It uses a JTable to display requests and custom renderers/editors for the "Reject" button.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class AdminDashboardFrame extends JFrame {
    private RegistrationService registrationService; // Service to interact with registration data
    private JTable registrationTable; // Table to display registration requests
    private DefaultTableModel tableModel; // Model for the JTable
    private JButton refreshButton; // Button to refresh the list of requests
    // Constructor to set up the admin dashboard GUI
    public AdminDashboardFrame(RegistrationService service) {
        this.registrationService = service;
        setTitle("Administrator Dashboard - Reject Enrollment");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the window
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize GUI components
        initComponents();
        // Load the initial list of pending requests
        loadPendingRequests();
    }
    // Method to initialize all GUI components and layout
    private void initComponents() {
        // Panel for holding the table
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Define table columns: ID, Student Name, Status, Action (for button)
        String[] columnNames = {"ID", "Student Name", "Status", "Action"};
        // Initialize table model without data; data will be loaded dynamically
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            // Make cells non-editable by default, except the "Action" button column
            public boolean isCellEditable(int row, int column) {
                return column == getColumnCount() - 1; // Only the last column (Action) is editable
            }
        };
        registrationTable = new JTable(tableModel);
        // Set up custom renderer and editor for the "Action" (Reject) button column
        registrationTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        registrationTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), this));
        // Add the table to a scroll pane so it's scrollable if many requests
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Panel for buttons (e.g., Refresh)
        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("Refresh Pending Requests");
        buttonPanel.add(refreshButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add action listener for the refresh button
        refreshButton.addActionListener(e -> loadPendingRequests());
        // Add the main panel to the frame
        add(mainPanel);
    }
    /**
     * Loads pending registration requests from the RegistrationService and updates the JTable.
     * This fulfills the precondition of "View Registration Requests" and
     * the system event "Displays the list of registrations yet to be activated".
     */
    public void loadPendingRequests() {
        // Clear existing table data
        tableModel.setRowCount(0);
        // Retrieve pending requests from the service
        List<StudentRegistration> pendingRequests = registrationService.getPendingRequests();
        if (pendingRequests.isEmpty()) {
            // Display a message if no pending requests
            tableModel.addRow(new Object[]{"", "No pending requests.", "", ""});
        } else {
            // Add each pending request to the table model
            for (StudentRegistration reg : pendingRequests) {
                // Add an empty string for the "Action" column, the renderer will put a button there
                Object[] rowData = {reg.getId(), reg.getStudentName(), reg.getStatus(), "Reject"};
                tableModel.addRow(rowData);
            }
        }
    }
    /**
     * Handles the rejection of a student registration.
     * This method is called from the ButtonEditor when a "Reject" button is clicked.
     * @param registrationId The ID of the registration to be rejected.
     */
    public void rejectRegistration(String registrationId) {
        // Attempt to reject the registration via the service
        boolean success = registrationService.rejectRegistration(registrationId);
        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Registration " + registrationId + " has been successfully eliminated.",
                    "Rejection Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            // After rejection, refresh the list to show the updated state (the rejected item will be gone)
            loadPendingRequests();
        } else {
            // Handle cases where rejection failed (e.g., student not found, already processed)
            JOptionPane.showMessageDialog(this,
                    "Failed to eliminate registration " + registrationId + ". It might no longer be pending or does not exist.",
                    "Rejection Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
        // Postcondition: The user refused a request for registration to the system.
        // Postcondition: The user interrupts the connection operation to the interrupted SMOS server (simulated by action completion).
    }
    /**
     * Custom TableCellRenderer for rendering a JButton in a JTable cell.
     * This class ensures the "Reject" text appears as a button in the table.
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString()); // Set button text
            return this; // Return the button component itself
        }
    }
    /**
     * Custom TableCellEditor for handling clicks on the JButton in a JTable cell.
     * This class makes the "Reject" button functional when clicked.
     */
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private AdminDashboardFrame parentFrame; // Reference to the parent frame to call rejectRegistration
        public ButtonEditor(JCheckBox checkBox, AdminDashboardFrame parentFrame) {
            super(checkBox);
            this.parentFrame = parentFrame;
            button = new JButton();
            button.setOpaque(true);
            // Action listener for the button click
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped(); // Notify editor that editing has stopped
                }
            });
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // When the button is clicked, get the ID from the current row being edited
                String registrationId = (String) parentFrame.registrationTable.getValueAt(
                        parentFrame.registrationTable.getEditingRow(), 0);
                // Call the rejectRegistration method in the AdminDashboardFrame
                parentFrame.rejectRegistration(registrationId);
            }
            isPushed = false;
            return label; // Return the label as the cell value
        }
        @Override
        public boolean stopCellEditing() {
            isPushed = false; // Reset the pushed flag
            return super.stopCellEditing(); // Call superclass method
        }
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped(); // Notify listeners that editing is stopped
        }
    }
}