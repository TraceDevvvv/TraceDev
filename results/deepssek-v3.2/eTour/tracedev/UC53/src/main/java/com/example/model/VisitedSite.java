package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a VisitedSite record.
 */
public class VisitedSite {
    private String visitedId;
    private String touristId;
    private String siteId;
    private LocalDateTime visitDate;

    /**
     * Constructor as per sequence diagram requirement.
     */
    public VisitedSite(String touristId, String siteId) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.visitDate = LocalDateTime.now();
        this.visitedId = "VS_" + touristId + "_" + siteId + "_" + visitDate.toString();
    }

    public String getVisitedId() {
        return visitedId;
    }

    public void setVisitedId(String visitedId) {
        this.visitedId = visitedId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }
}