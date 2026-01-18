package com.example.control;

import com.example.model.LoginCredentials;
import com.example.entity.LoginInteractor;
import com.example.service.AuthenticationService;
import com.example.model.AuthenticationResult;

/**
 * Control class that orchestrates the login flow.
 * Uses AuthenticationService and delegates to LoginInteractor on failure.
 */
public class LoginController {

    private AuthenticationService authService;
    private LoginInteractor loginInteractor;

    public LoginController(AuthenticationService authService, LoginInteractor loginInteractor) {
        this.authService = authService;
        this.loginInteractor = loginInteractor;
    }

    /**
     * Handles a login attempt with given credentials.
     * @param credentials the login credentials (username and password)
     */
    public void handleLoginAttempt(LoginCredentials credentials) {
        System.out.println("LoginController: Handling login attempt for user: " + credentials.getUsername());
        // Core business logic orchestration (corresponds to note m12)
        // Note m12: "Core business logic orchestrates the flow"
        AuthenticationResult result = authService.authenticate(credentials);
        
        if (!result.isSuccess()) {
            System.out.println("LoginController: Authentication failed - " + result.getMessage());
            handleAuthenticationFailure();
        } else {
            System.out.println("LoginController: Authentication successful.");
            // In a real system, proceed to successful login flow (not shown in diagrams).
        }
    }

    /**
     * Handles authentication failure by delegating to the interactor.
     * This method is called when authentication fails.
     */
    public void handleAuthenticationFailure() {
        System.out.println("LoginController: Handling authentication failure.");
        loginInteractor.executeOnFailure();
    }
}