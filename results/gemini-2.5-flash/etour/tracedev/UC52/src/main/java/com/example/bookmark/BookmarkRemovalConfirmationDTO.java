package com.example.bookmark;

/**
 * Data Transfer Object for carrying information about a bookmark removal confirmation.
 * This includes a unique confirmation ID, a message for the user, and details about the bookmark.
 */
public class BookmarkRemovalConfirmationDTO {
    public String confirmationId; // Unique ID for this specific removal confirmation process
    public String message; // User-friendly message for the confirmation prompt
    public String bookmarkDetails; // String representation of the bookmark details
    public String siteId; // The siteId associated with the bookmark to be removed

    /**
     * Constructs a new BookmarkRemovalConfirmationDTO.
     *
     * @param confirmationId A unique ID for the confirmation.
     * @param message A message to display to the user.
     * @param bookmarkDetails A string summarizing the bookmark to be removed.
     * @param siteId The ID of the site associated with the bookmark.
     */
    public BookmarkRemovalConfirmationDTO(String confirmationId, String message, String bookmarkDetails, String siteId) {
        this.confirmationId = confirmationId;
        this.message = message;
        this.bookmarkDetails = bookmarkDetails;
        this.siteId = siteId;
    }
}