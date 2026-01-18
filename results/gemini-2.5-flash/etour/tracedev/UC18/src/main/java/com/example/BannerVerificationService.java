package com.example;

/**
 * Application service responsible for verifying banner allowance based on
 * refreshment point and convention rules.
 * Annotated with <<Application Service>> stereotype.
 */
public class BannerVerificationService {
    // - conventionRepository : ConventionRepository
    private final ConventionRepository conventionRepository;
    // - refreshmentPointRepository : RefreshmentPointRepository
    private final RefreshmentPointRepository refreshmentPointRepository;
    // - notificationService : NotificationService
    private final NotificationService notificationService;
    // - errorHandlerService : ErrorHandlerService
    private final ErrorHandlerService errorHandlerService; // Added to satisfy requirement R13

    /**
     * Constructs a BannerVerificationService with necessary dependencies.
     *
     * @param conventionRepository The repository for Convention entities.
     * @param refreshmentPointRepository The repository for RefreshmentPoint entities.
     * @param notificationService The service for displaying notifications.
     * @param errorHandlerService The service for handling exceptions.
     */
    public BannerVerificationService(
            ConventionRepository conventionRepository,
            RefreshmentPointRepository refreshmentPointRepository,
            NotificationService notificationService,
            ErrorHandlerService errorHandlerService) {
        this.conventionRepository = conventionRepository;
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.notificationService = notificationService;
        this.errorHandlerService = errorHandlerService;
    }

    /**
     * Verifies if a new banner can be added to a specific refreshment point.
     * This method implements the core logic described in the sequence diagram.
     * Modified return type to satisfy R7, R8, R9, R13.
     *
     * @param refreshmentPointId The ID of the refreshment point to check.
     * @return A VerificationResult indicating whether a banner can be added,
     *         if the limit is exceeded, or if a connection error occurred.
     */
    public VerificationResult verifyBannerAllowance(String refreshmentPointId) {
        System.out.println("[BannerVerificationService] Verifying banner allowance for RP: " + refreshmentPointId);

        // 1. System loads data of the Refreshment Point and its Convention.
        RefreshmentPoint refreshmentPoint;
        try {
            // BannerVerificationService -> RefreshmentPointRepository: findById(refreshmentPointId)
            refreshmentPoint = refreshmentPointRepository.findById(refreshmentPointId);

            // Assumption: If findById returns null, it's an issue with data not connection
            // The sequence diagram's alt block only covers ETOURServiceException for connection interruption.
            // If RefreshmentPoint not found (null), we can treat it as an internal error or invalid ID.
            // For now, let's assume `findById` throws ETOURServiceException if not found, or there's a connection issue.
            // If it can return null, we would need to handle that explicitly outside the ETOURServiceException catch.
            // Let's add a check for null, and if it's null, we assume the data isn't available, thus a connection/data issue.
            if (refreshmentPoint == null) {
                throw new ETOURServiceException("Refreshment Point with ID " + refreshmentPointId + " not found.");
            }

            // The sequence diagram implies the RefreshmentPoint (with associated convention) is returned.
            // This means convention details are available through the RefreshmentPoint object.

            // 2. System verifies if the number of banners is less than specified.
            // BannerVerificationService -> RefreshmentPoint: isBannerLimitExceeded()
            boolean isLimitExceeded = refreshmentPoint.isBannerLimitExceeded();

            if (isLimitExceeded) {
                // isLimitExceeded is true (Number of banners is NOT less than specified)
                // 3. System ends operation input.
                // 4. System displays notification to Operator.
                // BannerVerificationService -> NotificationService: displayNotification(...)
                notificationService.displayNotification("Banner limit exceeded for RP " + refreshmentPointId + " (Current: " + refreshmentPoint.getCurrentBannersCount() + ", Max: " + refreshmentPoint.getConvention().getMaxAllowedBanners() + ").");
                // BannerVerificationService --> OperatorController: LIMIT_EXCEEDED
                return VerificationResult.LIMIT_EXCEEDED;
            } else {
                // isLimitExceeded is false (Number of banners IS less than specified)
                // BannerVerificationService --> OperatorController: LIMIT_OK
                return VerificationResult.LIMIT_OK;
            }
        } catch (ETOURServiceException ex) {
            // Connection Interruption (ETOURServiceException)
            // BannerVerificationService -> ErrorHandlerService: handleException(ex)
            errorHandlerService.handleException(ex);
            // BannerVerificationService -> NotificationService: displayNotification(...)
            notificationService.displayNotification("Connection to ETOUR interrupted. Operation failed for RP " + refreshmentPointId + ".");
            // BannerVerificationService --> OperatorController: CONNECTION_ERROR
            return VerificationResult.CONNECTION_ERROR;
        }
    }
}