package com.example.boundary;

import com.example.banner.Banner;
import com.example.point.RefreshmentPoint;
import com.example.controller.ModifyBannerController;
import com.example.notification.NotificationService;
import java.util.List;

/**
 * Boundary class for the banner modification UI.
 * Implements requirements REQ-006 and REQ-014.
 */
public class ModifyBannerForm {
    
    private ModifyBannerController controller;
    private NotificationService notificationService;
    
    /**
     * Constructs the form with controller and notification service.
     * @param controller the controller
     * @param notificationService the notification service (REQ-014)
     */
    public ModifyBannerForm(ModifyBannerController controller,
                           NotificationService notificationService) {
        this.controller = controller;
        this.notificationService = notificationService;
    }
    
    /**
     * Displays the list of refreshment points (REQ-006).
     * @param points the list of refreshment points
     */
    public void displayRefreshmentPointList(List<RefreshmentPoint> points) {
        System.out.println("=== Refreshment Points ===");
        for (RefreshmentPoint p : points) {
            System.out.println(p.getId() + ": " + p.getName());
        }
    }
    
    /**
     * Displays the list of banners for a selected point.
     * @param banners the list of banners
     */
    public void displayBannerList(List<Banner> banners) {
        System.out.println("=== Banners ===");
        for (Banner b : banners) {
            System.out.println("Banner ID: " + b.getId() + " (Point: " + b.getRefreshmentPointId() + ")");
        }
    }
    
    /**
     * Displays the edit form for a single banner.
     * @param banner the banner to edit
     */
    public void displayEditForm(Banner banner) {
        System.out.println("Editing Banner ID: " + banner.getId());
        System.out.println("Current image size: " + banner.getImageData().length + " bytes");
    }
    
    /**
     * Requests image selection from the user.
     * @return the selected image data
     */
    public byte[] requestImageSelection() {
        // In a real UI, this would open a file chooser.
        // For demonstration, we return a dummy byte array.
        System.out.println("Image selection requested.");
        return new byte[] {0x10, 0x20, 0x30}; // dummy image data
    }
    
    /**
     * Displays a confirmation prompt.
     * @param banner the banner being modified
     * @param newImage the new image data
     * @return true if user confirms, false otherwise
     */
    public boolean displayConfirmationPrompt(Banner banner, byte[] newImage) {
        System.out.println("Confirm modification of Banner " + banner.getId() + "? (yes/no)");
        // Simulate user answering "yes"
        return true;
    }
    
    /**
     * Displays a success notification.
     */
    public void displaySuccess() {
        System.out.println("Banner modification successful!");
        notificationService.sendSuccessNotification();
    }
    
    /**
     * Displays an error notification.
     */
    public void displayError() {
        System.out.println("Error during banner modification.");
        notificationService.sendErrorNotification();
    }
    
    /**
     * Select refreshment point (from list).
     * @param pointId the refreshment point ID
     */
    public void selectRefreshmentPoint(int pointId) {
        System.out.println("Refreshment point selected: " + pointId);
    }
    
    /**
     * Select banner for editing.
     * @param bannerId the banner ID
     */
    public void selectBannerForEditing(int bannerId) {
        System.out.println("Banner selected for editing: " + bannerId);
    }
    
    /**
     * Request image selection.
     */
    public void requestImageSelectionForm() {
        System.out.println("Display image selection form.");
    }
    
    /**
     * Select image file.
     * @param imageData the image data
     */
    public void selectImageFile(byte[] imageData) {
        System.out.println("Image file selected. Size: " + imageData.length + " bytes");
    }
    
    /**
     * Upload image (client-side).
     * @param imageData the image data
     */
    public void uploadImage(byte[] imageData) {
        System.out.println("Uploading image client-side.");
    }
    
    /**
     * Display confirmation prompt.
     * @param bannerId the banner ID
     * @param imageData the new image data
     */
    public void displayConfirmationPrompt(int bannerId, byte[] imageData) {
        System.out.println("Display confirmation prompt for banner " + bannerId);
    }
    
    /**
     * Confirm modification.
     * @param bannerId the banner ID
     * @param imageData the new image data
     */
    public void confirmModification(int bannerId, byte[] imageData) {
        System.out.println("User confirmed modification for banner " + bannerId);
    }
    
    /**
     * Display success notification.
     */
    public void displaySuccessNotification() {
        System.out.println("Display success notification.");
        notificationService.sendSuccessNotification();
    }
    
    /**
     * Display error (invalid image).
     */
    public void displayErrorInvalidImage() {
        System.out.println("Display error (invalid image).");
        notificationService.sendErrorNotification();
    }
}