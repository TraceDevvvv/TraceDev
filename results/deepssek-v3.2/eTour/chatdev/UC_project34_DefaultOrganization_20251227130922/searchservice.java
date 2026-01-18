'''
SearchService.java
Service class that handles business logic for searching cultural heritage sites.
Simulates connection to ETOUR server with interruption handling.
'''
package culturalheritage.search;
import java.util.ArrayList;
import java.util.List;
public class SearchService {
    private MockServer mockServer;
    public SearchService() {
        mockServer = new MockServer();
    }
    /**
     * Search for cultural heritage sites based on criteria
     * @param keyword Search keyword
     * @param category Site category
     * @param location Location preference
     * @return List of matching sites
     * @throws ServerConnectionException if connection to ETOUR server fails
     * @throws IllegalArgumentException if keyword is empty
     */
    public List<Site> searchSites(String keyword, String category, String location) throws ServerConnectionException, IllegalArgumentException {
        // Validate input
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty");
        }
        try {
            // Simulate step 4: Get position of Guest User and process research
            // In a real application, this would get actual user location
            String guestLocation = "Worldwide"; // Default location
            // Connect to server (simulated)
            if (!mockServer.connect()) {
                throw new ServerConnectionException("Connection to ETOUR server was interrupted.");
            }
            // Get all sites from server
            List<Site> allSites = mockServer.getAllSites();
            // Filter sites based on criteria
            List<Site> filteredSites = new ArrayList<>();
            for (Site site : allSites) {
                if (matchesCriteria(site, keyword, category, location, guestLocation)) {
                    filteredSites.add(site);
                }
            }
            // Sort by relevance (simplified - in reality would use more complex algorithm)
            filteredSites.sort((s1, s2) -> {
                // Sort by rating then by visitor count
                int ratingCompare = Double.compare(s2.getRating(), s1.getRating());
                if (ratingCompare != 0) return ratingCompare;
                return Integer.compare(s2.getVisitors(), s1.getVisitors());
            });
            return filteredSites;
        } catch (InterruptedException e) {
            // Thread interruption during server connection
            throw new ServerConnectionException("Connection to ETOUR server was interrupted.", e);
        } catch (IllegalStateException e) {
            // Server not connected when trying to get sites
            throw new ServerConnectionException("Cannot access ETOUR server. Connection lost.", e);
        } catch (Exception e) {
            // Wrap other exceptions
            throw new RuntimeException("An unexpected error occurred during search.", e);
        } finally {
            // Ensure connection is closed
            mockServer.disconnect();
        }
    }
    /**
     * Check if a site matches all search criteria
     */
    private boolean matchesCriteria(Site site, String keyword, String category, 
                                   String location, String guestLocation) {
        // Check keyword match in name or description
        boolean keywordMatch = site.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                              site.getDescription().toLowerCase().contains(keyword.toLowerCase());
        if (!keywordMatch) return false;
        // Check category (skip if "All Categories")
        if (!category.equals("All Categories") && !site.getCategory().equals(category)) {
            return false;
        }
        // Check location if specified
        if (location != null && !location.isEmpty()) {
            if (!site.getLocation().toLowerCase().contains(location.toLowerCase())) {
                return false;
            }
        }
        // Additional relevance: prioritize sites near guest location
        // For demo, we just ensure the site is available
        return site.isAvailable();
    }
}