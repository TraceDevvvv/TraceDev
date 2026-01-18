'''
Represents a simple User model with a username and a password.
In a real application, passwords would be hashed and typically salted
using stronger algorithms like BCrypt or Argon2, not just SHA-256.
This implementation provides basic SHA-256 hashing for demonstration purposes,
including a hardcoded salt for improved security characteristics (making rainbow table attacks harder).
'''
package chatdev.user; // Added package for organization
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64; // For Base64 encoding the hash
public class User {
    private String username;
    private String hashedPassword; // Stores the hashed password
    // A fixed salt for demonstration. In a real-world production system,
    // salts should be unique for each user and randomly generated.
    private static final String SALT = "chatdev_demo_changepassword_salt";
    /**
     * Constructs a new User object.
     * The provided plain text password will be hashed before storage.
     * @param username The user's unique identifier.
     * @param plainTextPassword The user's password in plain text.
     */
    public User(String username, String plainTextPassword) {
        this.username = username;
        this.hashedPassword = hashPassword(plainTextPassword); // Hash password on creation
    }
    /**
     * Retrieves the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets a new password for the user by hashing it.
     * @param newPlainTextPassword The new password in plain text.
     */
    public void setPassword(String newPlainTextPassword) {
        this.hashedPassword = hashPassword(newPlainTextPassword);
    }
    /**
     * Checks if the provided plain text password matches the stored hashed password.
     * @param plainTextPassword The plain text password to check.
     * @return true if the processed passwords match, false otherwise.
     */
    public boolean checkPassword(String plainTextPassword) {
        return hashedPassword.equals(hashPassword(plainTextPassword));
    }
    /**
     * Hashes a plain text password using SHA-256, combined with a predefined salt.
     * In a real application, a more robust and unique-per-user salt-based hashing like BCrypt would be used.
     * This method also handles potential NoSuchAlgorithmException.
     * @param plainTextPassword The password to hash.
     * @return The Base64 encoded SHA-256 hash of the password, or an empty string if hashing fails.
     */
    private String hashPassword(String plainTextPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Concatenate the plain text password with the salt before hashing.
            byte[] hash = digest.digest((plainTextPassword + SALT).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // This should ideally never happen with "SHA-256" on a standard JVM.
            // In a real application, proper logging would be implemented.
            System.err.println("Fatal Error: SHA-256 algorithm not found. Security compromised. " + e.getMessage());
            // It's critical to handle this, perhaps by throwing a runtime exception
            // to indicate a severe system configuration issue.
            throw new RuntimeException("Password hashing algorithm not available.", e);
        }
    }
}