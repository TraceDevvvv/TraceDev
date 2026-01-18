package com.example.domain;

import java.util.Date;
import java.util.UUID; // For generating unique feedback IDs

/**
 * Represents a feedback entry submitted by a tourist for a specific site.
 * This is a core domain entity.
 */
public class Feedback {
    public String feedbackId;
    public int vote; // e.g., 1 to 5 stars
    public String comment;
    public Date submissionDate;
    public String touristId; // Reference to the Tourist who submitted the feedback
    public String siteId;    // Reference to the Site for which feedback was submitted

    /**
     * Private constructor to enforce creation via the static factory method.
     * @param feedbackId Unique identifier for the feedback.
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     * @param vote The rating given (e.g., 1-5).
     * @param comment The textual comment.
     * @param submissionDate The date of submission.
     */
    private Feedback(String feedbackId, String touristId, String siteId, int vote, String comment, Date submissionDate) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
        this.submissionDate = submissionDate;
    }

    /**
     * Static factory method to create a new Feedback instance.
     * Encapsulates the logic for generating a unique ID and setting the submission date.
     * @param touristId The ID of the tourist submitting the feedback.
     * @param siteId The ID of the site for which feedback is submitted.
     * @param vote The vote/rating provided by the tourist.
     * @param comment The comment provided by the tourist.
     * @return A new Feedback object.
     */
    public static Feedback create(String touristId, String siteId, int vote, String comment) {
        String newFeedbackId = UUID.randomUUID().toString(); // Generate a unique ID
        Date currentSubmissionDate = new Date();             // Set current date
        return new Feedback(newFeedbackId, touristId, siteId, vote, comment, currentSubmissionDate);
    }

    // Getters for all attributes (not explicitly in diagram but good practice)
    public String getFeedbackId() {
        return feedbackId;
    }

    public int getVote() {
        return vote;
    }

    public String getComment() {
        return comment;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    @Override
    public String toString() {
        return "Feedback{" +
               "feedbackId='" + feedbackId + '\'' +
               ", touristId='" + touristId + '\'' +
               ", siteId='" + siteId + '\'' +
               ", vote=" + vote +
               ", comment='" + comment + '\'' +
               ", submissionDate=" + submissionDate +
               '}';
    }
}