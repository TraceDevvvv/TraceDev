package application;

import domain.CulturalHeritage;
import domain.CulturalHeritageRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller for the Modify Cultural Heritage use case.
 * Coordinates between UI, domain and infrastructure layers.
 * Implements main flow of events (trace Req5).
 * Postcondition (Req12): Notification of data change sent after confirmTransaction.
 */
public class ModifyCulturalHeritageUseCaseController {
    private CulturalHeritageRepository repository;
    private boolean isOperationInProgress;
    private CulturalHeritageDTO pendingModifiedData;

    public ModifyCulturalHeritageUseCaseController(CulturalHeritageRepository repository) {
        this.repository = repository;
        this.isOperationInProgress = false;
        this.pendingModifiedData = null;
    }

    /**
     * Loads a CulturalHeritage by id and returns its DTO representation.
     * Called when user selects an item to edit.
     */
    public CulturalHeritageDTO loadCulturalHeritage(String id) {
        CulturalHeritage entity = repository.findById(id);
        if (entity == null) {
            // In real scenario, might throw a custom exception or return null DTO
            return null;
        }
        return new CulturalHeritageDTO(entity);
    }

    /**
     * Validates the modified DTO and returns a result with confirmationId if valid.
     * If invalid, returns a failure result.
     */
    public OperationResult submitChanges(CulturalHeritageDTO modifiedData) {
        ValidationResult validation = validateInput(modifiedData);
        if (!validation.isValid) {
            String errorMsg = String.join(", ", validation.errorMessages);
            return new OperationResult(false, errorMsg, null);
        }
        // Store pending data for later confirmation
        this.pendingModifiedData = modifiedData;
        // Assuming validation passes, generate a confirmation transaction ID
        String confirmationId = "TX" + UUID.randomUUID().toString().substring(0, 8);
        return new OperationResult(true, "Please confirm", confirmationId);
    }

    /**
     * Confirms the transaction, applies changes to the domain object and saves.
     * Implements the main transaction execution flow.
     */
    public OperationResult confirmTransaction(String confirmationId) {
        if (pendingModifiedData == null) {
            return new OperationResult(false, "No pending changes", null);
        }
        CulturalHeritage existing = repository.findById(pendingModifiedData.getId());
        if (existing == null) {
            return new OperationResult(false, "Cultural heritage not found", null);
        }
        // Apply modified DTO data to domain object
        existing.setName(pendingModifiedData.getName());
        existing.setDescription(pendingModifiedData.getDescription());
        existing.setType(pendingModifiedData.getType());
        existing.setLocation(pendingModifiedData.getLocation());
        // Call domain object validation
        if (!existing.validateForModification()) {
            return new OperationResult(false, "Domain rules violation", null);
        }
        repository.save(existing);
        pendingModifiedData = null;
        return new OperationResult(true, "Data saved successfully", confirmationId);
    }

    /**
     * Validates input DTO. Checks required fields.
     * Returns ValidationResult with error messages if any.
     */
    private ValidationResult validateInput(CulturalHeritageDTO data) {
        List<String> errors = new ArrayList<>();
        if (data == null) {
            errors.add("Data is null");
            return new ValidationResult(false, errors);
        }
        if (data.getId() == null || data.getId().trim().isEmpty()) {
            errors.add("ID is required");
        }
        if (data.getName() == null || data.getName().trim().isEmpty()) {
            errors.add("Name is required");
        }
        if (data.getLocation() == null || data.getLocation().trim().isEmpty()) {
            errors.add("Location is required");
        }
        // Additional validation rules could be added here
        return new ValidationResult(errors.isEmpty(), errors);
    }

    /**
     * Handles connection errors as per requirement REQ-014.
     * Returns an OperationResult indicating failure.
     */
    public OperationResult handleConnectionError(String errorDetails) {
        // Log the error details if needed
        return new OperationResult(false, "Server connection lost: " + errorDetails, null);
    }
}