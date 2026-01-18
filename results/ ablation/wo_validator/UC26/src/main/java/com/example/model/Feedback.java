package com.example.model;

/**
 * Represents a feedback entry with a comment and status.
 */
public class Feedback {
    private int feedbackId;
    private String comment;
    private FeedbackStatus status;

    public Feedback(int feedbackId, String comment, FeedbackStatus status) {
        this.feedbackId = feedbackId;
        this.comment = comment;
        this.status = status;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getComment() {
        return comment;
    }

    /**
     * Updates the comment of this feedback.
     * @param newComment The new comment text.
     */
    public void updateComment(String newComment) {
        this.comment = newComment;
        this.status = FeedbackStatus.REVIEWED; // Assuming status changes on edit
        System.out.println("Feedback " + feedbackId + " comment updated.");
    }

    /**
     * Checks if feedback is valid for editing.
     * @return true if feedback is valid.
     */
    public boolean isValid() {
        return comment != null && !comment.trim().isEmpty();
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}