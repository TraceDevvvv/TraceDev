package com.example.uml;

import java.util.List;

/**
 * Controller for the Delete Banner use case.
 * Orchestrates the flow between UI, Service, and Notification.
 */
public class DeleteBannerController {
    private IBannerService bannerService;
    private NotificationService notificationService;

    public DeleteBannerController(IBannerService bannerService, NotificationService notificationService) {
        this.bannerService = bannerService;
        this.notificationService = notificationService;
    }

    public boolean execute(String refreshmentPointId, String bannerId, AgencyOperator operator) {
        System.out.println("DeleteBannerController.execute called for banner: " + bannerId);
        // Step 1: Display banners for the refreshment point.
        List<Banner> banners = displayBanners(refreshmentPointId);
        if (banners.isEmpty()) {
            notificationService.sendErrorMessage("No banners found for the selected refreshment point.");
            return false;
        }
        // Step 2: Confirm deletion.
        boolean confirm = confirmDeletion(bannerId);
        if (!confirm) {
            return false;
        }
        // Step 3: Perform deletion.
        performDeletion(bannerId);
        return true;
    }

    public List<Banner> displayBanners(String refreshmentPointId) {
        return bannerService.getBannersByRefreshmentPoint(refreshmentPointId);
    }

    public boolean confirmDeletion(String bannerId) {
        // As per sequence diagram, ask NotificationService for confirmation.
        boolean userConfirmed = notificationService.displayConfirmation("Delete selected banner?");
        if (!userConfirmed) {
            return false;
        }
        // Then confirm banner exists.
        return bannerService.confirmBannerExists(bannerId);
    }

    public void performDeletion(String bannerId) {
        boolean success = bannerService.deleteBanner(bannerId);
        if (success) {
            notificationService.sendSuccessMessage("Banner deleted successfully");
        } else {
            notificationService.sendErrorMessage("Deletion failed");
        }
    }
}