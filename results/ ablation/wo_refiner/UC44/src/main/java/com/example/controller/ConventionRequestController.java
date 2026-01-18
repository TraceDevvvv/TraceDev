package com.example.controller;

import com.example.dto.ConventionRequestDTO;
import com.example.dto.ConventionResponseDTO;
import com.example.model.Convention;
import com.example.model.ConventionStatus;
import com.example.repository.ConventionRepository;
import com.example.service.AuthenticationService;
import com.example.service.NotificationService;
import com.example.validation.ConventionValidator;
import com.example.validation.ValidationResult;
import com.example.handler.ErrorHandler;

import java.util.Date;

/**
 * Controller for handling convention requests.
 * Implements requirements: 3, 4, 5, 10, 11, 13
 */
public class ConventionRequestController {
    private ConventionRepository conventionRepository;
    private ConventionValidator conventionValidator;
    private NotificationService notificationService;
    private AuthenticationService authenticationService;
    private ErrorHandler errorHandler;

    /**
     * Constructor with dependency injection.
     */
    public ConventionRequestController(ConventionValidator validator,
                                      ConventionRepository repository,
                                      NotificationService notifier) {
        this.conventionValidator = validator;
        this.conventionRepository = repository;
        this.notificationService = notifier;
    }

    public ConventionRequestController(ConventionValidator validator,
                                      ConventionRepository repository,
                                      NotificationService notifier,
                                      AuthenticationService authService,
                                      ErrorHandler handler) {
        this.conventionValidator = validator;
        this.conventionRepository = repository;
        this.notificationService = notifier;
        this.authenticationService = authService;
        this.errorHandler = handler;
    }

    /**
     * Main method to handle a new convention request.
     */
    public ConventionResponseDTO handleRequest(ConventionRequestDTO requestDto) {
        // Step 1: Validate authentication (REQ-004)
        if (!validateAuthentication()) {
            return new ConventionResponseDTO(false, "Authentication failed", 0);
        }

        // Step 2: Validate request data
        if (!validateRequest(requestDto)) {
            // Error handling for validation errors (REQ-009)
            ValidationResult result = conventionValidator.validate(requestDto);
            errorHandler.handleValidationErrors(result.getErrors());
            return new ConventionResponseDTO(false, "Validation failed: " + result.getErrors(), 0);
        }

        // Step 3: Process and save convention
        Convention convention = processConvention(requestDto);
        int conventionId = conventionRepository.save(convention);

        // Return response with success and convention ID
        return new ConventionResponseDTO(true, "Convention request created successfully", conventionId);
    }

    /**
     * Cancel a convention request (REQ-013).
     */
    public ConventionResponseDTO cancelRequest(int conventionId) {
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null) {
            return new ConventionResponseDTO(false, "Convention not found", conventionId);
        }

        convention.setStatus(ConventionStatus.CANCELLED);
        conventionRepository.save(convention);
        return new ConventionResponseDTO(true, "Convention cancelled successfully", conventionId);
    }

    /**
     * Confirm a convention request (REQ-010, REQ-011).
     */
    public ConventionResponseDTO confirmRequest(int conventionId) {
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null) {
            return new ConventionResponseDTO(false, "Convention not found", conventionId);
        }

        // Notify the agency
        notifyAgency(convention);

        // Update status to CONFIRMED
        convention.setStatus(ConventionStatus.CONFIRMED);
        conventionRepository.save(convention);

        return new ConventionResponseDTO(true, "Convention confirmed and agency notified", conventionId);
    }

    /**
     * Validate authentication of the current user (REQ-004).
     */
    private boolean validateAuthentication() {
        return authenticationService.isAuthenticated(authenticationService.getCurrentUser().getUserId());
    }

    /**
     * Validate the request data using the validator.
     */
    private boolean validateRequest(ConventionRequestDTO requestDto) {
        ValidationResult result = conventionValidator.validate(requestDto);
        return result.isValid();
    }

    /**
     * Process the DTO and create a Convention object.
     */
    private Convention processConvention(ConventionRequestDTO requestDto) {
        return new Convention(
                requestDto.getPointOfRestId(),
                requestDto.getAgencyId(),
                requestDto.getStartDate(),
                requestDto.getEndDate(),
                requestDto.getTerms()
        );
    }

    /**
     * Notify the agency about the convention.
     */
    private void notifyAgency(Convention convention) {
        boolean success = notificationService.notifyAgency(convention.getId(), convention.getAgencyId());

        // According to sequence diagram, handle connection loss with retries (REQ-014)
        if (!success) {
            // Retry logic is inside NotificationService.handleConnectionLoss()
            // Here we just log or handle final failure
            System.out.println("Notification to agency failed after retries.");
        }
    }
}