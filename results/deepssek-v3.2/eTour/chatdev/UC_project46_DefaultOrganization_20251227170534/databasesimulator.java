/**
Simulates database operations for the ETOUR system.
Provides mock authentication and preference storage/retrieval.
Handles simulated server interruptions as per requirements.
*/
import java.util.HashMap;
import java.util.Map;
public class DatabaseSimulator {
    private static DatabaseSimulator instance;
    private Map<String, String> userCredentials;
    private Map<String, SearchPreference> userPreferences;
    private boolean serverConnected = true;
    // Private constructor for singleton pattern
    private DatabaseSimulator() {
        userCredentials = new HashMap<>();
        userPreferences = new HashMap<>();
        // Initialize with some mock users
        initializeMockData();
    }
    public static DatabaseSimulator getInstance() {
        if (instance == null) {
            instance = new DatabaseSimulator();
        }
        return instance;
    }
    private void initializeMockData() {
        // Add mock users
        userCredentials.put("tourist1", "password123");
        userCredentials.put("tourist2", "welcome2023");
        userCredentials.put("john", "travel123");
        // Add mock preferences for users
        userPreferences.put("tourist1", new SearchPreference("Mountain", 1500, 300, "Hotel", false, true, "Vegetarian"));
        userPreferences.put("tourist2", new SearchPreference("City", 3000, 1000, "Airbnb", true, false, "Non-vegetarian"));
        userPreferences.put("john", new SearchPreference());
    }
    public boolean authenticateUser(String username, String password) {
        // Simulate server connection interruption
        if (!serverConnected) {
            throw new RuntimeException("ETOUR Server connection interrupted");
        }
        return userCredentials.containsKey(username) && 
               userCredentials.get(username).equals(password);
    }
    public SearchPreference getUserPreferences(String username) {
        if (!serverConnected) {
            throw new RuntimeException("ETOUR Server connection interrupted");
        }
        // Return user's preferences or create default if not exist
        return userPreferences.getOrDefault(username, new SearchPreference());
    }
    public boolean saveUserPreferences(String username, SearchPreference preferences) {
        if (!serverConnected) {
            // Simulate 10% chance of server interruption during save operation for testing
            if (Math.random() < 0.1) {
                throw new RuntimeException("ETOUR Server connection interrupted");
            }
        }
        userPreferences.put(username, preferences);
        return true;
    }
    public void setServerConnected(boolean connected) {
        this.serverConnected = connected;
    }
    public boolean isServerConnected() {
        return serverConnected;
    }
}