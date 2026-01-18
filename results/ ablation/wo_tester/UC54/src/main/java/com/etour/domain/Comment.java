package com.etour.domain;

import java.time.LocalDateTime;

/**
 * Domain entity representing a comment made by a tourist.
 */
public class Comment {
    private String id;
    private String content;
    private LocalDateTime lastModified;
    private String touristId;
    private String siteId;

    public Comment(String id, String content, LocalDateTime lastModified, String touristId, String siteId) {
        this.id = id;
        this.content = content;
        this.lastModified = lastModified;
        this.touristId = touristId;
        this.siteId = siteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * Validates the comment content (non-null and non-empty).
     * @return true if content is valid.
     */
    public boolean validateContent() {
        return content != null && !content.trim().isEmpty();
    }

    /**
     * Updates the comment content and sets the last modified timestamp.
     * @param newContent the new content.
     */
    public void updateContent(String newContent) {
        this.content = newContent;
        this.lastModified = LocalDateTime.now();
        System.out.println("Comment " + id + " updated.");
    }
}