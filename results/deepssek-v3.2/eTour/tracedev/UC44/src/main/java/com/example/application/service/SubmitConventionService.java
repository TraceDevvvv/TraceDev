package com.example.application.service;

import com.example.application.port.in.SubmitConventionCommand;
import com.example.application.port.out.AuthenticationPort;
import com.example.application.port.out.ConventionRequestRepository;
import com.example.domain.ConventionData;
import com.example.domain.ConventionRequest;
import com.example.domain.UserContext;
import com.example.domain.ValidationResult;

/**
 * Service implementing the SubmitConventionCommand use case.
 * Handles submission of a new convention request.
 */
public class SubmitConventionService implements SubmitConventionCommand {
    private final ConventionRequestRepository repository;
    private final AuthenticationPort authenticationPort;
    private final ConventionValidationService validationService;

    public SubmitConventionService(ConventionRequestRepository repository,
                                   AuthenticationPort authenticationPort,
                                   ConventionValidationService validationService) {
        this.repository = repository;
        this.authenticationPort = authenticationPort;
        this.validationService = validationService;
    }

    /**
     * Submits a new convention request.
     * @param data Convention data from the operator
     * @return the created convention request
     * @throws IllegalArgumentException if validation fails
     */
    public ConventionRequest submitConvention(ConventionData data) {
        // Validate data
        ValidationResult validationResult = validationService.validateData(data);
        if (!validationResult.isValid()) {
            throw new ValidationFailedException("Validation failed: " + validationResult.getErrors());
        }

        // Get current user context to retrieve restaurantId
        UserContext user = authenticationPort.getCurrentUser();
        String restaurantId = user.getRestaurantId();

        // Create and save the request
        ConventionRequest request = createConventionRequest(data);
        request.submit(); // Change status from DRAFT to PENDING_VALIDATION
        return repository.save(request);
    }

    @Override
    public ConventionRequest execute(String restaurantId, ConventionData data) {
        // This method signature matches the port, but we'll use submitConvention(ConventionData) in the adapter.
        // For consistency we implement it, but delegate to submitConvention after setting user context.
        // In a real scenario we might need a different approach.
        // We'll assume the authenticationPort already provides the restaurantId.
        return submitConvention(data);
    }

    /**
     * Creates a new ConventionRequest.
     * @param data Convention data
     * @return a new ConventionRequest in DRAFT state
     */
    ConventionRequest createConventionRequest(ConventionData data) {
        // Retrieve restaurantId from current user
        UserContext user = authenticationPort.getCurrentUser();
        String restaurantId = user.getRestaurantId();
        return ConventionRequest.create(restaurantId, data);
    }

    // Custom exception for validation failures
    public static class ValidationFailedException extends RuntimeException {
        public ValidationFailedException(String message) {
            super(message);
        }
    }
}