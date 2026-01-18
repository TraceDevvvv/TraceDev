
package com.example.model;

import java.util.Date;

/**
 * Represents feedback given for a specific site.
 */

public class Feedback {
    // Attributes
    private String feedbackId;
    private String siteId; // Association to Site
    private String reviewer;
    private int rating; // e.g., 1-5 stars
    private String comment;
    private Date feedbackDate;

    /**
     * Constructs a new Feedback instance.
     * @param feedbackId A unique identifier for the feedback.
     * @param siteId The ID of the site this feedback is for.
     * @param reviewer The name of the person who provided the feedback.
     * @param rating The rating given, typically an integer.
     * @param comment The detailed comment.
     * @param feedbackDate The date when the feedback was given.
     */
    public Feedback(String feedbackId, String siteId, String reviewer, int rating, String comment, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    }

    // Getters for attributes
    public String getFeedbackId() {
        return feedbackId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * Provides a detailed string representation of the feedback.
     * @return A string containing reviewer, rating, comment, and date.
     */
    public String getDetails() {
        return "Reviewer: " + reviewer + ", Rating: " + rating + "/5, Comment: '" + comment + "', Date: " + feedbackDate;
    }

    @Override
    public String toString() {
        return "Feedback{" +
               "feedbackId='" + feedbackId + '\'' +
               ", siteId='" + siteId + '\'' +
               ", reviewer='" + reviewer + '\'' +
               ", rating=" + rating +
               ", comment='" + comment + '\'' +
               ", feedbackDate=" + feedbackDate +
               '}';
    }
}
