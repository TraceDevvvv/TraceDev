package com.example;

import java.time.LocalDateTime;

/**
 * Entity representing a comment.
 * Updated to hold feedbackId (instead of siteId) per REQ-001, REQ-005, REQ-009.
 */
public class Comment {
    private String id;
    private String text;
    private String authorId;
    private String siteId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(String id, String authorId, String siteId, String text) {
        this.id = id;
        this.authorId = authorId;
        this.siteId = siteId;
        this.text = text;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getSiteId() {
        return siteId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Updates the comment text and sets the update timestamp.
     */
    public void updateText(String newText) {
        this.text = newText;
        this.updatedAt = LocalDateTime.now();
    }
}