/*
The main application class for the RICERCASITO use case.
This class sets up the main JFrame, integrates the SearchPanel and ResultsPanel,
and orchestrates the search logic using the SiteSearchService.
It handles user input, displays results, and manages error conditions
including simulated connection interruptions.
*/
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class SiteSearchApp extends JFrame {
    // Panel for search input and button
    private SearchPanel searchPanel;
    // Panel for displaying search results or errors
    private ResultsPanel resultsPanel;
    // Service to simulate site searching logic
    private SiteSearchService searchService;
    /**
     * Constructor for the SiteSearchApp.
     * Initializes the GUI components, sets up the layout, and adds event listeners.
     */
    public SiteSearchApp() {
        super("RICERCASITO - Site Search Application"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setSize(600, 500); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize the service
        searchService = new SiteSearchService();
        // Initialize GUI components
        searchPanel = new SearchPanel();
        resultsPanel = new ResultsPanel();
        // Add action listener to the search button within the SearchPanel
        searchPanel.addSearchButtonListener(e -> performSearch());
        // Set up the main frame's layout
        setLayout(new BorderLayout());
        // Add panels to the frame
        add(searchPanel, BorderLayout.NORTH); // Search input at the top
        add(resultsPanel, BorderLayout.CENTER); // Results in the center
        setVisible(true); // Make the frame visible
    }
    /**
     * Initiates the search operation.
     * This method is called when the search button is clicked.
     * It retrieves the query, disables controls, and uses a SwingWorker
     * to perform the search in a background thread to prevent UI freezing.
     */
    private void performSearch() {
        String query = searchPanel.getSearchQuery();
        resultsPanel.clearResults(); // Clear any previous results or errors
        searchPanel.setControlsEnabled(false); // Disable controls during search
        // Check if the search query is empty after getSearchQuery() processes it
        if (query.isEmpty()) { 
            resultsPanel.displayError("Search query cannot be empty.");
            searchPanel.setControlsEnabled(true); // Re-enable controls
            return;
        }
        // Use SwingWorker for background processing to keep the UI responsive
        // This addresses the quality requirement "return the results within a time limit set",
        // ensuring the UI doesn't freeze during the simulated delay.
        new SwingWorker<List<Site>, Void>() {
            @Override
            protected List<Site> doInBackground() throws Exception {
                // This code runs in a background thread
                try {
                    return searchService.searchSites(query);
                } catch (SiteSearchService.ConnectionInterruptionException e) {
                    // Re-throw as a generic Exception which will be caught by done()
                    throw new Exception(e.getMessage());
                }
            }
            @Override
            protected void done() {
                // This code runs on the Event Dispatch Thread (EDT)
                searchPanel.setControlsEnabled(true); // Re-enable controls after search is done
                try {
                    List<Site> results = get(); // Get the result from doInBackground
                    resultsPanel.displayResults(results); // Display found sites
                } catch (InterruptedException e) {
                    // Handle cancellation or interruption
                    resultsPanel.displayError("Search operation was interrupted.");
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                } catch (java.util.concurrent.ExecutionException e) {
                    // Handle exceptions thrown from doInBackground
                    Throwable cause = e.getCause();
                    if (cause instanceof SiteSearchService.ConnectionInterruptionException) {
                        // Display specific error for connection issues
                        resultsPanel.displayError(cause.getMessage());
                    } else {
                        // Display generic error for other issues
                        resultsPanel.displayError("An unexpected error occurred during search: " + cause.getMessage());
                    }
                }
            }
        }.execute(); // Execute the SwingWorker
    }
    /**
     * Main method to start the application.
     * Ensures that GUI creation and updates are performed on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(SiteSearchApp::new);
    }
}