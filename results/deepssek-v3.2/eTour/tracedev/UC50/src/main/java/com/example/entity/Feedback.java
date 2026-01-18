package com.example.entity;

import java.util.Date;

/**
 * Entity for Feedback.
 * Added to satisfy requirement Description - Feedback History.
 */
public class Feedback {
    private String feedbackId;
    private Integer rating;
    private String comment;
    private Date date;

    public Feedback() {
    }

    public Feedback(String feedbackId, Integer rating, String comment, Date date) {
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