package com.etour.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Domain entity representing a bookmark.
 */
public class Bookmark {
    private final String id;
    private final String touristId;
    private final String siteId;
    private final Date createdAt;

    /**
     * Constructs a new Bookmark with the given tourist and site IDs.
     * The ID and creation date are generated automatically.
     */
    public Bookmark(String touristId, String siteId) {
        this.id = generateId();
        this.touristId = touristId;
        this.siteId = siteId;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bookmark bookmark = (Bookmark) obj;
        return Objects.equals(id, bookmark.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private String generateId() {
        return "BM" + System.currentTimeMillis() + "_" + hashCode();
    }
}