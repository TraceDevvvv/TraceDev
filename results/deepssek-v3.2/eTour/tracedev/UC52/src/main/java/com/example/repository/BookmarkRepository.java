package com.example.repository;

import com.example.model.Bookmark;
import com.example.exception.ConnectionException;

/**
 * Repository interface for bookmark operations.
 */
public interface BookmarkRepository {
    /**
     * Finds a bookmark by tourist and site.
     */
    Bookmark findBookmark(String touristId, String siteId);

    /**
     * Deletes a bookmark by its ID.
     * @return true if deletion succeeded, false otherwise.
     */
    boolean deleteBookmark(String bookmarkId);

    /**
     * Checks connection to the data source.
     * @return true if connection is alive, false otherwise.
     */
    boolean checkConnection();
}