package shared;

/**
 * Service responsible for user authentication.
 * Mock implementation for demonstration purposes.
 */
public class AuthenticationService {

    /**
     * Checks if a user is authenticated.
     * For this simulation, any non-null and non-empty userId is considered authenticated.
     *
     * @param userId The ID of the user to check.
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String userId) {
        // Simulating a successful authentication for any non-empty user ID
        // In a real application, this would involve checking credentials against a user store.
        System.out.println("[AuthService] Checking authentication for user: " + userId);
        return userId != null && !userId.trim().isEmpty();
    }
}