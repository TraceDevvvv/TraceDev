package com.example.dto;

/**
 * Data Transfer Object for feedback form.
 */
public class FeedbackFormDTO {
    private int feedbackId;
    private int commentId;

    public FeedbackFormDTO(int feedbackId, int commentId) {
        this.feedbackId = feedbackId;
        this.commentId = commentId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getCommentId() {
        return commentId;
    }

    public boolean validate() {
        return feedbackId > 0 && commentId > 0;
    }
}