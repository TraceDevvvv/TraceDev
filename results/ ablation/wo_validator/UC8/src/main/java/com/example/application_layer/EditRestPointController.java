
package com.example.application_layer;

import com.example.domain_layer.RestPoint;
import java.util.Optional;

/**
 * Application Layer: Controller that orchestrates the edit rest point use case.
 * Implements the use case interface and coordinates between layers.
 */
public class EditRestPointController implements EditRestPointUseCase {
    private RestPointRepository restPointRepository;
    private EditRestPointCommandValidator editCommandValidator;

    public EditRestPointController(RestPointRepository restPointRepository,
                                   EditRestPointCommandValidator editCommandValidator) {
        this.restPointRepository = restPointRepository;
        this.editCommandValidator = editCommandValidator;
    }

    /**
     * Main use case execution: validates command and initiates editing.
     * @param command The edit command with updated data.
     * @return Result indicating success/failure and validation errors.
     */
    @Override
    public EditRestPointResult execute(EditRestPointCommand command) {
        System.out.println("EditRestPointController: Executing edit command for ID " + command.restPointId);
        // Step 8: Validate command.
        ValidationResult validation = validateCommand(command);
        if (!validation.isValid()) {
            return new EditRestPointResult(false,
                    "Validation failed",
                    validation.getErrors(),
                    command.restPointId);
        }
        // If validation passes, return success (confirmation will be requested).
        return new EditRestPointResult(true,
                "Validation passed. Confirm to save.",
                validation.getErrors(),
                command.restPointId);
    }

    /**
     * Loads rest point data for a given ID and converts to DTO.
     * Called when the operator selects a rest point to edit.
     * @param restPointId The ID of the rest point.
     * @return DTO containing rest point data, or null if not found.
     */
    public RestPointDto loadRestPointData(String restPointId) {
        System.out.println("EditRestPointController: Loading data for ID " + restPointId);
        Optional<RestPoint> restPointOpt = restPointRepository.findById(restPointId);
        if (restPointOpt.isPresent()) {
            RestPoint restPoint = restPointOpt.get();
            // Convert domain entity to DTO.
            return RestPointDto.fromDomain(restPoint);
        } else {
            System.err.println("Rest point not found: " + restPointId);
            return null;
        }
    }

    /**
     * Validates the command using the validator.
     * @param command The edit command.
     * @return ValidationResult with errors if any.
     */
    public ValidationResult validateCommand(EditRestPointCommand command) {
        System.out.println("EditRestPointController: Validating command");
        return editCommandValidator.validate(command);
    }

    /**
     * Persists changes after confirmation.
     * Converts command to domain entity, updates, and saves.
     * @param command The edit command with final data.
     * @return Result indicating success/failure.
     */
    public EditRestPointResult confirmAndPersist(EditRestPointCommand command) {
        System.out.println("EditRestPointController: Confirming and persisting changes");
        // Convert command to domain entity.
        RestPoint restPoint = convertToDomain(command);
        if (restPoint == null) {
            return new EditRestPointResult(false,
                    "Rest point not found",
                    null,
                    command.restPointId);
        }
        // Step 11: Save updated entity.
        boolean saved = persistChanges(restPoint);
        if (saved) {
            return new EditRestPointResult(true,
                    "Saved successfully",
                    null,
                    command.restPointId);
        } else {
            return new EditRestPointResult(false,
                    "Failed to save",
                    null,
                    command.restPointId);
        }
    }

    /**
     * Converts EditRestPointCommand to a RestPoint domain entity.
     * Assumes the rest point already exists (fetched from repository).
     * @param command The command with new data.
     * @return Updated RestPoint entity, or null if not found.
     */
    RestPoint convertToDomain(EditRestPointCommand command) {
        Optional<RestPoint> restPointOpt = restPointRepository.findById(command.restPointId);
        if (restPointOpt.isEmpty()) {
            return null;
        }
        RestPoint restPoint = restPointOpt.get();
        // Update fields with new values from command.
        restPoint.updateName(command.name);
        restPoint.updateLocation(command.location);
        restPoint.updateCapacity(command.capacity);
        restPoint.updateAmenities(command.amenities);
        if ("active".equalsIgnoreCase(command.status)) {
            restPoint.activate();
        } else if ("inactive".equalsIgnoreCase(command.status)) {
            restPoint.deactivate();
        }
        return restPoint;
    }

    /**
     * Saves the updated rest point via repository.
     * @param restPoint The updated domain entity.
     * @return true if saved successfully, false otherwise.
     */
    boolean persistChanges(RestPoint restPoint) {
        try {
            restPointRepository.save(restPoint);
            return true;
        } catch (Exception e) {
            System.err.println("Error saving rest point: " + e.getMessage());
            return false;
        }
    }
}
