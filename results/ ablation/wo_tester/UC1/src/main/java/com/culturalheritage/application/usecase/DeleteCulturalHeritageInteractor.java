package com.culturalheritage.application.usecase;

import com.culturalheritage.application.port.in.DeleteCulturalHeritageInputPort;
import com.culturalheritage.application.request.DeleteCulturalHeritageRequest;
import com.culturalheritage.application.response.DeleteCulturalHeritageResponse;
import com.culturalheritage.domain.model.CulturalHeritage;
import com.culturalheritage.application.port.out.CulturalHeritageRepository;
import com.culturalheritage.application.service.NotificationService;
import com.culturalheritage.application.service.IdempotencyService;

/**
 * Interactor implementing the Delete Cultural Heritage use case.
 * Orchestrates the deletion flow as per sequence diagram.
 */
public class DeleteCulturalHeritageInteractor implements DeleteCulturalHeritageInputPort {
    private CulturalHeritageRepository repository;
    private NotificationService notificationService;
    private IdempotencyService idempotencyService;

    public DeleteCulturalHeritageInteractor(CulturalHeritageRepository repository,
                                           NotificationService notificationService,
                                           IdempotencyService idempotencyService) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.idempotencyService = idempotencyService;
    }

    @Override
    public DeleteCulturalHeritageResponse execute(DeleteCulturalHeritageRequest request) {
        // Mark request as processed for idempotency
        idempotencyService.markRequestAsProcessed(request.getConfirmationToken());

        // Step 6: Find the cultural heritage
        CulturalHeritage culturalHeritage = repository.findById(request.getCulturalHeritageId());
        if (culturalHeritage == null) {
            // If not found, consider it already deleted or non-existent
            notificationService.notifyFailure("Cultural heritage not found for deletion", request.getOperatorId());
            return new DeleteCulturalHeritageResponse(false, "Cultural heritage not found", request.getCulturalHeritageId());
        }

        // Step 6: Delete the cultural heritage
        boolean deleted = repository.deleteById(request.getCulturalHeritageId());
        if (deleted) {
            // Success notification
            notificationService.notifySuccess("Cultural heritage deleted successfully", request.getOperatorId());
            return new DeleteCulturalHeritageResponse(true, "Deletion successful", request.getCulturalHeritageId());
        } else {
            // Failure notification (e.g., server interruption)
            notificationService.notifyFailure("Deletion failed due to server error", request.getOperatorId());
            return new DeleteCulturalHeritageResponse(false, "Deletion failed: Server interrupted", request.getCulturalHeritageId());
        }
    }
}