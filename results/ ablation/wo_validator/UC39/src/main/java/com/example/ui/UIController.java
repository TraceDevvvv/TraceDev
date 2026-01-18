package com.example.ui;

import com.example.application.UpdateBannerImageUseCase;
import com.example.application.commands.UpdateBannerImageCommand;
import com.example.application.results.UpdateBannerImageResult;

/**
 * UI Controller that handles interactions from the operator as per the sequence diagram.
 * This is a simplified version; in a real application this would be a REST controller or similar.
 */
public class UIController {
    private final UpdateBannerImageUseCase updateBannerImageUseCase;

    public UIController(UpdateBannerImageUseCase updateBannerImageUseCase) {
        this.updateBannerImageUseCase = updateBannerImageUseCase;
    }

    // Step 1: Operator selects banner editing – display list (simulated here)
    public void selectBannerEditing() {
        // In a real UI this would fetch and display a list of banners
        System.out.println("Displaying banner list...");
    }

    // Step 2: Operator selects a specific banner – show image selection form
    public void selectBanner(String bannerId) {
        // Show form for uploading a new image
        System.out.println("Displaying image selection form for banner " + bannerId);
    }

    // Step 3: Operator uploads image and submits the change request
    public void submitChangeRequest(String bannerId, String operatorId, byte[] imageData, String contentType) {
        UpdateBannerImageCommand command = new UpdateBannerImageCommand(bannerId, operatorId, imageData, contentType);
        UpdateBannerImageResult result = updateBannerImageUseCase.execute(command);

        if (result.isSuccess()) {
            displaySuccessNotification(result.getMessage());
        } else {
            displayErrorNotification(result.getMessage());
        }
    }

    // UI notification methods
    public void displaySuccessNotification(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void displayErrorNotification(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }
}