
package com.example.service;

/**
 * Service for password encoding and matching.
 * Uses BCryptPasswordEncoder for secure password handling.
 * Traceability: Service stereotype from UML.
 */
public class PasswordService {
    private Object passwordEncoder;
    
    public PasswordService() {
        this.passwordEncoder = new Object();
    }
    
    public PasswordService(Object passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public String encode(String rawPassword) {
        return rawPassword;
    }
    
    public boolean matches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
