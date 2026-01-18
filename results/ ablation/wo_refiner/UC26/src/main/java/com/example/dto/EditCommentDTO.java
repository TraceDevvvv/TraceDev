package com.example.dto;

/**
 * Data Transfer Object for editing a comment.
 */
public class EditCommentDTO {
    private int commentId;
    private String newContent;

    public EditCommentDTO(int commentId, String newContent) {
        this.commentId = commentId;
        this.newContent = newContent;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getNewContent() {
        return newContent;
    }

    public boolean validate() {
        return newContent != null && !newContent.trim().isEmpty() && commentId > 0;
    }
}