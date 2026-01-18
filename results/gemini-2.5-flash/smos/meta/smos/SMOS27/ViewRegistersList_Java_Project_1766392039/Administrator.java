/**
 * Represents an administrator user in the system.
 * This class handles administrator authentication.
 */
public class Administrator {
    private String username;
    private String password;
    private boolean isLoggedIn;

    /**
     * Constructs a new Administrator with the given username and password.
     *
     * @param username The administrator's username.
     * @param password The administrator's password.
     */
    public Administrator(String username, String password) {
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
            System.out.println("Login failed for username '" + enteredUsername + "'. Invalid credentials.");
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
        }
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
     * Gets the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }
}