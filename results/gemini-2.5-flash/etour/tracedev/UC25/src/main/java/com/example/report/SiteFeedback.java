package com.example.report;

import java.util.Date;

/**
 * SiteFeedback class
 * Represents a single feedback submission for a location.
 */
public class SiteFeedback {
    // CD: SiteFeedback attribute id
    private String id;
    private String locationId;
    // CD: SiteFeedback attribute rating
    private int rating; // e.g., 1 to 5 stars
    // CD: SiteFeedback attribute comment
    private String comment;
    // CD: SiteFeedback attribute submissionDate
    private Date submissionDate;

    /**
     * Constructor for SiteFeedback.
     * @param id Unique identifier for the feedback.
     * @param locationId ID of the location this feedback is for.
     * @param rating Rating given by the user.
     * @param comment User's textual comment.
     * @param submissionDate Date of feedback submission.
     */
    public SiteFeedback(String id, String locationId, int rating, String comment, Date submissionDate) {
        this.id = id;
        this.locationId = locationId;
        this.rating = rating;
        this.comment = comment;
        this.submissionDate = submissionDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }
}