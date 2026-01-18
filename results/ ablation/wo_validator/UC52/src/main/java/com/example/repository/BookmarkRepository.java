package com.example.repository;

import com.example.model.Bookmark;

/**
 * Repository interface for bookmark operations.
 */
public interface BookmarkRepository {
    Bookmark findBookmarkByUserAndSite(String userId, String siteId);
    void deleteBookmark(Bookmark bookmark);
    void saveBookmark(Bookmark bookmark);
}