package hec.ports;

/**
 * Authentication service interface.
 * Provides methods to check login status and retrieve current user.
 */
public interface AuthenticationService {
    /**
     * Checks if a given user is logged in.
     *
     * @param userId the user ID to check
     * @return true if logged in, false otherwise
     */
    boolean isLoggedIn(String userId);

    /**
     * Retrieves the current logged-in user's ID.
     *
     * @return the current user ID
     */
    String getCurrentUser();
}