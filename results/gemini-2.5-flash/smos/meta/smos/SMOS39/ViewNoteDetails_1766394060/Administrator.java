/**
 * Represents an Administrator in the system.
 * This class simulates administrator login status for the purpose of the use case.
 * In a real-world application, this would involve more complex authentication mechanisms.
 */
public class Administrator {
    private String username;
    private boolean isLoggedIn;

    /**
     * Constructs a new Administrator object with a given username.
     * Initially, the administrator is not logged in.
     *
     * @param username The username of the administrator. Cannot be null or empty.
     * @throws IllegalArgumentException if the username is null or empty.
     */
    public Administrator(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
        this.isLoggedIn = false; // Administrator is not logged in by default
    }

    /**
     * Simulates the login process for the administrator.
     * For this use case, a successful login simply sets the isLoggedIn flag to true.
     * In a real system, this would involve checking credentials.
     *
     * @param password A placeholder for a password. Not actually checked in this simulation.
     * @return true if login is successful (always true in this simulation), false otherwise.
     */
    public boolean login(String password) {
        // In a real application, this would involve authenticating credentials.
        // For this simulation, we assume login is always successful if called.
        System.out.println("Administrator '" + username + "' attempting to log in...");
        this.isLoggedIn = true;
        System.out.println("Administrator '" + username + "' logged in successfully.");
        return true;
    }

    /**
     * Simulates the logout process for the administrator.
     * Sets the isLoggedIn flag to false.
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
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Returns the username of the administrator.
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }
}