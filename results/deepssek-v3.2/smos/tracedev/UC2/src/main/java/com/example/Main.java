package com.example;

import com.example.model.RegisteredUser;
import com.example.boundary.LoginForm;
import com.example.control.LoginController;
import com.example.service.AuthenticationService;
import com.example.entity.LoginInteractor;
import com.example.model.LoginCredentials;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram flow for authentication failure.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Authentication Failure Flow ===\n");

        // 1. Create actor (RegisteredUser) - though not actively used in code flow.
        RegisteredUser user = new RegisteredUser();

        // 2. Create boundary (LoginForm) which also implements FormDisplayPort.
        LoginForm loginForm = new LoginForm();

        // 3. Create service (AuthenticationService).
        AuthenticationService authService = new AuthenticationService();

        // 4. Create entity (LoginInteractor) with dependency on FormDisplayPort (which is loginForm).
        LoginInteractor interactor = new LoginInteractor(loginForm);

        // 5. Create controller with dependencies.
        LoginController controller = new LoginController(authService, interactor);

        // 6. Simulate the sequence diagram steps.
        // a) User calls attemptLogin() on LoginForm.
        loginForm.attemptLogin();

        // b) LoginForm creates credentials (simulated inside attemptLogin).
        LoginCredentials credentials = loginForm.getCredentials();

        // c) LoginForm calls controller.handleLoginAttempt(credentials) - we simulate this directly.
        controller.handleLoginAttempt(credentials);

        // The flow continues as per sequence diagram:
        // - Controller calls authService.authenticate
        // - AuthService returns failure result
        // - Controller calls handleAuthenticationFailure
        // - Controller calls interactor.executeOnFailure
        // - Interactor calls loginForm.displayLoginForm via port
        // - Form displays again (message printed)

        System.out.println("\n=== Flow completed. User can now try to log in again. ===");
    }
}