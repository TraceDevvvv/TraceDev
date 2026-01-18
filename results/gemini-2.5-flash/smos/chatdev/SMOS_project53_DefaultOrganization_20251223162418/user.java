'''
Represents a user in the system, typically used for authentication.
'''
public class User {
    private String username;
    private String password; // In a real application, passwords should be hashed and properly secured.
    private String role;     // e.g., "staff", "student"
    /**
     * Constructs a new User object.
     * @param username The user's username.
     * @param password The user's password.
     * @param role The user's role (e.g., "staff").
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    /**
     * Returns the user's username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Returns the user's password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the user's role.
     * @return The role.
     */
    public String getRole() {
        return role;
    }
}