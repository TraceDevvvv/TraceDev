package com.example.dtos;

/**
 * Data Transfer Object for the edit comment response.
 */
public class EditCommentResponseDTO {
    private String commentId;
    private String touristId;
    private String siteId;
    private String updatedContent;
    private boolean success;
    private String message;

    /**
     * Constructs a new response DTO.
     *
     * @param commentId the comment ID
     * @param touristId the tourist ID
     * @param siteId the site ID
     * @param updatedContent the updated content (may be null if failure)
     * @param success whether the operation succeeded
     * @param message a message describing the outcome
     */
    public EditCommentResponseDTO(String commentId, String touristId, String siteId, String updatedContent, boolean success, String message) {
        this.commentId = commentId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.updatedContent = updatedContent;
        this.success = success;
        this.message = message;
    }

    /**
     * Returns the comment ID.
     *
     * @return the commentId
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * Returns the tourist ID.
     *
     * @return the touristId
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the site ID.
     *
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Returns the updated content.
     *
     * @return the updatedContent
     */
    public String getUpdatedContent() {
        return updatedContent;
    }

    /**
     * Returns whether the operation succeeded.
     *
     * @return true if success, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}