'''
Main class for the VISUALIZZASTORICOCONVENZIONI application.
This class sets up the graphical user interface (GUI) using Swing.
It allows an Agency Operator to view the historical conventions associated with their designated point of rest.
'''
package com.chatdev.viscon; // Using a package for better organization
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Main GUI application for "VISUALIZZASTORICOCONVENZIONI".
 * Enables an Agency Operator to view the convention history for their designated PointOfRest.
 */
public class ConventionHistoryGUI extends JFrame {
    private JTable conventionTable;
    private ConventionTableModel tableModel;
    private JButton viewHistoryButton;
    private JLabel statusLabel;
    private ConventionService conventionService;
    // The designated point of rest for the operator, fulfilling the entry condition.
    // In a real application, this would be determined by user authentication or session data.
    private PointOfRest designatedPointOfRest;
    /**
     * Constructor for the ConventionHistoryGUI.
     * Initializes the service and sets up the user interface.
     */
    public ConventionHistoryGUI() {
        super("VISUALIZZASTORICOCONVENZIONI - View Convention History");
        this.conventionService = new ConventionService();
        // Simulate the operator being designated to a specific PointOfRest.
        // As per the use case's "Operator conditions", the agency *is* a designated point of rest.
        this.designatedPointOfRest = new PointOfRest("POR_A", "Restaurant A - The Great Food Place"); 
        initializeUI();
    }
    /**
     * Initializes all GUI components and lays them out in the window.
     * Sets up action listeners and default window properties.
     */
    private void initializeUI() {
        // --- Frame setup ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // --- Panel for controls (top) ---
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Padding
        // Display the designated point of rest instead of a selection combobox
        JLabel designatedLabel = new JLabel("Designated Point of Rest: " + designatedPointOfRest.getName());
        controlPanel.add(designatedLabel);
        // The button now acts as a "Refresh" button for the designated point of rest
        viewHistoryButton = new JButton("Refresh History");
        // Add action listener to the button to trigger convention loading
        viewHistoryButton.addActionListener(e -> loadConventions());
        controlPanel.add(viewHistoryButton);
        add(controlPanel, BorderLayout.NORTH);
        // --- Table for displaying conventions (center) ---
        tableModel = new ConventionTableModel();
        conventionTable = new JTable(tableModel);
        conventionTable.setFillsViewportHeight(true); // Table fills the height of the scroll pane
        // Add table to a scroll pane to ensure scrolling if content exceeds visible area
        JScrollPane scrollPane = new JScrollPane(conventionTable);
        add(scrollPane, BorderLayout.CENTER);
        // --- Status label (bottom) ---
        statusLabel = new JLabel("Ready.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Padding
        add(statusLabel, BorderLayout.SOUTH);
        // --- Initial state ---
        // Automatically load conventions for the designated point of rest upon startup
        loadConventions();
    }
    /**
     * Fetches convention data from the service for the designated PointOfRest
     * and updates the JTable. Handles potential connection errors using a SwingWorker
     * to prevent blocking the Event Dispatch Thread (EDT).
     */
    private void loadConventions() {
        // Use the designatedPointOfRest directly as per the use case requirement
        PointOfRest currentPointOfRest = this.designatedPointOfRest;
        // This null check is largely for robustness, though designatedPointOfRest should always be initialized.
        if (currentPointOfRest == null) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("System error: No designated Point of Rest found.");
            tableModel.clearData();
            return;
        }
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setText("Loading conventions for " + currentPointOfRest.getName() + "...");
        viewHistoryButton.setEnabled(false); // Disable button to prevent multiple concurrent requests
        // Use SwingWorker to perform network/long-running operations in the background thread
        SwingWorker<List<Convention>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Convention> doInBackground() throws ConnectionInterruptionException {
                // Simulate data fetching from the ConventionService
                return conventionService.getConventionsForPointOfRest(currentPointOfRest);
            }
            @Override
            protected void done() {
                try {
                    // Get the result from the background task
                    List<Convention> conventions = get();
                    // Update the table model with the fetched conventions
                    tableModel.setConventions(conventions);
                    statusLabel.setForeground(Color.BLACK);
                    statusLabel.setText("Successfully loaded " + conventions.size() + " conventions for " + currentPointOfRest.getName() + ".");
                } catch (Exception ex) {
                    // Handle potential exceptions during doInBackground, including ConnectionInterruptionException
                    statusLabel.setForeground(Color.RED);
                    String errorMessage;
                    if (ex.getCause() instanceof ConnectionInterruptionException) {
                        // Specific handling for server connection interruption
                        errorMessage = "Connection Error: " + ex.getCause().getMessage();
                    } else if (ex.getCause() != null) {
                        // General exception with a cause
                        errorMessage = "An unexpected error occurred: " + ex.getCause().getMessage();
                    } else {
                        // General exception without a specific cause
                        errorMessage = "An unexpected error occurred: " + ex.getMessage();
                    }
                    statusLabel.setText(errorMessage);
                    tableModel.clearData(); // Clear previous data on error
                    // Log the exception for detailed debugging
                    ex.printStackTrace();
                    // Display an error dialog to the user
                    JOptionPane.showMessageDialog(ConventionHistoryGUI.this,
                            errorMessage,
                            "Error Loading Conventions",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    viewHistoryButton.setEnabled(true); // Re-enable button regardless of success or failure
                }
            }
        };
        worker.execute(); // Start the background task
    }
    /**
     * Main method to run the application.
     * Ensures the GUI is created and updated on the Event Dispatch Thread (EDT)
     * for thread safety, which is standard practice in Swing applications.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI creation and updates happen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            ConventionHistoryGUI app = new ConventionHistoryGUI();
            app.setVisible(true); // Make the JFrame visible
        });
    }
}