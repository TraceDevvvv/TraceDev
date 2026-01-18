package com.example.touristapp.dto;

/**
 * Data Transfer Object (DTO) to combine Site and Feedback information for display.
 * This class is used to transfer aggregated data from the application layer to the presentation layer.
 */
public class SiteFeedbackDto {
    private String siteId;
    private String siteName;
    private String siteLocation;
    private String feedbackComment;
    private int feedbackRating;
    private String touristId; // Added for completeness, if needed in UI

    /**
     * Constructs a new SiteFeedbackDto.
     *
     * @param siteId The unique identifier of the site.
     * @param siteName The name of the site.
     * @param siteLocation The location of the site.
     * @param feedbackComment The comment provided by the tourist for this site.
     * @param feedbackRating The rating given by the tourist for this site.
     * @param touristId The unique identifier of the tourist who provided the feedback.
     */
    public SiteFeedbackDto(String siteId, String siteName, String siteLocation,
                           String feedbackComment, int feedbackRating, String touristId) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteLocation = siteLocation;
        this.feedbackComment = feedbackComment;
        this.feedbackRating = feedbackRating;
        this.touristId = touristId;
    }

    // Getters for all fields
    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteLocation() {
        return siteLocation;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public int getFeedbackRating() {
        return feedbackRating;
    }

    public String getTouristId() {
        return touristId;
    }

    /**
     * Provides a string representation of the DTO for display purposes.
     * @return A formatted string with site and feedback details.
     */
    @Override
    public String toString() {
        return "Site ID: " + siteId +
               ", Name: " + siteName +
               ", Location: " + siteLocation +
               ", Feedback: '" + (feedbackComment != null ? feedbackComment : "No feedback") + "'" +
               ", Rating: " + (feedbackRating > 0 ? feedbackRating : "N/A") + "/5";
    }
}