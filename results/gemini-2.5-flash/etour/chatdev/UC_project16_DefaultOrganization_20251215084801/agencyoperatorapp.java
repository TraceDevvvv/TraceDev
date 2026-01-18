'''
Main application class for the Agency Operator.
This GUI allows an operator to view a list of tourist accounts
and delete selected accounts from the system.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Main application class for the Agency Operator.
 * This GUI allows an operator to view a list of tourist accounts
 * and delete selected accounts from the system.
 */
public class AgencyOperatorApp extends JFrame {
    private TouristDatabase database;
    private DefaultListModel<Tourist> touristListModel;
    private JList<Tourist> touristJList;
    private JButton refreshButton;
    private JButton deleteButton;
    private JLabel statusLabel;
    /**
     * Constructor for the AgencyOperatorApp GUI.
     * Initializes the JFrame, its components, layout, and event listeners.
     */
    public AgencyOperatorApp() {
        super("Tourist Account Management - Agency Operator"); // Set window title
        // Initialize database instance (singleton pattern ensures only one instance)
        database = TouristDatabase.getInstance();
        // Set up the main window properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        // Use BorderLayout for the main frame to arrange components
        setLayout(new BorderLayout(10, 10)); // 10-pixel vertical and horizontal gap between components
        // --- North Panel: Status Label ---
        statusLabel = new JLabel("Welcome, Agency Operator. Please refresh to load tourists.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text within the label
        add(statusLabel, BorderLayout.NORTH);
        // --- Center Panel: Tourist List ---
        touristListModel = new DefaultListModel<>(); // Model to hold Tourist objects for the JList
        touristJList = new JList<>(touristListModel); // JList displays the tourists
        touristJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one item can be selected at a time
        JScrollPane scrollPane = new JScrollPane(touristJList); // Add scroll capability if list is long
        add(scrollPane, BorderLayout.CENTER);
        // --- South Panel: Buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered buttons with 10-pixel gaps
        refreshButton = new JButton("Refresh Tourists (RicercaTurista)");
        deleteButton = new JButton("Delete Selected Tourist");
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // --- Add Action Listeners ---
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTourists(); // Call method to load/refresh the tourist list
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTourist(); // Call method to delete the selected tourist
            }
        });
        // Initial setup for the delete button and list.
        // The delete button is initially disabled because no tourist is selected.
        deleteButton.setEnabled(false); 
        // Add a listener to the JList to enable/disable the delete button based on selection.
        touristJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // event fires twice, 'getValueIsAdjusting' checks if selection is settled
                deleteButton.setEnabled(touristJList.getSelectedIndex() != -1); // Enable if an item is selected
            }
        });
        // Load tourists upon application startup
        loadTourists(); 
        // Make the frame visible after all components are set up
        setVisible(true);
    }
    /**
     * Loads or reloads the list of tourists from the database and updates the JList.
     * This method simulates the "RicercaTurista" use case, retrieving the list of tourists.
     * It handles potential ServerConnectionException during data retrieval.
     */
    private void loadTourists() {
        touristListModel.clear(); // Clear existing entries in the list model
        try {
            List<Tourist> tourists = database.getTourists(); // Retrieve list of tourists from the database
            // Check if any tourist accounts were found
            if (tourists.isEmpty()) {
                statusLabel.setText("No tourist accounts found.");
            } else {
                // Add each retrieved tourist to the list model for display
                for (Tourist t : tourists) {
                    touristListModel.addElement(t);
                }
                statusLabel.setText("Tourist list loaded successfully. Select an account to delete.");
            }
        } catch (ServerConnectionException e) {
            // Handle connection interruption during data retrieval as per use case exit conditions.
            statusLabel.setText("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Failed to retrieve tourist data due to server connection issues.\nPlease try again later.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure delete button state is updated, especially important if an error cleared the list.
            deleteButton.setEnabled(touristJList.getSelectedIndex() != -1);
        }
    }
    /**
     * Handles the deletion of the currently selected tourist account.
     * This implements the core logic of the "ELIMINAACCOUNTTURISTA" use case,
     * including user confirmation and handling database operations and errors.
     */
    private void deleteSelectedTourist() {
        int selectedIndex = touristJList.getSelectedIndex();
        if (selectedIndex == -1) {
            // If no tourist is selected, inform the user and abort.
            JOptionPane.showMessageDialog(this,
                    "Please select a tourist account to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Tourist selectedTourist = touristListModel.getElementAt(selectedIndex);
        // Step 2: Asks for confirmation of the transaction.
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the account for: " + selectedTourist.getName() + " (ID: " + selectedTourist.getId() + ")?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        // Step 3: Confirm the operation.
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Step 4: Delete the selected data from the database.
                boolean deleted = database.deleteTouristAccount(selectedTourist.getId());
                if (deleted) {
                    // Exit condition: Notification system has been elimination of selected accounts Turista.
                    JOptionPane.showMessageDialog(this,
                            "Tourist account for " + selectedTourist.getName() + " (ID: " + selectedTourist.getId() + ") has been successfully deleted.",
                            "Deletion Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    statusLabel.setText("Account " + selectedTourist.getId() + " deleted successfully.");
                    loadTourists(); // Reload list to reflect the deletion
                } else {
                    // This case might occur if the tourist was somehow removed by another process
                    // between loading the list and attempting deletion, or if the ID was invalid.
                    JOptionPane.showMessageDialog(this,
                            "Failed to delete tourist account for " + selectedTourist.getName() + ". Account not found or already deleted.",
                            "Deletion Failed",
                            JOptionPane.ERROR_MESSAGE);
                    statusLabel.setText("Account " + selectedTourist.getId() + " not found or failed to delete.");
                    loadTourists(); // Refresh list in case it's out of sync
                }
            } catch (ServerConnectionException e) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                JOptionPane.showMessageDialog(this,
                        "Failed to delete tourist account due to server connection issues.\n" + e.getMessage(),
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Error: " + e.getMessage());
            }
        } else {
            // User chose not to confirm the deletion.
            statusLabel.setText("Deletion cancelled by operator.");
        }
    }
    /**
     * Main method to start the AgencyOperatorApp GUI application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * to prevent concurrency issues with Swing components.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgencyOperatorApp();
            }
        });
    }
}