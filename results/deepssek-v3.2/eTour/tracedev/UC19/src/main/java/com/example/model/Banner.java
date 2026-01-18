package com.example.model;

/**
 * Represents a banner associated with a refreshment point.
 * Can be marked as deleted (soft delete).
 */
public class Banner {
    private long id;
    private String name;
    private long associatedRefreshmentPointId;
    private String contentUrl;
    private boolean isDeleted = false;

    public Banner() {
    }

    public Banner(long id, String name, long associatedRefreshmentPointId, String contentUrl) {
        this.id = id;
        this.name = name;
        this.associatedRefreshmentPointId = associatedRefreshmentPointId;
        this.contentUrl = contentUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAssociatedRefreshmentPointId() {
        return associatedRefreshmentPointId;
    }

    public void setAssociatedRefreshmentPointId(long associatedRefreshmentPointId) {
        this.associatedRefreshmentPointId = associatedRefreshmentPointId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Deletes the banner (soft delete).
     * @return true if deletion is successful (i.e., banner was not already deleted)
     */
    public boolean delete() {
        if (!isDeleted) {
            isDeleted = true;
            return true;
        }
        return false;
    }

    /**
     * Explicitly set deleted flag (added to satisfy requirement Flow step 7).
     */
    public void setDeleted() {
        this.isDeleted = true;
    }
}