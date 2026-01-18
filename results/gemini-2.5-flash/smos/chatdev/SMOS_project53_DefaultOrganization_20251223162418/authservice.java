'''
Simulates user authentication against predefined credentials.
In a real application, this would interact with a database or an external authentication system.
'''
import java.util.HashMap;
import java.util.Map;
public class AuthService {
    // A static map to simulate a database of users for authentication purposes.
    private static final Map<String, User> USERS = new HashMap<>();
    static {
        // Pre-populate with a staff user as per the requirement.
        USERS.put("staff", new User("staff", "password", "staff"));
        // Add other users if necessary, e.g., for different roles
        // USERS.put("admin", new User("admin", "adminpass", "admin"));
    }
    /**
     * Authenticates a user with the provided username and password.
     * Checks if the user exists and if the provided password matches.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return The User object if authentication is successful and the user is 'staff', null otherwise.
     */
    public static User authenticate(String username, String password) {
        User user = USERS.get(username);
        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // As per precondition, only staff can use this feature.
            if ("staff".equalsIgnoreCase(user.getRole())) {
                return user; // Authentication successful for a staff user.
            }
        }
        return null; // Authentication failed or user is not staff.
    }
}