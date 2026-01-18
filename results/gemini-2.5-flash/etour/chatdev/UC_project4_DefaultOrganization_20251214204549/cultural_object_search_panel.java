'''
This class represents the main panel for searching cultural objects.
It includes input fields for search parameters, a search button, and displays results in a table.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * A JPanel that provides a user interface for searching cultural objects.
 * It includes input fields for various search criteria, a search button,
 * and a JTable to display the search results.
 */
public class CulturalObjectSearchPanel extends JPanel {
    // UI Components for search criteria
    private JTextField nameField;
    private JTextField typeField;
    private JTextField locationField;
    private JButton searchButton;
    // UI Component for displaying results
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    // Repository to handle cultural object data
    private CulturalObjectRepository repository;
    /**
     * Constructor for CulturalObjectSearchPanel.
     * Initializes the UI components and sets up event listeners.
     */
    public CulturalObjectSearchPanel() {
        // Use GridBagLayout for flexible component arrangement
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        // Initialize the repository with dummy data
        repository = new CulturalObjectRepository();
        // Initialize and lay out the UI components
        initUI();
    }
    /**
     * Initializes all the UI components (labels, text fields, buttons, table)
     * and arranges them using GridBagLayout.
     */
    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontal space
        // === Search Criteria Input Fields ===
        // Label and field for Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);
        // Label and field for Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        typeField = new JTextField(20);
        add(typeField, gbc);
        // Label and field for Location
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationField = new JTextField(20);
        add(locationField, gbc);
        // === Search Button ===
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST; // Align button to the right
        searchButton = new JButton("Search Cultural Objects");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, perform the search
                performSearch();
            }
        });
        add(searchButton, gbc);
        // === Results Table ===
        // Column names for the results table
        String[] columnNames = {"ID", "Name", "Type", "Location", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true); // Table fills the height of the scroll pane
        // Create a scroll pane for the table to handle many results
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Allow horizontal resizing
        gbc.weighty = 1.0; // Allow vertical resizing
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontal and vertical space
        add(scrollPane, gbc);
    }
    /**
     * Executes the search operation based on the current input in the text fields.
     * It retrieves cultural objects from the repository and updates the results table.
     * This method runs the search in a background thread using SwingWorker to keep the UI responsive.
     */
    private void performSearch() {
        // Collect search parameters from input fields.
        String name = nameField.getText().trim();
        String type = typeField.getText().trim();
        String location = locationField.getText().trim();
        // Clear previous results immediately.
        tableModel.setRowCount(0);
        // Disable the search button to prevent multiple concurrent searches and indicate ongoing process.
        searchButton.setEnabled(false);
        // Use SwingWorker to perform the search in a background thread,
        // preventing the UI from freezing.
        new SwingWorker<List<CulturalObject>, Void>() {
            private Exception thrownException = null; // Variable to capture exceptions from background task
            /**
             * This method runs in a background thread.
             * It simulates fetching data from a repository and handles potential connection errors.
             *
             * @return A list of CulturalObject matching the search criteria.
             */
            @Override
            protected List<CulturalObject> doInBackground() { // Removed 'throws Exception' because we're catching internally.
                try {
                    // Call the repository to get the filtered list of cultural objects.
                    // The repository itself is now responsible for simulating and throwing connection errors.
                    return repository.searchCulturalObjects(name, type, location);
                } catch (EtourConnectionException e) { // Catch the specific custom exception for ETour connection issues
                    // Store the specific exception to be processed in the done() method.
                    this.thrownException = e;
                    return null; // Return null as an error occurred.
                } catch (Exception e) { // Catch any other unexpected general exceptions during the background task
                    // Store any exception that occurs during the background task to be handled on the EDT.
                    this.thrownException = e;
                    return null; // Return null if an exception occurred.
                }
            }
            /**
             * This method runs on the Event Dispatch Thread (EDT) after doInBackground() completes.
             * It updates the UI with the search results or displays error messages.
             */
            @Override
            protected void done() {
                try {
                    // If an exception was caught in doInBackground, handle it here on the EDT.
                    if (thrownException != null) {
                        JOptionPane.showMessageDialog(CulturalObjectSearchPanel.this,
                                "Error: " + thrownException.getMessage() +
                                        "\nCould not retrieve data from the server. Please try again later.",
                                "Connection Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit done() method if an error occurred.
                    }
                    // Get the results from the background task.
                    List<CulturalObject> results = get(); // get() might throw ExecutionException if doInBackground failed
                    // Check if any results were found and update the table.
                    if (results.isEmpty()) {
                        JOptionPane.showMessageDialog(CulturalObjectSearchPanel.this, "No cultural objects found matching the criteria.",
                                "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Populate the table with the search results.
                        for (CulturalObject obj : results) {
                            Object[] rowData = {
                                    obj.getId(),
                                    obj.getName(),
                                    obj.getType(),
                                    obj.getLocation(),
                                    obj.getDescription()
                            };
                            tableModel.addRow(rowData);
                        }
                    }
                } catch (Exception ex) {
                    // Catch exceptions that might occur during the get() call itself
                    // (e.g., ExecutionException if doInBackground threw an unchecked exception not caught internally,
                    // or CancellationException if the task was cancelled).
                    JOptionPane.showMessageDialog(CulturalObjectSearchPanel.this,
                            "An unexpected error occurred during search: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Always re-enable the search button, regardless of success or failure.
                    searchButton.setEnabled(true);
                }
            }
        }.execute(); // Execute the SwingWorker to start the background task.
    }
}