package com.example.service;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Service for generating and validating confirmation tokens.
 * Implements blocking of multiple submissions (REQ-013).
 */
public class ConfirmationService {
    private Map<String, LocalDateTime> pendingConfirmations;
    private Set<String> usedTokens;
    private static final int TOKEN_VALIDITY_MINUTES = 5;

    public ConfirmationService() {
        this.pendingConfirmations = new HashMap<>();
        this.usedTokens = new HashSet<>();
    }

    public String generateToken() {
        String token = java.util.UUID.randomUUID().toString();
        pendingConfirmations.put(token, LocalDateTime.now());
        return token;
    }

    public boolean validateToken(String token) {
        if (usedTokens.contains(token)) {
            return false; // Already used
        }
        LocalDateTime generatedTime = pendingConfirmations.get(token);
        if (generatedTime == null) {
            return false; // Unknown token
        }
        long minutesSinceGeneration = ChronoUnit.MINUTES.between(generatedTime, LocalDateTime.now());
        if (minutesSinceGeneration > TOKEN_VALIDITY_MINUTES) {
            pendingConfirmations.remove(token);
            return false; // Expired
        }
        return true;
    }

    public void markTokenUsed(String token) {
        usedTokens.add(token);
        pendingConfirmations.remove(token);
    }

    // Added to satisfy requirement REQ-013
    public boolean blockMultipleSubmissions(String token) {
        // If token is already in usedTokens, block the submission.
        return !usedTokens.contains(token);
    }

    public boolean tokenValid(String token) {
        // This method corresponds to the message "token valid (true/false)" in the sequence diagram.
        return validateToken(token);
    }
}