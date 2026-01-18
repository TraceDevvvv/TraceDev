package com.example.application;

import com.example.interfaces.ITokenService;
import com.example.dtos.AuthToken;
import com.example.domain.User;
import java.time.LocalDateTime;

/**
 * Service for JWT token generation and validation.
 */
public class JwtTokenService implements ITokenService {
    private String secretKey;

    public JwtTokenService(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public AuthToken generateToken(User user) {
        // Simplified token generation.
        String token = "jwt." + user.getUserId() + "." + System.currentTimeMillis();
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);
        return new AuthToken(token, user.getUserId(), expiresAt);
    }

    @Override
    public boolean validateToken(String token) {
        // Simplified validation.
        return token != null && token.startsWith("jwt.");
    }

    @Override
    public String extractUserId(String token) {
        // Simplified extraction.
        if (token.startsWith("jwt.")) {
            String[] parts = token.split("\\.");
            if (parts.length >= 2) {
                return parts[1];
            }
        }
        return null;
    }
}