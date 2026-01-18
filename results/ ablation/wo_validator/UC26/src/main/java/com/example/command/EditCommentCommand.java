package com.example.command;

/**
 * Command object for editing a feedback comment.
 */
public class EditCommentCommand {
    private int feedbackId;
    private String newComment;
    private String operatorId;

    public EditCommentCommand(int feedbackId, String newComment, String operatorId) {
        this.feedbackId = feedbackId;
        this.newComment = newComment;
        this.operatorId = operatorId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getNewComment() {
        return newComment;
    }

    public String getOperatorId() {
        return operatorId;
    }
}