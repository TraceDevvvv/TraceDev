package com.etour.feedback;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a feedback entry from a tourist for a specific site.
 */
public class Feedback {
    private final String touristId;
    private final String siteId;
    private final int vote; // e.g., 1 to 5 stars
    private final String comment;
    private final LocalDateTime timestamp;

    public Feedback(String touristId, String siteId, int vote, String comment) {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty");
        }
        if (vote < 1 || vote > 5) {
            throw new IllegalArgumentException("Vote must be between 1 and 5");
        }
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        this.touristId = touristId.trim();
        this.siteId = siteId.trim();
        this.vote = vote;
        this.comment = comment.trim();
        this.timestamp = LocalDateTime.now();
    }

    public String getTouristId() { return touristId; }
    public String getSiteId() { return siteId; }
    public int getVote() { return vote; }
    public String getComment() { return comment; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return touristId.equals(feedback.touristId) && siteId.equals(feedback.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(touristId, siteId);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "touristId='" + touristId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", vote=" + vote +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}