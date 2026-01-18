package com.example.diagram;

/**
 * Service for authorization and access validation.
 */
public class AuthorizationService {
    /**
     * Validates if a user has the required role.
     * This corresponds to sequence message m8: check user role.
     * @param userId the user identifier.
     * @param requiredRole the required role.
     * @return true if access is granted, false otherwise.
     */
    public boolean validateAccess(String userId, String requiredRole) {
        // m8: check user role
        // In a real implementation, this would check user roles in a database or external service.
        // For demo, we assume user with userId "admin123" has Administrator role.
        if ("admin123".equals(userId) && "Administrator".equals(requiredRole)) {
            System.out.println("Authorization successful for user: " + userId + " with role: " + requiredRole);
            return true;
        }
        System.out.println("Authorization failed for user: " + userId + " with required role: " + requiredRole);
        return false;
    }
}