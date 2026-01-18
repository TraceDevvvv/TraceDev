package com.system.controller;

import com.system.entities.BannerRequest;
import com.system.entities.VerificationResult;
import com.system.serv.BannerVerificationService;
import com.system.serv.NotificationService;
import com.system.serv.StateRecoveryService;
import com.system.exceptions.ConnectionException;

/**
 * Main controller for the banner compliance use case.
 * Entry Condition: Agency intent represented by BannerRequest.
 */
public class UseCaseController {
    private BannerVerificationService bannerVerificationService;
    private NotificationService notificationService;
    private StateRecoveryService stateRecoveryService;

    public UseCaseController(BannerVerificationService bannerVerificationService,
                             NotificationService notificationService,
                             StateRecoveryService stateRecoveryService) {
        this.bannerVerificationService = bannerVerificationService;
        this.notificationService = notificationService;
        this.stateRecoveryService = stateRecoveryService;
    }

    /**
     * Checks if adding a new banner complies with convention limits.
     * Implements the sequence diagram flow.
     */
    public VerificationResult checkBannerCompliance(BannerRequest bannerRequest) {
        String conventionId = bannerRequest.getConventionId();
        try {
            VerificationResult result = bannerVerificationService.verifyBannerCount(conventionId);
            if (result.isCompliant()) {
                // Banner addition allowed
                // m9: BVS -> UCC: return VerificationResult(compliant=true)
                // m10: UCC -> EO: return Success (allow banner addition)
                return new VerificationResult(true, "Success (allow banner addition)");
            } else {
                // Banner limit exceeded - follow rejection flow
                // Step 3: Return operation rejected to caller first
                // (The return is done after handling notification and recovery)
                // Step 4: Display notification and recover state
                // m11: BVS -> UCC: return VerificationResult(compliant=false, "Max banners exceeded")
                // m13: NS -> EO: Show notification
                notificationService.displayWarning("Max banners exceeded - operation rejected");
                // m14: EO -> NS: confirmReading()
                // m15: NS -> UCC: confirmation received
                boolean confirmed = notificationService.getConfirmation();
                if (confirmed) {
                    // m17: SRS -> UCC: state recovered
                    stateRecoveryService.recoverPreviousState();
                }
                // m18: UCC -> EO: return Operation failed with notification
                return new VerificationResult(false, "Operation failed with notification");
            }
        } catch (ConnectionException e) {
            // Server connection failed
            return new VerificationResult(false, "Operation failed (server error)");
        }
    }
}