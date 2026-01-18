package com.example.service;

import com.example.model.LoginRequest;
import com.example.model.SessionState;
import com.example.model.ValidationResult;

/**
 * Core login service handling authentication, validation and state recovery.
 */
public class LoginService {
    private ValidationModule validationModule;
    private StateManager stateManager;

    public LoginService(ValidationModule validationModule, StateManager stateManager) {
        this.validationModule = validationModule;
        this.stateManager = stateManager;
    }

    /**
     * Authenticates a login request (implements REQ‑005).
     * @param loginRequest the login request
     * @return true if authentication succeeds
     */
    public boolean authenticate(LoginRequest loginRequest) {
        return validationModule.validateCredentials(loginRequest);
    }

    /**
     * Validates input format and security (implements REQ‑006 validation feedback).
     * @param loginRequest the login request
     * @return ValidationResult with details
     */
    public ValidationResult validateInput(LoginRequest loginRequest) {
        if (!loginRequest.validate()) {
            return new ValidationResult(false, 400, "Username or password empty");
        }
        if (!validationModule.checkFormat(loginRequest)) {
            return new ValidationResult(false, 422, "Invalid format");
        }
        if (!validationModule.performSecurityValidation(loginRequest)) {
            return new ValidationResult(false, 403, "Security validation failed");
        }
        return new ValidationResult(true, 200, "Validation passed");
    }

    /**
     * Recovers a previous session state.
     * @param sessionId the session identifier
     * @return the recovered SessionState (or null)
     */
    public SessionState recoverState(String sessionId) {
        return stateManager.loadState(sessionId);
    }

    /**
     * Cleans up session data (called when user does not confirm).
     * @param loginData the login request
     * @return true if cleanup succeeded
     */
    public boolean cleanupSession(LoginRequest loginData) {
        // Simulate cleanup – in real scenario might clear temporary data
        return true;
    }
}