package com.etour.repository;

import com.etour.model.Bookmark;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Repository for managing Bookmark entities.
 */
public class BookmarkRepository {
    // In-memory storage for demo purposes.
    private List<Bookmark> bookmarks = new ArrayList<>();

    public BookmarkRepository() {
        // Initialize with some dummy data if needed.
    }

    /**
     * Retrieves all bookmarks for a given tourist.
     * @param touristId the tourist's ID.
     * @return list of bookmarks.
     */
    public List<Bookmark> getByTouristId(String touristId) {
        return bookmarks.stream()
                .filter(b -> b.getTouristId().equals(touristId))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a bookmark by its ID.
     * @param bookmarkId the bookmark ID.
     * @return true if deleted, false otherwise.
     */
    public boolean delete(String bookmarkId) {
        boolean removed = bookmarks.removeIf(b -> b.getBookmarkId().equals(bookmarkId));
        return removed;
    }

    /**
     * Checks if a bookmark exists.
     * @param bookmarkId the bookmark ID.
     * @return true if exists, false otherwise.
     */
    public boolean exists(String bookmarkId) {
        return bookmarks.stream().anyMatch(b -> b.getBookmarkId().equals(bookmarkId));
    }

    /**
     * Finds a bookmark by tourist ID and site ID.
     * @param touristId the tourist ID.
     * @param siteId the site ID.
     * @return the bookmark if found, null otherwise.
     */
    public Bookmark findBookmarkBySiteId(String touristId, String siteId) {
        return bookmarks.stream()
                .filter(b -> b.getTouristId().equals(touristId) && b.getSiteId().equals(siteId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a bookmark to the repository (for initialization).
     * @param bookmark the bookmark to add.
     */
    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
    }
}