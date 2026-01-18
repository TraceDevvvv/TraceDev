package com.etour.domain;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Bookmark entities.
 */
public interface BookmarkRepository {
    /**
     * Saves a bookmark.
     */
    Bookmark save(Bookmark bookmark);

    /**
     * Finds a bookmark by tourist ID and site ID.
     */
    Optional<Bookmark> findByTouristIdAndSiteId(String touristId, String siteId);

    /**
     * Finds all bookmarks for a given tourist.
     */
    List<Bookmark> findAllByTouristId(String touristId);
}