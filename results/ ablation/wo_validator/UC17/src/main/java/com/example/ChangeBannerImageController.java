package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for changing banner images.
 * Orchestrates interactions between repositories, validator, and notification serv.
 * Implements the flow described in the sequence diagram.
 */
public class ChangeBannerImageController {
    private RefreshmentPointRepository refreshmentPointRepository;
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private NotificationService notificationService;
    private ETOURServerAdapter etourServerAdapter;

    public ChangeBannerImageController(RefreshmentPointRepository refreshmentPointRepository,
                                       BannerRepository bannerRepository,
                                       ImageValidator imageValidator) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        // Initialize notification service with a default ETOURServerAdapter.
        this.etourServerAdapter = new ETOURServerAdapter();
        this.notificationService = new NotificationService(etourServerAdapter);
    }

    // Constructor with all dependencies for flexibility.
    public ChangeBannerImageController(RefreshmentPointRepository refreshmentPointRepository,
                                       BannerRepository bannerRepository,
                                       ImageValidator imageValidator,
                                       NotificationService notificationService,
                                       ETOURServerAdapter etourServerAdapter) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.notificationService = notificationService;
        this.etourServerAdapter = etourServerAdapter;
    }

    /**
     * Retrieves all refreshment points.
     * As per sequence diagram: calls RefreshmentPointRepository.findAll()
     */
    public List<RefreshmentPoint> getRefreshmentPoints() {
        return refreshmentPointRepository.findAll();
    }

    /**
     * Retrieves banners for a given refreshment point.
     * Sequence: find refreshment point, then find its banners.
     */
    public List<Banner> getBannersForPoint(int refreshmentPointId) {
        // First, find the refreshment point (as per sequence diagram).
        RefreshmentPoint point = refreshmentPointRepository.findById(refreshmentPointId);
        if (point == null) {
            return new ArrayList<>();
        }
        // Then, get banners for that point.
        return bannerRepository.findByRefreshmentPointId(refreshmentPointId);
    }

    /**
     * Initiates the banner image change process.
     * Validates the image, updates the banner, and sends notifications.
     * Returns true if successful, false otherwise.
     */
    public boolean changeBannerImage(int bannerId, byte[] newImageData) {
        // Step 1: Validate image (max size 5 MB as per sequence diagram).
        ValidationResult validationResult = imageValidator.validateImage(newImageData, 5);
        if (!validationResult.isValid()) {
            // Image is NOT valid: send error notification and notify ETOUR server.
            notificationService.sendServerConnectionError("Invalid image");
            // According to sequence diagram, after error notification, notifyBannerChanged is called.
            etourServerAdapter.notifyBannerChanged(bannerId);
            return false; // Error response
        }

        // Step 2: Image is valid – request confirmation (simulated).
        // In a real UI, this would involve user interaction. Here we assume confirmation is given.
        System.out.println("Display confirmation request to user.");

        // Step 3: For simulation, we assume confirmation is given (call confirmChange).
        // We'll directly proceed as if confirmChange was called.
        return confirmChange(bannerId, newImageData);
    }

    /**
     * Confirms the change after validation and user confirmation.
     * Updates the banner and sends success notification.
     */
    private boolean confirmChange(int bannerId, byte[] newImageData) {
        // Find the banner.
        RefreshmentPoint refreshmentPoint = refreshmentPointRepository.findById(bannerId);
        if (refreshmentPoint == null) {
            System.out.println("Refreshment point not found with ID: " + bannerId);
            return false;
        }
        
        // Assuming Banner is associated with RefreshmentPoint, we need to find the banner.
        // Since we don't have a direct banner findById, we can get banners for the point and find the first one.
        // Alternatively, we might need to adjust the method signature or repository method.
        // For now, let's assume we can get the banner by its ID from the banner repository.
        // But the error indicates bannerRepository.findById expects a RefreshmentPoint, not an int.
        // So we need to fix the repository method call or the logic.
        // Let's change to use refreshmentPoint to find banners.
        List<Banner> banners = bannerRepository.findByRefreshmentPointId(refreshmentPoint.getId());
        if (banners.isEmpty()) {
            System.out.println("No banners found for refreshment point ID: " + refreshmentPoint.getId());
            return false;
        }
        // For simplicity, take the first banner.
        Banner banner = banners.get(0);

        // Simulate generating a new image path (in reality, this would involve file storage).
        String newImagePath = "/images/banner_" + bannerId + "_updated.jpg";
        banner.setImagePath(newImagePath);

        // Update the banner in the repository.
        Banner updatedBanner = bannerRepository.update(banner);
        if (updatedBanner == null) {
            System.out.println("Failed to update banner.");
            return false;
        }

        // Send success notification.
        notificationService.sendSuccessNotification("Banner updated successfully");

        // Notify ETOUR server.
        boolean serverNotified = etourServerAdapter.notifyBannerChanged(bannerId);
        if (!serverNotified) {
            // Simulate server connection interruption.
            System.out.println("Server connection interrupted during notification.");
            return false;
        }

        // Successful notification.
        System.out.println("Banner image changed successfully.");
        return true;
    }
}