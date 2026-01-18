'''
A service class responsible for user authentication.
This class simulates a backend authentication system with hardcoded user credentials
for simplicity, fulfilling the precondition of a logged-in administrator.
'''
import java.util.HashMap;
import java.util.Map;
public class AuthService {
    // A simple in-memory store for user credentials
    private static final Map<String, String> USERS_DB = new HashMap<>();
    private static User currentUser; // Stores the currently logged-in user
    static {
        // Initialize with a predefined administrator user
        USERS_DB.put("admin", "adminpass");
    }
    /**
     * Attempts to log in a user with the given username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return A User object if authentication is successful and the user is an administrator, null otherwise.
     */
    public User login(String username, String password) {
        // Check if the username exists and the password matches
        if (USERS_DB.containsKey(username) && USERS_DB.get(username).equals(password)) {
            // For this use case, we only allow "admin" to log in
            if ("admin".equalsIgnoreCase(username)) {
                currentUser = new User(username, "Administrator");
                System.out.println("User '" + username + "' logged in successfully as Administrator.");
                return currentUser;
            } else {
                System.out.println("User '" + username + "' found, but not an Administrator role.");
                return null; // Not an administrator
            }
        }
        System.out.println("Login failed for user '" + username + "'. Invalid credentials.");
        return null; // Invalid credentials
    }
    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    /**
     * Gets the currently logged-in user.
     *
     * @return The current User object, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Logs out the current user.
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("User '" + currentUser.getUsername() + "' logged out.");
            currentUser = null;
        }
    }
}