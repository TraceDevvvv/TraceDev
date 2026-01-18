package com.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a user session with state data.
 */
public class Session {
    private String sessionId;
    private Map<String, Object> userData;
    private Map<String, Object> stateData;

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.userData = new HashMap<>();
        this.stateData = new HashMap<>();
    }

    /**
     * Gets the current state data of the session.
     *
     * @return a map representing the state
     */
    public Map<String, Object> getState() {
        // Return a copy to avoid external modification.
        return new HashMap<>(stateData);
    }

    /**
     * Restores the session state from the provided state data.
     *
     * @param stateData the state data to restore
     */
    public void restoreState(Map<String, Object> stateData) {
        if (stateData != null) {
            this.stateData = new HashMap<>(stateData);
        }
    }

    // Getters and setters for other attributes (if needed)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, Object> getUserData() {
        return userData;
    }

    public void setUserData(Map<String, Object> userData) {
        this.userData = userData;
    }

    public Map<String, Object> getStateData() {
        return stateData;
    }

    public void setStateData(Map<String, Object> stateData) {
        this.stateData = stateData;
    }
}