package com.etour.service;

import com.etour.dto.BookmarkDTO;
import com.etour.exception.ConnectionException;
import com.etour.repository.BookmarkRepository;
import com.etour.auth.TouristAuthenticator;
import java.util.List;

/**
 * Service layer for bookmark operations.
 * Depends on BookmarkRepository and TouristAuthenticator (for entry conditions).
 */
public class BookmarkService {
    private BookmarkRepository repository;
    private TouristAuthenticator authenticator;

    // Constructor with repository and authenticator
    public BookmarkService(BookmarkRepository repository, TouristAuthenticator authenticator) {
        this.repository = repository;
        this.authenticator = authenticator;
    }

    /**
     * Retrieves favorites for a user, after checking authentication.
     * Propagates ConnectionException if repository throws one.
     */
    public List<BookmarkDTO> getFavoritesForUser(int userId) throws ConnectionException {
        // Entry condition: user must be authenticated (as per class diagram)
        if (!authenticator.isAuthenticated()) {
            throw new SecurityException("User is not authenticated");
        }
        // Delegate to repository
        return repository.findAllByUserId(userId);
    }

    /**
     * Upload bookmarks for a user.
     * As per class diagram requirement.
     */
    public void uploadBookmarks(int userId) {
        // Implementation placeholder
        // Since the requirement only states existence, we provide a minimal implementation.
        // Could be expanded later to upload bookmarks to a repository.
        System.out.println("Uploading bookmarks for user: " + userId);
    }
}