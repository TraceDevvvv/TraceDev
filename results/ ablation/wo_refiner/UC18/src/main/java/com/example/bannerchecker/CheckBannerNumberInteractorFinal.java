package com.example.bannerchecker;

/**
 * Final version of the interactor that includes the public recovery method.
 * This class replaces the earlier CheckBannerNumberInteractor to satisfy REQ-007 and REQ-008.
 */
public class CheckBannerNumberInteractorFinal {
    private final RefreshmentPointConventionRepository conventionRepository;
    private final NotificationService notificationService;

    public CheckBannerNumberInteractorFinal(RefreshmentPointConventionRepository repository,
                                            NotificationService notificationService) {
        this.conventionRepository = repository;
        this.notificationService = notificationService;
    }

    /**
     * Executes the banner limit check for a given refreshment point and new banner.
     * Follows the flow described in the sequence diagram.
     * @param refreshmentPointId The ID of the refreshment point.
     * @param newBanner The new banner to be placed.
     * @return true if the operation is successful (limit not exceeded), false otherwise.
     */
    public boolean execute(String refreshmentPointId, Banner newBanner) {
        try {
            RefreshmentPointConvention convention = conventionRepository.findById(refreshmentPointId);
            if (convention == null) {
                notificationService.sendNotification("Refreshment point not found");
                return false;
            }
            if (convention.isBannerLimitExceeded()) {
                notificationService.sendNotification("Maximum banner limit reached");
                return false;
            } else {
                return true;
            }
        } catch (ConnectionException e) {
            notificationService.sendNotification("Server connection lost");
            rollbackState(refreshmentPointId);
            return false;
        }
    }

    /**
     * Rolls back the state of a refreshment point convention to its previous state.
     * This method is called when an error occurs (e.g., connection loss).
     * It is package-private for internal error handling.
     * @param refreshmentPointId The ID of the refreshment point to rollback.
     */
    void rollbackState(String refreshmentPointId) {
        try {
            RefreshmentPointConvention previousState = conventionRepository.getPreviousState(refreshmentPointId);
            conventionRepository.save(previousState);
            notificationService.sendNotification("State recovery completed after error");
        } catch (ConnectionException e) {
            notificationService.sendNotification("Failed to recover state: " + e.getMessage());
        }
    }

    /**
     * Public method to trigger state recovery, accessible by BannerController.
     * Implements the State Recovery Flow sequence diagram (REQ-007, REQ-008).
     * @param refreshmentPointId The ID of the refreshment point to recover.
     * @return true if recovery succeeded, false otherwise.
     */
    public boolean performRecovery(String refreshmentPointId) {
        try {
            RefreshmentPointConvention previousState = conventionRepository.getPreviousState(refreshmentPointId);
            conventionRepository.save(previousState);
            notificationService.sendNotification("State recovery completed");
            return true;
        } catch (ConnectionException e) {
            notificationService.sendNotification("Recovery failed: " + e.getMessage());
            return false;
        }
    }
}

// Update BannerController to use CheckBannerNumberInteractorFinal.