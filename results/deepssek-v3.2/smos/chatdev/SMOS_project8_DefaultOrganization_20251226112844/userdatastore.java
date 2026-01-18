import java.util.HashMap;
import java.util.Map;
/**
 * Simulates a data store for user information.
 * In a real application, this would be replaced by a database or external service.
 */
public class UserDataStore {
    private Map<String, User> users;
    public UserDataStore() {
        users = new HashMap<>();
        // Initialize with some sample users for testing.
        users.put("admin123", new User("admin123", "Admin User", "admin@example.com"));
        users.put("user456", new User("user456", "John Doe", "john.doe@example.com"));
    }
    /**
     * Retrieves a user by ID.
     * @param userId The ID of the user to retrieve.
     * @return User object if found, null otherwise.
     */
    public User getUser(String userId) {
        return users.get(userId);
    }
    /**
     * Updates an existing user's details.
     * @param userId The ID of the user to update.
     * @param newName The new name for the user.
     * @param newEmail The new email for the user.
     * @return true if successful, false if user not found.
     */
    public boolean updateUser(String userId, String newName, String newEmail) {
        User user = users.get(userId);
        if (user == null) {
            return false;
        }
        user.setName(newName);
        user.setEmail(newEmail);
        return true;
    }
}