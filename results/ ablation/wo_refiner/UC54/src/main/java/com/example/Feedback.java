package com.example;

/**
 * Entity representing feedback for a site.
 * Satisfies REQ-001, REQ-005, REQ-009 (feedback aggregation).
 */
public class Feedback {
    private String id;
    private String siteId;
    private double overallRating;

    public Feedback(String id, String siteId, double overallRating) {
        this.id = id;
        this.siteId = siteId;
        this.overallRating = overallRating;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public double getOverallRating() {
        return overallRating;
    }
}