package com.example.bookmark;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service class responsible for managing the bookmark removal process.
 * Handles initiation, confirmation, and cancellation of bookmark removals.
 *
 * Relationship: BookmarkRemovalService ..> BookmarkRepository (uses)
 * Relationship: BookmarkRemovalService ..> BookmarkIdentifierDTO (uses)
 * Relationship: BookmarkRemovalService ..> BookmarkRemovalConfirmationDTO (creates)
 */
public class BookmarkRemovalService {

    private final BookmarkRepository bookmarkRepository;

    // Temporary storage for initiated removal requests, mapping confirmationId to the bookmark to be removed.
    private final Map<String, Bookmark> pendingRemovals = new ConcurrentHashMap<>();

    /**
     * Constructs a BookmarkRemovalService with a dependency on BookmarkRepository.
     * Uses constructor injection for the repository.
     *
     * @param bookmarkRepository The repository for bookmark data access.
     */
    public BookmarkRemovalService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
        System.out.println("BookmarkRemovalService initialized.");
    }

    /**
     * Initiates the bookmark removal process.
     * Retrieves bookmark details and creates a confirmation DTO.
     *
     * @param identifier The DTO containing the siteId (assumed bookmark ID for lookup).
     * @return A BookmarkRemovalConfirmationDTO with details for the user.
     */
    public BookmarkRemovalConfirmationDTO initiateRemoval(BookmarkIdentifierDTO identifier) {
        System.out.println("BookmarkRemovalService: initiateRemoval for siteId: " + identifier.siteId);

        // Generates a unique confirmation ID for this removal attempt
        String confirmationId = UUID.randomUUID().toString();

        Optional<Bookmark> optionalBookmark = bookmarkRepository.findById(identifier.siteId); // findById(identifier.siteId) as per sequence diagram

        if (optionalBookmark.isPresent()) {
            Bookmark bookmark = optionalBookmark.get();
            // Store the bookmark for later confirmation using the generated confirmationId
            pendingRemovals.put(confirmationId, bookmark);

            String message = "Do you want to remove the bookmark for site: " + bookmark.getSiteId() + "?";
            String bookmarkDetails = bookmark.toString();
            System.out.println("BookmarkRemovalService: Removal initiated. Confirmation ID: " + confirmationId);
            return new BookmarkRemovalConfirmationDTO(confirmationId, message, bookmarkDetails, bookmark.getSiteId());
        } else {
            System.out.println("BookmarkRemovalService: Bookmark not found for siteId: " + identifier.siteId);
            return new BookmarkRemovalConfirmationDTO(
                    "", // No valid confirmation ID as no bookmark was found
                    "Bookmark for site " + identifier.siteId + " not found.",
                    "No bookmark details available.",
                    identifier.siteId
            );
        }
    }

    /**
     * Confirms the removal of a bookmark identified by the confirmation ID.
     * Ensures data integrity checks before removal (simulated by checking pendingRemovals).
     *
     * @param confirmationId The unique ID received during initiation.
     * @return true if removal was successful, false otherwise.
     * @throws ConnectionException If a connection error occurs during the actual removal from the repository.
     */
    public boolean confirmRemoval(String confirmationId) throws ConnectionException {
        System.out.println("BookmarkRemovalService: confirmRemoval for confirmationId: " + confirmationId);

        // Ensures data integrity checks before removal
        // Check if the confirmationId is valid and corresponds to a pending removal
        Bookmark bookmarkToRemov = pendingRemovals.get(confirmationId);

        if (bookmarkToRemov != null) {
            // Remove from the repository
            try {
                // The sequence diagram shows `remove(bookmarkId : String)` which means the actual Bookmark.id.
                // We stored the full Bookmark object with its original ID.
                bookmarkRepository.remove(bookmarkToRemov.getId());
                pendingRemovals.remove(confirmationId); // Clear from pending after successful removal
                System.out.println("BookmarkRemovalService: Bookmark " + bookmarkToRemov.getId() + " confirmed and removed.");
                return true;
            } catch (ConnectionException e) {
                // Propagate the exception as per sequence diagram
                System.err.println("BookmarkRemovalService: Connection error during removal confirmation for " + bookmarkToRemov.getId() + ": " + e.getMessage());
                throw e; // REQ-001: propagate exception
            }
        } else {
            System.out.println("BookmarkRemovalService: Invalid or expired confirmationId: " + confirmationId);
            return false;
        }
    }

    /**
     * Cancels a pending bookmark removal request.
     *
     * @param confirmationId The unique ID of the pending request to cancel.
     */
    public void cancelRemoval(String confirmationId) {
        System.out.println("BookmarkRemovalService: cancelRemoval for confirmationId: " + confirmationId);
        // Remove the pending removal request from the temporary storage
        Bookmark canceledBookmark = pendingRemovals.remove(confirmationId);
        if (canceledBookmark != null) {
            System.out.println("BookmarkRemovalService: Removal request for bookmark " + canceledBookmark.getId() + " canceled.");
        } else {
            System.out.println("BookmarkRemovalService: No pending removal found for confirmationId: " + confirmationId);
        }
        // Acknowledgment - nothing more to do in this service for cancellation
    }
}