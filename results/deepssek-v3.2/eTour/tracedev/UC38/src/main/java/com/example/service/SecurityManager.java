package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages security and session validation.
 * Satisfies quality requirement REQ-SEC-001.
 */
public class SecurityManager {
    private String sessionToken;
    private Map<String, String> sessionStore = new HashMap<>();

    public boolean validateSession(String token) {
        return sessionStore.containsKey(token);
    }

    public String createSession(String userId) {
        String token = UUID.randomUUID().toString();
        sessionStore.put(token, userId);
        this.sessionToken = token;
        return token;
    }

    public void invalidateSession(String token) {
        sessionStore.remove(token);
    }

    public String getSessionToken() {
        return sessionToken;
    }
}