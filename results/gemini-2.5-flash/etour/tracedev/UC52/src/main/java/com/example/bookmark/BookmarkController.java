package com.example.bookmark;

import java.util.UUID;

/**
 * Controller class that handles user requests related to bookmark removal.
 * Acts as an intermediary between the BookmarkView and BookmarkRemovalService.
 *
 * Relationship: BookmarkController ..> BookmarkRemovalService (uses)
 * Relationship: BookmarkController ..> BookmarkIdentifierDTO (creates)
 * Relationship: BookmarkController ..> BookmarkRemovalConfirmationDTO (receives)
 */
public class BookmarkController {

    private final BookmarkRemovalService bookmarkRemovalService;
    private BookmarkView bookmarkView; // The controller needs to communicate back to the view

    /**
     * Constructs a BookmarkController with dependencies.
     * @param bookmarkRemovalService The service handling bookmark removal logic.
     */
    public BookmarkController(BookmarkRemovalService bookmarkRemovalService) {
        this.bookmarkRemovalService = bookmarkRemovalService;
        System.out.println("BookmarkController initialized.");
    }

    /**
     * Sets the view for this controller. This is a common pattern for UI controllers.
     * @param bookmarkView The view component.
     */
    public void setBookmarkView(BookmarkView bookmarkView) {
        this.bookmarkView = bookmarkView;
    }

    /**
     * Handles the request to initiate bookmark removal from the user interface.
     * Creates a BookmarkIdentifierDTO and calls the service.
     *
     * @param siteId The ID of the site associated with the bookmark to be removed.
     */
    public void requestRemoveBookmark(String siteId) {
        System.out.println("BookmarkController: requestRemoveBookmark for siteId: " + siteId);
        // Creates BookmarkIdentifierDTO from siteId
        BookmarkIdentifierDTO identifier = new BookmarkIdentifierDTO(siteId);

        // Call service to initiate removal, receives confirmation DTO
        BookmarkRemovalConfirmationDTO confirmationDTO = bookmarkRemovalService.initiateRemoval(identifier);

        // Communicate back to the view to show the confirmation prompt
        if (!confirmationDTO.confirmationId.isEmpty()) {
            bookmarkView.showConfirmationPrompt(confirmationDTO); // Harmonized message name
        } else {
            // If confirmationId is empty, it means the bookmark was not found
            bookmarkView.showRemovalFailure(confirmationDTO.message); // Show failure for not found
        }
    }

    /**
     * Handles the request to confirm bookmark removal from the user interface.
     * Calls the service to finalize the removal.
     *
     * @param confirmationId The unique ID of the pending removal confirmation.
     */
    public void confirmRemoveBookmark(String confirmationId) {
        System.out.println("BookmarkController: confirmRemoveBookmark for confirmationId: " + confirmationId);

        try {
            // Call service to confirm removal
            boolean removalStatus = bookmarkRemovalService.confirmRemoval(confirmationId);

            // Based on removal status, update the view
            if (removalStatus) {
                bookmarkView.showRemovalSuccess();
            } else {
                bookmarkView.showRemovalFailure("Bookmark not found or removal failed.");
            }
        } catch (ConnectionException e) {
            // Propagate exception to controller, which then informs the view (REQ-001)
            System.err.println("BookmarkController: Error confirming removal for " + confirmationId + ": " + e.getMessage());
            bookmarkView.showRemovalFailure("Connection error: " + e.getMessage());
        }
    }

    /**
     * Handles the request to cancel bookmark removal from the user interface.
     * Calls the service to cancel the pending removal.
     *
     * @param confirmationId The unique ID of the pending removal confirmation to cancel.
     */
    public void cancelRemoveBookmark(String confirmationId) {
        System.out.println("BookmarkController: cancelRemoveBookmark for confirmationId: " + confirmationId);
        // Call service to cancel removal
        bookmarkRemovalService.cancelRemoval(confirmationId);

        // Inform the view that cancellation was successful (REQ-002)
        bookmarkView.showCancellationConfirmation(); // Harmonized message name
    }
}