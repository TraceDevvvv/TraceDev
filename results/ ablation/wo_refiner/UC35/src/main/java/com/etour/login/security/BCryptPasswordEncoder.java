package com.etour.login.security;

/**
 * A simple implementation of PasswordEncoder.
 * In a real application, use a proper library like Spring Security BCrypt.
 * This is a dummy implementation for demonstration.
 */
public class BCryptPasswordEncoder implements PasswordEncoder {
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        // In reality, this would use BCrypt algorithm
        // For demo, we simulate matching by checking if raw password ends with "123"
        // and the encoded password contains "dummyhash"
        // This is just to demonstrate the flow - not secure!
        return rawPassword != null && encodedPassword != null
                && rawPassword.endsWith("123") && encodedPassword.contains("dummyhash");
    }
}