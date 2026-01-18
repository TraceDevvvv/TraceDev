package com.example.domain;

/**
 * Service to authenticate operators.
 * Added to satisfy requirement: Entry Condition "Operator HAS successfully authenticated".
 */
public class AuthenticationService {
    public boolean authenticate(String operatorId) {
        // In a real application, this would validate credentials against an identity provider
        // For this example, assume authentication always succeeds for non-empty operatorId
        return operatorId != null && !operatorId.trim().isEmpty();
    }
}