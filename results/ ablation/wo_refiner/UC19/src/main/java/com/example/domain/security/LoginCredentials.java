package com.example.domain.security;

/**
 * DTO for login credentials (assumed from LoginService usage).
 */
public class LoginCredentials {
    private String username;
    private String password;
    
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}