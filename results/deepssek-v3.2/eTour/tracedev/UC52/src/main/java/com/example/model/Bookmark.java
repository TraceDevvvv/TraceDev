package com.example.model;

import java.util.Date;

/**
 * Represents a bookmark entity.
 */
public class Bookmark {
    private String bookmarkId;
    private String touristId;
    private String siteId;
    private Date createdDate;

    public Bookmark(String bookmarkId, String touristId, String siteId) {
        this.bookmarkId = bookmarkId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.createdDate = new Date(); // current date
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}