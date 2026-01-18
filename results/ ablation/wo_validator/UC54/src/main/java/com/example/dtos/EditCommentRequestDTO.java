package com.example.dtos;

/**
 * Data Transfer Object for the edit comment request.
 */
public class EditCommentRequestDTO {
    private String commentId;
    private String touristId;
    private String siteId;
    private String newContent;

    /**
     * Constructs a new request DTO.
     *
     * @param commentId the comment ID
     * @param touristId the tourist ID
     * @param siteId the site ID
     * @param newContent the new content to set
     */
    public EditCommentRequestDTO(String commentId, String touristId, String siteId, String newContent) {
        this.commentId = commentId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.newContent = newContent;
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
     * Returns the new content.
     *
     * @return the newContent
     */
    public String getNewContent() {
        return newContent;
    }
}