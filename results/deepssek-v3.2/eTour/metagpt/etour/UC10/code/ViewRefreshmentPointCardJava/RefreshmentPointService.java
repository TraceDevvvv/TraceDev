/**
 * RefreshmentPointService.java
 * Service class that handles business logic and simulated server connection for refreshment points.
 * Simulates data retrieval, network delays, and potential connection interruptions.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RefreshmentPointService {
    // Simulated database of refreshment points
    private Map<Integer, RefreshmentPoint> refreshmentPointDatabase;
    private Random random;
    private boolean serverConnected;
    
    /**
     * Constructor initializes the service with simulated data.
     * Simulates server connection status.
     */
    public RefreshmentPointService() {
        this.random = new Random();
        this.serverConnected = true; // Initially connected
        initializeDatabase();
    }
    
    /**
     * Initializes the simulated database with sample refreshment points.
     * This simulates data that would normally come from an external server/database.
     */
    private void initializeDatabase() {
        refreshmentPointDatabase = new HashMap<>();
        
        // Create sample refreshment points
        refreshmentPointDatabase.put(1, new RefreshmentPoint(
            1, "Sunset Cafe", "A cozy cafe with outdoor seating and river view.",
            "123 Riverside Drive", "Seattle", "Cafe", 4.5, 2,
            "+1-206-555-0187", "Mon-Fri: 7am-9pm, Sat-Sun: 8am-10pm",
            true, true
        ));
        
        refreshmentPointDatabase.put(2, new RefreshmentPoint(
            2, "Mountain View Restaurant", "Fine dining with panoramic mountain views.",
            "456 Alpine Road", "Denver", "Restaurant", 4.8, 4,
            "+1-303-555-0142", "Tue-Sun: 5pm-11pm",
            true, true
        ));
        
        refreshmentPointDatabase.put(3, new RefreshmentPoint(
            3, "Central Park Bistro", "Casual bistro near the park, perfect for quick lunches.",
            "789 Park Avenue", "New York", "Bistro", 4.2, 3,
            "+1-212-555-0129", "Daily: 11am-10pm",
            true, false
        ));
        
        refreshmentPointDatabase.put(4, new RefreshmentPoint(
            4, "Ocean Side Grill", "Fresh seafood with oceanfront patio seating.",
            "321 Coastal Highway", "Miami", "Seafood Restaurant", 4.6, 3,
            "+1-305-555-0195", "Mon-Sun: 12pm-11pm",
            true, true
        ));
        
        refreshmentPointDatabase.put(5, new RefreshmentPoint(
            5, "Downtown Express", "Quick service coffee and sandwiches for busy professionals.",
            "555 Business Center", "Chicago", "Coffee Shop", 3.9, 1,
            "+1-312-555-0163", "Mon-Fri: 6am-7pm, Sat: 7am-5pm",
            true, false
        ));
    }
    
    /**
     * Simulates checking server connection to ETOUR server.
     * In a real application, this would check network connectivity.
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean checkServerConnection() {
        // Simulate occasional connection loss (10% chance)
        if (random.nextInt(100) < 10) {
            serverConnected = false;
        } else {
            serverConnected = true;
        }
        return serverConnected;
    }
    
    /**
     * Simulates the SearchRefreshmentPoint use case (previous step).
     * Returns a list of refreshment points based on simulated search.
     * 
     * @return List of refreshment points (search results)
     * @throws ServerConnectionException if server connection is lost
     */
    public List<RefreshmentPoint> searchRefreshmentPoints() throws ServerConnectionException {
        System.out.println("Searching refreshment points... (simulating network request)");
        
        // Simulate network delay
        simulateNetworkDelay(1000, 2000);
        
        // Check server connection
        if (!checkServerConnection()) {
            throw new ServerConnectionException("Connection to ETOUR server lost during search.");
        }
        
        // Return all refreshment points as search results
        return new ArrayList<>(refreshmentPointDatabase.values());
    }
    
    /**
     * Retrieves a specific refreshment point by its ID.
     * Simulates the "Upload data to a selected restaurant" step.
     * 
     * @param id The ID of the refreshment point to retrieve
     * @return The RefreshmentPoint object if found
     * @throws ServerConnectionException if server connection is lost
     * @throws IllegalArgumentException if ID is not found
     */
    public RefreshmentPoint getRefreshmentPointById(int id) throws ServerConnectionException {
        System.out.println("Retrieving refreshment point details for ID: " + id + "...");
        
        // Simulate network delay (slightly longer for detailed data)
        simulateNetworkDelay(1500, 2500);
        
        // Check server connection
        if (!checkServerConnection()) {
            throw new ServerConnectionException("Connection to ETOUR server lost while retrieving details.");
        }
        
        // Check if ID exists
        if (!refreshmentPointDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Refreshment point with ID " + id + " not found.");
        }
        
        return refreshmentPointDatabase.get(id);
    }
    
    /**
     * Simulates network delay to make the application feel more realistic.
     * 
     * @param minDelay Minimum delay in milliseconds
     * @param maxDelay Maximum delay in milliseconds
     */
    private void simulateNetworkDelay(int minDelay, int maxDelay) {
        try {
            int delay = minDelay + random.nextInt(maxDelay - minDelay + 1);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Network delay simulation interrupted.");
        }
    }
    
    /**
     * Manually sets the server connection status (for testing).
     * 
     * @param connected Desired connection status
     */
    public void setServerConnection(boolean connected) {
        this.serverConnected = connected;
    }
    
    /**
     * Gets the current server connection status.
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    
    /**
     * ServerConnectionException - Custom exception for server connection issues.
     * Represents the "Interruption of the connection to the server ETOUR" scenario.
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
        
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}