'''
User class representing a registered user in the system.
Stores user credentials with hashed passwords for security.
'''
public class User {
    private String username;
    private String passwordHash; // Store hashed password, not plain text
    private boolean isLocked;
    /**
     * Constructs a new User with the specified credentials.
     * @param username The user's username.
     * @param passwordHash The hashed password.
     * @param isLocked Whether the account is locked.
     */
    public User(String username, String passwordHash, boolean isLocked) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.isLocked = isLocked;
    }
    /**
     * Gets the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the hashed password.
     * @return The hashed password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    /**
     * Sets the hashed password.
     * @param passwordHash The new hashed password.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    /**
     * Checks if the account is locked.
     * @return true if account is locked, false otherwise.
     */
    public boolean isLocked() {
        return isLocked;
    }
    /**
     * Sets the account locked status.
     * @param locked The new locked status.
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
    /**
     * Verifies if the provided password matches the stored hash.
     * @param password The plain text password to verify.
     * @return true if password matches, false otherwise.
     * @throws java.security.NoSuchAlgorithmException If hashing algorithm is not available.
     */
    public boolean verifyPassword(String password) throws java.security.NoSuchAlgorithmException {
        // Hash the provided password and compare with stored hash
        String hashedPassword = PasswordHasher.hashPassword(password);
        return this.passwordHash.equals(hashedPassword);
    }
}