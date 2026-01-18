import java.util.Objects;
import java.util.Random;
import java.util.Arrays; // Added for secure password comparison
/**
 * Provides authentication serv for user login.
 * This class simulates a backend authentication system, including
 * credential validation and potential connection interruptions.
 * @author Programmer
 * @version 1.0
 * @since 2023-10-27
 */
public class AuthService {
    // Hardcoded credentials for simulation purposes
    private static final String CORRECT_USERNAME = "user";
    // Using char array for password for security best practice, cannot be explicitly cleared for static final.
    // For a real application, passwords should be hashed and never stored in plain text.
    private static final char[] CORRECT_PASSWORD_CHARS = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
    /**
     * Attempts to authenticate a user with the given username and password.
     * This method simulates a backend authentication service.
     * It includes a random chance to simulate a network/server error (ETOUR, as per use case)
     * to test error handling.
     * Passwords are accepted as char arrays for enhanced security, preventing them from being
     * held in immutable String objects in memory.
     *
     * @param username The username provided by the user.
     * @param passwordChars The password provided by the user as a char array.
     * @return true if authentication is successful, false otherwise (incorrect credentials).
     * @throws ConnectionInterruptionException If a simulated connection issue occurs.
     * @throws IllegalArgumentException If username or password (char array) are null or empty.
     */
    public boolean authenticate(String username, char[] passwordChars) throws ConnectionInterruptionException, IllegalArgumentException {
        // Simulate a small delay for network latency to mimic real-world scenarios
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Re-assert the interrupted status
            Thread.currentThread().interrupt();
            throw new ConnectionInterruptionException("Login service interrupted during delay.", e);
        }
        // Simulate a connection interruption (ETOUR) with a 20% chance as per use case requirements
        Random random = new Random();
        if (random.nextInt(100) < 20) { // 20% chance of connection error
            throw new ConnectionInterruptionException("Connection to authentication server interrupted (ETOUR). Please try again later.");
        }
        // Validate input - basic check for null/empty for robustness
        if (username == null || username.trim().isEmpty() || passwordChars == null || passwordChars.length == 0) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }
        // Check if the provided credentials match the hardcoded ones using secure array comparison
        return Objects.equals(username, CORRECT_USERNAME) && Arrays.equals(passwordChars, CORRECT_PASSWORD_CHARS);
    }
}