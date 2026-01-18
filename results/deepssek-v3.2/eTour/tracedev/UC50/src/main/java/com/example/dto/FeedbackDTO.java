package com.example.dto;

import java.util.Date;

/**
 * DTO for Feedback.
 * Added to satisfy requirement Description - Feedback History.
 */
public class FeedbackDTO {
    public String feedbackId;
    public Integer rating;
    public String comment;
    public Date date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String feedbackId, Integer rating, String comment, Date date) {
        this.feedbackId = feedbackId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}