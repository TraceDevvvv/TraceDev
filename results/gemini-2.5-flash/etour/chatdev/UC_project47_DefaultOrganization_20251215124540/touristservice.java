'''
This class simulates a service layer for managing Tourist data.
It provides methods for authentication, fetching, and updating tourist information,
and can simulate network errors.
In a real application, this would interact with a database or a remote API.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class TouristService {
    // A simple in-memory "database" to store tourist data, keyed by username
    private static final Map<String, Tourist> touristDatabase = new HashMap<>();
    // Simulate network error chance
    private static final Random random = new Random();
    private static boolean simulateConnectionInterruption = false;
    static {
        // Initialize with some dummy data
        touristDatabase.put("user1", new Tourist("T001", "user1", "user1@example.com", "John", "Doe"));
        touristDatabase.put("user2", new Tourist("T002", "user2", "user2@example.com", "Jane", "Smith"));
    }
    /**
     * Custom exception for service-related errors, e.g., network issues.
     */
    public static class TouristServiceException extends Exception {
        public TouristServiceException(String message) {
            super(message);
        }
    }
    /**
     * Attempts to authenticate a user.
     *
     * @param username The username to authenticate.
     * @param password The password (ignored for simplicity, assumes any non-empty password is fine).
     * @return true if authentication is successful; false otherwise.
     * @throws TouristServiceException If a simulated network error occurs during authentication.
     */
    public boolean authenticate(String username, String password) throws TouristServiceException {
        // Simulate network error during authentication
        if (simulateConnectionInterruption && random.nextBoolean()) { // 50% chance if enabled
            throw new TouristServiceException("Authentication failed: Connection to ETOUR server interrupted.");
        }
        // Basic authentication: check if username exists and password is not empty
        if (touristDatabase.containsKey(username) && password != null && !password.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * Fetches the data for a given tourist ID (which is the username in this simulation).
     *
     * @param touristUsername The username/ID of the tourist to fetch.
     * @return The Tourist object if found, null otherwise.
     * @throws TouristServiceException If a simulated network error occurs during data retrieval.
     */
    public Tourist getTouristData(String touristUsername) throws TouristServiceException {
        // Simulate network error during data retrieval
        if (simulateConnectionInterruption && random.nextBoolean()) { // 50% chance if enabled
            throw new TouristServiceException("Data retrieval failed: Connection to ETOUR server interrupted.");
        }
        return touristDatabase.get(touristUsername);
    }
    /**
     * Updates the data for a tourist. The tourist is identified by their username.
     *
     * @param tourist The Tourist object with updated information.
     * @return true if the update was successful, false if the tourist was not found.
     * @throws TouristServiceException If a simulated network error occurs during the update.
     */
    public boolean updateTouristData(Tourist tourist) throws TouristServiceException {
        // Simulate network error during data update
        if (simulateConnectionInterruption && random.nextBoolean()) { // 50% chance if enabled
            throw new TouristServiceException("Data update failed: Connection to ETOUR server interrupted.");
        }
        // Check if the tourist exists (by username) before updating
        if (touristDatabase.containsKey(tourist.getUsername())) {
            touristDatabase.put(tourist.getUsername(), tourist); // Overwrite with updated tourist object
            return true;
        }
        return false; // Tourist not found
    }
    /**
     * Sets whether to simulate a connection interruption.
     * This is for testing the "Interruption of the connection to the server ETOUR" exit condition.
     *
     * @param simulate true to enable simulation, false to disable.
     */
    public void setSimulateConnectionInterruption(boolean simulate) {
        simulateConnectionInterruption = simulate;
    }
}