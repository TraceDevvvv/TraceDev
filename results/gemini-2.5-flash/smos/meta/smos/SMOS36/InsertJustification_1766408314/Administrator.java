/**
 * Represents an administrator user in the system.
 * This class manages the administrator's login status and identity.
 */
public class Administrator {
    private String username;
    private boolean loggedIn;

    /**
     * Constructs a new Administrator instance.
     *
     * @param username The username of the administrator.
     */
    public Administrator(String username) {
        this.username = username;
        this.loggedIn = false; // Initially not logged in
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
     * Checks if the administrator is currently logged in.
     *
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the login status of the administrator.
     *
     * @param loggedIn true to log the administrator in, false to log out.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Simulates the login process for an administrator.
     * In a real system, this would involve password verification.
     *
     * @param username The username to attempt to log in with.
     * @param password The password to attempt to log in with.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        // For this simulation, we'll just check the username and set loggedIn to true.
        // In a real application, this would involve authentication against a database.
        if (this.username.equals(username) && "adminPass".equals(password)) { // Simplified password check
            this.loggedIn = true;
            System.out.println(username + " logged in successfully.");
            return true;
        }
        System.out.println("Login failed for " + username + ".");
        this.loggedIn = false;
        return false;
    }

    /**
     * Simulates the logout process for an administrator.
     */
    public void logout() {
        if (this.loggedIn) {
            this.loggedIn = false;
            System.out.println(username + " logged out successfully.");
        } else {
            System.out.println(username + " was not logged in.");
        }
    }
}