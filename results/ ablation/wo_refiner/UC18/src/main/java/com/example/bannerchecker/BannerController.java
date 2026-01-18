package com.example.bannerchecker;

/**
 * Boundary controller that handles requests from the Agency Operator (actor).
 */
public class BannerController {
    private final CheckBannerNumberInteractor checkBannerNumberUseCase;

    public BannerController(CheckBannerNumberInteractor useCase) {
        this.checkBannerNumberUseCase = useCase;
    }

    /**
     * Checks the banner limit for a refreshment point.
     * Called by the Agency Operator (actor) as per sequence diagram.
     * @param refreshmentPointId The ID of the refreshment point.
     * @param newBanner The new banner to be placed.
     * @return true if banner placement is allowed, false otherwise.
     */
    public boolean checkBannerLimit(String refreshmentPointId, Banner newBanner) {
        return checkBannerNumberUseCase.execute(refreshmentPointId, newBanner);
    }

    /**
     * Acknowledges a notification and triggers a state recovery flow (REQ-007).
     * This is a separate recovery action as per the second sequence diagram.
     * @return true if recovery was successful, false otherwise.
     */
    public boolean acknowledgeNotification() {
        // For demonstration, we need a refreshment point ID to rollback.
        // In a real scenario, the controller might have context (e.g., last operation).
        // We assume a default ID for simplicity.
        String lastRefreshmentPointId = "RP001";
        // The sequence diagram shows calling rollbackState on the interactor.
        // However, the interactor's rollbackState is package-private for error handling.
        // To satisfy REQ-007 and REQ-008, we expose a public recovery method in the interactor.
        // Since the diagram shows the controller calling rollbackState, we assume it's accessible.
        // We'll add a public recovery method in CheckBannerNumberInteractor.
        // For now, we call a method that triggers recovery.
        // Note: The actual implementation would be more complex with proper state tracking.
        // We'll simulate by calling a public method (see CheckBannerNumberInteractor modification).
        return checkBannerNumberUseCase.performRecovery(lastRefreshmentPointId);
    }
}