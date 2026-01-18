'''
Represents a user in the system with a username and a role.
This simple model is used for authentication and authorization.
'''
public class User {
    private String username;
    private String role; // e.g., "Administrator", "Student", "Teacher"
    /**
     * Constructs a new User object.
     *
     * @param username The username of the user.
     * @param role     The role assigned to the user.
     */
    public User(String username, String role) {
        this.username = username;
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
     * Sets the username of the user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the role of the user.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }
    /**
     * Sets the role of the user.
     *
     * @param role The new role.
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Checks if the user has the "Administrator" role.
     *
     * @return true if the user is an administrator, false otherwise.
     */
    public boolean isAdmin() {
        return "Administrator".equalsIgnoreCase(role);
    }
}