/**
 * Utility for authenticating administrators.
 * Handles login and logout operations.
 * In a production environment, this would integrate with a secure authentication service.
 */
public class AdminAuthenticator {
    /**
     * Simulates administrator login with hardcoded credentials for demonstration.
     * @param username The administrator username.
     * @param password The administrator password.
     * @return true if credentials are valid (username: admin, password: admin123), false otherwise.
     */
    public static boolean login(String username, String password) {
        // Hardcoded credentials for simulation; replace with secure authentication in production
        return "admin".equals(username) && "admin123".equals(password);
    }
    /**
     * Simulates administrator logout.
     * Prints a logout message to the console.
     */
    public static void logout() {
        System.out.println("Administrator logged out.");
    }
}