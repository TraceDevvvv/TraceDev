package com.example.bannerchecker;

/**
 * Updated BannerController that uses the final interactor with recovery capability.
 */
public class BannerControllerFinal {
    private final CheckBannerNumberInteractorFinal checkBannerNumberUseCase;

    public BannerControllerFinal(CheckBannerNumberInteractorFinal useCase) {
        this.checkBannerNumberUseCase = useCase;
    }

    public boolean checkBannerLimit(String refreshmentPointId, Banner newBanner) {
        return checkBannerNumberUseCase.execute(refreshmentPointId, newBanner);
    }

    /**
     * Acknowledges a notification and triggers a state recovery flow (REQ-007).
     * Calls the public recovery method on the interactor.
     * @return true if recovery was successful, false otherwise.
     */
    public boolean acknowledgeNotification() {
        // In a real application, the refreshment point ID would come from context.
        // We use a placeholder for demonstration.
        String lastRefreshmentPointId = "RP001";
        return checkBannerNumberUseCase.performRecovery(lastRefreshmentPointId);
    }
}