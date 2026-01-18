/**
 * Main application class for the Cultural Heritage Search system.
 * Sets up the GUI, orchestrates user interactions, and manages search functionality.
 * It acts as the main frame, integrating the search input panel and the results display panel.
 * It also handles the "Guest User logs on" entry condition by immediately presenting the search interface
 * and manages the "ETOUR server interruption" exit condition by displaying an error.
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
public class CulturalHeritageApp extends JFrame implements SearchListener {
    private SearchPanel searchPanel;
    private ResultPanel resultPanel;
    private SearchService searchService;
    private LocationService locationService;
    /**
     * Constructor for the CulturalHeritageApp.
     * Initializes the GUI components and sets up the main application window.
     */
    public CulturalHeritageApp() {
        // Set up the main window properties
        setTitle("Cultural Heritage Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Initialize serv
        locationService = new LocationService();
        searchService = new SearchService(); // Initializes with dummy data
        // Initialize GUI panels
        searchPanel = new SearchPanel(this); // Pass this instance as the SearchListener
        resultPanel = new ResultPanel();
        // Set up the layout for the main frame
        setLayout(new BorderLayout());
        // Add panels to the frame
        add(searchPanel, BorderLayout.NORTH); // Search form at the top
        add(resultPanel, BorderLayout.CENTER); // Results in the center
        // Simulate "Guest User logs on" by making the application visible
        // In a real application, this might involve a login screen before showing this.
        setVisible(true);
        // Display initial message
        resultPanel.displayMessage("Welcome, Guest! Please enter your search criteria.");
    }
    /**
     * Callback method triggered when a search is requested from the SearchPanel.
     * This method orchestrates the search process:
     * 1. Gets the current user's location.
     * 2. Calls the SearchService to perform the research.
     * 3. Displays the results or an error message.
     *
     * @param criteria The search criteria entered by the user.
     */
    @Override
    public void onSearchRequested(SearchCriteria criteria) {
        resultPanel.displayMessage("Searching..."); // Provide immediate feedback to the user
        try {
            // Step 4: Gets the position of the Guest User and processes research.
            // Simulate getting the actual user's location (distinct from form input location)
            String userActualLocation = locationService.getCurrentUserLocation();
            System.out.println("User is currently in: " + userActualLocation); // For debugging
            // Perform the search using the criteria and actual user location
            List<CulturalSite> results = searchService.performSearch(criteria, userActualLocation);
            // Exit condition: The system displays a list of sites that meet the search criteria.
            if (results.isEmpty()) {
                resultPanel.displayMessage("No cultural sites found matching your criteria.");
            } else {
                resultPanel.displayResults(results);
            }
        } catch (ETOURConnectionException e) {
            // Exit condition: Interruption of the connection to the server ETOUR.
            resultPanel.displayError("Connection to ETOUR server interrupted: " + e.getMessage() + ". Please try again.");
            System.err.println("ETOUR Connection Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected errors during search operation
            resultPanel.displayError("An unexpected error occurred during search: " + e.getMessage());
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Main method to run the application.
     * Creates and displays the CulturalHeritageApp GUI on the Event Dispatch Thread.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(CulturalHeritageApp::new);
    }
}