package com.etour.dto;

import com.etour.error.ValidationError;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for comment operation result.
 */
public class CommentResultDTO {
    private boolean success;
    private String commentId;
    private String updatedContent;
    private LocalDateTime timestamp;
    private String message;
    private List<ValidationError> errors;

    // Private constructor for factory methods
    private CommentResultDTO(boolean success, String commentId, String updatedContent,
                            LocalDateTime timestamp, String message, List<ValidationError> errors) {
        this.success = success;
        this.commentId = commentId;
        this.updatedContent = updatedContent;
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Factory method for successful result.
     */
    public static CommentResultDTO success(String commentId, String updatedContent,
                                           LocalDateTime timestamp, String message) {
        return new CommentResultDTO(true, commentId, updatedContent, timestamp, message, null);
    }

    /**
     * Factory method for error result with validation errors.
     */
    public static CommentResultDTO error(String message, List<ValidationError> errors) {
        return new CommentResultDTO(false, null, null, LocalDateTime.now(), message, errors);
    }

    /**
     * Factory method for error result without validation errors.
     */
    public static CommentResultDTO error(String message) {
        return new CommentResultDTO(false, null, null, LocalDateTime.now(), message, null);
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUpdatedContent() {
        return updatedContent;
    }

    public void setUpdatedContent(String updatedContent) {
        this.updatedContent = updatedContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;