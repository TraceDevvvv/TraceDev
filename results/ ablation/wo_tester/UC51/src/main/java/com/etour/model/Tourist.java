package com.etour.model;

import java.util.List;

/**
 * Represents a Tourist user.
 * <<trace(R-FR-1)>> Links to Tourist actor requirements
 */
public class Tourist {
    private String userId;

    public Tourist(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the bookmarks of the tourist.
     * @return list of bookmarks
     */
    public List<Bookmark> getUserBookmarks() {
        // Implementation would depend on repository.
        // For now, return null as this is a stub.
        return null;
    }

    /**
     * Adds a bookmark for a site.
     * @param site the site to bookmark
     */
    public void addBookmark(Site site) {
        // Implementation would involve creating a Bookmark and saving via repository.
    }

    /**
     * Added to satisfy requirement Flow of Events (1, 3)
     * Returns tourist details for the given user ID.
     * @param userId the user ID
     * @return TouristInfo object with details
     */
    public TouristInfo getTouristDetails(String userId) {
        // In a real scenario, this would fetch from a database.
        // Here we return a dummy TouristInfo.
        return new TouristInfo(userId, "John Doe", "john@example.com");
    }
}