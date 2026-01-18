package com.example.bannerapp;

import java.util.List;

/**
 * Acts as the controller in an MVC pattern, handling user input and coordinating
 * between the view and the service layers for banner management.
 */
public class BannerController {
    private BannerService bannerService;
    private BannerView bannerView;
    private String selectedBannerId;
    private String currentOperatorId; // Stores the operator currently interacting with the system
    private List<Banner> currentBannersDisplayed; // To keep track of banners for selection

    /**
     * Constructs a BannerController with the necessary service and view dependencies.
     * @param bannerService The service layer for banner operations.
     * @param bannerView The view layer for displaying UI and handling user input.
     */
    public BannerController(BannerService bannerService, BannerView bannerView) {
        this.bannerService = bannerService;
        this.bannerView = bannerView;
    }

    /**
     * Initiates the "remove banner" feature flow.
     * This method retrieves banners for the given operator's Point of Rest and
     * then prompts the user for selection.
     * @param operatorId The ID of the operator requesting the feature.
     */
    public void handleRemoveBannerFeature(String operatorId) {
        System.out.println("\n[Controller] handleRemoveBannerFeature initiated for operator: " + operatorId);
        this.currentOperatorId = operatorId; // Store the current operator ID

        try {
            // Step 1: Controller asks Service for banners
            List<Banner> banners = bannerService.getBannersByPointOfRest(operatorId);
            this.currentBannersDisplayed = banners; // Store the list for later reference (e.g., getting selected banner details)

            // Step 2: Controller tells View to display the list
            bannerView.displayBannerList(banners);

            if (!banners.isEmpty()) {
                // Step 3: Controller asks View to prompt for banner selection
                String bannerId = bannerView.promptSelectBanner();
                handleBannerSelection(bannerId);
            } else {
                bannerView.displayError("No banners found for your Point of Rest.");
            }
        } catch (NetworkConnectionException e) {
            bannerView.displayError("Connection to ETOUR server interrupted: " + e.getMessage());
        } catch (RuntimeException e) {
            // Catching other potential runtime exceptions like from authorization in service if it re-throws network error
            bannerView.displayError("An unexpected error occurred: " + e.getMessage());
            System.err.println("Detailed error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the user's selection of a banner to remove.
     * It stores the selected banner ID and prompts for confirmation.
     * @param bannerId The ID of the banner selected by the user.
     */
    public void handleBannerSelection(String bannerId) {
        System.out.println("[Controller] Banner selected: " + bannerId);
        this.selectedBannerId = bannerId;

        // Find the selected banner object to pass to the view for confirmation prompt
        Banner selectedBanner = null;
        if (currentBannersDisplayed != null) {
            selectedBanner = currentBannersDisplayed.stream()
                                  .filter(b -> b.getId().equals(bannerId))
                                  .findFirst()
                                  .orElse(null);
        }

        if (selectedBanner != null) {
            // Step 4: Controller tells View to display confirmation prompt
            bannerView.displayConfirmationPrompt(selectedBanner);

            // Step 5: Controller asks View for confirmation decision
            boolean confirmed = bannerView.promptConfirmDeletion();
            if (confirmed) {
                handleConfirmation();
            } else {
                handleCancellation();
            }
        } else {
            bannerView.displayError("Selected banner '" + bannerId + "' not found in the list or invalid selection.");
            // Clear selected banner state if selection was invalid
            this.selectedBannerId = null;
        }
    }

    /**
     * Handles the user's confirmation to delete the previously selected banner.
     * This triggers the deletion process via the service layer.
     */
    public void handleConfirmation() {
        System.out.println("[Controller] Deletion confirmed for banner: " + selectedBannerId);
        if (selectedBannerId == null) {
            bannerView.displayError("No banner was selected for deletion.");
            return;
        }

        try {
            // Step 6: Controller asks Service to delete the banner
            boolean success = bannerService.deleteBanner(selectedBannerId, currentOperatorId);

            if (success) {
                // Step 7: Controller tells View to display success message
                bannerView.displayDeletionSuccess();
            } else {
                // This 'else' branch handles cases like data integrity failure from service
                // If authorization fails, UnauthorizedOperationException will be thrown and caught below
                bannerView.displayError("Deletion failed due to data integrity issues.");
            }
        } catch (UnauthorizedOperationException e) {
            // Specific handling for authorization failure as per sequence diagram m29
            bannerView.displayError("Error: Not authorized.");
        } catch (NetworkConnectionException e) {
            // Step 8: Controller catches NetworkConnectionException and tells View to display error
            bannerView.displayError("Connection to ETOUR server interrupted. Please try again later. (" + e.getMessage() + ")");
        } catch (RuntimeException e) {
            // Catching other potential runtime exceptions like from authorization in service if it re-throws network error
            bannerView.displayError("An unexpected error occurred during deletion: " + e.getMessage());
            System.err.println("Detailed error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clear selected banner state after an operation completes
            this.selectedBannerId = null;
            this.currentBannersDisplayed = null;
        }
    }

    /**
     * Handles the user's cancellation of the deletion operation.
     */
    public void handleCancellation() {
        System.out.println("[Controller] Deletion cancelled by user.");
        bannerView.displayError("Operation cancelled."); // Matches sequence diagram m40 return message
        // Clear selected banner state after cancellation
        this.selectedBannerId = null;
        this.currentBannersDisplayed = null;
    }
}