/**
 * Represents a user account with a username and password.
 */
import java.util.Arrays;
public class User {
    private String username;
    private char[] password; // Changed from String to char[] for better security
    /**
     * Constructs a new User object.
     * @param username The username of the user.
     * @param password The initial password of the user (as a char array).
     */
    public User(String username, char[] password) {
        this.username = username;
        // Store a copy of the password array to prevent external modification
        this.password = Arrays.copyOf(password, password.length);
    }
    /**
     * Constructs a new User object from a String password (less secure, use with caution or convert).
     * This constructor is provided for convenience but char[] is preferred.
     * @param username The username of the user.
     * @param password The initial password of the user (as a String).
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password.toCharArray(); // Convert String to char[] for storage
    }
    /**
     * Gets the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets a copy of the password of the user as a char array.
     * Callers should clear this array after use.
     * @return A copy of the password as a char array.
     */
    public char[] getPassword() {
        // Return a copy to prevent direct modification of the internal array
        return Arrays.copyOf(password, password.length);
    }
    /**
     * Sets a new password for the user.
     * Securely clears the old password and stores a copy of the new password.
     * @param newPassword The new password as a char array.
     */
    public void setPassword(char[] newPassword) {
        // Securely clear the old password from memory
        if (this.password != null) {
            Arrays.fill(this.password, ' ');
        }
        // Store a copy of the new password
        this.password = Arrays.copyOf(newPassword, newPassword.length);
    }
}