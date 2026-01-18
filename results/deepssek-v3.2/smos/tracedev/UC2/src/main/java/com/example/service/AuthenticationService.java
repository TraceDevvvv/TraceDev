package com.example.service;

import com.example.model.LoginCredentials;
import com.example.model.AuthenticationResult;

/**
 * Service that performs authentication.
 * Traceability: REQ-003 - Handles authentication logic
 */
public class AuthenticationService {

    /**
     * Authenticates the given credentials.
     * For demonstration, always returns failure to trigger the failure flow.
     * @param credentials the login credentials
     * @return AuthenticationResult indicating success or failure
     */
    public AuthenticationResult authenticate(LoginCredentials credentials) {
        System.out.println("AuthenticationService: Authenticating user: " + credentials.getUsername());
        // Simulate authentication failure for this example.
        // In a real system, this would check against a user database or external service.
        boolean success = false;
        String message = "Invalid username or password.";
        AuthenticationResult result = new AuthenticationResult(success, message);
        // Return statement corresponds to sequence diagram return message m4
        return result;
    }
}