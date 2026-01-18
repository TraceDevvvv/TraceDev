package com.example.commentediting.domain;

import java.util.Date;

/**
 * Represents the Comment entity in the domain.
 */
public class Comment {
    private String id;
    private String text;
    private String touristId;
    private String siteId;
    private Date lastModifiedDate;

    /**
     * Constructs a new Comment.
     *
     * @param id The unique ID of the comment.
     * @param text The text content of the comment.
     * @param touristId The ID of the tourist who posted the comment.
     * @param siteId The ID of the site the comment is about.
     */
    public Comment(String id, String text, String touristId, String siteId) {
        this.id = id;
        this.text = text;
        this.touristId = touristId;
        this.siteId = siteId;
        this.lastModifiedDate = new Date(); // Initial creation date
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Updates the text of the comment if the provided touristId matches the comment's owner.
     * (R13: Should update lastModifiedDate implicitly)
     *
     * @param newText The new text for the comment.
     * @param requestingTouristId The ID of the tourist attempting to update the comment.
     * @return true if the comment was updated, false if the requestingTouristId does not match the owner.
     */
    public boolean updateText(String newText, String requestingTouristId) {
        if (this.touristId.equals(requestingTouristId)) {
            this.text = newText;
            this.lastModifiedDate = new Date(); // Update last modified date implicitly (R13)
            return true;
        }
        return false; // Tourist is not the owner
    }

    /**
     * Performs a basic validation check for the comment.
     *
     * @return true if the comment is considered valid, false otherwise.
     */
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
               text != null && !text.trim().isEmpty() &&
               touristId != null && !touristId.trim().isEmpty() &&
               siteId != null && !siteId.trim().isEmpty();
    }
}