package com.example.interactor;

import com.example.repository.BookmarkRepository;
import com.example.model.Bookmark;
import com.example.model.Site;
import com.example.model.Tourist;
import com.example.exception.ConnectionException;

/**
 * Interactor that orchestrates the bookmark removal use case.
 */
public class RemoveBookmarkInteractor {
    private BookmarkRepository bookmarkRepository;

    public RemoveBookmarkInteractor(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    /**
     * Executes the remove bookmark request.
     */
    public RemoveBookmarkResponse execute(RemoveBookmarkRequest request) {
        String touristId = request.getTouristId();
        String siteId = request.getSiteId();

        // 1. Validate site exists
        Site site = new Site(siteId, "Sample Site");
        if (!site.exists()) {
            return new RemoveBookmarkResponse(false, "Site does not exist");
        }

        // 2. Check tourist is at site (entry condition)
        Tourist tourist = new Tourist(touristId);
        if (!tourist.isTouristAtSite(siteId)) {
            return new RemoveBookmarkResponse(false, "Tourist is not at the site");
        }

        // 3. Check connection (quality requirement)
        try {
            if (!bookmarkRepository.checkConnection()) {
                return new RemoveBookmarkResponse(false, "Connection lost");
            }
        } catch (Exception e) {
            return new RemoveBookmarkResponse(false, "Connection error: " + e.getMessage());
        }

        // 4. Find bookmark
        Bookmark bookmark = bookmarkRepository.findBookmark(touristId, siteId);
        if (bookmark == null) {
            return new RemoveBookmarkResponse(false, "Bookmark not found");
        }

        // 5. Delete bookmark
        boolean deleted = bookmarkRepository.deleteBookmark(bookmark.getBookmarkId());
        if (deleted) {
            return new RemoveBookmarkResponse(true, "Site removed successfully");
        } else {
            return new RemoveBookmarkResponse(false, "Removal failed");
        }
    }

    /**
     * Sends success response.
     */
    public void successResponse() {
        System.out.println("Interactor: Success response.");
    }

    /**
     * Sends failure response.
     */
    public void failureResponse() {
        System.out.println("Interactor: Failure response.");
    }

    /**
     * Sends error response.
     */
    public void errorResponse() {
        System.out.println("Interactor: Error response.");
    }
}