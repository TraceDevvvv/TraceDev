package com.example;

/**
 * Concrete implementation of AuthenticationService.
 * For simplicity, we assume the session is valid if the sessionId is not null and not empty.
 */
public class ConcreteAuthService extends AuthenticationService {
    @Override
    public boolean isAuthenticated(String userId) {
        return userId != null && !userId.trim().isEmpty();
    }

    @Override
    public boolean validateSession(String sessionId) {
        // Simplified validation: session is valid if non-null and non-empty
        return sessionId != null && !sessionId.trim().isEmpty();
    }
}