package com.example.ui;

import com.example.controller.DeleteBannerController;
import com.example.model.Banner;
import com.example.model.RefreshmentPoint;
import java.util.List;

/**
 * UI boundary for the Delete Banner use case.
 * Displays lists, dialogs, and notifications.
 */
public class DeleteBannerUI {
    private DeleteBannerController controller;

    public DeleteBannerUI(DeleteBannerController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of refreshment points.
     * @param points the list to display
     */
    public void displayRefreshmentPoints(List<RefreshmentPoint> points) {
        System.out.println("=== Refreshment Points ===");
        for (RefreshmentPoint p : points) {
            System.out.println(p.getId() + ": " + p.getName() + " at " + p.getLocation());
        }
        System.out.println();
    }

    /**
     * Displays a list of banners.
     * @param banners the list to display
     */
    public void displayBanners(List<Banner> banners) {
        System.out.println("=== Banners ===");
        for (Banner b : banners) {
            System.out.println(b.getId() + ": " + b.getName() + " (URL: " + b.getContentUrl() + ")");
        }
        System.out.println();
    }

    /**
     * Shows a confirmation dialog.
     * @return true if user confirms, false otherwise
     */
    public boolean showConfirmationDialog() {
        // Simulate user confirmation: assume yes for simplicity
        System.out.println("Confirm deletion? (Yes/No) -> Assuming Yes");
        return true;
    }

    /**
     * Shows a success notification.
     */
    public void showSuccessNotification() {
        System.out.println("SUCCESS: Banner deleted successfully.");
    }

    /**
     * Shows an error notification with a message.
     * @param message error details
     */
    public void showErrorNotification(String message) {
        System.out.println("ERROR: " + message);
    }

    // Sequence diagram required methods for traceability
    public void requestRefreshmentPointsList() {
        // This method corresponds to m1: "Request refreshment points list"
        // The actual request is handled by the controller; UI just triggers the action.
        // In a real implementation, this would be called by the AO.
    }

    public void displayListOfRefreshmentPoints(List<RefreshmentPoint> points) {
        // This method corresponds to m4: "Display list of refreshment points"
        displayRefreshmentPoints(points);
    }

    public void selectRefreshmentPointAndAccessBannerRemovalFunction() {
        // This method corresponds to m5: "Select refreshment point and access banner removal function"
        // The actual selection is handled by the controller; UI triggers the action.
    }

    public void displayListOfBanners(List<Banner> banners) {
        // This method corresponds to m10: "Display list of banners"
        displayBanners(banners);
    }

    public void selectBannerFromListAndTriggerDeletion() {
        // This method corresponds to m11: "Select banner from list and trigger deletion"
        // The actual selection and deletion trigger are handled by the controller.
    }

    public void displayConfirmationMessage() {
        // This method corresponds to m19: "Display confirmation message"
        System.out.println("Confirm deletion of the selected banner?");
    }

    public void showConfirmDeletionMessage() {
        // This method corresponds to m20: "Show \"Confirm deletion?\" message"
        System.out.println("Confirm deletion?");
    }

    public void confirmOperation() {
        // This method corresponds to m21: "Confirm operation"
        // The actual confirmation is handled by showConfirmationDialog.
    }

    public void showSuccessNotificationSequence() {
        // This method corresponds to m24: "Show success notification"
        showSuccessNotification();
    }
}