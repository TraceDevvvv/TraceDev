package com.example.model;

import java.util.Date;

/**
 * Represents a bookmark entity.
 */
public class Bookmark {
    private String bookmarkId;
    private String userId;
    private String siteId;
    private Date createdAt;

    public Bookmark(String bookmarkId, String userId, String siteId, Date createdAt) {
        this.bookmarkId = bookmarkId;
        this.userId = userId;
        this.siteId = siteId;
        this.createdAt = createdAt;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}