package com.example;

/**
 * Controller responsible for verifying banner count.
 * Orchestrates the verification logic using repositories and server connection.
 */
public class BannerVerificationController {
    private RefreshmentPointRepository refreshmentPointRepository;
    private ConventionRepository conventionRepository;
    private BannerRepository bannerRepository;
    private ServerConnection serverConnection;

    /**
     * Constructor for BannerVerificationController.
     * @param refreshmentPointRepository Repository for refreshment points.
     * @param conventionRepository Repository for conventions.
     * @param bannerRepository Repository for banners.
     * @param serverConnection The server connection to use.
     */
    public BannerVerificationController(RefreshmentPointRepository refreshmentPointRepository,
                                        ConventionRepository conventionRepository,
                                        BannerRepository bannerRepository,
                                        ServerConnection serverConnection) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.conventionRepository = conventionRepository;
        this.bannerRepository = bannerRepository;
        this.serverConnection = serverConnection;
    }

    /**
     * Verifies if a new banner can be added to a refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @param agencyId The ID of the agency.
     * @return A VerificationResult indicating the outcome.
     */
    public VerificationResult verifyBannerCount(String refreshmentPointId, String agencyId) {
        try {
            // Step 1: Load refreshment point data
            RefreshmentPoint refreshmentPoint = refreshmentPointRepository.findById(refreshmentPointId);
            if (refreshmentPoint == null) {
                return new VerificationResult(false, "Refreshment point not found", "NOT_FOUND");
            }

            // Step 2: Load convention data for the refreshment point
            Convention convention = conventionRepository.findById(refreshmentPoint.getConventionId());
            if (convention == null) {
                return new VerificationResult(false, "Convention not found", "NOT_FOUND");
            }

            // Step 3: Count existing banners for the refreshment point
            int currentBannerCount = bannerRepository.countByRefreshmentPointId(refreshmentPointId);
            refreshmentPoint.setCurrentBanners(currentBannerCount); // Update current banners count

            // Step 4: Check if a banner can be added based on max banners and current count
            if (refreshmentPoint.canAddBanner() && currentBannerCount < convention.getMaxBannersPerPoint()) {
                // Verification passed: banner can be added
                // Note: Actual banner addition is not performed here, only verification.
                return new VerificationResult(true, "Can add banner");
            } else {
                // Verification failed: maximum banners reached
                return new VerificationResult(false, "Maximum banners reached");
            }
        } catch (Exception e) {
            // Handle any unexpected errors during verification
            return new VerificationResult(false, "Verification failed", "ERROR");
        }
    }

    /**
     * Method representing "Operation continues normally" message (m15).
     * This method is called when verification succeeds.
     */
    public void operationContinuesNormally() {
        // Perform operations that continue normally after successful verification.
        // Could be logging, updating other serv, etc.
        System.out.println("Operation continues normally.");
    }
}