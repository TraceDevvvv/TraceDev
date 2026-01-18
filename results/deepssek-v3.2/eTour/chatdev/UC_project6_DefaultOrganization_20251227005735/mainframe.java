'''
Main GUI window for the Refreshment Point Deletion System.
Displays list of refreshment points retrieved from SearchCulturalHeritage use case.
This is only accessible after successful agency operator authentication.
'''
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class MainFrame extends JFrame {
    private RefreshmentPointDAO dao;
    private JTable refreshmentTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    /**
     * Constructor sets up the main application window.
     */
    public MainFrame() {
        dao = new RefreshmentPointDAO();
        // Set up the main window
        setTitle("Refreshment Point Management System - Agency Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Add title label
        JLabel titleLabel = new JLabel("Refreshment Point Deletion System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create table to display refreshment points
        setupRefreshmentTable();
        JScrollPane scrollPane = new JScrollPane(refreshmentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        deleteButton = new JButton("Delete Selected Point");
        cancelButton = new JButton("Cancel Operation");
        statusLabel = new JLabel("Ready - Please select a refreshment point to delete");
        // Set up button actions
        setupButtonActions();
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        // Create south panel for buttons and status
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        southPanel.add(statusLabel, BorderLayout.SOUTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Update button state based on selection
        updateButtonState();
    }
    /**
     * Sets up the JTable to display refreshment points.
     */
    private void setupRefreshmentTable() {
        String[] columnNames = {"ID", "Name", "Location", "Type", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        refreshmentTable = new JTable(tableModel);
        refreshmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshmentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateButtonState();
            }
        });
        refreshmentTable.setRowHeight(25);
        // Load data into table
        loadRefreshmentPoints();
    }
    /**
     * Loads refreshment points from DAO into the table.
     */
    private void loadRefreshmentPoints() {
        // Clear existing rows
        tableModel.setRowCount(0);
        List<RefreshmentPoint> points = dao.getAllRefreshmentPoints();
        for (RefreshmentPoint point : points) {
            Object[] rowData = {
                point.getId(),
                point.getName(),
                point.getLocation(),
                point.getType(),
                point.getDescription()
            };
            tableModel.addRow(rowData);
        }
        statusLabel.setText("Loaded " + points.size() + " refreshment points from system");
    }
    /**
     * Sets up action listeners for buttons.
     */
    private void setupButtonActions() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedPoint();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Operator Agency cancels the operation
                statusLabel.setText("Operation cancelled by operator");
                refreshmentTable.clearSelection();
                updateButtonState();
            }
        });
    }
    /**
     * Updates the state of the delete button based on table selection.
     */
    private void updateButtonState() {
        int selectedRow = refreshmentTable.getSelectedRow();
        deleteButton.setEnabled(selectedRow != -1);
        if (selectedRow != -1) {
            statusLabel.setText("Point selected. Click 'Delete Selected Point' to proceed");
        } else {
            statusLabel.setText("Please select a refreshment point to delete");
        }
    }
    /**
     * Handles the deletion of the selected refreshment point.
     * This simulates the flow of events described in the use case.
     */
    private void deleteSelectedPoint() {
        int selectedRow = refreshmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a refreshment point to delete.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get selected refreshment point details
        int pointId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String pointName = (String) tableModel.getValueAt(selectedRow, 1);
        // Step 2: Ask for confirmation of the transaction
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the refreshment point:\n" +
            pointName + " (ID: " + pointId + ")?\n\n" +
            "This action cannot be undone.",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        // Step 3: Confirm the deletion (user clicked Yes)
        if (confirmation == JOptionPane.YES_OPTION) {
            // Check server connection before proceeding (for ETOUR interruption scenario)
            if (!dao.checkServerConnection()) {
                JOptionPane.showMessageDialog(this,
                    "Error: Cannot connect to server. Please check your ETOUR connection and try again.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Deletion failed: Server connection interrupted");
                return;
            }
            try {
                // Step 4: Delete the selected point of rest
                boolean success = dao.deleteRefreshmentPoint(pointId);
                if (success) {
                    // Exit condition: Notify successful elimination
                    JOptionPane.showMessageDialog(this,
                        "Refreshment point '" + pointName + "' has been successfully deleted from the system.",
                        "Deletion Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the table to show updated list
                    loadRefreshmentPoints();
                    statusLabel.setText("Refreshment point deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Error: Could not find the refreshment point to delete.",
                        "Deletion Failed",
                        JOptionPane.ERROR_MESSAGE);
                    statusLabel.setText("Deletion failed: Point not found in system");
                }
            } catch (Exception ex) {
                // Handle any unexpected errors
                JOptionPane.showMessageDialog(this,
                    "An unexpected error occurred: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Deletion failed: System error occurred");
                ex.printStackTrace();
            }
        } else {
            // User cancelled the deletion
            statusLabel.setText("Deletion cancelled by operator");
        }
    }
}