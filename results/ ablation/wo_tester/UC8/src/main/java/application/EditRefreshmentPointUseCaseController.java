package application;

import domain.RefreshmentPoint;
import domain.Status;
import exceptions.EditPointException;
import interfaces.ConfirmationHandler;
import interfaces.IRefreshmentPointRepository;
import java.util.Optional;

/**
 * Use case controller for editing a refreshment point.
 */
public class EditRefreshmentPointUseCaseController {
    private IRefreshmentPointRepository pointRepository;
    private RefreshmentPointValidator validator;
    // Added to satisfy requirement Flow step 9
    private ConfirmationHandler confirmer;

    public EditRefreshmentPointUseCaseController(IRefreshmentPointRepository pointRepository, RefreshmentPointValidator validator) {
        this.pointRepository = pointRepository;
        this.validator = validator;
    }

    /**
     * Sets the confirmation handler for the use case.
     * @param confirmer the confirmation handler.
     */
    public void setConfirmationHandler(ConfirmationHandler confirmer) {
        this.confirmer = confirmer;
    }

    /**
     * Executes the edit operation.
     * @param formData the DTO with form data.
     * @param confirmer the confirmation handler.
     * @return the operation result.
     */
    public OperationResult execute(RefreshmentPointDTO formData, ConfirmationHandler confirmer) {
        this.confirmer = confirmer;
        // Validate the form data
        ValidationResult validationResult = validator.validate(formData);
        if (!validationResult.isValid()) {
            // Handle validation error via ErroredUseCase (Flow step 10)
            ErroredUseCase errorHandler = new ErroredUseCase();
            errorHandler.handleError(validationResult);
            return new OperationResult(false, "Validation failed", "VALIDATION_ERROR");
        }

        // Request confirmation (Flow step 9)
        if (confirmer != null && !confirmer.requestConfirmation("Confirm transaction?")) {
            return new OperationResult(false, "Operation cancelled by user", "CANCELLED");
        }

        // Convert DTO to entity (Flow step 12)
        RefreshmentPoint point = convertDTOToEntity(formData);
        try {
            // Update the point
            pointRepository.update(point);
            return new OperationResult(true, "Refreshment point updated successfully", null);
        } catch (EditPointException e) {
            // Handle server connection error
            ErroredUseCase errorHandler = new ErroredUseCase();
            errorHandler.logError(e);
            return new OperationResult(false, "Server connection error", "CONNECTION_ERROR");
        }
    }

    /**
     * Loads point data by ID.
     * @param pointId the point ID.
     * @return the DTO with point data.
     */
    public RefreshmentPointDTO loadPointData(String pointId) {
        Optional<RefreshmentPoint> pointOpt = pointRepository.findById(pointId);
        if (pointOpt.isPresent()) {
            RefreshmentPointDTO dto = new RefreshmentPointDTO();
            dto.fromEntity(pointOpt.get());
            return dto;
        }
        return null;
    }

    /**
     * Validates the form DTO.
     * @param dto the DTO to validate.
     * @return the validation result.
     */
    public ValidationResult validateForm(RefreshmentPointDTO dto) {
        return validator.validate(dto);
    }

    /**
     * Confirms the transaction via the confirmation handler.
     * Assumption: Uses the set confirmer.
     * @return true if confirmed, false otherwise.
     */
    public boolean confirmTransaction() {
        if (confirmer != null) {
            return confirmer.requestConfirmation("Confirm transaction?");
        }
        return false;
    }

    /**
     * Converts a DTO to an entity (Flow step 12).
     * @param dto the DTO to convert.
     * @return the RefreshmentPoint entity.
     */
    public RefreshmentPoint convertDTOToEntity(RefreshmentPointDTO dto) {
        // Assumption: The DTO contains all necessary data.
        return dto.toEntity();
    }
}