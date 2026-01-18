
package com.example.application;

import com.example.domain.*;

import java.util.List;

/**
 * Application Service for managing Conventions.
 * This orchestrates interactions between the domain layer, ports, and other application serv.
 * It encapsulates the use case logic.
 */
public class ConventionService {

    private final ConventionRepository conventionRepository;
    private final AgencyNotificationPort agencyNotificationPort;
    private final ConventionValidator conventionValidator;
    private final AuthenticationService authenticationService; // Added for authorization concerns (REQ-001)
    private final ConventionErrorHandler conventionErrorHandler; // Added to satisfy REQ-003, REQ-005

    /**
     * Constructs a ConventionService with necessary dependencies.
     * Dependencies are injected (e.g., via constructor injection) following Hexagonal Architecture principles.
     *
     * @param conventionRepository The repository for convention persistence.
     * @param agencyNotificationPort The port for notifying external agencies.
     * @param conventionValidator The validator for convention requests.
     * @param authenticationService The service for authentication and authorization.
     * @param conventionErrorHandler The handler for various errors.
     */
    public ConventionService(
            ConventionRepository conventionRepository,
            AgencyNotificationPort agencyNotificationPort,
            ConventionValidator conventionValidator,
            AuthenticationService authenticationService,
            ConventionErrorHandler conventionErrorHandler) {
        this.conventionRepository = conventionRepository;
        this.agencyNotificationPort = agencyNotificationPort;
        this.conventionValidator = conventionValidator;
        this.authenticationService = authenticationService; // Not directly used in current sequence flow, but part of model
        this.conventionErrorHandler = conventionErrorHandler;
    }

    /**
     * Handles the "Request Convention" use case.
     * It validates the request, creates a new convention, and persists it.
     *
     * @param request The command containing agreement details for the new convention.
     * @return The ID of the newly created convention.
     * @throws ConventionOperationException if validation fails, or other operational errors occur.
     */
    public ConventionId requestConvention(ConventionRequestCommand request) {
        System.out.println("Service: Receiving requestConvention command for details: " + request.getAgreementDetails());

        // Step 3: Service -> Validator: validate(command)
        List<String> validationErrors = conventionValidator.getValidationErrors(request);
        if (!validationErrors.isEmpty()) {
            // Step 6: data is invalid. Service -> ErrorHandler: handleValidationError(...)
            ErroredUseCaseContext erroredContext = conventionErrorHandler.handleValidationError(validationErrors);
            // Service --> UI: error(erroredContext.errorDetails)
            throw new ConventionOperationException(erroredContext.getErrorDetails());
        }

        // Create Convention entity (status PENDING_VERIFICATION)
        // Service -> Convention: create(details)
        Convention newConvention = Convention.create(request.getAgreementDetails());
        System.out.println("Service: Created new Convention with ID: " + newConvention.getId().getValue() + ", Status: " + newConvention.getStatus());

        // Service -> Repository: save(newConvention)
        conventionRepository.save(newConvention); // The save operation usually returns the same or an updated entity
        System.out.println("Service: Saved new Convention with ID: " + newConvention.getId().getValue());

        return newConvention.getId();
    }

    /**
     * Handles the "Confirm Convention" use case.
     * It retrieves the convention, updates its status to VERIFIED, attempts to notify the agency,
     * and updates the status based on the notification result.
     *
     * @param conventionId The ID of the convention to confirm.
     * @throws ConventionOperationException if the convention is not found, or if agency notification fails.
     */
    public void confirmConvention(ConventionId conventionId) {
        System.out.println("Service: Confirming Convention ID: " + conventionId.getValue());

        // Service -> Repository: findById(conventionId)
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null) {
            throw new ConventionOperationException("Convention with ID " + conventionId.getValue() + " not found.");
        }
        System.out.println("Service: Found Convention with ID: " + convention.getId().getValue() + ", Current Status: " + convention.getStatus());

        // Update Convention status to VERIFIED
        // Service -> Convention: updateStatus(VERIFIED)
        convention.updateStatus(ConventionStatus.VERIFIED);
        System.out.println("Service: Convention ID " + convention.getId().getValue() + " status updated to VERIFIED.");

        try {
            // Service -> AgencyPort: notifyConvention(convention)
            System.out.println("Service: Attempting to notify Agency for Convention ID: " + convention.getId().getValue());
            boolean sent = agencyNotificationPort.notifyConvention(convention); // This might throw EtoURConnectionException

            if (sent) {
                // Update Convention status to SENT_TO_AGENCY
                // Service -> Convention: updateStatus(SENT_TO_AGENCY)
                convention.updateStatus(ConventionStatus.SENT_TO_AGENCY);
                System.out.println("Service: Convention ID " + convention.getId().getValue() + " status updated to SENT_TO_AGENCY.");
                // Service -> Repository: save(convention)
                conventionRepository.save(convention);
                System.out.println("Service: Convention ID " + convention.getId().getValue() + " saved after successful agency notification.");
            } else {
                // If notifyConvention returns false but doesn't throw, treat as a logical failure for notification.
                // In a real system, this path might be more detailed or always throw for failures.
                System.err.println("Service: Agency notification for Convention ID " + convention.getId().getValue() + " returned false (logical failure).");
                // The sequence diagram implies throws EtoURConnectionException on failure,
                // so this `else` branch might not be reached if the port always throws on error.
                // For robustness, we handle it explicitly.
                conventionErrorHandler.handleEtoURConnectionError(
                        new EtoURConnectionException("Agency notification returned false for unknown reason."), convention);
                throw new ConventionOperationException("Failed to notify agency for Convention ID: " + convention.getId().getValue() + " (logical failure).");
            }
        } catch (EtoURConnectionException e) {
            // Service -> ErrorHandler: handleEtoURConnectionError(exception, convention)
            System.err.println("Service: Caught EtoURConnectionException for Convention ID: " + convention.getId().getValue());
            conventionErrorHandler.handleEtoURConnectionError(e, convention);
            // Service --> UI: error("Failed to notify agency...")
            throw new ConventionOperationException("Failed to notify agency due to connection issue. Please try again. Convention ID: " + convention.getId().getValue(), e);
        }
    }

    /**
     * Handles the "Cancel Convention" use case. (REQ-002)
     * It retrieves the convention, updates its status to CANCELLED, and persists the change.
     *
     * @param conventionId The ID of the convention to cancel.
     * @throws ConventionOperationException if the convention is not found.
     */
    public void cancelConvention(ConventionId conventionId) {
        System.out.println("Service: Cancelling Convention ID: " + conventionId.getValue());

        // Service -> Repository: findById(conventionId)
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null) {
            throw new ConventionOperationException("Convention with ID " + conventionId.getValue() + " not found.");
        }
        System.out.println("Service: Found Convention with ID: " + convention.getId().getValue() + ", Current Status: " + convention.getStatus());

        // Update Convention status to CANCELLED (REQ-002)
        // Service -> Convention: updateStatus(CANCELLED)
        convention.updateStatus(ConventionStatus.CANCELLED);
        System.out.println("Service: Convention ID " + convention.getId().getValue() + " status updated to CANCELLED.");

        // Service -> Repository: save(convention)
        conventionRepository.save(convention);
        System.out.println("Service: Convention ID " + convention.getId().getValue() + " saved after cancellation.");
    }
}
