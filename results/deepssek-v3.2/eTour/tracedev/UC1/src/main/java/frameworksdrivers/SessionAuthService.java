package frameworksdrivers;

/**
 * Session-based authentication service.
 */
public class SessionAuthService implements IAuthenticationService {
    @Override
    public boolean isUserLogged(String userId) {
        // Simplified: assume user is logged in for demonstration.
        // Real implementation would check session or token.
        return true;
    }
}