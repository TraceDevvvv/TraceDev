package com.culturalheritage.application.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Service to ensure idempotency of delete requests using confirmation tokens.
 */
public class IdempotencyService {
    private Set<String> processedTokens = new HashSet<>();

    public boolean isRequestProcessed(String token) {
        return processedTokens.contains(token);
    }

    public void markRequestAsProcessed(String token) {
        processedTokens.add(token);
    }
}