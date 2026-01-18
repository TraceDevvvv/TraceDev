'''
Handles user authentication and authorization.
It simulates a user database with a predefined administrator account.
'''
import java.util.HashMap;
import java.util.Map;
/**
 * Service for user authentication and authorization.
 * Manages user login, logout, and role checking.
 */
class AuthService {
    private User currentUser;
    private Map<String, User> userDatabase; // Simulated user database
    /**
     * Enum to represent the outcome of a login attempt.
     */
    public enum LoginResult {
        SUCCESS_ADMIN,
        SUCCESS_NON_ADMIN,
        INVALID_CREDENTIALS
    }
    /**
     * Constructs an AuthService and initializes a dummy admin user.
     */
    public AuthService() {
        userDatabase = new HashMap<>();
        // Add a dummy administrator user
        userDatabase.put("admin", new User("admin", "admin", User.Role.ADMINISTRATOR));
        userDatabase.put("teacher", new User("teacher", "pass", User.Role.TEACHER));
        userDatabase.put("parent", new User("parent", "pass", User.Role.PARENT));
    }
    /**
     * Attempts to log in a user with the given username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return A LoginResult indicating the outcome of the login attempt.
     */
    public LoginResult login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user; // Set current user regardless of role for internal tracking
            System.out.println("Login successful for user: " + username + " with role " + user.getRole());
            if (user.isAdmin()) {
                return LoginResult.SUCCESS_ADMIN;
            } else {
                // User cannot perform admin actions, but credentials are valid.
                // For the purpose of this task, a non-admin effectively fails the login for progression
                // to admin-specific screens.
                return LoginResult.SUCCESS_NON_ADMIN;
            }
        }
        this.currentUser = null;
        System.out.println("Login failed for user: " + username);
        return LoginResult.INVALID_CREDENTIALS;
    }
    /**
     * Logs out the current user.
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " logged out.");
        }
        this.currentUser = null;
    }
    /**
     * Checks if a user is currently logged in.
     *
     * @return True if a user is logged in, false otherwise.
     */
    public boolean isAuthenticated() {
        return currentUser != null;
    }
    /**
     * Checks if the currently logged-in user is an administrator.
     *
     * @return True if the current user is an administrator, false if not logged in or not an admin.
     */
    public boolean isAdministrator() {
        return currentUser != null && currentUser.isAdmin();
    }
    /**
     * Gets the currently logged-in user.
     *
     * @return The User object, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}