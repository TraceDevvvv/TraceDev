/**
 * Represents a user session with authentication and password change capabilities.
 * In a real application, this would interface with a database or server.
 */
import java.util.HashMap;
import java.util.Map;
public class UserSession {
    private String username;
    private String password;
    private static Map<String, String> userDatabase = new HashMap<>();
    static {
        // Simulate a database with a sample user for testing
        userDatabase.put("testUser", "oldPass123");
    }
    /**
     * Constructor for UserSession.
     * @param username The username of the logged-in user.
     * @param currentPassword The current password for validation.
     */
    public UserSession(String username, String currentPassword) {
        this.username = username;
        this.password = currentPassword;
    }
    /**
     * Changes the user's password after validating the old password.
     * Simulates server interaction; throws ServerConnectionException on connection failure.
     * @param oldPassword The old password for verification.
     * @param newPassword The new password to set.
     * @return true if password change is successful, false if old password is incorrect.
     * @throws ServerConnectionException If connection to the server (SMOS) fails.
     */
    public boolean changePassword(String oldPassword, String newPassword) throws ServerConnectionException {
        // Simulate server connection: 10% chance of failure for demonstration
        if (Math.random() < 0.1) {
            throw new ServerConnectionException("Unable to connect to SMOS server. Please try again.");
        }
        // Validate old password against the current session and database
        if (oldPassword.equals(this.password) && oldPassword.equals(userDatabase.get(this.username))) {
            // Update password in session and simulated database
            this.password = newPassword;
            userDatabase.put(this.username, newPassword);
            System.out.println("Password changed successfully for user: " + username);
            return true;
        }
        System.out.println("Password change failed: Old password incorrect for user: " + username);
        return false;
    }
    /**
     * Gets the username associated with this session.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the current password for this session (for testing purposes only).
     * @return The current password.
     */
    public String getPassword() {
        return password;
    }
}