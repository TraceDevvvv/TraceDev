package com.example.service;

import com.example.model.LoginRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Security validation module (implements security quality attribute REQ‑010).
 */
public class ValidationModule {
    // Simulated validation rules
    private Map<String, String> rules;

    public ValidationModule() {
        rules = new HashMap<>();
        rules.put("username.minLength", "3");
        rules.put("password.minLength", "6");
    }

    /**
     * Validates credentials against security rules.
     * @param loginRequest the login request
     * @return true if credentials are valid
     */
    public boolean validateCredentials(LoginRequest loginRequest) {
        // Simulate credential validation
        return "admin".equals(loginRequest.username) && "password123".equals(loginRequest.password);
    }

    /**
     * Checks format of login request.
     * @param loginRequest the login request
     * @return a FormatValidationResult (simplified as boolean for this implementation)
     */
    public boolean checkFormat(LoginRequest loginRequest) {
        return loginRequest.validate();
    }

    public Map<String, String> getValidationRules() {
        return new HashMap<>(rules);
    }

    /**
     * Performs additional security validation (e.g., password strength, threat detection).
     * @param loginRequest the login request
     * @return SecurityValidationResult (simplified as boolean here)
     */
    public boolean performSecurityValidation(LoginRequest loginRequest) {
        // Simulate security checks
        return loginRequest.password != null && loginRequest.password.length() >= 6;
    }
}