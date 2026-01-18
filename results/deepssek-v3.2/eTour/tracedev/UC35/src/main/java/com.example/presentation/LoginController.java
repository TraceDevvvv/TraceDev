
package com.example.presentation;

import com.example.interfaces.IAuthenticatorService;
import com.example.interfaces.IUserQueryService;
import com.example.application.LoginCommand;
import com.example.dtos.AuthToken;
import com.example.dtos.RoleDetails;
import com.example.dtos.CredentialsDTO;
import com.example.interfaces.ILogger;

/**
 * Controller for handling login-related HTTP requests.
 * Presentation layer component.
 */
public class LoginController {
    private IAuthenticatorService authenticatorService;
    private IUserQueryService userQueryService;
    private Object systemAccessValidator;
    private ILogger logger;
    private Object loginErrorUseCase;

    /**
     * Constructor with dependencies.
     */
    public LoginController(IAuthenticatorService authenticatorService,
                           IUserQueryService userQueryService,
                           Object systemAccessValidator,
                           ILogger logger,
                           Object loginErrorUseCase) {
        this.authenticatorService = authenticatorService;
        this.userQueryService = userQueryService;
        this.systemAccessValidator = systemAccessValidator;
        this.logger = logger;
        this.loginErrorUseCase = loginErrorUseCase;
    }

    /**
     * Displays the login form.
     * Validates system access before showing the form.
     *
     * @return the login form view
     */
    public LoginFormView showLoginForm() {
        // Entry condition: User HAS access to the system
        String ip = getClientIp(); // Assumption: method to retrieve client IP
        boolean hasAccess = true; // Simplified validation
        if (!hasAccess) {
            throw new SecurityException("Access denied for IP: " + ip);
        }
        logger.info("Login form requested for IP: " + ip);
        return new LoginFormView();
    }

    /**
     * Handles login form submission.
     * Quality Requirement: Login process ≤ 2 seconds under normal network conditions.
     *
     * @param loginCommand the login data from the form
     * @return ResponseEntity containing authentication result (simplified as AuthResponse)
     */
    public AuthResponse submitLogin(LoginCommand loginCommand) {
        long startTime = System.currentTimeMillis();
        try {
            logger.info("Login attempt for user: " + loginCommand.getUsername());

            // Create CredentialsDTO from LoginCommand
            CredentialsDTO credentials = new CredentialsDTO(
                loginCommand.getUsername(), 
                loginCommand.getPassword()
            );
            
            // Delegate to command handler
            AuthToken token = authenticatorService.authenticate(credentials);
            
            if (token != null) {
                // Retrieve user role (CQRS query side)
                RoleDetails roleDetails = userQueryService.getUserRole(token.getUserId());
                // Display work area
                WorkAreaView workAreaView = new WorkAreaView(roleDetails.getRole());
                workAreaView.display(roleDetails);

                long duration = System.currentTimeMillis() - startTime;
                logger.info("Login successful for user: " + loginCommand.getUsername() + " in " + duration + " ms");
                return new AuthResponse(true, "Login successful", token.getToken());
            } else {
                // Login failed
                logger.error("Login failed for user: " + loginCommand.getUsername() + " - Authentication failed");
                return new AuthResponse(false, "Authentication failed", null);
            }
        } catch (Exception e) {
            // Exception flow: Connection Interrupted
            logger.error("Service unavailable during login: " + e.getMessage());
            return new AuthResponse(false, "Server unavailable", null);
        }
    }

    /**
     * Cancels the login operation.
     * Exit condition: User cancels operation.
     */
    public void cancelLogin() {
        logger.info("Login cancelled by user.");
        // Close login form (UI logic would be here)
    }

    // Helper method to simulate getting client IP
    private String getClientIp() {
        return "127.0.0.1"; // Simplified for example
    }

    // Simplified response class for demonstration
    public static class AuthResponse {
        private boolean success;
        private String message;
        private String token;

        public AuthResponse(boolean success, String message, String token) {
            this.success = success;
            this.message = message;
            this.token = token;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public String getToken() { return token; }
    }
}
