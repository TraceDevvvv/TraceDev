
package com.example.session;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a user session in the system.
 */
public class UserSession {
    private final String sessionId;
    private final String userId;
    private final String role;
    private boolean isActive;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public UserSession(String sessionId, String userId, String role) {
        this.sessionId = Objects.requireNonNull(sessionId);
        this.userId = Objects.requireNonNull(userId);
        this.role = Objects.requireNonNull(role);
        this.isActive = true;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
