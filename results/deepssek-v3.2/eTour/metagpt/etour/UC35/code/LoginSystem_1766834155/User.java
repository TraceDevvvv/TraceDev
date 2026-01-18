/**
 * User class representing a registered user in the system.
 * Contains user credentials (username and password) and their associated privileges.
 * This class is immutable to ensure data integrity after creation.
 */
public class User {
    private final String username;
    private final String password;
    private final String privileges;

    /**
     * Constructs a new User with the specified credentials and privileges.
     *
     * @param username the username for authentication (must not be null or empty)
     * @param password the password for authentication (must not be null or empty)
     * @param privileges the access privileges assigned to this user (e.g., "admin", "user")
     */
    public User(String username, String password, String privileges) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (privileges == null || privileges.trim().isEmpty()) {
            throw new IllegalArgumentException("Privileges cannot be null or empty");
        }
        
        this.username = username.trim();
        this.password = password.trim();
        this.privileges = privileges.trim();
    }

    /**
     * Gets the username of this user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of this user.
     * In a real system, passwords should be stored as hashed values, not plain text.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the privileges of this user.
     *
     * @return the privileges
     */
    public String getPrivileges() {
        return privileges;
    }

    /**
     * Validates if the provided credentials match this user's credentials.
     * In a production system, password comparison should use secure hashing.
     *
     * @param inputUsername the username to validate
     * @param inputPassword the password to validate
     * @return true if both username and password match, false otherwise
     */
    public boolean validateCredentials(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            return false;
        }
        return this.username.equals(inputUsername.trim()) && 
               this.password.equals(inputPassword.trim());
    }

    /**
     * Returns a string representation of the user (excluding password for security).
     *
     * @return a string containing username and privileges
     */
    @Override
    public String toString() {
        return "User{username='" + username + "', privileges='" + privileges + "'}";
    }
}