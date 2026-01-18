package domain;

/**
 * Represents a user in the system.
 * Added to satisfy REQ-001.
 */
public class User {
    private String id;
    private String username;
    private String passwordHash;
    private Role role;

    /**
     * Constructs a new User.
     * @param id The unique identifier for the user.
     * @param username The username of the user.
     * @param passwordHash The hashed password of the user.
     * @param role The role assigned to the user.
     */
    public User(String id, String username, String passwordHash, Role role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    // Setters can be added if mutable properties are desired, but not specified in CD.
}