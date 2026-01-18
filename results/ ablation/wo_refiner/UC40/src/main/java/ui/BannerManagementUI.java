package ui;

import controller.BannerController;
import dto.DeleteBannerRequest;
import dto.DeleteBannerResponse;

/**
 * UI component for banner management (REQ‑007).
 * Simulates user interactions and displays notifications.
 */
public class BannerManagementUI {
    private BannerController bannerController;
    private String currentPointOfRestId; // assumed to be set by context

    public BannerManagementUI(BannerController controller) {
        this.bannerController = controller;
    }

    /**
     * Loads and displays the list of banners for a given point of rest (REQ‑007).
     * This is a stub for the actual UI logic.
     */
    public void loadBannerList(String pointOfRestId) {
        this.currentPointOfRestId = pointOfRestId;
        System.out.println("[UI] Loading banner list for point of rest: " + pointOfRestId);
        // In a real UI, we would fetch data and update the view.
    }

    /**
     * Shows a confirmation dialog for banner deletion (REQ‑009).
     * Returns true if the user confirms, false otherwise.
     */
    public boolean showConfirmationDialog(String bannerId) {
        System.out.println("[UI] Show confirmation dialog for deleting banner: " + bannerId);
        // Simulate user confirmation – in reality, this would block until user action.
        return true; // assume confirmed for main flow
    }

    /**
     * Displays a notification message to the operator.
     */
    public void displayNotification(String message) {
        System.out.println("[UI Notification] " + message);
    }

    /**
     * Displays an error message to the operator.
     */
    public void displayError(String message) {
        System.err.println("[UI Error] " + message);
    }

    /**
     * Simulates the delete banner workflow as per sequence diagram.
     * @param operatorId the ID of the operator performing the action
     * @param bannerId the banner selected for deletion
     */
    public void triggerDeleteBanner(String operatorId, String bannerId) {
        // Step 1‑3 are simulated by calling this method.
        // Step 4: confirmation dialog
        boolean confirmed = showConfirmationDialog(bannerId);
        if (!confirmed) {
            // Alternative Flow 1: Operator cancels
            bannerController.cancelOperation("req-" + bannerId);
            displayNotification("Operation cancelled");
            return;
        }

        // Step 5: user confirmed, create request
        DeleteBannerRequest request = new DeleteBannerRequest(bannerId, operatorId, currentPointOfRestId);

        // Step 6: call controller
        DeleteBannerResponse response = bannerController.handleDeleteBanner(request);

        // Step 29‑31: handle response
        if (response.isOperationCancelled()) {
            displayNotification("Operation cancelled");
        } else if (response.isSuccessful()) {
            displayNotification(response.getMessage());
        } else {
            displayError(response.getMessage());
        }
    }

    // New method to simulate step 2: Load & display list of banners for the point of rest
    public void loadAndDisplayBanners() {
        System.out.println("[UI] Load & display list of banners for the point of rest");
        // This method is called from the UI after operator selects the feature.
    }

    // New method to simulate step 4: Display confirmation message
    public void displayConfirmationMessage() {
        System.out.println("[UI] Display confirmation message");
    }

    // New method to simulate step 30: Display success notification
    public void displaySuccessNotification() {
        System.out.println("[UI] Display success notification");
    }

    // New method to simulate step 37: Display error message
    public void displayErrorMessage() {
        System.err.println("[UI] Display error message");
    }

    // New method to simulate step 40: Close confirmation dialog
    public void closeConfirmationDialog() {
        System.out.println("[UI] Close confirmation dialog");
    }

    // New method to simulate step 48: Display connection error
    public void displayConnectionError() {
        System.err.println("[UI] Display connection error");
    }
}