import java.util.Objects;

/**
 * Represents an Administrator in the system.
 * An administrator has a username and a password, and can be logged in.
 */
public class Administrator {
    private final String username;
    private final String password;
    private boolean isLoggedIn;

    /**
     * Constructs a new Administrator object.
     *
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     * @throws IllegalArgumentException if username or password is null or empty.
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator username cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator password cannot be null or empty.");
        }
        this.username = username;
        this.password = password;
        this.isLoggedIn = false; // Initially not logged in
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
     * Returns the password of the administrator.
     * Note: In a real system, passwords should never be directly exposed.
     * This is for simulation purposes.
     *
     * @return The administrator's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Simulates logging in the administrator.
     *
     * @param enteredUsername The username entered for login.
     * @param enteredPassword The password entered for login.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for administrator '" + username + "'. Invalid credentials.");
            return false;
        }
    }

    /**
     * Simulates logging out the administrator.
     */
    public void logout() {
        if (this.isLoggedIn) {
            this.isLoggedIn = false;
            System.out.println("Administrator '" + username + "' logged out.");
        } else {
            System.out.println("Administrator '" + username + "' was not logged in.");
        }
    }

    /**
     * Compares this Administrator object with the specified object for equality.
     * Two Administrator objects are considered equal if their usernames are the same.
     *
     * @param o The object to compare with.
     * @return true if the given object is an Administrator and its username is equal to this Administrator's username, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return username.equals(that.username);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the username.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns a string representation of the Administrator object.
     *
     * @return A string in the format "Administrator{username='...', isLoggedIn=...}".
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "username='" + username + '\'' +
               ", isLoggedIn=" + isLoggedIn +
               '}';
    }
}