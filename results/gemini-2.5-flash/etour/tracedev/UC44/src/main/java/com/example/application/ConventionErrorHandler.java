package com.example.application;

import com.example.domain.Convention;
import com.example.domain.ConventionRepository;
import com.example.domain.ConventionStatus;
import com.example.domain.EtoURConnectionException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles various errors that can occur during Convention-related use cases.
 * (Added to satisfy REQ-003, REQ-005)
 * This class interacts with the domain layer to update convention status in case of specific errors.
 */
public class ConventionErrorHandler {

    private final ConventionRepository conventionRepository;

    /**
     * Constructs a ConventionErrorHandler with a ConventionRepository dependency.
     *
     * @param conventionRepository The repository used to save convention status updates.
     */
    public ConventionErrorHandler(ConventionRepository conventionRepository) {
        this.conventionRepository = conventionRepository;
    }

    /**
     * Handles connection errors to the external EtoUR system (Agency).
     * It updates the convention's status to FAILED_TO_NOTIFY (REQ-007) and saves it.
     *
     * @param exception The EtoURConnectionException that occurred.
     * @param convention The Convention entity that failed to be notified.
     */
    public void handleEtoURConnectionError(EtoURConnectionException exception, Convention convention) {
        System.err.println("Handling EtoUR Connection Error for Convention ID: " + convention.getId().getValue());
        System.err.println("Error message: " + exception.getMessage());
        // REQ-007: Update Convention status to FAILED_TO_NOTIFY
        convention.updateStatus(ConventionStatus.FAILED_TO_NOTIFY);
        conventionRepository.save(convention); // Save the updated status
        System.err.println("Convention ID " + convention.getId().getValue() + " status updated to FAILED_TO_NOTIFY.");

        // In a real system, this might trigger an alert, a retry mechanism, or further logging.
        // The sequence diagram shows `failureHandled` which implies this method completes the handling.
        // The service layer might then re-throw a more generic exception or return an error indicator.
    }

    /**
     * Handles validation errors encountered during a use case.
     * It aggregates validation error messages into an ErroredUseCaseContext.
     *
     * @param validationErrors A list of strings, each describing a validation error.
     * @return An ErroredUseCaseContext containing details about the validation errors.
     */
    public ErroredUseCaseContext handleValidationError(List<String> validationErrors) {
        System.err.println("Handling Validation Error:");
        String detailedErrors = validationErrors.stream()
                .map(error -> "- " + error)
                .collect(Collectors.joining("\n"));
        System.err.println(detailedErrors);

        // REQ-005: Create and return an ErroredUseCaseContext
        String errorMessage = "Validation failed. Please check the input data:\n" + detailedErrors;
        return new ErroredUseCaseContext(errorMessage, "VALIDATION_ERROR");
    }
}