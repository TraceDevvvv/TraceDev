package com.example;

/**
 * Data Transfer Object (DTO) for holding data related to editing a feedback comment.
 * Used to package feedback ID and the new comment text for processing.
 */
public class CommentEditDTO {
    public String feedbackId;
    public String newComment;

    /**
     * Constructs a new CommentEditDTO.
     * @param feedbackId The ID of the feedback whose comment is being edited.
     * @param newComment The new text for the comment.
     */
    public CommentEditDTO(String feedbackId, String newComment) {
        this.feedbackId = feedbackId;
        this.newComment = newComment;
    }
}