package com.example.bannerchecker;

/**
 * Use case interactor for checking banner limits.
 * Implements the main business logic and error handling as per sequence diagrams.
 */
public class CheckBannerNumberInteractor {
    private RefreshmentPointConventionRepository conventionRepository;
    private NotificationService notificationService;

    public CheckBannerNumberInteractor(RefreshmentPointConventionRepository repository,
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
            // Step 1: Retrieve the convention from the repository.
            RefreshmentPointConvention convention = conventionRepository.findById(refreshmentPointId);

            if (convention == null) {
                // Convention not found scenario (REQ-006).
                notificationService.sendNotification("Refreshment point not found");
                return false;
            }

            // Step 2: Check if banner limit is exceeded.
            if (convention.isBannerLimitExceeded()) {
                // Banner limit exceeded scenario (REQ-006).
                notificationService.sendNotification("Maximum banner limit reached");
                return false;
            } else {
                // Valid operation: banner limit not exceeded.
                // According to sequence diagram, we return true and proceed with placement.
                // Note: The actual banner placement (incrementing count) is not part of this use case.
                // The caller may handle that.
                return true;
            }
        } catch (ConnectionException e) {
            // Interruption: Server Connection Lost (REQ-010).
            notificationService.sendNotification("Server connection lost");
            // Perform rollback to ensure consistency (as per diagram note).
            rollbackState(refreshmentPointId);
            return false;
        }
    }

    /**
     * Rolls back the state of a refreshment point convention to its previous state.
     * This method is called when an error occurs (e.g., connection loss).
     * This method is package-private for internal error handling.
     * @param refreshmentPointId The ID of the refreshment point to rollback.
     */
    void rollbackState(String refreshmentPointId) {
        try {
            RefreshmentPointConvention previousState = conventionRepository.getPreviousState(refreshmentPointId);
            conventionRepository.save(previousState);
            notificationService.sendNotification("State recovery completed after error");
        } catch (ConnectionException e) {
            // If even rollback fails, we log and notify.
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