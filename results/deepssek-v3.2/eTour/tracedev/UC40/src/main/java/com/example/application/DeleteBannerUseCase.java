package com.example.application;

import com.example.domain.Banner;
import com.example.domain.BannerRepository;
import com.example.infrastructure.ETOURService;
import java.util.List;

/**
 * Use case for deleting a banner following the specified business flow.
 */
public class DeleteBannerUseCase {
    private final BannerRepository bannerRepository;
    private final ETOURService etourService;

    public DeleteBannerUseCase(BannerRepository bannerRepository, ETOURService etourService) {
        this.bannerRepository = bannerRepository;
        this.etourService = etourService;
    }

    /**
     * Retrieves banners for a point of rest (Flow of Events: 2).
     */
    public List<Banner> getBannersForPointOfRest(String pointOfRestId) {
        return bannerRepository.findByPointOfRest(pointOfRestId);
    }

    /**
     * Executes the banner deletion process.
     * @param pointOfRestId the point of rest ID
     * @param bannerId the banner ID to delete
     * @param operatorId the operator performing the action
     * @param authToken authentication token for ETOUR
     * @return result of the deletion operation
     */
    public DeleteBannerResult execute(String pointOfRestId, String bannerId, String operatorId, String authToken) {
        // Step 1: Authenticate with ETOUR (precondition)
        if (!etourService.authenticate(authToken)) {
            return new DeleteBannerResult(false, "Unauthorized", bannerId);
        }

        // Step 2: Find the banner
        Banner banner = bannerRepository.findById(bannerId).orElse(null);
        if (banner == null) {
            return new DeleteBannerResult(false, "Banner not found", bannerId);
        }

        // Step 3: Validate association
        if (!banner.isAssociatedWith(pointOfRestId)) {
            return new DeleteBannerResult(false, "Invalid banner", bannerId);
        }

        try {
            // Step 4: Deactivate (soft delete)
            banner.deactivate();
            bannerRepository.save(banner);

            // Step 5: Remove from remote system (ETOUR)
            boolean remoteRemoved = etourService.removeRemoteBanner(bannerId);
            if (!remoteRemoved) {
                // Rollback activation
                banner.reactivate();
                bannerRepository.save(banner);
                return new DeleteBannerResult(false, "ETOUR removal failed", bannerId);
            }

            // Step 6: Delete permanently from local DB
            bannerRepository.delete(banner);

            // Step 7: Notify ETOUR about deletion
            etourService.notifyDeletion(bannerId, pointOfRestId);

            return new DeleteBannerResult(true, "Banner deleted", bannerId);

        } catch (Exception e) {
            // ETOUR connection interrupted during deletion (rollback)
            banner.reactivate();
            bannerRepository.save(banner);
            return new DeleteBannerResult(false, "ETOUR connection failed", bannerId);
        }
    }

    /**
     * Cancels the deletion operation (Operator cancels).
     */
    public DeleteBannerResult cancel() {
        return new DeleteBannerResult(false, "Operation cancelled", null);
    }

    /**
     * Authenticate method from sequence diagram (message m15/m16).
     * This is already implemented inside execute, but added for traceability.
     */
    public boolean authenticate(String authToken) {
        return etourService.authenticate(authToken);
    }

    /**
     * Deactivate method from sequence diagram (message m30).
     * This is already implemented in Banner, but added for traceability.
     */
    public void deactivateBanner(Banner banner) {
        banner.deactivate();
    }

    /**
     * Save method from sequence diagram (message m31).
     */
    public Banner saveBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    /**
     * Remove remote banner method from sequence diagram (message m38).
     */
    public boolean removeRemoteBanner(String bannerId) {
        return etourService.removeRemoteBanner(bannerId);
    }

    /**
     * Notify deletion method from sequence diagram (message m44).
     */
    public void notifyDeletion(String bannerId, String pointOfRestId) {
        etourService.notifyDeletion(bannerId, pointOfRestId);
    }
}