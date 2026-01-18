/**
 * Authentication class handles administrator login functionality.
 */
public class Authentication {
    private boolean isLoggedIn = false;
    private String currentUser;
    /**
     * Attempts to log in an administrator user.
     *
     * @param username The administrator username.
     * @param password The administrator password.
     * @return true if login successful, false otherwise.
     */
    public boolean login(String username, String password) {
        // Simple authentication - in real application, this would connect to authentication server
        if ("admin".equals(username) && "admin123".equals(password)) {
            isLoggedIn = true;
            currentUser = username;
            System.out.println("[SYSTEM] Administrator " + username + " logged in successfully.");
            return true;
        }
        System.out.println("[SYSTEM] Login failed: Invalid credentials.");
        return false;
    }
    /**
     * Logs out the current user.
     */
    public void logout() {
        if (isLoggedIn) {
            System.out.println("[SYSTEM] Administrator " + currentUser + " logged out.");
            currentUser = null;
            isLoggedIn = false;
        }
    }
    /**
     * Checks if a user is currently logged in as administrator.
     *
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    /**
     * Gets the current logged-in user.
     *
     * @return The username of the current user, or null if no user is logged in.
     */
    public String getCurrentUser() {
        return currentUser;
    }
}