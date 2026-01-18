/**
 * Main GUI frame for the administrator to view and reject registration requests.
 * This implements the "RejectEnrollmentStudent" use case with a list of pending
 * requests and reject buttons for each.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class AdminGUI extends JFrame {
    private JTable requestsTable;
    private DefaultTableModel tableModel;
    private RequestDAO requestDAO;
    // Column names for the requests table
    private static final String[] COLUMN_NAMES = {
        "Request ID", "Student Name", "Student ID", "Email", "Request Date", "Action"
    };
    // Map to store button editors for each row to prevent memory leaks
    private Map<Integer, ButtonEditor> buttonEditors;
    public AdminGUI() {
        super("Student Registration Request Manager");
        requestDAO = new RequestDAO();
        buttonEditors = new HashMap<>();
        initializeComponents();
        setupLayout();
        loadPendingRequests();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 500); // Slightly increased for better layout
        setLocationRelativeTo(null); // Center on screen
    }
    /**
     * Initialize all GUI components
     */
    private void initializeComponents() {
        // Create table model with non-editable cells except action column
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the action column (last column) should be editable
                return column == COLUMN_NAMES.length - 1;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Return appropriate class for each column
                if (columnIndex == COLUMN_NAMES.length - 1) {
                    return JButton.class;
                }
                return String.class;
            }
        };
        requestsTable = new JTable(tableModel);
        requestsTable.setRowHeight(40);
        requestsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        // Set custom renderer and editor for the action column
        int actionColumn = COLUMN_NAMES.length - 1;
        requestsTable.getColumnModel().getColumn(actionColumn).setCellRenderer(new ButtonRenderer());
    }
    /**
     * Setup the layout of the GUI
     */
    private void setupLayout() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title label
        JLabel titleLabel = new JLabel("Pending Student Registration Requests", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 0, 139)); // Dark blue color
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Table in scroll pane
        JScrollPane scrollPane = new JScrollPane(requestsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Button panel at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton refreshButton = new JButton("Refresh Requests");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.setBackground(new Color(70, 130, 180)); // Steel blue
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPendingRequests();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        exitButton.setBackground(new Color(220, 20, 60)); // Crimson
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Load pending registration requests from the data source
     * This simulates the "View Registration Requests" pre-condition
     */
    private void loadPendingRequests() {
        // Clear existing rows and button editors
        tableModel.setRowCount(0);
        buttonEditors.clear();
        try {
            // Get pending requests from DAO
            List<RegistrationRequest> pendingRequests = requestDAO.getPendingRequests();
            // Add each request to the table
            for (RegistrationRequest request : pendingRequests) {
                Object[] rowData = {
                    request.getRequestId(),
                    request.getStudentName(),
                    request.getStudentId(),
                    request.getEmail(),
                    request.getRequestDate(),
                    "Reject"  // Button text
                };
                tableModel.addRow(rowData);
                // Create button editor for this row
                int rowIndex = tableModel.getRowCount() - 1;
                ButtonEditor editor = new ButtonEditor(new JCheckBox(), request.getRequestId());
                buttonEditors.put(rowIndex, editor);
                requestsTable.getColumnModel().getColumn(COLUMN_NAMES.length - 1).setCellEditor(editor);
            }
            // Show message if no pending requests
            if (pendingRequests.isEmpty()) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(AdminGUI.this, 
                        "No pending registration requests.", 
                        "Information", 
                        JOptionPane.INFORMATION_MESSAGE));
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(AdminGUI.this, 
                    "Error loading requests: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE));
            e.printStackTrace();
        }
    }
    /**
     * Reject a specific registration request
     * This implements the main use case functionality
     * 
     * @param requestId The ID of the request to reject
     */
    private void rejectRequest(String requestId) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to reject registration request " + requestId + "?\n" +
            "This action cannot be undone.",
            "Confirm Rejection",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Attempt to reject the request
                requestDAO.rejectRequest(requestId);
                // Display success message
                JOptionPane.showMessageDialog(this, 
                    "Successfully rejected registration request " + requestId, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Refresh the list (Events sequence step 2: Display updated list)
                loadPendingRequests();
            } catch (SMOSException e) {
                // Handle SMOS server interruption (from postconditions)
                JOptionPane.showMessageDialog(this, 
                    "Request rejected but SMOS server connection was interrupted.\n" +
                    "The rejection was successful, but SMOS synchronization failed.\n" +
                    "Error: " + e.getMessage(), 
                    "SMOS Server Warning", 
                    JOptionPane.WARNING_MESSAGE);
                // Still refresh the list as the rejection succeeded
                loadPendingRequests();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error rejecting request: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Custom cell renderer for buttons in the table
     */
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setFont(new Font("Arial", Font.BOLD, 12));
            // Set button appearance
            if (isSelected) {
                setBackground(new Color(184, 207, 229)); // Light blue for selection
                setForeground(Color.BLACK);
            } else {
                setBackground(new Color(220, 20, 60)); // Crimson background
                setForeground(Color.WHITE);
            }
            // Add hover effect
            if (hasFocus) {
                setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
            return this;
        }
    }
    /**
     * Custom cell editor for buttons in the table
     */
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String requestId;
        private boolean isPushed;
        public ButtonEditor(JCheckBox checkBox, String requestId) {
            super(checkBox);
            this.requestId = requestId;
            this.isPushed = false;
            button = new JButton();
            button.setOpaque(true);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBackground(new Color(220, 20, 60)); // Crimson
            button.setForeground(Color.WHITE);
            // Add action listener to handle button clicks
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    isPushed = true;
                    // Call rejectRequest when button is clicked
                    rejectRequest(requestId);
                }
            });
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setBackground(new Color(184, 207, 229));
                button.setForeground(Color.BLACK);
            } else {
                button.setBackground(new Color(220, 20, 60));
                button.setForeground(Color.WHITE);
            }
            button.setText((value == null) ? "" : value.toString());
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // After button click, reset the button state
                isPushed = false;
            }
            return button.getText();
        }
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}