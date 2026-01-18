package com.example.etour;

import java.util.Date;
import java.util.UUID;

/**
 * JWT-based authentication service.
 */
public class JwtAuthService implements AuthService {
    private String secretKey = "secret";
    private SessionRepository sessionRepository;
    private SessionStore sessionStore;

    public JwtAuthService() {
        this.sessionRepository = new JpaSessionRepository();
        this.sessionStore = new SessionStore();
    }

    @Override
    public AuthResultDTO authenticate(LoginDTO loginData) {
        // Generate session token.
        String token = generateSessionToken(loginData.username);
        // Create session entity and save.
        SessionEntity session = new SessionEntity();
        String sessionId = UUID.randomUUID().toString();
        session.setSessionId(sessionId);
        session.setUserId(loginData.username);
        session.setCreatedAt(new Date());
        Date expiry = new Date(System.currentTimeMillis() + 3600000); // 1 hour
        session.setExpiresAt(expiry);
        sessionRepository.save(session);
        
        // Store session in SessionStore
        storeSession(sessionId, loginData.username, expiry);

        AuthResultDTO result = new AuthResultDTO();
        result.success = true;
        result.message = "Authentication successful";
        result.token = token;
        return result;
    }

    @Override
    public String generateSessionToken(String userId) {
        // Simulate JWT token generation.
        return "jwt_token_" + userId + "_" + System.currentTimeMillis();
    }

    @Override
    public void invalidateSession(String userId) {
        sessionRepository.deleteByUserId(userId);
        sessionStore.deleteByUserId(userId);
    }

    /**
     * Validates a given token.
     * @param token the token to validate.
     * @return true if valid.
     */
    public Boolean validateToken(String token) {
        // Simple validation: token not empty.
        return token != null && !token.isEmpty();
    }

    /**
     * Stores a session in the SessionStore.
     * @param sessionId the session ID.
     * @param userId the user ID.
     * @param expiry the expiry date.
     */
    public void storeSession(String sessionId, String userId, Date expiry) {
        sessionStore.storeSession(sessionId, userId, expiry);
    }
}