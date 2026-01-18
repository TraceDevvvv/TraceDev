package com.example.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the state after a recovery attempt.
 */
public class RecoveryState {
    public SessionState recoveredSession;
    public LocalDateTime recoveryTime;
    public boolean isSuccessful;

    public RecoveryState(SessionState recoveredSession, boolean isSuccessful) {
        this.recoveredSession = recoveredSession;
        this.isSuccessful = isSuccessful;
        this.recoveryTime = LocalDateTime.now();
    }

    public Map<String, Object> getRecoveredData() {
        Map<String, Object> data = new HashMap<>();
        if (recoveredSession != null) {
            data.put("sessionId", recoveredSession.sessionId);
            data.put("previousUrl", recoveredSession.previousUrl);
            data.put("timestamp", recoveredSession.timestamp);
        }
        return data;
    }
}