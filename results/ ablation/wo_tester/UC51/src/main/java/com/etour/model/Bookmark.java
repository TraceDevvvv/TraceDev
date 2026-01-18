package com.etour.model;

import java.util.Date;

/**
 * Represents a bookmark linking a tourist and a site.
 */
public class Bookmark {
    private String bookmarkId;
    private String touristId;
    private String siteId;
    private Date createdAt;

    /**
     * Constructor to satisfy requirement Flow of Events (4)
     * @param touristId the tourist ID
     * @param siteId the site ID
     */
    public Bookmark(String touristId, String siteId) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.createdAt = new Date(); // set current timestamp
        // generate a simple ID for demo purposes
        this.bookmarkId = "BM-" + touristId + "-" + siteId + "-" + createdAt.getTime();
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

    public Date getCreatedAt() {
        return createdAt;
    }
}