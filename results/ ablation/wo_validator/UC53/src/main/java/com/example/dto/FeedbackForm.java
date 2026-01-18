package com.example.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing a feedback form submitted by a tourist.
 */
public class FeedbackForm {
    private String siteId;
    private String touristId;
    private int rating;
    private String comment;
    private LocalDateTime submissionDate;

    public FeedbackForm() {
        this.submissionDate = LocalDateTime.now();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * Converts this form to a Feedback entity.
     * @return Feedback object with the same data.
     */
    public Feedback toFeedback() {
        Feedback feedback = new Feedback();
        feedback.setSiteId(siteId);
        feedback.setTouristId(touristId);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setSubmissionDate(submissionDate);
        // Generate a simple feedback ID (in real app, use UUID or DB sequence)
        feedback.setFeedbackId("FB" + System.currentTimeMillis());
        return feedback;
    }
}