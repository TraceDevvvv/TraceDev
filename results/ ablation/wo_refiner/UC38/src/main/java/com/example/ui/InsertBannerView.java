package com.example.ui;

import com.example.controller.InsertBannerController;
import com.example.web.InsertBannerRequestDTO;
import com.example.web.InsertBannerResponseDTO;

/**
 * View (UI boundary) for Insert Banner use case.
 * Added for consistency with sequence diagram UI boundary.
 */
public class InsertBannerView {
    private InsertBannerController controller;

    public InsertBannerView(InsertBannerController controller) {
        this.controller = controller;
    }

    /**
     * Links to requirement REQ-6.
     */
    public void selectInsertBannerFeature() {
        System.out.println("Insert Banner feature selected.");
        // Assume current user ID for simplicity
        String userId = "user123";
        if (controller.isUserAuthenticated(userId)) {
            displayImageSelectionForm();
        } else {
            System.out.println("User not authenticated.");
        }
    }

    /**
     * Links to requirement REQ-7.
     */
    public void displayImageSelectionForm() {
        System.out.println("Displaying image selection form.");
    }

    /**
     * Links to requirement REQ-8.
     */
    public void submitImageSelection(InsertBannerRequestDTO requestDTO) {
        System.out.println("Image selection submitted.");
        InsertBannerResponseDTO response = controller.handleInsertionRequest(requestDTO);
        if (response.getSuccess()) {
            displaySuccessNotification(response.getMessage());
        } else {
            displayErrorMessage(response.getMessage());
        }
    }

    /**
     * Links to requirement REQ-10.
     */
    public void confirmInsertion() {
        System.out.println("Insertion confirmed by user.");
        // In real scenario, would pass the confirmed request again.
    }

    /**
     * Links to requirement REQ-13.
     */
    public void cancelRequest() {
        System.out.println("Request cancelled by user.");
        displayCancellationMessage();
    }

    /**
     * Links to requirement REQ-13.
     */
    public void displayCancellationMessage() {
        System.out.println("Insertion cancelled.");
    }

    public void displayConfirmationPreview(byte[] imageData) {
        System.out.println("Showing preview of image.");
    }

    public void displaySuccessNotification(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void displayErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    // New methods for sequence diagram messages
    public void requestConfirmationShowPreview() {
        System.out.println("Request confirmation (show preview)");
    }

    public void displayConfirmationWithPreview() {
        System.out.println("Display confirmation with preview");
    }

    public void showSuccessNotification() {
        System.out.println("Show success notification");
    }

    public void showErrorMaximumBannersAlreadyEntered() {
        System.out.println("Show error: \"Maximum banners already entered\"");
    }

    public void showValidationError() {
        System.out.println("Show validation error");
    }

    public void displayConnectionError() {
        System.out.println("Display connection error");
    }
}