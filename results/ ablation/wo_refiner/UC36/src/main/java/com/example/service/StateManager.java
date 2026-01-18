package com.example.service;

import com.example.model.SessionState;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages session state persistence.
 */
public class StateManager {
    private Map<String, SessionState> sessionStore = new HashMap<>();
    // Attribute Management>> from class diagram
    private List<State> management = new ArrayList<>();

    public StateManager() {
        // Pre‑populate with a sample session for testing
        sessionStore.put("session123", new SessionState("session123", "/home"));
    }

    public boolean saveState(SessionState sessionState) {
        sessionStore.put(sessionState.sessionId, sessionState);
        return true;
    }

    public SessionState loadState(String sessionId) {
        return sessionStore.get(sessionId);
    }

    public boolean clearState(String sessionId) {
        return sessionStore.remove(sessionId) != null;
    }

    /**
     * Internal restore method as per sequence diagram message internal restore()
     */
    public void internalRestore() {
        System.out.println("[StateManager] Internal restore performed.");
        // For example, reload all states into management list
        management.clear();
        for (SessionState ss : sessionStore.values()) {
            management.add(new State(ss.sessionId, ss.timestamp));
        }
    }

    // Inner class representing <<State>> stereotype
    private static class State {
        String sessionId;
        java.time.LocalDateTime timestamp;

        State(String sessionId, java.time.LocalDateTime timestamp) {
            this.sessionId = sessionId;
            this.timestamp = timestamp;
        }
    }
}