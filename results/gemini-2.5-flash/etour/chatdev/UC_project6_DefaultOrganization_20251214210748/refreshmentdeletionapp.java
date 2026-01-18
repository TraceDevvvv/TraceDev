/**
 * This is the main application class for the ELIMINAPUNTODIRISTORO use case.
 * It provides a graphical user interface (GUI) for an Agency Operator to
 * view a list of refreshment points, select one, and delete it after confirmation.
 * It handles interactions with the RefreshmentService and provides user feedback.
 */
package com.chatdev.app;
import com.chatdev.model.RefreshmentPoint;
import com.chatdev.service.RefreshmentService;
import com.chatdev.service.ServiceException;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * This is the main application class for the ELIMINAPUNTODIRISTORO use case.
 * It provides a graphical user interface (GUI) for an Agency Operator to
 * view a list of refreshment points, select one, and delete it after confirmation.
 * It handles interactions with the RefreshmentService and provides user feedback.
 */
public class RefreshmentDeletionApp extends JFrame {
    private RefreshmentService refreshmentService;
    private JList<RefreshmentPoint> refreshmentPointJList;
    private DefaultListModel<RefreshmentPoint> listModel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    /**
     * Constructs the main application window.
     * Initializes the service, sets up the GUI components, and loads initial data.
     */
    public RefreshmentDeletionApp() {
        super("Delete Refreshment Point - ETOUR System");
        refreshmentService = new RefreshmentService();
        setupGUI();
        loadRefreshmentPoints();
    }
    /**
     * Sets up all the graphical user interface components for the application.
     */
    private void setupGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(600, 400); // Window size
        setLocationRelativeTo(null); // Center the window
        // Set layout manager for the frame
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("<html><h2>Delete a Refreshment from the System</h2></html>"));
        add(titlePanel, BorderLayout.NORTH);
        // Main content panel for the list and controls
        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        // List display area
        listModel = new DefaultListModel<>();
        refreshmentPointJList = new JList<>(listModel);
        refreshmentPointJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow single selection
        // Add a scroll pane for the list
        JScrollPane scrollPane = new JScrollPane(refreshmentPointJList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center buttons with spacing
        deleteButton = new JButton("Delete Selected Refreshment Point");
        cancelButton = new JButton("Cancel Operation");
        // Add action listeners
        deleteButton.addActionListener(e -> deleteSelectedRefreshmentPoint());
        cancelButton.addActionListener(e -> handleCancelOperation());
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.CENTER);
        // Status bar area
        statusLabel = new JLabel("Welcome, Agency Operator. Please select a refreshment point to delete.", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE); // Initial status color
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Loads the list of refreshment points from the service and populates the JList.
     * This simulates getting data from 'RicercaBeneCulturale'.
     */
    private void loadRefreshmentPoints() {
        listModel.clear(); // Clear existing items
        try {
            List<RefreshmentPoint> points = refreshmentService.getAllRefreshmentPoints();
            if (points.isEmpty()) {
                statusLabel.setText("No refreshment points found in the system.");
                deleteButton.setEnabled(false); // Disable delete if no items
            } else {
                for (RefreshmentPoint point : points) {
                    listModel.addElement(point);
                }
                statusLabel.setText("Select a refreshment point and click 'Delete'.");
                deleteButton.setEnabled(true); // Enable delete if items exist
            }
        } catch (ServiceException e) {
            // Handle simulated connection interruption or other service errors
            statusLabel.setText("Error loading data: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
            deleteButton.setEnabled(false); // Can't delete if data isn't loaded
            JOptionPane.showMessageDialog(this,
                    "<html><b>Service Error:</b><br>" + e.getMessage() + "<br>Please try again later.</html>",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Handles the deletion of the selected refreshment point.
     * This involves fetching selection, confirming with the user, and calling the service.
     */
    private void deleteSelectedRefreshmentPoint() {
        RefreshmentPoint selectedPoint = refreshmentPointJList.getSelectedValue();
        // 1. Check if an item is selected
        if (selectedPoint == null) {
            statusLabel.setText("Please select a refreshment point from the list first.");
            statusLabel.setForeground(Color.ORANGE);
            JOptionPane.showMessageDialog(this,
                    "No refreshment point selected. Please select one from the list.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 2. Ask for confirmation of the transaction
        int confirmationResult = JOptionPane.showConfirmDialog(
                this,
                "<html>Are you sure you want to delete the following refreshment point?<br><br><b>" +
                        selectedPoint.getName() + " (ID: " + selectedPoint.getId() + ")</b><br>" +
                        "This action cannot be undone.</html>",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        // 3. Confirm the deletion
        if (confirmationResult == JOptionPane.YES_OPTION) {
            try {
                // 4. Deletes the selected point of rest.
                boolean deleted = refreshmentService.deleteRefreshmentPoint(selectedPoint.getId());
                if (deleted) {
                    // Update UI and notify success
                    listModel.removeElement(selectedPoint); // Remove from display list
                    statusLabel.setText("Successfully deleted refreshment point: " + selectedPoint.getName());
                    statusLabel.setForeground(Color.GREEN.darker());
                    JOptionPane.showMessageDialog(this,
                            "Refreshment point '" + selectedPoint.getName() + "' deleted successfully.",
                            "Deletion Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    // If no more items, disable delete button
                    if (listModel.isEmpty()) {
                        deleteButton.setEnabled(false);
                    }
                } else {
                    // Item not found (shouldn't happen if selected from list, but good practice)
                    statusLabel.setText("Error: Refreshment point not found or already deleted.");
                    statusLabel.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(this,
                            "The selected refreshment point could not be found or was already deleted.",
                            "Deletion Error",
                            JOptionPane.ERROR_MESSAGE);
                    loadRefreshmentPoints(); // Reload to reflect actual state
                }
            } catch (ServiceException e) {
                // Handle simulated connection interruption or other service errors during deletion
                statusLabel.setText("Deletion failed: " + e.getMessage());
                statusLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this,
                        "<html><b>Service Error during deletion:</b><br>" + e.getMessage() + "<br>Please check connection and try again.</html>",
                        "Deletion Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Operator Agency cancels the operation.
            statusLabel.setText("Deletion cancelled by operator.");
            statusLabel.setForeground(Color.BLUE);
        }
    }
    /**
     * Handles the cancellation of the entire operation by the operator.
     */
    private void handleCancelOperation() {
        statusLabel.setText("Operation cancelled. No action taken.");
        statusLabel.setForeground(Color.BLUE);
        JOptionPane.showMessageDialog(this,
                "The deletion operation has been cancelled.",
                "Operation Cancelled",
                JOptionPane.INFORMATION_MESSAGE);
        // Optionally, reset selection or close window
        refreshmentPointJList.clearSelection();
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Set native look and feel for better integration with OS
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Fallback to default Swing LAF if system LAF fails
                System.err.println("Could not set system look and feel: " + e.getMessage());
            }
            new RefreshmentDeletionApp().setVisible(true);
        });
    }
}