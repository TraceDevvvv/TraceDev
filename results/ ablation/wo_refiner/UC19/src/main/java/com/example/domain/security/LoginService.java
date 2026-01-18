
package com.example.domain.security;

/**
 * Security service for authenticating agency operators.
 * Implements REQ-014 security requirement.
 */
public class LoginService {
    
    public AuthenticationResult authenticate(LoginCredentials credentials) {
        // Simplified authentication - in real application, would validate against a user store.
        if ("validUser".equals(credentials.getUsername()) && "validPass".equals(credentials.getPassword())) {
            return new AuthenticationResult(true, "dummy-token", "Authentication successful");
        } else {
            return new AuthenticationResult(false, null, "Invalid credentials");
        }
    }
}
