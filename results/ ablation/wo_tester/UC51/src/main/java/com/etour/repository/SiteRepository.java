package com.etour.repository;

import com.etour.model.Bookmark;
import java.util.List;

/**
 * Interface for bookmark repository operations.
 */
public interface SiteRepository {
    void saveBookmark(Bookmark bookmark);
    List<Bookmark> findBookmarksByTourist(String touristId);
}