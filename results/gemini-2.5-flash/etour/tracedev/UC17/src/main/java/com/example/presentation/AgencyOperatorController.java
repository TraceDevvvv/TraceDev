package com.example.presentation;

import com.example.application.ChangeBannerImageService;
import com.example.application.ImageValidationResult;
import com.example.domain.Banner;
import com.example.domain.TurningPoint;

import java.util.List;
import java.util.Arrays; // For byte[] representation in display methods

/**
 * Presentation layer controller for Agency Operators to manage banner images.
 * This class handles user input, orchestrates calls to the application service,
 * and displays results or errors back to the user.
 */
public class AgencyOperatorController {

    // Dependency on the application service layer
    private final ChangeBannerImageService changeBannerImageService;

    // Temporary storage for image data between upload and confirmation steps,
    // as suggested by the sequence diagram passing newImageData from AOC to CBIS
    // in applyImageChange. In a real UI, this would be managed by the UI form/state.
    private byte[] currentImageDataForConfirmation;
    private String currentBannerIdForConfirmation;

    /**
     * Constructor for Dependency Injection.
     * @param changeBannerImageService The service for changing banner images.
     */
    public AgencyOperatorController(ChangeBannerImageService changeBannerImageService) {
        this.changeBannerImageService = changeBannerImageService;
    }

    /**
     * Requests a list of available turning points from the system.
     * Corresponds to Step 1 in the Sequence Diagram.
     * @return A list of TurningPoint objects.
     */
    public List<TurningPoint> requestTurningPoints() {
        System.out.println("AOC: Requesting available turning points.");
        List<TurningPoint> turningPointsList = changeBannerImageService.getAvailableTurningPoints();
        displayTurningPoints(turningPointsList);
        return turningPointsList;
    }

    /**
     * Selects a turning point and retrieves its associated banners.
     * Corresponds to Step 2 in the Sequence Diagram.
     * @param id The ID of the selected turning point.
     * @return A list of Banner objects associated with the turning point.
     */
    public List<Banner> selectTurningPoint(String id) {
        System.out.println("AOC: Selecting turning point with ID: " + id);
        List<Banner> bannerList = changeBannerImageService.getBannersForTurningPoint(id);
        displayBanners(bannerList);
        return bannerList;
    }

    /**
     * Selects a specific banner to view its details for editing.
     * Corresponds to REQ-SD-002 and Step 5 in the Sequence Diagram.
     * @param id The ID of the selected banner.
     * @return The selected Banner object.
     */
    public Banner getBannerDetails(String id) { // Renamed from selectBanner to match class diagram and SD message m16
        System.out.println("AOC: Getting banner details for ID: " + id + " for editing.");
        Banner selectedBannerDetails = changeBannerImageService.getBannerDetails(id); // REQ-SD-002, m16
        if (selectedBannerDetails != null) {
            displayBannerEditor(selectedBannerDetails);
        } else {
            displayError("Banner with ID " + id + " not found.");
        }
        return selectedBannerDetails;
    }

    /**
     * Uploads a new image for a specified banner and initiates validation.
     * Corresponds to REQ-CD-006 and Step 7, 9, 10 in the Sequence Diagram.
     * @param bannerId The ID of the banner to update.
     * @param imageData The byte array of the new image data.
     */
    public void uploadNewImage(String bannerId, byte[] imageData) { // Renamed from uploadImage to match class diagram and SD message m21
        System.out.println("AOC: Uploading image for banner ID: " + bannerId + ". Image data size: " + imageData.length + " bytes.");
        // Store image data temporarily for potential confirmation
        this.currentImageDataForConfirmation = imageData;
        this.currentBannerIdForConfirmation = bannerId;

        // CBIS.processNewImageForBanner now handles the callback for confirmation/error
        ImageValidationResult validationResult = changeBannerImageService.processNewImageForBanner(bannerId, imageData); // m22

        if (validationResult != ImageValidationResult.VALID) {
            // If not valid, CBIS would have called displayError
            System.out.println("AOC: Image validation failed. Check messages for details.");
        }
        // If VALID, CBIS will call displayConfirmationPrompt directly.
    }

