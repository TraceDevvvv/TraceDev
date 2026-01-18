package com.duplicatefeedback.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain entity representing a Feedback.
 * Core business object with identity and attributes.
 */
public class Feedback {
    private final String id;
    private final String siteId;
    private final String userId;
    private final String content;
    private final LocalDateTime timestamp;

    public Feedback(String id, String siteId, String userId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.siteId = siteId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) &&
                Objects.equals(siteId, feedback.siteId) &&
                Objects.equals(userId, feedback.userId) &&
                Objects.equals(content, feedback.content) &&
                Objects.equals(timestamp, feedback.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, userId, content, timestamp);
    }
}