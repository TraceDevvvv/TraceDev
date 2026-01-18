package com.example.presentation;

import com.example.application.ConventionOperationException;
import com.example.application.ConventionRequestCommand;
import com.example.application.ConventionService;
import com.example.domain.ConventionId;

/**
 * REST Controller (Adapter) for handling Convention-related HTTP requests.
 * This acts as the entry point from the external world into the application layer.
 */
public class ConventionRestController {

    private final ConventionService conventionService;

    /**
     * Constructs a ConventionRestController with a ConventionService dependency.
     *
     * @param conventionService The application service responsible for convention business logic.
     */
    public ConventionRestController(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    /**
     * Endpoint to request a new convention.
     * (Maps to `requestConvention` sequence diagram)
     *
     * @param requestDTO The data transfer object containing the agreement details.
     * @return A response DTO indicating success or failure and the new convention ID.
     */
    public ConventionResponseDTO requestConventionEndpoint(ConventionRequestDTO requestDTO) {
        System.out.println("Controller: Received request to create convention with details: " + requestDTO.getAgreementDetails());
        try {
            // Map DTO to Command
            ConventionRequestCommand command = new ConventionRequestCommand(requestDTO.getAgreementDetails());

            // Call application service
            ConventionId conventionId = conventionService.requestConvention(command);

            // Return success response
            return new ConventionResponseDTO(conventionId.getValue(), "PENDING_VERIFICATION", "Convention request initiated successfully.");
        } catch (ConventionOperationException e) {
            System.err.println("Controller: Error requesting convention: " + e.getMessage());
            // Handle validation errors or other operational exceptions from the service
            return new ConventionResponseDTO(null, "ERROR", e.getMessage());
        } catch (Exception e) {
            System.err.println("Controller: Unexpected error requesting convention: " + e.getMessage());
            return new ConventionResponseDTO(null, "ERROR", "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to confirm a previously requested convention.
     * (Maps to `confirmConvention` sequence diagram)
     *
     * @param conventionId The ID of the convention to confirm.
     * @return A response DTO indicating the result of the confirmation.
     */
    public ConventionResponseDTO confirmConventionEndpoint(String conventionId) {
        System.out.println("Controller: Received request to confirm convention ID: " + conventionId);
        try {
            ConventionId id = new ConventionId(conventionId);
            // Call application service
            conventionService.confirmConvention(id);

            // Return success response
            return new ConventionResponseDTO(id.getValue(), "SENT_TO_AGENCY", "Convention confirmed and agency notified successfully.");
        } catch (ConventionOperationException e) {
            System.err.println("Controller: Error confirming convention: " + e.getMessage());
            // This catches specific exceptions indicating failure during confirmation or notification
            return new ConventionResponseDTO(conventionId, "ERROR", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Controller: Invalid Convention ID format: " + e.getMessage());
            return new ConventionResponseDTO(conventionId, "ERROR", "Invalid convention ID format.");
        } catch (Exception e) {
            System.err.println("Controller: Unexpected error confirming convention: " + e.getMessage());
            return new ConventionResponseDTO(conventionId, "ERROR", "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to cancel an existing convention. (REQ-002)
     * (Maps to `cancelConvention` sequence diagram)
     *
     * @param conventionId The ID of the convention to cancel.
     * @return A response DTO indicating the result of the cancellation.
     */
    public ConventionResponseDTO cancelConventionEndpoint(String conventionId) {
        System.out.println("Controller: Received request to cancel convention ID: " + conventionId);
        try {
            ConventionId id = new ConventionId(conventionId);
            // Call application service
            conventionService.cancelConvention(id);

            // Return success response
            return new ConventionResponseDTO(id.getValue(), "CANCELLED", "Convention cancelled successfully.");
        } catch (ConventionOperationException e) {
            System.err.println("Controller: Error cancelling convention: " + e.getMessage());
            // Catches if convention not found or other operational errors
            return new ConventionResponseDTO(conventionId, "ERROR", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Controller: Invalid Convention ID format: " + e.getMessage());
            return new ConventionResponseDTO(conventionId, "ERROR", "Invalid convention ID format.");
        } catch (Exception e) {
            System.err.println("Controller: Unexpected error cancelling convention: " + e.getMessage());
            return new ConventionResponseDTO(conventionId, "ERROR", "An unexpected error occurred: " + e.getMessage());
        }
    }
}