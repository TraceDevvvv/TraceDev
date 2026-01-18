package com.example;

import java.time.LocalDateTime;

/**
 * Represents a bookmark for a site.
 */
public class Bookmark {
    private String bookmarkId;
    private String siteId;
    private String touristId;
    private LocalDateTime timestamp;

    public Bookmark(String bookmarkId, String siteId, String touristId, LocalDateTime timestamp) {
        this.bookmarkId = bookmarkId;
        this.siteId = siteId;
        this.touristId = touristId;
        this.timestamp = timestamp;
    }

    public String getBookmarkDetails() {
        return "Bookmark ID: " + bookmarkId + ", Site ID: " + siteId + ", Tourist ID: " + touristId + ", Time: " + timestamp;
    }
}