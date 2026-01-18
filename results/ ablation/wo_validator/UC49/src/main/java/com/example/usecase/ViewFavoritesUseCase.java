
package com.example.usecase;

import com.example.dto.BookmarkDTO;
import com.example.repository.BookmarkRepository;
import java.util.List;

/**
 * Use case for viewing favorites.
 */
public class ViewFavoritesUseCase {
    private BookmarkRepository bookmarkRepository;

    /**
     * Constructor.
     * @param repository the bookmark repository
     */
    public ViewFavoritesUseCase(BookmarkRepository repository) {
        this.bookmarkRepository = repository;
    }

    /**
     * Executes the use case to get favorites for a user.
     * Implements sequence diagram main flow and connection interrupted flow.
     * @param userId the user ID
     * @return list of bookmarks for the user
     * @throws RuntimeException if connection fails
     */
    public List<BookmarkDTO> execute(String userId) {
        try {
            // This call corresponds to the sequence diagram interaction
            return bookmarkRepository.findByUserId(userId);
        } catch (Exception e) {
            // Handles connection interrupted alternative flow
            throw new RuntimeException("Connection to server interrupted", e);
        }
    }
}
