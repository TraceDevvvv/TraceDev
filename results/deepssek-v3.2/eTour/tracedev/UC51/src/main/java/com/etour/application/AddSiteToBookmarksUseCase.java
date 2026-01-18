package com.etour.application;

import com.etour.domain.Bookmark;
import com.etour.domain.BookmarkRepository;

import java.util.Optional;

/**
 * Use case that adds a site to the tourist’s bookmarks.
 * Quality Requirement: ensures operation is completed successfully
 * and the bookmark list is updated through validation and repository operations.
 */
public class AddSiteToBookmarksUseCase {
    private final BookmarkRepository bookmarkRepository;

    public AddSiteToBookmarksUseCase(BookmarkRepository repository) {
        this.bookmarkRepository = repository;
    }

    /**
     * Executes the use case with the given request.
     * Returns a response indicating success or failure.
     */
    public AddSiteToBookmarksResponse execute(AddSiteToBookmarksRequest request) {
        // Validate request (step 3 internal validation)
        if (!validateRequest(request)) {
            return new AddSiteToBookmarksResponse(false, "", "Invalid request");
        }

        String touristId = request.getTouristId();
        String siteId = request.getSiteId();

        // Check if already bookmarked
        Optional<Bookmark> existing = bookmarkRepository.findByTouristIdAndSiteId(touristId, siteId);
        if (existing.isPresent()) {
            return new AddSiteToBookmarksResponse(false, "", "Already bookmarked");
        }

        // Create new bookmark and save (step 4)
        Bookmark newBookmark = new Bookmark(touristId, siteId);
        Bookmark savedBookmark = bookmarkRepository.save(newBookmark);

        // Success response
        return new AddSiteToBookmarksResponse(true, savedBookmark.getId(), "Bookmark added successfully");
    }

    /**
     * Validates the request parameters (non‑null and non‑empty).
     */
    private boolean validateRequest(AddSiteToBookmarksRequest request) {
        return request != null
                && request.getTouristId() != null && !request.getTouristId().isEmpty()
                && request.getSiteId() != null && !request.getSiteId().isEmpty();
    }
}