'''
This class represents a User entity in the system.
It holds basic user information such as ID, name, email, and role.
'''
public class User {
    private String id;
    private String name;
    private String email;
    private String role; // e.g., "Administrator", "Standard User"
    /**
     * Constructs a new User object.
     * @param id The unique identifier for the user.
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param role The role of the user (e.g., Administrator).
     */
    public User(String id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
    // --- Getters ---
    /**
     * Returns the user's ID.
     * @return The user's unique identifier.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the user's name.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the user's email.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Returns the user's role.
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }
    // --- Setters ---
    /**
     * Sets the user's name.
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the user's email.
     * @param email The new email address for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Sets the user's role.
     * @param role The new role for the user.
     */
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User [ID=" + id + ", Name=" + name + ", Email=" + email + ", Role=" + role + "]";
    }
}