import java.util.Objects;

/**
 * Represents an Administrator user in the system.
 * This class holds basic information about an administrator, including their
 * login status, which is a precondition for the "EditJustification" use case.
 */
public class Administrator {
    private String userId;
    private String username;
    private boolean loggedIn;

    /**
     * Constructs a new Administrator instance.
     *
     * @param userId The unique identifier for the administrator. Must not be null or empty.
     * @param username The username of the administrator. Must not be null or empty.
     * @throws IllegalArgumentException if userId or username is null or empty.
     */
    public Administrator(String userId, String username) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator userId cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator username cannot be null or empty.");
        }
        this.userId = userId;
        this.username = username;
        this.loggedIn = false; // Administrators are not logged in by default upon creation.
    }

    /**
     * Returns the unique identifier of the administrator.
     *
     * @return The administrator's user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the administrator.
     *
     * @param userId The new user ID for the administrator. Must not be null or empty.
     * @throws IllegalArgumentException if userId is null or empty.
     */
    public void setUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator userId cannot be null or empty.");
        }
        this.userId = userId;
    }

    /**
     * Returns the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username The new username for the administrator. Must not be null or empty.
     * @throws IllegalArgumentException if username is null or empty.
     */
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator username cannot be null or empty.");
        }
        this.username = username;
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the login status of the administrator.
     * This method is typically called by an authentication service.
     *
     * @param loggedIn The new login status.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Administrator objects are considered equal if they have the same userId.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(userId, that.userId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the administrator's userId.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    /**
     * Returns a string representation of the Administrator object.
     *
     * @return A string containing the userId, username, and login status.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               ", loggedIn=" + loggedIn +
               '}';
    }
}