package com.example.bookmark;

import java.util.Optional;

/**
 * Interface defining the contract for bookmark data access operations.
 * Implements REQ-001 by including the 'remove' method.
 *
 * Relationship: BookmarkRepository o-- Bookmark (aggregation - manages bookmarks)
 */
public interface BookmarkRepository {

    /**
     * Finds a bookmark by its unique identifier.
     *
     * @param bookmarkId The unique ID of the bookmark.
     * @return An Optional containing the Bookmark if found, or an empty Optional otherwise.
     */
    Optional<Bookmark> findById(String bookmarkId);

    /**
     * Removes a bookmark identified by its unique ID.
     * Throws ConnectionException if there's an issue connecting to the data source.
     *
     * @param bookmarkId The unique ID of the bookmark to remove.
     * @throws ConnectionException If a database connection error occurs.
     */
    void remove(String bookmarkId) throws ConnectionException;
}