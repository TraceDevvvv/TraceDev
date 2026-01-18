'''
This class represents a User entity in the system.
It holds basic user information such as ID, username, email, and role.
'''
package dev.chat.system; // Assuming a package structure
public class User {
    private String id;
    private String username;
    private String email;
    private String role;
    /**
     * Constructs a new User object.
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param email The email address of the user.
     * @param role The role of the user (e.g., Administrator, Regular User).
     */
    public User(String id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
    /**
     * Gets the user's ID.
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the user's ID.
     * @param id The new ID for the user.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the user's username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the user's username.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the user's email.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the user's email.
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the user's role.
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }
    /**
     * Sets the user's role.
     * @param role The new role.
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Returns a string representation of the User object.
     * @return A string containing user details.
     */
    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}