'''
Simulates a central database for managing Tourist accounts.
This class acts as a singleton to ensure a single instance of the database
throughout the application. It includes functionality to add, retrieve,
and delete tourist accounts, and can simulate server connection issues.
'''
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Simulates a central database for managing Tourist accounts.
 * This class acts as a singleton to ensure a single instance of the database
 * throughout the application. It includes functionality to add, retrieve,
 * and delete tourist accounts, and can simulate server connection issues.
 */
public class TouristDatabase {
    private static TouristDatabase instance;
    // Changed from List<Tourist> to Map<String, Tourist> for efficient lookup and deletion by ID
    private Map<String, Tourist> tourists; 
    private Random random; // For simulating server connection issues
    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the in-memory map of tourists with some sample data.
     */
    private TouristDatabase() {
        tourists = new HashMap<>();
        random = new Random();
        // Populate with some dummy data for demonstration
        tourists.put("T001", new Tourist("T001", "Alice Smith"));
        tourists.put("T002", new Tourist("T002", "Bob Johnson"));
        tourists.put("T003", new Tourist("T003", "Charlie Brown"));
        tourists.put("T004", new Tourist("T004", "Diana Prince"));
    }
    /**
     * Provides the global access point to the TouristDatabase instance.
     * @return The singleton instance of TouristDatabase.
     */
    public static synchronized TouristDatabase getInstance() {
        if (instance == null) {
            instance = new TouristDatabase();
        }
        return instance;
    }
    /**
     * Retrieves all tourist accounts currently in the database.
     * This method can simulate a server connection interruption by throwing a ServerConnectionException.
     * @return A list of Tourist objects.
     * @throws ServerConnectionException If a simulated server connection error occurs.
     */
    public List<Tourist> getTourists() throws ServerConnectionException {
        // Simulate potential connection interruption
        if (random.nextInt(100) < 5) { // 5% chance of connection error for listing
            throw new ServerConnectionException("ETOUR server connection interrupted during data retrieval.");
        }
        // Return a new ArrayList containing all Tourist objects from the HashMap values
        // This ensures the caller gets a mutable list and protects the internal map.
        return new ArrayList<>(tourists.values()); 
    }
    /**
     * Deletes a tourist account based on their ID.
     * This method can simulate a server connection interruption.
     * @param touristId The ID of the tourist to delete.
     * @return true if the tourist was successfully found and deleted, false otherwise.
     * @throws ServerConnectionException If a simulated server connection error occurs during deletion.
     */
    public boolean deleteTouristAccount(String touristId) throws ServerConnectionException {
        // Simulate potential connection interruption
        if (random.nextInt(100) < 15) { // 15% chance of connection error for deletion
            throw new ServerConnectionException("ETOUR server connection interrupted during account deletion.");
        }
        // Use HashMap's remove method which provides O(1) average time complexity for deletion by key.
        // It returns the value previously associated with the key, or null if the key was not found.
        return tourists.remove(touristId) != null; 
    }
    /**
     * Adds a new tourist account to the database.
     * This method is primarily for internal setup or future extensions.
     * @param tourist The Tourist object to add. The tourist's ID is used as the key.
     */
    public void addTourist(Tourist tourist) {
        // Use HashMap's put method to add a new tourist, using their ID as the key.
        this.tourists.put(tourist.getId(), tourist);
    }
}