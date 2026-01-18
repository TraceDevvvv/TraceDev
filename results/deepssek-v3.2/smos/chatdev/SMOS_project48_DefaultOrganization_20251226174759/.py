/**
 * A utility class for validating administrator login.
 * In a real system, this would interact with an authentication service.
 */
public class AdminAuthenticator {
    /**
     * Simulates administrator login.
     * @param username The administrator username.
     * @param password The administrator password.
     * @return true if credentials are valid, false otherwise.
     */
    public static boolean login(String username, String password) {
        // For demonstration, accept a hardcoded admin credential.
        return "admin".equals(username) && "admin123".equals(password);
    }
    /**
     * Simulates administrator logout.
     */
    public static void logout() {
        System.out.println("Administrator logged out.");
    }
}