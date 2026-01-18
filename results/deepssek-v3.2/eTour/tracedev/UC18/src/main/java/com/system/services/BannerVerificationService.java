package com.system.serv;

import com.system.entities.RefreshmentPointConvention;
import com.system.entities.VerificationResult;
import com.system.exceptions.ConnectionException;
import com.system.repositories.IConventionRepository;
import com.system.repositories.IBannerRepository;
import com.system.cache.ConventionCache;
import com.system.network.ETOURService;

/**
 * Service that verifies if a banner can be added to a convention based on count limits.
 * Quality Requirement: Efficient verification to prevent data conflicts.
 */
public class BannerVerificationService {
    private IBannerRepository bannerRepository;
    private IConventionRepository conventionRepository;
    private ConventionCache conventionCache;
    private ETOURService etourService;

    public BannerVerificationService(IBannerRepository bannerRepository,
                                     IConventionRepository conventionRepository,
                                     ConventionCache conventionCache,
                                     ETOURService etourService) {
        this.bannerRepository = bannerRepository;
        this.conventionRepository = conventionRepository;
        this.conventionCache = conventionCache;
        this.etourService = etourService;
    }

    /**
     * Verifies if the banner count for a convention is within allowed limits.
     * Implements the sequence diagram interactions.
     */
    public VerificationResult verifyBannerCount(String conventionId) {
        // Step 1: Try cache
        RefreshmentPointConvention convention = conventionCache.getConvention(conventionId);
        if (convention == null) {
            // Cache miss: fetch from repository
            convention = conventionRepository.findById(conventionId);
            conventionCache.updateConvention(convention);
        }

        // Step 2: Get current banner count
        int currentBannerCount = bannerRepository.countByConventionId(conventionId);

        // Step 3: Check server connection (Exit Condition)
        boolean connected = etourService.checkConnection();
        if (!connected) {
            // Connection failed - throw exception as per diagram
            throw new ConnectionException("CONN_ERR", "Server disconnected");
        }

        // Step 4: Compare with allowed limit
        int maxAllowed = convention.getMaxAllowedBanners();
        if (currentBannerCount < maxAllowed) {
            return new VerificationResult(true, "Banner addition allowed.");
        } else {
            return new VerificationResult(false, "Max banners exceeded.");
        }
    }
}