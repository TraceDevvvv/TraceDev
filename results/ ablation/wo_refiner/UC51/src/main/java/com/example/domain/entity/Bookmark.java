package com.example.domain.entity;

import java.util.Date;

/**
 * Core Domain Entity: Bookmark
 */
public class Bookmark {
    private String bookmarkId;
    private String touristId;
    private String siteId;
    private Date addedDate;

    public Bookmark(String bookmarkId, String touristId, String siteId, Date addedDate) {
        this.bookmarkId = bookmarkId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.addedDate = addedDate;
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

    public Date getAddedDate() {
        return addedDate;
    }
}