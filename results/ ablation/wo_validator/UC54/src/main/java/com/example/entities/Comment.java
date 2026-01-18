package com.example.entities;

import java.time.LocalDateTime;

/**
 * Represents a comment made by a tourist on a site.
 * This is an entity in the domain layer.
 */
public class Comment {
    private String commentId;
    private String touristId;
    private String siteId;
    private String content;
    private LocalDateTime timestamp;

    /**
     * Constructs a new Comment with the given parameters.
     * The timestamp is set to the current date and time.
     *
     * @param commentId the unique identifier of the comment
     * @param touristId the identifier of the tourist who made the comment
     * @param siteId the identifier of the site the comment is about
     * @param content the text content of the comment
     */
    public Comment(String commentId, String touristId, String siteId, String content) {
        this.commentId = commentId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Edits the content of the comment.
     *
     * @param newContent the new content to set
     */
    public void editContent(String newContent) {
        this.content = newContent;
        this.timestamp = LocalDateTime.now(); // Update timestamp on edit
    }

    /**
     * Returns the current content of the comment.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the comment's unique identifier.
     *
     * @return the commentId
     */
    public String getId() {
        return commentId;
    }

    /**
     * Returns the tourist's identifier.
     *
     * @return the touristId
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the site's identifier.
     *
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Returns the timestamp of the comment (creation or last edit).
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}