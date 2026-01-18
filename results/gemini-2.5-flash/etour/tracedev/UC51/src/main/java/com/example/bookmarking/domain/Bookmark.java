package com.example.bookmarking.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a Bookmark created by a tourist for a specific site.
 */
public class Bookmark {
    /** Unique identifier for the bookmark. */
    public String id;
    /** Identifier of the site being bookmarked. */
    public String siteId;
    /** Identifier of the tourist who created the bookmark. */
    public String touristId;
    /** Date and time when the bookmark was created. */
    public Date creationDate;

    /**
     * Constructs a new Bookmark instance with automatically generated ID and creation date.
     *
     * @param siteId The ID of the site to be bookmarked.
     * @param touristId The ID of the tourist creating the bookmark.
     */
    public Bookmark(String siteId, String touristId) {
        // Automatically generate a unique ID for the bookmark
        this.id = UUID.randomUUID().toString();
        this.siteId = siteId;
        this.touristId = touristId;
        // Set the creation date to the current date and time
        this.creationDate = new Date();
    }

    @Override
    public String toString() {
        return "Bookmark{" +
               "id='" + id + '\'' +
               ", siteId='" + siteId + '\'' +
               ", touristId='" + touristId + '\'' +
               ", creationDate=" + creationDate +
               '}';
    }
}