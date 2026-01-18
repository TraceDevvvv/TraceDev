package com.etour.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Tourist in the system.
 * The Tourist has a list of bookmarks and is associated with a current site.
 */
public class Tourist {
    private String touristId;
    private String username;
    private String currentSiteId;  // Added to satisfy requirement: "Tourist card IS in a particular site"
    private List<Bookmark> bookmarks;

    public Tourist(String touristId, String username) {
        this.touristId = touristId;
        this.username = username;
        this.bookmarks = new ArrayList<>();
        this.currentSiteId = null;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Added to satisfy requirement: "Tourist card IS in a particular site"
    public String getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(String currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    // Added to satisfy requirement: "Tourist HAS a list of bookmarks"
    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    // Added to satisfy requirement: "Tourist chooses to remove a site from the list of bookmarks by specific features"
    public String selectSiteFromBookmarks() {
        // Simplified: returns the siteId of the first bookmark, if any.
        if (bookmarks != null && !bookmarks.isEmpty()) {
            return bookmarks.get(0).getSiteId();
        }
        return null;
    }

    // Added to satisfy requirement: "Tourist HAS a list of bookmarks"
    public void addBookmark(String siteId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setBookmarkId(java.util.UUID.randomUUID().toString());
        bookmark.setSiteId(siteId);
        bookmark.setTouristId(touristId);
        bookmark.setCreationDate(new java.util.Date());
        bookmarks.add(bookmark);
    }

    // Added to satisfy requirement: "Tourist HAS a list of bookmarks"
    public void removeBookmark(String bookmarkId) {
        bookmarks.removeIf(b -> b.getBookmarkId().equals(bookmarkId));
    }

    public Bookmark selectBookmark(String siteId) {
        for (Bookmark b : bookmarks) {
            if (b.getSiteId().equals(siteId)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Responds to a removal prompt from the controller.
     * @param confirm true if the tourist confirms removal, false otherwise.
     * @return the confirmation value (as per sequence diagram).
     */
    public boolean respondToRemovalPrompt(boolean confirm) {
        // In a real application, this might involve user interaction.
        // Here we simply return the provided confirmation.
        return confirm;
    }

    /**
     * Requests the removal of a site from bookmarks.
     * This method is called by the tourist to initiate the removal process.
     * @param siteId the ID of the site to remove.
     */
    public void requestSiteRemoval(String siteId) {
        // This method is a placeholder for the tourist's action.
        // The actual removal is handled by BookmarkManagementController.
        System.out.println("Tourist " + touristId + " requests removal of site " + siteId);
    }
}