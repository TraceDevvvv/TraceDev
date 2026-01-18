package com.example.bannermanagement;

import java.util.List;

/**
 * Controller for the Agency Operator interface, handling user actions and orchestrating
 * business logic through the BannerManagementService and AuthenticationService.
 */
public class AgencyOperatorController {
    private final BannerManagementService bannerManagementService;
    private final AuthenticationService authenticationService; // Added to satisfy requirement REQ-001
    private String selectedRefreshmentPointId;
    private String selectedBannerId;
    private AgencyOperatorView view; // Reference to the view for callbacks

    /**
     * Constructs an AgencyOperatorController with injected service dependencies.
     *
     * @param service The BannerManagementService instance.
     * @param authService The AuthenticationService instance (added for REQ-001).
     */
    public AgencyOperatorController(BannerManagementService service, AuthenticationService authService) {
        this.bannerManagementService = service;
        this.authenticationService = authService;
    }

    /**
     * Sets the view associated with this controller. This is a common pattern
     * for enabling controller-to-view communication (e.g., updating UI).
     * @param view The AgencyOperatorView instance.
     */
    public void setView(AgencyOperatorView view) {
        this.view = view;
    }

    /**
     * Checks if the operator is authenticated. Added to satisfy requirement REQ-001.
     * @return true if authenticated, false otherwise.
     */
    public boolean checkAuthentication() {
        System.out.println("Controller: Checking authentication status.");
        return authenticationService.isLoggedIn();
    }

    /**
     * Fetches and displays all available refreshment points.
     * Catches ETOURConnectionException and informs the view.
     */
    public void showRefreshmentPoints() {
        System.out.println("Controller: Requesting all refreshment points.");
        try {
            List<RefreshmentPoint> points = bannerManagementService.getAvailableRefreshmentPoints();
            if (view != null) {
                view.displayRefreshmentPoints(points);
            }
        } catch (ETOURConnectionException e) {
            System.err.println("Controller Error: " + e.getMessage());
            if (view != null) {
                view.displayErrorMessage("Connection to ETOUR server interrupted. Please try again later. (" + e.getMessage() + ")");
            }
        }
    }

    /**
     * Stores the selected refreshment point ID.
     * @param rpId The ID of the selected refreshment point.
     */
    public void selectRefreshmentPoint(String rpId) {
        this.selectedRefreshmentPointId = rpId;
        System.out.println("Controller: Selected Refreshment Point ID: " + rpId);
    }

    /**
     * Initiates the banner removal flow by fetching and displaying banners for the selected refreshment point.
     * Catches ETOURConnectionException and informs the view.
     */
    public void accessBannerRemovalFunction() {
        if (selectedRefreshmentPointId == null || selectedRefreshmentPointId.isEmpty()) {
            if (view != null) {
                view.displayErrorMessage("Please select a refreshment point first.");
            }
            return;
        }
        System.out.println("Controller: Accessing banner removal for RP ID: " + selectedRefreshmentPointId);
        try {
            List<Banner> banners = bannerManagementService.getBannersForRefreshmentPoint(selectedRefreshmentPointId);
            if (view != null) {
                view.displayBanners(banners);
            }
        } catch (ETOURConnectionException e) {
            System.err.println("Controller Error: " + e.getMessage());
            if (view != null) {
                view.displayErrorMessage("Connection to ETOUR server interrupted. Cannot retrieve banners. (" + e.getMessage() + ")");
            }
        }
    }

    /**
     * Stores the ID of the banner selected for deletion.
     * @param bannerId The ID of the banner to be deleted.
     */
    public void selectBannerToDelete(String bannerId) {
        this.selectedBannerId = bannerId;
        System.out.println("Controller: Selected Banner ID for deletion: " + bannerId);
    }

    /**
     * Prompts the view to display a confirmation message for banner deletion.
     */
    public void confirmBannerDeletionRequest() {
        if (selectedBannerId == null || selectedBannerId.isEmpty()) {
            if (view != null) {
                view.displayErrorMessage("No banner selected for deletion.");
            }
            return;
        }
        if (view != null) {
            view.displayConfirmationMessage("Confirm deletion of banner " + selectedBannerId + "?");
        }
    }

    /**
     * Performs the banner deletion based on user confirmation.
     * Catches ETOURConnectionException and informs the view.
     * @param confirmation True if the user confirmed deletion, false otherwise.
     */
    public void performBannerDeletion(boolean confirmation) {
        if (!confirmation) {
            if (view != null) {
                view.displaySuccessMessage("Banner deletion cancelled.");
            }
            return;
        }

        if (selectedBannerId == null || selectedBannerId.isEmpty()) {
            if (view != null) {
                view.displayErrorMessage("No banner selected for deletion to confirm.");
            }
            return;
        }

        System.out.println("Controller: Performing banner deletion for ID: " + selectedBannerId);
        try {
            bannerManagementService.deleteBanner(selectedBannerId);
            if (view != null) {
                view.displaySuccessMessage("Banner deleted successfully.");
            }
            // Clear selected banner after deletion attempt
            selectedBannerId = null;
        } catch (ETOURConnectionException e) {
            System.err.println("Controller Error: " + e.getMessage());
            if (view != null) {
                view.displayErrorMessage("Connection to ETOUR server interrupted. Banner not deleted. (" + e.getMessage() + ")");
            }
        }
    }
}