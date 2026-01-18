package com.etour.dto;

import com.etour.error.ValidationResult;
import com.etour.error.ValidationError;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for edit comment request.
 */
public class EditCommentRequestDTO {
    private String touristId;
    private String commentId;
    private String newContent;

    public EditCommentRequestDTO(String touristId, String commentId, String newContent) {
        this.touristId = touristId;
        this.commentId = commentId;
        this.newContent = newContent;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    /**
     * Validates the DTO fields as per sequence diagram REQ-FLOW-002.
     * @return ValidationResult indicating success or failure.
     */
    public ValidationResult validate() {
        List<ValidationError> errors = new ArrayList<>();
        if (touristId == null || touristId.trim().isEmpty()) {
            errors.add(new ValidationError("touristId", "Tourist ID is required", "REQ-001"));
        }
        if (commentId == null || commentId.trim().isEmpty()) {
            errors.add(new ValidationError("commentId", "Comment ID is required", "REQ-002"));
        }
        if (newContent == null || newContent.trim().isEmpty()) {
            errors.add(new ValidationError("newContent", "New content cannot be empty", "REQ-003"));
        } else if (newContent.length() > 500) {
            errors.add(new ValidationError("newContent", "Content exceeds 500 characters", "REQ-004"));
        }
        return new ValidationResult(errors);
    }
}