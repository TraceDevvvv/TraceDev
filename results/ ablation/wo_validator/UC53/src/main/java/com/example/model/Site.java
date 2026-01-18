package com.example.model;

/**
 * Represents a tourist site with an average rating and total ratings.
 */
public class Site {
    private String siteId;
    private String siteName;
    private double averageRating;
    private int totalRatings;

    public Site(String siteId, String siteName) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.averageRating = 0.0;
        this.totalRatings = 0;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return siteName;
    }

    public void setName(String siteName) {
        this.siteName = siteName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }
}