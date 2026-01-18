package com.example.ui;

import com.example.controller.BookmarkController;
import com.example.interactor.RemoveBookmarkResponse;

/**
 * User interface for tourist interactions.
 */
public class TouristUI {
    private BookmarkController bookmarkController;

    public TouristUI(BookmarkController bookmarkController) {
        this.bookmarkController = bookmarkController;
    }

    public void displayRemovalPrompt() {
        System.out.println("UI: Display removal prompt.");
    }

    /**
     * Displays removal confirmation dialog.
     */
    public void displayRemovalConfirmationDialog() {
        System.out.println("UI: Display removal confirmation dialog.");
    }

    /**
     * Simulates user confirming removal.
     */
    public void confirmRemoval(String touristId, String siteId) {
        System.out.println("UI: User confirmed removal.");
        RemoveBookmarkResponse response = bookmarkController.confirmRemoval(touristId, siteId);
        displayResult(response);
    }

    /**
     * Displays the result of the operation.
     */
    public void displayResult(RemoveBookmarkResponse response) {
        if (response.isSuccess()) {
            displaySiteRemovedSuccessfully();
        } else {
            if (response.getMessage().contains("Connection lost")) {
                displayConnectionLost();
            } else if (response.getMessage().contains("Bookmark not found")) {
                displayBookmarkNotFound();
            } else if (response.getMessage().contains("Removal failed")) {
                displayRemovalFailed();
            } else {
                System.out.println("UI: Error - " + response.getMessage());
            }
        }
    }

    /**
     * Simulates user cancelling the operation.
     */
    public void cancelRemoval() {
        System.out.println("UI: User cancelled removal.");
        bookmarkController.cancelRemoval();
        displayOperationCancelled();
    }

    /**
     * Displays "Site removed successfully"
     */
    public void displaySiteRemovedSuccessfully() {
        System.out.println("UI: Site removed successfully");
    }

    /**
     * Displays "Removal failed"
     */
    public void displayRemovalFailed() {
        System.out.println("UI: Removal failed");
    }

    /**
     * Displays "Bookmark not found"
     */
    public void displayBookmarkNotFound() {
        System.out.println("UI: Bookmark not found");
    }

    /**
     * Displays "Connection lost"
     */
    public void displayConnectionLost() {
        System.out.println("UI: Connection lost");
    }

    /**
     * Displays "Operation cancelled"
     */
    public void displayOperationCancelled() {
        System.out.println("UI: Operation cancelled");
    }

    /**
     * Shows failure result.
     */
    public void failureResult() {
        System.out.println("UI: Failure result.");
    }

    /**
     * Shows success result.
     */
    public void successResult() {
        System.out.println("UI: Success result.");
    }

    /**
     * Shows error result.
     */
    public void errorResult() {
        System.out.println("UI: Error result.");
    }
}