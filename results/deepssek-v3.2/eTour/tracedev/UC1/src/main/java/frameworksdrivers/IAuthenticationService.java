package frameworksdrivers;

/**
 * Authentication service interface.
 */
public interface IAuthenticationService {
    /**
     * Checks if a user is logged in.
     * @param userId the user identifier
     * @return true if user is logged in, false otherwise
     */
    boolean isUserLogged(String userId);
}