import java.util.Objects;

/**
 * Represents an Administrator in the school management system.
 * Administrators have credentials (username and password) and can perform
 * privileged operations like managing parent-student associations.
 */
public class Administrator {
    private final String username;
    private final String password; // In a real application, passwords should be hashed and salted.
    private boolean isLoggedIn;

    /**
     * Constructs a new Administrator object.
     *
     * @param username The administrator's username.
     * @param password The administrator's password.
     * @throws IllegalArgumentException if username or password is null or empty.
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.username = username;
        this.password = password;
        this.isLoggedIn = false; // Initially not logged in
    }

    /**
     * Attempts to log in the administrator with the provided credentials.
     *
     * @param enteredUsername The username entered by the user.
     * @param enteredPassword The password entered by the user.
     * @return true if the login is successful, false otherwise.
     */
    public boolean login(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for '" + enteredUsername + "'. Invalid credentials.");
            return false;
        }
    }

    /**
     * Logs out the administrator.
     */
    public void logout() {
        if (isLoggedIn) {
            this.isLoggedIn = false;
            System.out.println("Administrator '" + username + "' logged out.");
        } else {
            System.out.println("Administrator '" + username + "' was not logged in.");
        }
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
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
     * Overrides the toString method to provide a meaningful string representation of the Administrator object.
     *
     * @return A string containing the administrator's username and login status.
     */
    @Override
    public String toString() {
        return "Administrator [Username: " + username + ", Logged In: " + isLoggedIn + "]";
    }

    /**
     * Compares this Administrator object with the specified object for equality.
     * Two Administrator objects are considered equal if they have the same username.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
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
     * The hash code is based on the username, ensuring consistency with the equals method.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}