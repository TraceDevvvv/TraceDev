package com.example.banner;

import java.util.List;
import java.util.Map;

/**
 * Controller layer for banner management, handling user input,
 * interacting with serv, and updating the view.
 */
public class BannerController {
    private BannerService bannerService;
    private BannerView bannerView;
    private AuthenticationService authenticationService; // R3
    private UserSession userSession; // R3

    // To hold state between `changeBannerImage` and `confirmBannerImageChange` calls
    private String pendingBannerId;
    private String pendingNewImageUrl;

    /**
     * Constructs a new BannerController.
     * @param bannerService The service for banner business logic.
     * @param bannerView The view for displaying UI.
     * @param authenticationService The service for authentication.
     * @param userSession The current user session.
     */
    public BannerController(BannerService bannerService, BannerView bannerView,
                            AuthenticationService authenticationService, UserSession userSession) {
        this.bannerService = bannerService;
        this.bannerView = bannerView;
        this.authenticationService = authenticationService;
        this.userSession = userSession;
    }

    /**
     * Authenticates the user and checks if they have the 'operator' role.
     * Added to satisfy requirement R3 and precondition in sequence diagram.
     * @return true if authenticated as an operator, false otherwise.
     */
    private boolean checkAuthenticationAndRole() {
        System.out.println("[Controller] Checking authentication...");
        if (!authenticationService.isAuthenticated() || userSession == null || !userSession.isAuthenticated) {
            bannerView.displayError("Authentication required. Please log in.");
            return false;
        }

        User currentUser = authenticationService.getCurrentUser();
        if (currentUser == null || !"operator".equals(currentUser.getRole())) {
            bannerView.displayError("Unauthorized access. Only operators can perform this action.");
            return false;
        }
        System.out.println("[Controller] User '" + currentUser.getId() + "' (Role: " + currentUser.getRole() + ") is authenticated.");
        return true;
    }

    /**
     * Initiates the process of selecting and displaying banners for editing.
     * @param restaurantId The ID of the restaurant whose banners are to be edited.
     */
    public void selectBannerEditing(String restaurantId) {
        System.out.println("\n[Controller] Operator requests to select banners for editing for restaurant: " + restaurantId);
        if (!checkAuthenticationAndRole()) {
            return;
        }

        try {
            // Comment: Call service to get banners. Handle NetworkException.
            List<Banner> banners = bannerService.getBannersByRestaurant(restaurantId);
            bannerView.displayBannersList(banners);
        } catch (NetworkException e) {
            // Comment: Handles R17 by displaying an error if network connection is interrupted.
            bannerView.displayError("Connection to server interrupted while fetching banners. Please try again. Details: " + e.getMessage());
        }
    }

    /**
     * Selects a specific banner for editing and displays its form.
     * @param bannerId The ID of the banner to select.
     */
    public void selectBanner(String bannerId) {
        System.out.println("\n[Controller] Operator selects banner: " + bannerId + " for editing.");
        if (!checkAuthenticationAndRole()) {
            return;
        }

        try {
            // Comment: Call service to get the specific banner. Handle NetworkException.
            Banner selectedBanner = bannerService.getBannerById(bannerId);
            if (selectedBanner != null) {
                // Comment: Display the edit form and then the image selection form.
                bannerView.displayBannerEditForm(selectedBanner);
                bannerView.displayImageSelectionForm();
                // Store the selected banner ID for future actions related to this edit session
                this.pendingBannerId = bannerId;
            } else {
                bannerView.displayError("Banner with ID " + bannerId + " not found.");
            }
        } catch (NetworkException e) {
            // Comment: Handles R17 by displaying an error if network connection is interrupted.
            bannerView.displayError("Connection to server interrupted while fetching banner details. Please try again. Details: " + e.getMessage());
        }
    }

    /**
     * Handles the request to change a banner's image. This method performs validation
     * and prompts for confirmation, but does not finalize the change.
     * This method is typically called by the view's `onImageSelected` callback (R9).
     * @param bannerId The ID of the banner whose image is to be changed.
     * @param newImageUrl The proposed new image URL.
     * @param imageCharacteristics A map of characteristics of the new image.
     */
    public void changeBannerImage(String bannerId, String newImageUrl, Map<String, String> imageCharacteristics) {
        System.out.println("\n[Controller] Operator requests to change image for banner " + bannerId + " to: " + newImageUrl);
        if (!checkAuthenticationAndRole()) {
            return;
        }

        try {
            // Comment: Validate the image and prepare for confirmation using the service.
            boolean isValid = bannerService.validateAndPrepareImageChange(bannerId, newImageUrl, imageCharacteristics);

            if (isValid) {
                // Comment: If valid, retrieve the banner to display in the confirmation prompt.
                Banner bannerToConfirm = bannerService.getBannerById(bannerId);
                if (bannerToConfirm != null) {
                    // Comment: Store pending details for the confirmation step.
                    this.pendingBannerId = bannerId;
                    this.pendingNewImageUrl = newImageUrl;
                    bannerView.displayConfirmationPrompt(bannerToConfirm, newImageUrl);
                } else {
                    bannerView.displayError("Could not retrieve banner " + bannerId + " for confirmation.");
                }
            } else {
                // Comment: If not valid, display an error directly.
                bannerView.displayError("Invalid image. Please upload a valid image (e.g., check size and format).");
                this.pendingBannerId = null; // Clear pending state
                this.pendingNewImageUrl = null;
            }
        } catch (NetworkException e) {
            // Comment: Handles R17 by displaying an error if network connection is interrupted during banner retrieval.
            bannerView.displayError("Connection to server interrupted while validating image change. Please try again. Details: " + e.getMessage());
            this.pendingBannerId = null; // Clear pending state
            this.pendingNewImageUrl = null;
        }
    }

    /**
     * Confirms and finalizes the banner image change.
     * This method is called after the user has confirmed the change in the UI.
     * @param bannerId The ID of the banner to update.
     * @param newImageUrl The new image URL to set.
     */
    public void confirmBannerImageChange(String bannerId, String newImageUrl) {
        System.out.println("\n[Controller] Operator confirms image change for banner " + bannerId + " to: " + newImageUrl);
        if (!checkAuthenticationAndRole()) {
            return;
        }

        // Comment: Verify if the confirmation matches the pending change.
        if (!bannerId.equals(this.pendingBannerId) || !newImageUrl.equals(this.pendingNewImageUrl)) {
            bannerView.displayError("Mismatch in pending confirmation. Please re-initiate the image change.");
            return;
        }

        try {
            // Comment: Call service to finalize the image change and persist it. Handle NetworkException.
            bannerService.finalizeImageChange(bannerId, newImageUrl);
            bannerView.displaySuccessNotification();
        } catch (NetworkException e) {
            // Comment: Handles R17 by displaying an error if network connection is interrupted during persistence.
            bannerView.displayError("Connection to server interrupted while finalizing image change. Please try again. Details: " + e.getMessage());
        } finally {
            // Comment: Clear pending state after confirmation attempt.
            this.pendingBannerId = null;
            this.pendingNewImageUrl = null;
        }
    }

    /**
     * Cancels the current banner editing operation or a pending image change.
     */
    public void cancelOperation() {
        System.out.println("\n[Controller] Operator cancels current operation.");
        if (!checkAuthenticationAndRole()) {
            return;
        }
        // Comment: Clear any pending state and notify the view.
        this.pendingBannerId = null;
        this.pendingNewImageUrl = null;
        bannerView.displayCancellationNotification();
    }
}