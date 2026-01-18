package com.system.auth;

/**
 * Service for validating user access and roles.
 */
public class AuthorizationService {
    private UserSession userSession;

    public AuthorizationService(UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * Validates if the user has access to a specific use case.
     * @param userId The user ID (not directly used here, but passed for logging).
     * @param useCaseId The use case identifier.
     * @return true if access is granted.
     */
    public boolean validateAccess(String userId, String useCaseId) {
        // Delegates to UserSession's method.
        return userSession != null && userSession.hasActiveUseCase(useCaseId);
    }

    /**
     * Checks if the user has the required role.
     * @param userId The user ID (for logging).
     * @param requiredRole The required role.
     * @return true if the user has the role.
     */
    public boolean checkRole(String userId, String requiredRole) {
        return userSession != null && userSession.hasRole(requiredRole);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}