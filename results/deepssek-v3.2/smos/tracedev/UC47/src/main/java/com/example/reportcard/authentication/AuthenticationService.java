package com.example.reportcard.authentication;

/**
 * Service for verifying administrator role.
 */
public class AuthenticationService {
    public boolean verifyAdminRole(String userId) {
        // In a real system, would check against an identity provider.
        // For simulation, always return true (Admin is logged in).
        return true;
    }
}