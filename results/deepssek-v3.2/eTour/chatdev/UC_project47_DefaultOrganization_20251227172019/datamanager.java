/**
 * Manages tourist data storage and retrieval.
 * Simulates interaction with a database or external service (ETOUR).
 */
import java.util.HashMap;
import java.util.Map;
public class DataManager {
    // In-memory storage for demonstration (replace with actual database in production)
    private Map<String, Tourist> touristDatabase;
    public DataManager() {
        touristDatabase = new HashMap<>();
        // Initialize with some sample data for testing
        initializeSampleData();
    }
    /**
     * Loads tourist data for the given username.
     * @param username The username to look up
     * @return Tourist object if found, null otherwise
     * @throws RuntimeException if server connection fails
     */
    public Tourist loadTouristData(String username) {
        // Simulate server connection check
        if (!checkServerConnection()) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Simulate network delay
        simulateNetworkDelay();
        return touristDatabase.get(username);
    }
    /**
     * Saves modified tourist data.
     * @param tourist The updated tourist object
     * @return true if save was successful, false otherwise
     * @throws RuntimeException if server connection fails during save
     */
    public boolean saveTouristData(Tourist tourist) {
        // Simulate server connection check
        if (!checkServerConnection()) {
            throw new RuntimeException("Connection to server ETOUR interrupted during save");
        }
        // Simulate network delay
        simulateNetworkDelay();
        // Update the tourist in the database
        touristDatabase.put(tourist.getUsername(), tourist);
        // In a real implementation, this would return success/failure from the persistence layer
        return true;
    }
    /**
     * Checks if the server connection is available.
     * @return true if connection is available, false otherwise
     */
    private boolean checkServerConnection() {
        // Simulate occasional server unavailability (10% chance)
        return Math.random() > 0.1;
    }
    /**
     * Simulates network latency.
     */
    private void simulateNetworkDelay() {
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Initializes the database with sample tourist data for testing.
     */
    private void initializeSampleData() {
        touristDatabase.put("jdoe", new Tourist("jdoe", "John", "Doe", 
            "john.doe@example.com", "+1234567890", "123 Main St, City"));
        touristDatabase.put("asmith", new Tourist("asmith", "Alice", "Smith", 
            "alice.smith@example.com", "+1987654321", "456 Oak Ave, Town"));
        touristDatabase.put("bwilson", new Tourist("bwilson", "Bob", "Wilson", 
            "bob.wilson@example.com", "+1555123456", "789 Pine Rd, Village"));
    }
}