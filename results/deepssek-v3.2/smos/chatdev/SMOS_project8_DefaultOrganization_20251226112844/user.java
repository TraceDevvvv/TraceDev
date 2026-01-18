/**
 * Represents a user in the system with basic attributes.
 * Provides getter and setter methods for accessing and modifying user data.
 */
public class User {
    private String id;
    private String name;
    private String email;
    /**
     * Constructor to initialize a User object.
     * @param id The unique identifier for the user.
     * @param name The full name of the user.
     * @param email The email address of the user.
     */
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // Getter and setter methods for user attributes
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    /**
     * Returns a string representation of the user for display purposes.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nEmail: " + email;
    }
}