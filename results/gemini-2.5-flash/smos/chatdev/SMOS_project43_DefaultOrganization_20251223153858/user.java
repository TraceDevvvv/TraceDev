'''
Represents a user in the system with a username, password, and role.
Used for authentication and authorization purposes.
'''
/**
 * Represents a user in the system.
 */
class User {
    public enum Role {
        ADMINISTRATOR, TEACHER, PARENT
    }
    private String username;
    private String password; // In a real system, passwords should be hashed and salted.
    private Role role;
    /**
     * Constructs a new User object.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role     The user's role (e.g., ADMINISTRATOR, PARENT).
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Gets the role of the user.
     *
     * @return The role.
     */
    public Role getRole() {
        return role;
    }
    /**
     * Checks if the user has an administrative role.
     *
     * @return true if the user is an administrator, false otherwise.
     */
    public boolean isAdmin() {
        return this.role == Role.ADMINISTRATOR;
    }
}