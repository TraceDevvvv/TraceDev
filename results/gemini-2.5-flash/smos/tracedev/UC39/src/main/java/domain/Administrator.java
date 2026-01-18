package domain;

/**
 * Represents an Administrator actor in the system (REQ-002).
 * This class holds basic administrative user information.
 */
public class Administrator {
    // Attributes as specified in the Class Diagram
    public String userId;
    public String username;

    /**
     * Constructs a new Administrator object.
     * @param userId The unique identifier for the administrator.
     * @param username The username of the administrator.
     */
    public Administrator(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getters for the attributes
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Administrator{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}