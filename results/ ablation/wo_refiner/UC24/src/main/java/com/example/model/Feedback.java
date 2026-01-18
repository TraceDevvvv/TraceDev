package com.example.model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents feedback for a specific site with content and timestamp.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String content;
    private Date timestamp;

    public Feedback(String id, String siteId, String content, Date timestamp) {
        this.id = id;
        this.siteId = siteId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(siteId, feedback.siteId)
                && Objects.equals(content, feedback.content) && Objects.equals(timestamp, feedback.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, content, timestamp);
    }

    @Override
    public String toString() {
        return "Feedback{id='" + id + "', siteId='" + siteId + "', content='" + content + "', timestamp=" + timestamp + "}";
    }
}