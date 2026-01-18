package com.example;

/**
 * AuthService for handling authentication messages from Administrator.
 * (Matches sequence diagram participant "AuthService")
 */
public class AuthService {
    private AuthenticationService authenticationService;

    public AuthService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Login as Administrator (message m1).
     */
    public boolean loginAsAdministrator(Administrator admin) {
        // Delegate to AuthenticationService
        return authenticationService.login(admin);
    }

    /**
     * Authentication successful (message m2).
     */
    public String authenticationSuccessful() {
        return "Authentication successful";
    }
}