/**
 * Service handling authentication and session state.
 */
public class AuthenticationService {
    private User currentUser;
    private boolean isAuthenticated;

    public AuthenticationService() {
        this.isAuthenticated = false;
    }

    public boolean isLoggedIn() {
        return isAuthenticated;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Simulate authentication
    public boolean authenticate(Credentials credentials) {
        // Dummy authentication logic
        if ("admin".equals(credentials.getUsername()) && "pass".equals(credentials.getPassword())) {
            currentUser = new User(credentials.getUsername());
            isAuthenticated = true;
            return true;
        }
        return false;
    }

    // For testing: set authenticated state
    public void setAuthenticated(boolean auth) {
        isAuthenticated = auth;
    }
}