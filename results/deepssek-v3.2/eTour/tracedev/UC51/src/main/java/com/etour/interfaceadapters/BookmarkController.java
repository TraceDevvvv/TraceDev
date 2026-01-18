package com.etour.interfaceadapters;

import com.etour.application.AddSiteToBookmarksRequest;
import com.etour.application.AddSiteToBookmarksResponse;
import com.etour.application.AddSiteToBookmarksUseCase;
import com.etour.infrastructure.ETourServerClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller that handles the "insert site to bookmarks" use case.
 */
public class BookmarkController {
    private final AddSiteToBookmarksUseCase addSiteToBookmarksUseCase;
    private final ConfirmationDialog confirmationDialog;
    private final ETourServerClient eTourServerClient;

    public BookmarkController(AddSiteToBookmarksUseCase useCase) {
        this.addSiteToBookmarksUseCase = useCase;
        this.confirmationDialog = new ConfirmationDialog();
        this.eTourServerClient = new ETourServerClient();
    }

    // For testing or alternative construction
    public BookmarkController(AddSiteToBookmarksUseCase useCase, ConfirmationDialog dialog, ETourServerClient client) {
        this.addSiteToBookmarksUseCase = useCase;
        this.confirmationDialog = dialog;
        this.eTourServerClient = client;
    }

    /**
     * Main entry point for the "insert site to bookmarks" feature.
     * Implements the flow described in the sequence diagram.
     *
     * @param touristId ID of the tourist currently at the site
     * @param siteId ID of the site to bookmark
     * @return ResponseDTO with status and details
     */
    public ResponseDTO insertSite(String touristId, String siteId) {
        // Step 1: Tourist activates the feature (call arrives here).

        // Step 2: System prompts the inclusion.
        boolean confirmed = confirmationDialog.displayPrompt(touristId, siteId);
        if (!confirmed) {
            // Tourist cancels
            return new ResponseDTO("CANCELLED", null, "Insertion cancelled by user");
        }

        // Step 3 & 4: Use case execution.
        AddSiteToBookmarksRequest request = new AddSiteToBookmarksRequest(touristId, siteId);
        AddSiteToBookmarksResponse response = addSiteToBookmarksUseCase.execute(request);

        if (response.isSuccess()) {
            // Success path
            confirmationDialog.showSuccessMessage();

            // Notify external server (Exit Condition handling)
            boolean notified = eTourServerClient.notifyBookmarkAdded(touristId, siteId);
            String notifyMsg = notified ? "Notification sent" : "Notification failed – bookmark saved locally";

            Map<String, Object> data = new HashMap<>();
            data.put("bookmarkId", response.getBookmarkId());
            data.put("notificationStatus", notified ? "SENT" : "FAILED");

            return new ResponseDTO("SUCCESS", data, "Bookmark added successfully. " + notifyMsg);
        } else {
            // Duplicate or validation error
            confirmationDialog.showErrorMessage(response.getMessage());
            return new ResponseDTO("ERROR", null, response.getMessage());
        }
    }
}