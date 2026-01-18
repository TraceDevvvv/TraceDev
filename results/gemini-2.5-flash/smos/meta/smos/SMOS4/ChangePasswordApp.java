```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents a registered user in the system.
 * In a real application, the password would be hashed and salted for security.
 */
class User {
    private String username;
    private String password; // Stored as plain text for simulation purposes only.

    /**
     * Constructs a new User object.
     *
     * @param username The user's unique username.
     * @param password The user's password.
     */
    public User(String username, String password) {
        this.username = username;
        this.