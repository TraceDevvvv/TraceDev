/**
 * SearchSite Use Case Implementation
 * Retrieves site feedback data for a specific location
 * This implements the SearchSite use case mentioned in Step 4 of the specification
 */
import java.util.List;
public class SearchSite {
    private MockDataService dataService;
    public SearchSite() {
        dataService = new MockDataService();
    }
    /**
     * Search for site feedback data for a given location
     * Implements the SearchSite use case activation
     * @param locationName The name of the location to search for
     * @return List of site feedback data
     * @throws Exception If connection is interrupted or other errors occur
     */
    public List<SiteFeedback> searchForSiteFeedback(String locationName) throws Exception {
        // Validate input
        if (locationName == null || locationName.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty");
        }
        // Specific implementation of SearchSite use case
        try {
            // Simulate network connection and search processing
            Thread.sleep(300);
            // Perform the search through the data service
            return dataService.getSiteFeedbackForLocation(locationName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Search interrupted while retrieving site feedback");
        } catch (Exception e) {
            // Provide more user-friendly error messages
            if (e.getMessage() != null && e.getMessage().contains("Connection to server interrupted")) {
                throw new Exception("Unable to retrieve data due to network issues. Please check your connection and try again.");
            }
            throw new Exception("Error searching for site feedback: " + e.getMessage());
        }
    }
}