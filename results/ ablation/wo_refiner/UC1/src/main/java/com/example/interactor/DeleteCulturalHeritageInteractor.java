package com.example.interactor;

import com.example.repository.CulturalHeritageRepository;
import com.example.request.DeleteCulturalHeritageRequest;
import com.example.response.DeleteCulturalHeritageResponse;
import com.example.service.ConfirmationService;
import com.example.exception.ETOURConnectionException;
import com.example.model.CulturalHeritage;

/**
 * Interactor containing the business logic for deleting cultural heritage.
 */
public class DeleteCulturalHeritageInteractor {
    private CulturalHeritageRepository repository;
    private ConfirmationService confirmationService;

    public DeleteCulturalHeritageInteractor(CulturalHeritageRepository repository) {
        this.repository = repository;
        this.confirmationService = new ConfirmationService();
    }

    public DeleteCulturalHeritageResponse execute(DeleteCulturalHeritageRequest request) {
        // REQ-013: block multiple submissions
        if (!confirmationService.blockMultipleSubmissions(request.getConfirmationToken())) {
            return DeleteCulturalHeritageResponse.failureMultipleSubmissions();
        }

        // Validate token
        if (!confirmationService.validateToken(request.getConfirmationToken())) {
            return DeleteCulturalHeritageResponse.failureInvalidToken();
        }

        // Mark token as used to prevent reuse
        confirmationService.markTokenUsed(request.getConfirmationToken());

        // REQ-012: check server connection
        try {
            if (!repository.checkConnection()) {
                throw new ETOURConnectionException(500, "Server connection interrupted");
            }
        } catch (ETOURConnectionException e) {
            return DeleteCulturalHeritageResponse.failureConnection(e.getErrorCode(), e.getErrorMessage());
        }

        // Find the cultural heritage
        CulturalHeritage culturalHeritage = repository.findById(request.getCulturalHeritageId());
        if (culturalHeritage == null) {
            return DeleteCulturalHeritageResponse.failureNotFound();
        }

        // Delete the cultural heritage
        boolean deleted = repository.delete(request.getCulturalHeritageId());
        if (deleted) {
            return DeleteCulturalHeritageResponse.success("Successful elimination of cultural heritage");
        } else {
            return DeleteCulturalHeritageResponse.failureDelete();
        }
    }

    // Added method to create success response as per sequence diagram
    public DeleteCulturalHeritageResponse createSuccessResponse() {
        return DeleteCulturalHeritageResponse.success("Successful elimination of cultural heritage");
    }

    // Added method to create failure response as per sequence diagram
    public DeleteCulturalHeritageResponse createFailureResponse() {
        return DeleteCulturalHeritageResponse.failureInvalidToken();
    }

    // This method corresponds to the "token valid (true/false)" return message.
    public boolean isTokenValid(String token) {
        return confirmationService.tokenValid(token);
    }
}