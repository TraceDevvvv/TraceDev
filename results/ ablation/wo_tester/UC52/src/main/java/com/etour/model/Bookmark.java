package com.etour.model;

import java.util.Date;

/**
 * Represents a Bookmark entity.
 */
public class Bookmark {
    private String bookmarkId;
    private String siteId;
    private String touristId;
    private Date creationDate;

    public Bookmark() {
        // Default constructor
    }

    public Bookmark(String bookmarkId, String siteId, String touristId, Date creationDate) {
        this.bookmarkId = bookmarkId;
        this.siteId = siteId;
        this.touristId = touristId;
        this.creationDate = creationDate;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}