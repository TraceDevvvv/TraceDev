package com.example.banner;

import java.util.List;
import java.util.Map;

/**
 * A mock implementation of the BannerView interface for testing and demonstration purposes.
 * It prints messages to the console to simulate UI interactions.
 */
public class MockBannerView implements BannerView {
    // A reference to the controller is needed to simulate user interaction callbacks (like onImageSelected).
    // This creates a circular dependency which is often avoided in pure MVC, but necessary for a runnable mock.
    private BannerController controller;
    private String lastDisplayedConfirmationBannerId;
    private String lastDisplayedConfirmationImageUrl;

    /**
     * Sets the controller reference. This is typically done after both view and controller are instantiated.
     * @param controller The BannerController instance to callback to.
     */
    public void setController(BannerController controller) {
        this.controller = controller;
    }

    @Override
    public void displayBannersList(List<Banner> banners) {
        System.out.println("\n--- MockBannerView: Displaying Banners List ---");
        if (banners == null || banners.isEmpty()) {
            System.out.println("  No banners available.");
        } else {
            banners.forEach(banner -> System.out.println("  - " + banner));
        }
        System.out.println("----------------------------------------------");
    }

    @Override
    public void displayBannerEditForm(Banner banner) {
        System.out.println("\n--- MockBannerView: Displaying Banner Edit Form ---");
        System.out.println("  Editing Banner: " + banner.getName() + " (ID: " + banner.getId() + ")");
        System.out.println("  Current Image URL: " + banner.getCurrentImageUrl());
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void displayImageSelectionForm() {
        System.out.println("--- MockBannerView: Displaying Image Selection Form ---");
        System.out.println("  Please select a new image from available options.");
        System.out.println("  (Simulating user selecting an image and calling onImageSelected internally)");
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displayConfirmationPrompt(Banner banner, String newImageUrl) {
        System.out.println("\n--- MockBannerView: Displaying Confirmation Prompt ---");
        System.out.println("  Are you sure you want to change banner '" + banner.getName() + "' (ID: " + banner.getId() + ")");
        System.out.println("  image from '" + banner.getCurrentImageUrl() + "' to '" + newImageUrl + "'?");
        System.out.println("  (To confirm, call controller.confirmBannerImageChange. To cancel, call controller.cancelOperation.)");
        this.lastDisplayedConfirmationBannerId = banner.getId();
        this.lastDisplayedConfirmationImageUrl = newImageUrl;
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displaySuccessNotification() {
        System.out.println("\n*** MockBannerView: SUCCESS: Operation completed successfully! ***");
    }

    @Override
    public void displayCancellationNotification() {
        System.out.println("\n--- MockBannerView: INFO: Operation cancelled. ---");
    }

    @Override
    public void displayError(String message) {
        System.err.println("\n!!! MockBannerView: ERROR: " + message + " !!!");
    }

    /**
     * Simulates the user selecting an image. This method then calls the controller's
     * changeBannerImage method as a callback, fulfilling R9.
     * @param selectedImageUrl The URL of the selected image.
     * @param characteristics A map of characteristics of the selected image.
     */
    @Override
    public void onImageSelected(String selectedImageUrl, Map<String, String> characteristics) {
        System.out.println("\n--- MockBannerView: Image Selected Callback ---");
        System.out.println("  Image selected: " + selectedImageUrl + " with characteristics: " + characteristics);
        if (controller != null && lastDisplayedConfirmationBannerId != null) {
            // Comment: This assumes the view knows which banner is currently being edited.
            // In a real UI, this context would be managed (e.g., via a selected banner ID in the view's state).
            // For this mock, we use the last banner ID that an edit form was displayed for.
            // Note: The sequence diagram shows `Operator -> Controller: changeBannerImage`,
            // implying `onImageSelected` is a UI internal event that then triggers the controller method.
            System.out.println("  Calling controller.changeBannerImage for banner ID: " + lastDisplayedConfirmationBannerId);
            controller.changeBannerImage(lastDisplayedConfirmationBannerId, selectedImageUrl, characteristics);
        } else {
            System.err.println("  Error: Controller not set or no banner selected for editing in MockBannerView.");
        }
        System.out.println("-----------------------------------------------");
    }

    // Helper for demo to get context for confirmation calls
    public String getLastDisplayedConfirmationBannerId() {
        return lastDisplayedConfirmationBannerId;
    }

    public String getLastDisplayedConfirmationImageUrl() {
        return lastDisplayedConfirmationImageUrl;
    }
}