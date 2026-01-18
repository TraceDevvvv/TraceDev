'''
Simulates a backend service for managing tourist preferences.
It handles fetching and updating preferences, and can simulate connection issues.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class PreferenceService {
    // A simple in-memory store for preferences, simulating a database.
    private Map<String, Tourist> touristPreferences;
    private Random random; // For simulating connection errors
    /**
     * Constructor for PreferenceService. Initializes dummy data.
     */
    public PreferenceService() {
        touristPreferences = new HashMap<>();
        // Add some dummy tourist data
        touristPreferences.put("tourist123", new Tourist("tourist123", "JohnDoe", "Paris", "Sightseeing, Food tours", true));
        touristPreferences.put("tourist456", new Tourist("tourist456", "JaneSmith", "Tokyo", "Shopping, Local cuisine", false));
        // Add a tourist withoutpreferences to simulate null return for some IDs (e.g., new user with no preferences set)
        touristPreferences.put("newTourist789", null); // Example: no preferences set yet
        random = new Random();
    }
    /**
     * Authenticates a user. For this simulation, it just checks for a known username.
     * In a real system, it would verify password hash and return the user's ID.
     * @param username The username to authenticate.
     * @param password The password (not used in this simulation).
     * @return The tourist's ID if authentication is successful, otherwise null.
     */
    public String authenticate(String username, String password) {
        for (Map.Entry<String, Tourist> entry : touristPreferences.entrySet()) {
            Tourist tourist = entry.getValue();
            if (tourist != null && tourist.getUsername().equals(username)) {
                // For this example, we assume authentication is successful if username matches.
                // In a real application, password verification would happen here.
                return tourist.getId(); // Return the ID of the authenticated user
            } else if (entry.getKey().equals("newTourist789") && username.equals("NewUser")) {
                // Special case for our 'newTourist789' to be authenticated, even if it has null preferences
                return "newTourist789";
            }
        }
        return null; // Authentication failed
    }
    /**
     * Fetches generic personal preferences for a given tourist ID.
     * Simulates potential server connection issues randomly.
     * @param touristId The ID of the tourist.
     * @return The Tourist object with their preferences, or null if not found/no preferences set.
     * @throws ServiceConnectionException If a simulated connection error occurs.
     */
    public Tourist getPreferences(String touristId) throws ServiceConnectionException {
        // Simulate a 20% chance of connection failure
        if (random.nextInt(100) < 20) {
            throw new ServiceConnectionException("Failed to connect to ETOUR server to fetch preferences.");
        }
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Return a copy to prevent direct modification of stored object
        Tourist original = touristPreferences.get(touristId);
        if (original != null) {
            return new Tourist(original.getId(), original.getUsername(), original.getFavoriteDestination(),
                               original.getPreferredActivities(), original.wantsReceiveNotifications());
        }
        return null; // Return null if preferences are not found for the tourist ID or if there are no preferences set.
    }
    /**
     * Updates the generic personal preferences for a tourist.
     * Simulates potential server connection issues randomly.
     * @param tourist The Tourist object containing the updated preferences.
     * @throws ServiceConnectionException If a simulated connection error occurs.
     */
    public void updatePreferences(Tourist tourist) throws ServiceConnectionException {
        // Simulate a 20% chance of connection failure
        if (random.nextInt(100) < 20) {
            throw new ServiceConnectionException("Failed to connect to ETOUR server to update preferences.");
        }
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Update the stored preferences. Assume tourist ID exists.
        touristPreferences.put(tourist.getId(), tourist);
        System.out.println("Preferences updated for " + tourist.getUsername() + ": " + tourist);
    }
}