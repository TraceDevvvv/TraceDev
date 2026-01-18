'''
This JPanel represents the search functionality for tourist accounts.
It contains input fields for search parameters, a search button,
and a table to display the results.
It interacts with the TouristService to perform searches.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException; // Import for ExecutionException
class TouristSearchPanel extends JPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField countryField;
    private JTextField emailField;
    private JButton searchButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private TouristService touristService;
    /**
     * Constructor for TouristSearchPanel.
     * Initializes the UI components and sets up event listeners.
     */
    public TouristSearchPanel() {
        // Initialize the service layer
        this.touristService = new TouristService();
        // Use BorderLayout for the main panel layout
        setLayout(new BorderLayout(10, 10)); // 10px horizontal and vertical gap
        // --- Search Form Panel ---
        JPanel searchFormPanel = new JPanel(new GridBagLayout());
        searchFormPanel.setBorder(BorderFactory.createTitledBorder("Search Parameters"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // First Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchFormPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(20);
        searchFormPanel.add(firstNameField, gbc);
        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchFormPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(20);
        searchFormPanel.add(lastNameField, gbc);
        // Country
        gbc.gridx = 0;
        gbc.gridy = 2;
        searchFormPanel.add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        countryField = new JTextField(20);
        searchFormPanel.add(countryField, gbc);
        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        searchFormPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        searchFormPanel.add(emailField, gbc);
        // Search Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        searchButton = new JButton("Search Tourists");
        searchFormPanel.add(searchButton, gbc);
        // Add the search form panel to the top of the main panel
        add(searchFormPanel, BorderLayout.NORTH);
        // --- Results Table Panel ---
        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Country"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true); // Fills the entire height of the scroll pane
        resultsTable.setAutoCreateRowSorter(true); // Enable sorting clicks on header
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results"));
        // Add the results table panel to the center of the main panel
        add(scrollPane, BorderLayout.CENTER);
        // --- Event Listener for Search Button ---
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }
    /**
     * Executes the search operation using the parameters from the input fields.
     * Clears previous results and updates the table with new ones.
     * Handles potential ConnectionInterruptionException.
     */
    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String country = countryField.getText().trim();
        String email = emailField.getText().trim();
        // Provide feedback that search is in progress (e.g., disable button, show loading)
        searchButton.setEnabled(false);
        searchButton.setText("Searching...");
        // Execute the search in a separate thread to prevent freezing the GUI
        // This is crucial for long-running operations like network calls
        SwingWorker<List<Tourist>, Void> worker = new SwingWorker<List<Tourist>, Void>() {
            private TouristService.ConnectionInterruptionException connectionError = null;
            @Override
            protected List<Tourist> doInBackground() throws Exception {
                try {
                    // Call the service layer to get the search results
                    return touristService.searchTourists(firstName.isEmpty() ? null : firstName,
                                                         lastName.isEmpty() ? null : lastName,
                                                         country.isEmpty() ? null : country,
                                                         email.isEmpty() ? null : email);
                } catch (TouristService.ConnectionInterruptionException ex) {
                    connectionError = ex;
                    return null; // Return null on error, will be checked in done()
                }
            }
            @Override
            protected void done() {
                // This method runs on the Event Dispatch Thread (EDT)
                searchButton.setEnabled(true);
                searchButton.setText("Search Tourists");
                // Handle server connection interruption if it occurred in doInBackground
                if (connectionError != null) {
                    JOptionPane.showMessageDialog(TouristSearchPanel.this,
                            "Error: " + connectionError.getMessage(),
                            "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    // Reset the error for the next search attempt
                    connectionError = null;
                    return; // Stop processing further for this search attempt
                }
                try {
                    List<Tourist> results = get(); // This retrieves the results or re-throws exceptions from doInBackground
                    // Previous results were cleared at the start of performSearch(), so just populate.
                    if (results != null && !results.isEmpty()) {
                        for (Tourist tourist : results) {
                            tableModel.addRow(new Object[]{
                                    tourist.getId(),
                                    tourist.getFirstName(),
                                    tourist.getLastName(),
                                    tourist.getEmail(),
                                    tourist.getCountry()
                            });
                        }
                    } else {
                        // No results found
                        JOptionPane.showMessageDialog(TouristSearchPanel.this,
                                "No tourist accounts found matching your criteria.",
                                "No Results",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (InterruptedException e) {
                    // This exception means the thread executing doInBackground was interrupted
                    JOptionPane.showMessageDialog(TouristSearchPanel.this,
                            "Search operation was interrupted.",
                            "Search Interrupted",
                            JOptionPane.WARNING_MESSAGE);
                    Thread.currentThread().interrupt(); // Restore interrupted status
                } catch (ExecutionException e) {
                    // This exception wraps any exception thrown by doInBackground()
                    Throwable cause = e.getCause(); // Get the actual exception that occurred in doInBackground
                    String errorMessage = "An unexpected error occurred during search.";
                    if (cause != null) {
                        // If there's a specific cause message, use it
                        errorMessage += ": " + (cause.getMessage() != null ? cause.getMessage() : cause.getClass().getSimpleName());
                    } else {
                        // Fallback to ExecutionException's message if cause is null
                        errorMessage += ": " + e.getMessage();
                    }
                    JOptionPane.showMessageDialog(TouristSearchPanel.this,
                            errorMessage,
                            "Search Error",
                            JOptionPane.ERROR_MESSAGE);
                    // Print stack trace for debugging purposes
                    if (cause != null) {
                        cause.printStackTrace();
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Start the SwingWorker thread
        worker.execute();
    }
}