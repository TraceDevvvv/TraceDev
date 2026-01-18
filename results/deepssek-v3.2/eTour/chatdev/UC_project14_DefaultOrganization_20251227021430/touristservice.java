import java.util.ArrayList;
import java.util.List;
/**
 * Service layer for tourist operations
 * Implements the business logic for searching tourists
 */
public class TouristService {
    private ETOURConnectionManager connectionManager;
    public TouristService(ETOURConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    /**
     * Searches for tourists based on criteria
     * @param criteria Search criteria from the form
     * @return List of tourists matching the criteria
     * @throws ConnectionInterruptedException if connection to ETOUR is lost
     */
    public List<Tourist> searchTourists(SearchCriteria criteria) throws ConnectionInterruptedException {
        // Validate connection
        if (!connectionManager.isConnected()) {
            throw new ConnectionInterruptedException("Not connected to ETOUR server. Please reconnect.");
        }
        List<Tourist> tourists = new ArrayList<>();
        try {
            // Build query based on criteria
            String query = buildQuery(criteria);
            // Execute query through connection manager
            String result = connectionManager.executeQuery(query);
            System.out.println("Query executed: " + result);
            // In a real application, this would parse the result from ETOUR server
            // For demonstration, we create mock data
            tourists = getMockTourists(criteria);
        } catch (ConnectionInterruptedException e) {
            // Log the interruption
            System.err.println("Connection interrupted during search: " + e.getMessage());
            // Attempt to reconnect
            try {
                connectionManager.reconnect();
                throw new ConnectionInterruptedException("Search failed due to connection interruption. Please try again.");
            } catch (ConnectionInterruptedException re) {
                throw new ConnectionInterruptedException("Search failed and reconnection failed: " + re.getMessage());
            }
        }
        return tourists;
    }
    /**
     * Builds SQL-like query from search criteria
     */
    private String buildQuery(SearchCriteria criteria) {
        StringBuilder query = new StringBuilder("SELECT * FROM tourists WHERE 1=1");
        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            query.append(" AND name LIKE '%").append(criteria.getName()).append("%'");
        }
        if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
            query.append(" AND email = '").append(criteria.getEmail()).append("'");
        }
        if (criteria.getTouristId() != null && !criteria.getTouristId().trim().isEmpty()) {
            query.append(" AND id = '").append(criteria.getTouristId()).append("'");
        }
        return query.toString();
    }
    /**
     * Provides mock tourist data for demonstration
     * In a real application, this would retrieve data from the ETOUR database
     */
    private List<Tourist> getMockTourists(SearchCriteria criteria) {
        List<Tourist> mockTourists = new ArrayList<>();
        // Create some sample tourists
        mockTourists.add(new Tourist("T001", "John Smith", "john.smith@example.com", "+1-555-0101", "USA"));
        mockTourists.add(new Tourist("T002", "Maria Garcia", "maria.garcia@example.com", "+34-555-0202", "Spain"));
        mockTourists.add(new Tourist("T003", "Chen Wei", "chen.wei@example.com", "+86-555-0303", "China"));
        mockTourists.add(new Tourist("T004", "Anna Schmidt", "anna.schmidt@example.com", "+49-555-0404", "Germany"));
        mockTourists.add(new Tourist("T005", "David Brown", "david.brown@example.com", "+44-555-0505", "UK"));
        // Filter based on criteria if provided
        if (!criteria.isEmpty()) {
            List<Tourist> filtered = new ArrayList<>();
            for (Tourist tourist : mockTourists) {
                if (matchesCriteria(tourist, criteria)) {
                    filtered.add(tourist);
                }
            }
            return filtered;
        }
        return mockTourists;
    }
    /**
     * Checks if a tourist matches the search criteria
     */
    private boolean matchesCriteria(Tourist tourist, SearchCriteria criteria) {
        boolean matches = true;
        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            matches &= tourist.getName().toLowerCase().contains(criteria.getName().toLowerCase());
        }
        if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
            matches &= tourist.getEmail().equalsIgnoreCase(criteria.getEmail());
        }
        if (criteria.getTouristId() != null && !criteria.getTouristId().trim().isEmpty()) {
            matches &= tourist.getId().equalsIgnoreCase(criteria.getTouristId());
        }
        return matches;
    }
}