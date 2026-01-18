package com.culturalheritage.application.controller;

import com.culturalheritage.application.port.in.DeleteCulturalHeritageInputPort;
import com.culturalheritage.application.request.DeleteCulturalHeritageRequest;
import com.culturalheritage.application.response.DeleteCulturalHeritageResponse;
import com.culturalheritage.application.service.RequestValidator;
import com.culturalheritage.application.service.ValidationResult;
import com.culturalheritage.application.service.AuthenticationService;
import com.culturalheritage.application.service.IdempotencyService;

/**
 * Use Case Controller coordinating authentication, validation, idempotency, and delegating to interactor.
 * Implements steps 4 and 5 from sequence diagram.
 */
public class DeleteCulturalHeritageUseCaseController {
    private DeleteCulturalHeritageInputPort inputPort;
    private RequestValidator requestValidator;
    private AuthenticationService authenticationService;
    private IdempotencyService idempotencyService;

    // Note: Added IdempotencyService as dependency to match sequence diagram flow
    public DeleteCulturalHeritageUseCaseController(DeleteCulturalHeritageInputPort inputPort,
                                                  RequestValidator validator,
                                                  AuthenticationService authService,
                                                  IdempotencyService idempotencyService) {
        this.inputPort = inputPort;
        this.requestValidator = validator;
        this.authenticationService = authService;
        this.idempotencyService = idempotencyService;
    }

    public DeleteCulturalHeritageResponse deleteCulturalHeritage(DeleteCulturalHeritageRequest request) {
        // Step 4: Check if operator is logged in
        boolean isLoggedIn = authenticationService.isUserLoggedIn(request.getOperatorId());
        if (!isLoggedIn) {
            return new DeleteCulturalHeritageResponse(false, "User not logged in", null);
        }

        // Step 4 & 5: Validate request
        ValidationResult validationResult = requestValidator.validateDeleteRequest(request);
        if (!validationResult.isValid()) {
            return new DeleteCulturalHeritageResponse(false, "Validation failed", request.getCulturalHeritageId());
        }

        // Step 5: Check if request is already processed (idempotency)
        if (idempotencyService.isRequestProcessed(request.getConfirmationToken())) {
            return new DeleteCulturalHeritageResponse(false, "Request already processed", request.getCulturalHeritageId());
        }

        // Step 5: Check if operation was cancelled in UI
        if (request.isCancelled()) {
            return new DeleteCulturalHeritageResponse(false, "Operation cancelled", request.getCulturalHeritageId());
        }

        // Step 5: Delegate to interactor
        return inputPort.execute(request);
    }
}