package hec.adapters;

import hec.ports.AuthenticationService;

/**
 * Session-based implementation of AuthenticationService.
 * Uses a SessionManager to manage user sessions.
 */
public class SessionAuthenticationService implements AuthenticationService {
    private final SessionManager sessionManager;

    /**
     * Constructor that accepts a SessionManager.
     *
     * @param sessionManager the session manager
     */
    public SessionAuthenticationService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean isLoggedIn(String userId) {
        return sessionManager.isValid(userId);
    }

    @Override
    public String getCurrentUser() {
        return sessionManager.getCurrentUserId();
    }
}