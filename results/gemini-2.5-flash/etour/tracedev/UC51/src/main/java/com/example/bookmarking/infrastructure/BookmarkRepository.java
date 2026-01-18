package com.example.bookmarking.infrastructure;

import com.example.bookmarking.domain.Bookmark;

/**
 * Interface for managing Bookmark persistence.
 * This defines the contract for how bookmarks are stored and retrieved.
 */
public interface BookmarkRepository {

    /**
     * Saves a given bookmark instance.
     *
     * @param bookmark The bookmark to be saved.
     * @return The saved bookmark, potentially with updated information (e.g., assigned ID if not already present).
     */
    Bookmark save(Bookmark bookmark);
}