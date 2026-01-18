package com.example.etour;

/**
 * Repository interface for session persistence.
 */
public interface SessionRepository {
    SessionEntity save(SessionEntity session);
    SessionEntity findById(String sessionId);
    void deleteByUserId(String userId);
}