    /**
     * Confirms the image change after preview.
     * Corresponds to Step 13 in the Sequence Diagram.
     * @param bannerId The ID of the banner being updated.
     */
    public void confirmImageChange(String bannerId) {
        System.out.println("AOC: Confirmation received for banner ID: " + bannerId);
        if (currentImageDataForConfirmation == null || !bannerId.equals(currentBannerIdForConfirmation)) {
            displayError("No image data found for confirmation or banner ID mismatch. Please re-upload the image.");
            return;
        }

        boolean success = changeBannerImageService.applyImageChange(bannerId, currentImageDataForConfirmation);

        if (success) {
            // Success messages are handled by CBIS's call to AOC
            System.out.println("AOC: Image change process completed. Check previous messages for status.");
        } else {
            // Error messages are handled by CBIS's call to AOC
            System.out.println("AOC: Image change process failed. Check previous messages for status.");
        }

        // Clear temporary data after attempt
        this.currentImageDataForConfirmation = null;
        this.currentBannerIdForConfirmation = null;
    }

    /**
     * Displays a success message to the Agency Operator.
     * @param message The success message.
     */
    public void displaySuccess(String message) {
        System.out.println("--- Success ---");
        System.out.println(message);
        System.out.println("---------------");
    }

    /**
     * Displays a warning message to the Agency Operator.
     * @param message The warning message.
     */
    public void displayWarning(String message) {
        System.out.println("--- Warning ---");
        System.out.println(message);
        System.out.println("---------------");
    }

    /**
     * Displays an error message to the Agency Operator.
     * @param message The error message.
     */
    public void displayError(String message) {
        System.err.println("--- Error ---");
        System.err.println(message);
        System.err.println("---------------");
    }

    /**
     * Mock method to display turning points to the UI.
     * @param turningPoints The list of turning points.
     */
    private void displayTurningPoints(List<TurningPoint> turningPoints) {
        System.out.println("AOC: Displaying available turning points:");
        if (turningPoints.isEmpty()) {
            System.out.println("  No turning points found.");
        } else {
            turningPoints.forEach(tp -> System.out.println("  ID: " + tp.getId() + ", Name: " + tp.getName()));
        }
    }

    /**
     * Mock method to display banners for a selected turning point to the UI.
     * @param banners The list of banners.
     */
    private void displayBanners(List<Banner> banners) {
        System.out.println("AOC: Displaying banners for selected turning point:");
        if (banners.isEmpty()) {
            System.out.println("  No banners found for this turning point.");
        } else {
            banners.forEach(b -> System.out.println("  ID: " + b.getId() + ", Name: " + b.getName() + ", Current Image URL: " + (b.getImage() != null ? b.getImage().getUrl() : "N/A")));
        }
    }

    /**
     * Mock method to display the banner editor with details.
     * @param banner The selected banner details.
     */
    private void displayBannerEditor(Banner banner) {
        System.out.println("AOC: Displaying banner editor for banner ID: " + banner.getId());
        System.out.println("  Banner Name: " + banner.getName());
        System.out.println("  Current Image URL: " + (banner.getImage() != null ? banner.getImage().getUrl() : "None"));
        System.out.println("  Last Modified: " + banner.getLastModifiedDate());
        System.out.println("  Ready for image upload...");
    }

    /**
     * Mock method to display the image preview and ask for confirmation.
     * Corresponds to Step 11 in the Sequence Diagram (message m28).
     * @param bannerId The ID of the banner.
     * @param imagePreviewData The byte array of the image preview.
     */
    public void displayConfirmationPrompt(String bannerId, byte[] imagePreviewData) { // Renamed from displayImagePreviewAndConfirmation to match SD message m28
        System.out.println("AOC: Displaying image preview for banner ID: " + bannerId);
        System.out.println("  Preview data (first 20 bytes): " + Arrays.toString(Arrays.copyOf(imagePreviewData, Math.min(imagePreviewData.length, 20))) + "...");
        System.out.println("  Please confirm to apply this image change.");
        // In a real UI, this would render the image and provide confirm/cancel buttons.
    }
}