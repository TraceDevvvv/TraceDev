// File: UserEntity.java
/**
 * Represents a User entity, extending AbstractEntity.
 * Includes specific attributes for email and role.
 */
public class UserEntity extends AbstractEntity {
    // + email : String
    public String email;
    // + role : String
    public String role;

    /**
     * Constructs a UserEntity with the specified details.
     *
     * @param id    The unique identifier for the user.
     * @param name  The full name of the user.
     * @param email The email address of the user.
     * @param role  The role of the user (e.g., "Student", "Administrator").
     */
    public UserEntity(String id, String name, String email, String role) {
        super(id, name);
        this.email = email;
        this.role = role;
    }

    /**
     * Returns the type of this entity, which is "User".
     *
     * @return The string "User".
     */
    @Override
    public String getType() {
        return "User";
    }

    /**
     * Provides a string representation of the UserEntity.
     *
     * @return A string combining the user's id, name, email, and role.
     */
    @Override
    public String toString() {
        return "UserEntity{id='" + id + "', name='" + name + "', email='" + email + "', role='" + role + "'}";
    }
}