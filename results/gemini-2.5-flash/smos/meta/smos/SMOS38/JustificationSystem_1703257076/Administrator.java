import java.util.Objects;

/**
 * Represents an Administrator user in the system.
 * This class provides a simplified model for an administrator, primarily for
 * simulating login and user identification within the context of the use case.
 */
public class Administrator {

    private String userId;
    private String username;
    private String password; // In a real system, passwords should be hashed and salted.

    /**
     * Constructor for creating a new Administrator object.
     *
     * @param userId A unique identifier for the administrator.
     * @param username The administrator's username for login.
     * @param password The administrator's password for login.
     */
    public Administrator(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the unique user ID of the administrator.
     *
     * @return The administrator's user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique user ID of the administrator.
     *
     * @param userId The new user ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the administrator.
     *
     * @return The administrator's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Simulates a login attempt for the administrator.
     * In a real application, this would involve more robust authentication logic.
     *
     * @param enteredUsername The username provided by the user.
     * @param enteredPassword The password provided by the user.
     * @return true if the provided credentials match, false otherwise.
     */
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    /**
     * Provides a string representation of the Administrator object.
     * Note: Passwords are not included in toString for security reasons in real applications.
     * For this simulation, it's included for debugging clarity.
     *
     * @return A formatted string containing the administrator's details.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               '}';
    }

    /**
     * Compares this Administrator object to the specified object. The result is true if and only if
     * the argument is not null and is an Administrator object that has the same userId.
     *
     * @param o The object to compare this Administrator against.
     * @return true if the given object represents an Administrator equivalent to this administrator, false otherwise.
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
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}