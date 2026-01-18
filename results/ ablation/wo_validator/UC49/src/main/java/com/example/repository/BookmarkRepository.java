package com.example.repository;

import com.example.dto.BookmarkDTO;
import java.util.List;

/**
 * Repository interface for bookmark operations.
 */
public interface BookmarkRepository {
    /**
     * Finds bookmarks by user ID.
     * @param userId the user ID
     * @return list of bookmarks for the user
     */
    List<BookmarkDTO> findByUserId(String userId);
}