package com.example.dto;

/**
 * Command object for updating a feedback comment.
 */
public class UpdateCommentCommand {
    public String feedbackId;
    public String newComment;

    // Constructor
    public UpdateCommentCommand(String feedbackId, String newComment) {
        this.feedbackId = feedbackId;
        this.newComment = newComment;
    }

    // Getters
    public String getFeedbackId() {
        return feedbackId;
    }

    public String getNewComment() {
        return newComment;
    }
}