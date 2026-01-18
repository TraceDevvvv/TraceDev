/**
 * Manages the login session for an Agency Operator.
 * It provides methods to log in, log out, and check the current login status.
 */
public class AgencyOperatorSession {
    private boolean loggedIn = false; // Tracks the login status
    /**
     * Attempts to log in an operator with the provided username and password.
     * This is a mock implementation for demonstration purposes.
     * @param username The operator's username.
     * @param password The operator's password.
     * @return True if login is successful (username "admin", password "admin"), false otherwise.
     */
    public boolean login(String username, String password) {
        // Mock login logic: checks for a hardcoded username and password
        if ("admin".equals(username) && "admin".equals(password)) {
            loggedIn = true;
            return true;
        }
        loggedIn = false;
        return false;
    }
    /**
     * Logs out the current operator.
     */
    public void logout() {
        loggedIn = false;
    }
    /**
     * Checks if an operator is currently logged in.
     * @return True if an operator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}