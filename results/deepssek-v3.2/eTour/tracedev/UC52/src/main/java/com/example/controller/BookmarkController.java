package com.example.controller;

import com.example.interactor.RemoveBookmarkInteractor;
import com.example.interactor.RemoveBookmarkRequest;
import com.example.interactor.RemoveBookmarkResponse;

/**
 * Controller for bookmark operations.
 */
public class BookmarkController {
    private RemoveBookmarkInteractor removeBookmarkInteractor;

    public BookmarkController(RemoveBookmarkInteractor removeBookmarkInteractor) {
        this.removeBookmarkInteractor = removeBookmarkInteractor;
    }

    /**
     * Shows removal prompt (UI would call this).
     */
    public void displayRemovalPrompt() {
        System.out.println("Controller: Display removal prompt.");
    }

    /**
     * Confirms removal and triggers the interactor.
     */
    public RemoveBookmarkResponse confirmRemoval(String touristId, String siteId) {
        RemoveBookmarkRequest request = new RemoveBookmarkRequest(touristId, siteId);
        return removeBookmarkInteractor.execute(request);
    }

    /**
     * Cancels the removal operation.
     */
    public void cancelRemoval() {
        System.out.println("Controller: Removal operation cancelled.");
    }

    /**
     * Shows success result.
     */
    public void successResult() {
        System.out.println("Controller: Success result.");
    }

    /**
     * Shows failure result.
     */
    public void failureResult() {
        System.out.println("Controller: Failure result.");
    }

    /**
     * Shows error result.
     */
    public void errorResult() {
        System.out.println("Controller: Error result.");
    }

    /**
     * Shows operation cancelled.
     */
    public void operationCancelled() {
        System.out.println("Controller: Operation cancelled.");
    }
}