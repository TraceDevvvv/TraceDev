package EntitySearch_1766409602;

/**
 * Represents a User entity in the system.
 * Implements the Entity interface to allow it to be searched.
 * Users can be administrators, students, faculty, or other roles.
 */
public class User implements Entity {
    private String id; // Unique identifier for the user (e.g., username, employee ID)
    private String name;
    private String email;
    private String role; // e.g., "Administrator", "Student", "Faculty", "Guest"

    /**
     * Constructs a new User instance.
     *
     * @param id A unique identifier for the user.
     * @param name The full name of the user.
     * @param email The email address of the user.
     * @param role The role of the user in the system.
     */
    public User(String id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return The user ID.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns a string containing all searchable content for the user.
     * This includes their ID, name, email, and role.
     *
     * @return A concatenated string of searchable attributes.
     */
    @Override
    public String getSearchableContent() {
        // Convert to lowercase for case-insensitive searching
        return (id + " " + name + " " + email + " " + role).toLowerCase();
    }

    /**
     * Returns the display name of the user, which is their full name.
     *
     * @return The full name of the user.
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns the type of the entity, which is "User".
     *
     * @return The string "User".
     */
    @Override
    public String getType() {
        return "User";
    }

    /**
     * Provides a string representation of the User object for debugging and display.
     *
     * @return A formatted string including the user type, ID, name, email, and role.
     */
    @Override
    public String toString() {
        return String.format("Type: %s, ID: %s, Name: %s, Email: %s, Role: %s",
                             getType(), id, name, email, role);
    }
}