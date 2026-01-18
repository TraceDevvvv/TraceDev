package com.example.domain.security;

/**
 * Result of authentication (assumed from LoginService).
 */
public class AuthenticationResult {
    private boolean success;
    private String token;
    private String message;
    
    public AuthenticationResult(boolean success, String token, String message) {
        this.success = success;
        this.token = token;
        this.message = message;
    }
    
    public boolean isSuccess() { return success; }
    public String getToken() { return token; }
    public String getMessage() { return message; }
}