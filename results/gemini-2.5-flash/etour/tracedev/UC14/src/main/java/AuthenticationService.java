/**
 * Service for handling user authentication.
 * Added to satisfy requirement REQ-001.
 */
public class AuthenticationService {
    // A simple flag to simulate login state
    private boolean loggedIn = false;

    /**
     * Checks if a user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        System.out.println("AuthenticationService: User is " + (loggedIn ? "logged in." : "not logged in."));
        return loggedIn;
    }

    /**
     * Attempts to log in a user with the given credentials.
     * For simulation, any non-empty credentials will be considered successful.
     * @param credentials The user's credentials.
     */
    public void login(Credentials credentials) {
        // Simulate a successful login for any non-empty credentials
        if (credentials != null && !credentials.username.isEmpty() && !credentials.password.isEmpty()) {
            this.loggedIn = true;
            System.out.println("AuthenticationService: User '" + credentials.username + "' logged in successfully.");
        } else {
            System.out.println("AuthenticationService: Login failed. Invalid credentials.");
            this.loggedIn = false;
        }
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println("AuthenticationService: User logged out.");
    }
}