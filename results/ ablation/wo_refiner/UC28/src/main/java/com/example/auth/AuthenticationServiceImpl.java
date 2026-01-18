package com.example.auth;

import com.example.agency.AgencyOperator;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of AuthenticationService.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    // Simulated token storage; in real app, use secure storage
    private Map<String, Session> sessionStore = new HashMap<>();
    private Map<String, AgencyOperator> userStore = new HashMap<>();

    public AuthenticationServiceImpl() {
        // Initialize with a sample user
        AgencyOperator sampleOp = new AgencyOperator(1L, "Operator1");
        userStore.put("operator1", sampleOp);
    }

    @Override
    public boolean validateSession(String token) {
        Session session = sessionStore.get(token);
        return session != null && session.isValid();
    }

    @Override
    public AgencyOperator getCurrentUser() {
        // Simplified: return first user as current.
        // In real app, retrieve from session/security context.
        return userStore.values().iterator().next();
    }

    @Override
    public Session login(String username, String password) {
        // Simplified authentication: check if user exists (ignoring password for simplicity)
        if (userStore.containsKey(username)) {
            AgencyOperator user = userStore.get(username);
            Session session = new Session(user.getId());
            sessionStore.put(session.getToken(), session);
            return session;
        }
        throw new SecurityException("Invalid credentials");
    }

    @Override
    public void logout(String token) {
        sessionStore.remove(token);
    }
}