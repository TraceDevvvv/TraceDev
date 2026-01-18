// Represents a feedback issued by a tourist for a site
package com.etour.model;

public class Feedback {
    private int feedbackId;
    private int touristId;
    private int siteId;
    private String siteName;
    private String feedbackText;

    public Feedback() {}

    public Feedback(int feedbackId, int touristId, int siteId, String siteName, String feedbackText) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.siteName = siteName;
        this.feedbackText = feedbackText;
    }

    // Getters and setters
    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }

    public int getTouristId() { return touristId; }
    public void setTouristId(int touristId) { this.touristId = touristId; }

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getFeedbackText() { return feedbackText; }
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    @Override
    public String toString() {
        return "Site: " + siteName + " (ID: " + siteId + ")";
    }
}