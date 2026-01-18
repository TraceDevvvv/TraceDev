package com.example.banner.application;

import com.example.banner.domain.RefreshmentPoint;

/**
 * Controller for checking banner count.
 * Orchestrates the use case.
 */
public class CheckBannerCountController {
    private IRefreshmentPointRepository refreshmentPointRepository;

    /**
     * Constructor with dependency injection.
     * @param repository the refreshment point repository
     */
    public CheckBannerCountController(IRefreshmentPointRepository repository) {
        this.refreshmentPointRepository = repository;
    }

    /**
     * Executes the banner count check.
     * @param operatorId the operator identifier (not used in this implementation but kept for signature)
     * @param refreshmentPointId the refreshment point to check
     * @return the result of the check
     */
    public CheckBannerCountResult execute(String operatorId, String refreshmentPointId) {
        // Find the refreshment point
        RefreshmentPoint rp = refreshmentPointRepository.findById(refreshmentPointId);
        if (rp == null) {
            return new CheckBannerCountResult(false, 0, 0, "Refreshment point not found");
        }

        // Verify banner limit
        boolean canPlace = rp.verifyBannerLimit();
        String message = canPlace ? "Proceed with banner placement" : "Banner limit exceeded";

        return new CheckBannerCountResult(
            canPlace,
            rp.getCurrentBannerCount(),
            rp.getMaxBannerCount(),
            message
        );
    }
}