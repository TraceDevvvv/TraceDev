package com.example.bookmark;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a bookmark entity with details like ID, site ID, tourist ID, and creation date.
 */
public class Bookmark {
    private String id; // Unique identifier for the bookmark
    private String siteId; // Identifier for the site being bookmarked
    private String touristId; // Identifier for the tourist who created the bookmark
    private Date creationDate; // Date when the bookmark was created

    /**
     * Constructs a new Bookmark instance.
     *
     * @param id The unique ID of the bookmark.
     * @param siteId The ID of the site.
     * @param touristId The ID of the tourist.
     * @param creationDate The creation date of the bookmark.
     */
    public Bookmark(String id, String siteId, String touristId, Date creationDate) {
        this.id = id;
        this.siteId = siteId;
        this.touristId = touristId;
        this.creationDate = creationDate;
    }

    // Getters for all attributes

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    // It's good practice to override equals and hashCode for entity classes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(id, bookmark.id); // Assuming 'id' is the primary key
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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