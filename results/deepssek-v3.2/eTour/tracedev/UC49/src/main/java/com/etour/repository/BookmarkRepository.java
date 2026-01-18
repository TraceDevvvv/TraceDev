package com.etour.repository;

import com.etour.dto.BookmarkDTO;
import com.etour.exception.ConnectionException;
import java.util.List;

/**
 * Interface for the Bookmark Repository.
 * Follows Liskov Substitution Principle.
 */
public interface BookmarkRepository {
    /**
     * Finds all bookmarks for a given user.
     *
     * @param userId the user ID
     * @return list of bookmarks for the user
     * @throws ConnectionException if connection to the data source fails
     */
    List<BookmarkDTO> findAllByUserId(int userId) throws ConnectionException;
}