
package com.example.bannerchecker;

/**
 * Extension to CheckBannerNumberInteractor to add a public recovery method for the controller.
 * This is added to satisfy REQ-007 and REQ-008 as per the state recovery sequence diagram.
 * Alternatively, we could modify the original class, but to keep changes clear we extend.
 * In practice, you might add the method directly to CheckBannerNumberInteractor.
 */
public class CheckBannerNumberInteractorExtension extends CheckBannerNumberInteractor {
    private final RefreshmentPointConventionRepository repository;
    private final NotificationService notificationService;

    public CheckBannerNumberInteractorExtension(RefreshmentPointConventionRepository repository,
                                                NotificationService notificationService) {
        super(repository, notificationService);
        this.repository = repository;
        this.notificationService = notificationService;
    }

    /**
     * Public method to trigger state recovery, accessible by BannerController.
     * Mirrors the rollbackState but with a different name to avoid confusion.
     * @param refreshmentPointId The ID of the refreshment point to recover.
     * @return true if recovery succeeded, false otherwise.
     */
    public boolean performRecovery(String refreshmentPointId) {
        try {
            // Follow the State Recovery Flow sequence diagram.
            // Step 1: Get previous state from repository.
            RefreshmentPointConvention previousState = repository.getPreviousState(refreshmentPointId);
            // Step 2: Save the previous state (rollback).
            repository.save(previousState);
            // Step 3: Send notification.
            notificationService.sendNotification("State recovery completed");
            return true;
        } catch (ConnectionException e) {
            notificationService.sendNotification("Recovery failed: " + e.getMessage());
            return false;
        }
    }
}
