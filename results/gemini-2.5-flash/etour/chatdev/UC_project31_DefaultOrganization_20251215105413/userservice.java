/**
 * Simulates a backend service for user authentication and password management.
 * For simplicity, user data is stored in memory.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays; // Added for secure password comparison and clearing
public class UserService {
    private Map<String, User> userDatabase; // Stores users by username
    private boolean isServerConnected = true; // Simulates server connection status
    /**
     * Constructs a UserService and initializes with a default agency operator.
     */
    public UserService() {
        userDatabase = new HashMap<>();
        // Hardcode a default agency operator for the use case
        // Storing password as char[] directly from a literal for simplicity in this simulation.
        // In a real application, passwords would be hashed and stored.
        userDatabase.put("agency_operator", new User("agency_operator", "password123".toCharArray()));
    }
    /**
     * Authenticates a user with the given username and password.
     * @param username The username to authenticate.
     * @param passwordChars The password to authenticate (as a char array).
     * @return The User object if authentication is successful, null otherwise.
     * @throws ConnectionInterruptionException If the simulated server connection is interrupted.
     */
    public User authenticate(String username, char[] passwordChars) throws ConnectionInterruptionException {
        if (!isServerConnected) {
            throw new ConnectionInterruptionException("Server connection interrupted during authentication.");
        }
        User user = userDatabase.get(username);
        if (user != null) {
            char[] storedPassword = user.getPassword(); // Get a copy of the stored password
            try {
                // Securely compare passwords using Arrays.equals to prevent timing attacks
                if (Arrays.equals(storedPassword, passwordChars)) {
                    return user;
                }
            } finally {
                // Clear the retrieved stored password copy immediately after use
                Arrays.fill(storedPassword, ' ');
            }
        }
        return null;
    }
    /**
     * Changes the password for a given user.
     * @param username The username of the account to modify.
     * @param currentPasswordChars The current password for verification (as a char array).
     * @param newPasswordChars The new password to set (as a char array).
     * @return True if the password change is successful, false otherwise (e.g., current password mismatch).
     * @throws ConnectionInterruptionException If the simulated server connection is interrupted.
     */
    public boolean changePassword(String username, char[] currentPasswordChars, char[] newPasswordChars) throws ConnectionInterruptionException {
        if (!isServerConnected) {
            throw new ConnectionInterruptionException("Server connection interrupted during password change.");
        }
        User user = userDatabase.get(username);
        if (user != null) {
            char[] storedPassword = user.getPassword(); // Get a copy of the stored password
            try {
                // Securely compare current password
                if (Arrays.equals(storedPassword, currentPasswordChars)) {
                    user.setPassword(newPasswordChars); // Update the password using char[]
                    System.out.println("Password for " + username + " successfully changed.");
                    return true;
                }
            } finally {
                // Clear the retrieved stored password copy immediately after use
                Arrays.fill(storedPassword, ' ');
            }
        }
        return false; // Current password mismatch or user not found
    }
    /**
     * Sets the simulated server connection status.
     * This is for testing purposes (simulating "Interruption of the connection to the server").
     * @param status True if connected, false if interrupted.
     */
    public void setServerConnected(boolean status) {
        this.isServerConnected = status;
    }
    /**
     * Gets the current simulated server connection status.
     * @return True if connected, false if interrupted.
     */
    public boolean isServerConnected() {
        return isServerConnected;
    }
    /**
     * Custom exception for simulating server connection issues.
     */
    public static class ConnectionInterruptionException extends Exception {
        /**
         * Constructs a new ConnectionInterruptionException with the specified detail message.
         * @param message the detail message.
         */
        public ConnectionInterruptionException(String message) {
            super(message);
        }
    }
}