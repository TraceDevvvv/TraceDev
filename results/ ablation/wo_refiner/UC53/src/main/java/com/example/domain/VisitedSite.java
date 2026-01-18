package com.example.domain;

import java.time.LocalDateTime;

/**
 * Represents a visited site record.
 */
public class VisitedSite {
    private String touristId;
    private String siteId;
    private LocalDateTime firstVisited;

    public VisitedSite(String touristId, String siteId, LocalDateTime firstVisited) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.firstVisited = firstVisited;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public LocalDateTime getFirstVisited() {
        return firstVisited;
    }
}