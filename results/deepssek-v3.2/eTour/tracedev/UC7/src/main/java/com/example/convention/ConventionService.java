package com.example.convention;

/**
 * Service layer for convention-related business logic.
 */
public class ConventionService {
    private ConventionRepository conventionRepository;
    private NotificationAdapter notificationAdapter;
    private ETOURServerAdapter etourServerAdapter;

    public ConventionService(ConventionRepository conventionRepository,
                             NotificationAdapter notificationAdapter,
                             ETOURServerAdapter etourServerAdapter) {
        this.conventionRepository = conventionRepository;
        this.notificationAdapter = notificationAdapter;
        this.etourServerAdapter = etourServerAdapter;
    }

    /**
     * Loads convention data for a given refreshment point.
     * @param pointId the refreshment point ID.
     * @return the Convention entity, or null if not found.
     */
    public Convention loadConventionData(String pointId) {
        return conventionRepository.findByRefreshmentPointId(pointId);
    }

    /**
     * Activates a convention after validating its data.
     * @param conventionId the ID of the convention to activate.
     * @return an ActivationResult indicating success or failure.
     */
    public ActivationResult activateConvention(String conventionId) {
        // We need to find the convention by its pointId.
        // Since repository finds by pointId, we assume conventionId is the same as pointId for simplicity.
        // In a real system, we would have a separate findById method.
        Convention convention = conventionRepository.findByRefreshmentPointId(conventionId);
        if (convention == null) {
            return new ActivationResult(false, "Convention not found");
        }

        // Validate agreement data (Step 4, Step 5 from requirements).
        if (!convention.validateAgreementData()) {
            return new ActivationResult(false, "Invalid data");
        }

        // Activate the convention.
        convention.activate();
        conventionRepository.save(convention);

        // Send activation notification (which also notifies ETOUR server).
        try {
            notificationAdapter.sendActivationNotification(conventionId);
        } catch (Exception e) {
            // If notification fails, we still consider activation successful? This is a business decision.
            // For now, we return success but with a warning message.
            return new ActivationResult(true, "Activated but notification failed: " + e.getMessage());
        }

        return new ActivationResult(true, "Activated");
    }
}