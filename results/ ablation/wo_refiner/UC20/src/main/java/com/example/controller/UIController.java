package com.example.controller;

import com.example.service.BannerService;
import com.example.service.AuthService;
import com.example.model.RestPoint;
import java.util.List;

/**
 * UI Controller that manages interactions between the Agency Operator and the system.
 * Implements flow from REQ-005.
 */
public class UIController {
    private String selectedRestPointId;
    private byte[] imageData;
    private String imageType;
    private BannerService bannerService;
    private AuthService authService;

    public UIController(BannerService bannerService, AuthService authService) {
        this.bannerService = bannerService;
        this.authService = authService;
    }

    public void selectRestPoint(String restPointId) {
        // This method is called by the Agency Operator (sequence diagram step 2).
        // In a real UI, this would trigger a display update.
        this.selectedRestPointId = restPointId;
    }

    /**
     * Access the banner insertion function (sequence diagram group "Access Banner Insertion Function").
     */
    public void accessInsertBanner() {
        if (validateSelectedRestPoint()) {
            // Display Image Selection Form (sequence diagram step after validation).
            // In a real implementation, this would update the UI.
            displayImageSelectionForm();
        } else {
            // Handle invalid selection.
        }
    }

    public void selectImage(byte[] imageData, String imageType) {
        this.imageData = imageData;
        this.imageType = imageType;
    }

    public void submitInsertRequest() {
        // Called by Agency Operator (sequence diagram step 9).
        // Delegates to BannerService.
        bannerService.insertBanner(selectedRestPointId, imageData, imageType);
    }

    public void storeSelectedRestPointId(String restPointId) {
        this.selectedRestPointId = restPointId;
    }

    public void storeImageData(byte[] imageData, String imageType) {
        this.imageData = imageData;
        this.imageType = imageType;
    }

    /**
     * Validates that a rest point has been selected.
     * @return true if a rest point is selected.
     */
    public boolean validateSelectedRestPoint() {
        return selectedRestPointId != null && !selectedRestPointId.isEmpty();
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageType() {
        return imageType;
    }

    /**
     * Retrieves rest points for the agency (sequence diagram step 4).
     * This would be called from the UI layer.
     */
    public List<RestPoint> getRestPointsForAgency(String agencyId) {
        // Delegate to BannerService (sequence diagram step 5).
        return bannerService.getRestPointsForAgency(agencyId);
    }

    /**
     * Displays a success notification to the Agency Operator.
     */
    public void showSuccessNotification(String message) {
        System.out.println("Success: " + message);
    }

    /**
     * Displays an error notification to the Agency Operator.
     */
    public void showErrorNotification(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a cancellation notification.
     */
    public void showCancellationNotification(String message) {
        System.out.println("Cancelled: " + message);
    }

    /**
     * Display Rest Point Selection Form (sequence diagram return m7).
     */
    public void displayRestPointSelectionForm() {
        System.out.println("Displaying Rest Point Selection Form");
    }

    /**
     * Display Image Selection Form (sequence diagram return m12).
     */
    public void displayImageSelectionForm() {
        System.out.println("Displaying Image Selection Form");
    }

    /**
     * Show operation cancelled message (sequence diagram return m45).
     */
    public void showOperationCancelled() {
        System.out.println("Operation Cancelled");
    }

    /**
     * Show error: maximum banners reached (sequence diagram return m48).
     */
    public void showErrorMaximumBannersReached() {
        System.out.println("Error: Maximum banners reached");
    }

    /**
     * Show error: invalid image format/dimensions (sequence diagram return m54).
     */
    public void showErrorInvalidImageFormat() {
        System.out.println("Error: Invalid image format/dimensions");
    }

    /**
     * Show error: connection interrupted (sequence diagram return m60).
     */
    public void showErrorConnectionInterrupted() {
        System.out.println("Error: Connection interrupted");
    }
}