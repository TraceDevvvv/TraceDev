import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Service class to handle tourist operations including updating account information.
 * This class manages the business logic for modifying tourist data.
 */
public class TouristService {
    private Map<String, Tourist> touristDatabase = new HashMap<>();

    /**
     * Constructor that initializes with some sample data for demonstration.
     */
    public TouristService() {
        // Initialize with sample data
        touristDatabase.put("user1", new Tourist("user1", "password123", "Alice Smith", "alice@example.com", "1234567890"));
        touristDatabase.put("user2", new Tourist("user2", "pass456", "Bob Johnson", "bob@example.com", "0987654321"));
    }

    /**
     * Updates tourist information after validation.
     * @param username The authenticated tourist's username.
     * @param updatedTourist The Tourist object containing updated fields.
     * @return true if update is successful, false otherwise.
     */
    public boolean updateTouristInfo(String username, Tourist updatedTourist) {
        if (touristDatabase.containsKey(username)) {
            // Preserve original password if not changed (for simplicity, assuming password is unchanged)
            Tourist existing = touristDatabase.get(username);
            if (updatedTourist.getPassword() == null || updatedTourist.getPassword().isEmpty()) {
                updatedTourist.setPassword(existing.getPassword());
            }
            touristDatabase.put(username, updatedTourist);
            return true;
        }
        return false;
    }

    /**
     * Retrieves tourist data by username.
     * @param username The tourist's username.
     * @return Tourist object or null if not found.
     */
    public Tourist getTourist(String username) {
        return touristDatabase.get(username);
    }

    /**
     * Validates tourist authentication.
     * @param username The tourist's username.
     * @param password The tourist's password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        Tourist tourist = touristDatabase.get(username);
        return tourist != null && tourist.getPassword().equals(password);
    }
}