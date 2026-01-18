package com.etour.controller;

import com.etour.model.Tourist;
import com.etour.model.Bookmark;
import com.etour.repository.BookmarkRepository;
import com.etour.repository.SiteRepository;
import com.etour.connection.IETourServerConnection;
import com.etour.connection.ConnectionListener;

/**
 * Controller for managing bookmark removal operations.
 * Implements ConnectionListener to handle server disconnection events.
 * Generic quality requirements (e.g., reliability) are considered.
 */
public class BookmarkManagementController implements ConnectionListener {
    private BookmarkRepository bookmarkRepository;
    private SiteRepository siteRepository;
    private IETourServerConnection serverConnection;
    private boolean confirmationReceived = false;

    public BookmarkManagementController(BookmarkRepository bookmarkRepository,
                                        SiteRepository siteRepository,
                                        IETourServerConnection serverConnection) {
        this.bookmarkRepository = bookmarkRepository;
        this.siteRepository = siteRepository;
        this.serverConnection = serverConnection;
        this.serverConnection.addConnectionListener(this);
    }

    /**
     * Requests removal of a bookmark for a tourist.
     * Implements the main flow and alternative flows from the sequence diagram.
     * @param touristId the tourist's ID.
     * @param siteId the site ID to remove from bookmarks.
     * @return true if removal successful, false otherwise.
     */
    public boolean requestRemoval(String touristId, String siteId) {
        // Check connection first (alternative flow)
        if (!serverConnection.isConnected()) {
            throw new RuntimeException("Server disconnected");
        }

        // Check if site exists
        if (!siteRepository.exists(siteId)) {
            System.out.println("Site does not exist.");
            return false;
        }

        // Prompt confirmation (main success scenario)
        promptRemovalConfirmation();
        boolean confirm = getConfirmation();
        if (!confirm) {
            // Tourist cancels (alternative flow)
            cancelRemoval();
            System.out.println("Operation cancelled");
            return false;
        }

        // Find the bookmark
        Bookmark bookmark = bookmarkRepository.findBookmarkBySiteId(touristId, siteId);
        if (bookmark == null) {
            System.out.println("Bookmark not found.");
            return false;
        }

        // Execute removal
        boolean success = executeRemoval(bookmark.getBookmarkId());
        if (success) {
            System.out.println("Removal successful.");
            return true;
        } else {
            System.out.println("Removal failed.");
            return false;
        }
    }

    /**
     * Executes the removal of a bookmark by ID.
     * @param bookmarkId the bookmark ID.
     * @return true if deletion successful, false otherwise.
     */
    public boolean executeRemoval(String bookmarkId) {
        return bookmarkRepository.delete(bookmarkId);
    }

    /**
     * Prompts the tourist for removal confirmation.
     * This method is called by the controller to initiate the prompt.
     */
    public void promptRemovalConfirmation() {
        // In a real application, this would trigger a UI prompt.
        // For simulation, we set a default confirmation.
        // The actual confirmation is obtained via getConfirmation().
        System.out.println("Prompting tourist for removal confirmation...");
    }

    /**
     * Retrieves the confirmation status.
     * This simulates the tourist's response.
     * @return true if confirmed, false if cancelled.
     */
    public boolean getConfirmation() {
        // In a real app, this would come from user input.
        // We simulate by returning a preset value.
        return confirmationReceived;
    }

    /**
     * Sets the confirmation status (for testing/simulation).
     * @param confirmation true to simulate confirmation, false to simulate cancellation.
     */
    public void setConfirmation(boolean confirmation) {
        this.confirmationReceived = confirmation;
    }

    /**
     * Cancels the removal operation.
     * Called when the tourist declines the prompt.
     */
    public void cancelRemoval() {
        System.out.println("Removal operation cancelled.");
    }

    /**
     * Handles cancellation (alternative flow).
     */
    public void handleCancellation() {
        // Additional cleanup or logging could be performed here.
        System.out.println("Handling cancellation...");
    }

    @Override
    public void onDisconnect() {
        // Handle server disconnection (alternative flow)
        System.out.println("Server disconnected during operation.");
        throw new RuntimeException("Server disconnected");
    }
}