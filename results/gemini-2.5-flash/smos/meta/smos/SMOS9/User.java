import java.util.Objects;

/**
 * Represents a user in the system.
 * Each user has a unique ID and a name.
 */
public class User {
    private final String userId;
    private final String userName;

    /**
     * Constructs a new User object.
     *
     * @param userId   The unique identifier for the user.
     * @param userName The name of the user.
     * @throws IllegalArgumentException if userId or userName is null or empty.
     */
    public User(String userId, String userName) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * Returns the unique ID of the user.
     *
     * @return The user's ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the name of the user.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Compares this User object with the specified object for equality.
     * Two User objects are considered equal if their user IDs are the same.
     *
     * @param o The object to compare with.
     * @return true if the given object is a User and its userId is equal to this User's userId, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the user ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string in the format "User{userId='...', userName='...'}".
     */
    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + '\'' +
               ", userName='" + userName + '\'' +
               '}';
    }
}