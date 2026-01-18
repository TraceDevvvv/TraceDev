package com.example.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a feedback submitted for a site.
 * Corresponds to the Feedback class in the Class Diagram.
 */
public class Feedback {
    private int feedbackId;
    private int siteId;
    private String comment;
    private int rating;
    private Date dateSubmitted;

    public Feedback(int feedbackId, int siteId, String comment, int rating, Date dateSubmitted) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
        this.dateSubmitted = dateSubmitted;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedbackId == feedback.feedbackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", siteId=" + siteId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", dateSubmitted=" + dateSubmitted +
                '}';
    }
}