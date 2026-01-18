package com.banner.controller;

import com.banner.dto.InsertBannerRequest;
import com.banner.dto.InsertBannerResponse;
import com.banner.interactor.InsertBannerInteractor;
import com.banner.auth.Authenticator;
import com.banner.auth.Credentials;
import com.banner.handler.ErrorHandler;
import com.banner.ui.ImageSelectionForm;
import com.banner.notification.NotificationService;

/**
 * Controller for banner insertion operations.
 * Orchestrates authentication, form display, and interaction with the Interactor.
 */
public class BannerController {
    private InsertBannerInteractor insertBannerInteractor;
    private Authenticator authenticator;
    private ErrorHandler errorHandler;
    private NotificationService notificationService;
    private ImageSelectionForm currentForm;

    public BannerController(InsertBannerInteractor insertBannerInteractor,
                            Authenticator authenticator,
                            ErrorHandler errorHandler,
                            NotificationService notificationService) {
        this.insertBannerInteractor = insertBannerInteractor;
        this.authenticator = authenticator;
        this.errorHandler = errorHandler;
        this.notificationService = notificationService;
    }

    /**
     * Authenticate operator (entry condition).
     */
    public boolean authenticate(Credentials credentials) {
        return authenticator.validate(credentials);
    }

    /**
     * Display form for image selection (system displays form requirement).
     */
    public ImageSelectionForm requestBannerForm() {
        currentForm = new ImageSelectionForm();
        currentForm.display();
        return currentForm;
    }

    /**
     * Insert banner after confirmation.
     */
    public InsertBannerResponse insertBanner(InsertBannerRequest request) {
        if (!requestConfirmation()) {
            return cancelOperation();
        }
        InsertBannerResponse response = insertBannerInteractor.execute(request);
        if (response.isSuccess()) {
            // Notify about successful insertion (exit condition)
            notificationService.sendInsertionNotification(response.getBannerId());
        }
        return response;
    }

    /**
     * Cancel operation at any point (break flow in sequence diagram).
     */
    public InsertBannerResponse cancelOperation() {
        return errorHandler.handleCancelledOperation();
    }

    /**
     * Simulate user confirmation prompt.
     */
    public boolean requestConfirmation() {
        // In real UI would pop up a confirmation dialog.
        // Here assume true for successful flow.
        return true;
    }

    // New method to handle sequence diagram message m12: confirmInsertion()
    public InsertBannerResponse confirmInsertion() {
        // This would be called after operator confirms insertion
        // For now, delegate to insertBanner with a dummy request (to be filled by UI)
        // In real implementation, the form would provide the request
        return new InsertBannerResponse(false, null, "Not implemented in confirmInsertion");
    }
}