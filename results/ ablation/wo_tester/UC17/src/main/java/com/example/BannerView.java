package com.example;

import java.util.List;

/**
 * View component for displaying banners and handling user interactions.
 */
public class BannerView {
    private BannerController controller;
    private ErrorHandler errorHandler;

    public BannerView(BannerController controller, ErrorHandler errorHandler) {
        this.controller = controller;
        this.errorHandler = errorHandler;
    }

    public void displayRefreshmentPoints(List<RefreshmentPoint> points) {
        System.out.println("=== Refreshment Points ===");
        for (RefreshmentPoint point : points) {
            System.out.println(point.getId() + ": " + point.getName());
        }
    }

    public void displayBanners(List<Banner> banners) {
        System.out.println("=== Banners ===");
        for (Banner banner : banners) {
            System.out.println(banner.getId() + ": " + banner.getImageUrl() + 
                             " (Point: " + banner.getRefreshmentPointId() + ")");
        }
    }

    public void showImageSelectionForm(Banner banner) {
        System.out.println("=== Edit Banner: " + banner.getId() + " ===");
        System.out.println("Current image: " + banner.getImageUrl());
        System.out.println("Please select a new image file.");
    }

    public void displayImagePreview(ImageData imageData) {
        System.out.println("=== Image Preview ===");
        System.out.println("File: " + imageData.getFileName());
        System.out.println("Type: " + imageData.getMimeType());
        System.out.println("Size: " + imageData.getSize() + " bytes");
    }

    public boolean showConfirmationDialog() {
        // In a real application, this would show a dialog
        // For simplicity, we'll assume the user confirms
        System.out.println("Confirm image update? (Y/N)");
        return true; // Assume confirmation
    }

    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
        errorHandler.handleError(message);
    }
}