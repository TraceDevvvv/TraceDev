package com.example.service;

import com.example.dto.RefreshmentPointFormDTO;
import com.example.exception.ETOURConnectionException;
import com.example.model.*;
import com.example.repository.RefreshmentPointRepository;

import java.util.List;

/**
 * Core service for refreshment point operations.
 */
public class RefreshmentPointService {
    private RefreshmentPointRepository repository;
    private TransactionLock transactionLock;
    private ErrorHandler errorHandler;

    public RefreshmentPointService(RefreshmentPointRepository repository, TransactionLock transactionLock, ErrorHandler errorHandler) {
        this.repository = repository;
        this.transactionLock = transactionLock;
        this.errorHandler = errorHandler;
    }

    /**
     * Loads a refreshment point by ID.
     * @param id point identifier
     * @return the RefreshmentPoint if found and active
     */
    public RefreshmentPoint loadRefreshmentPoint(String id) {
        return repository.findById(id)
                .filter(RefreshmentPoint::isActive)
                .orElse(null);
    }

    /**
     * Modifies a refreshment point.
     * @param command modification command
     * @return result of the operation
     */
    public Result modifyRefreshmentPoint(ModifyRefreshmentPointCommand command) {
        // Business validation
        RefreshmentPoint point = loadRefreshmentPoint(command.getRefreshmentPointId());
        if (point == null) {
            return Result.failure("Point not available");
        }

        ValidationResult validation = validateBusinessRules(point, command.getFormData());
        if (!validation.isValid()) {
            return Result.failure("Business validation failed: " + validation.getErrors());
        }

        // In sequence diagram, this method returns a request for confirmation.
        // We'll simulate that by returning a special result.
        String summary = "Modification summary for point " + point.getId();
        return Result.success("Confirmation required", summary);
    }

    /**
     * Validates business rules.
     * @param point the existing point
     * @param formData new data
     * @return validation result
     */
    public ValidationResult validateBusinessRules(RefreshmentPoint point, RefreshmentPointFormDTO formData) {
        ValidationResult result = new ValidationResult();
        // Example rule: name cannot be empty
        if (formData.getName() == null || formData.getName().trim().isEmpty()) {
            result.addError("Name cannot be empty");
        }
        // Additional rules could be added.
        return result;
    }

    /**
     * Creates a validation result with errors.
     * Added for REQ-009.
     * @param errors list of error messages
     * @return ValidationResult
     */
    public ValidationResult createValidationResult(List<String> errors) {
        ValidationResult result = new ValidationResult();
        for (String error : errors) {
            result.addError(error);
        }
        return result;
    }

    /**
     * Confirms the transaction with a token.
     * @param token confirmation token
     * @return true if confirmed successfully
     */
    public boolean confirmTransaction(String token) {
        if (!verifyConfirmationToken(token)) {
            return false;
        }
        // Token valid, proceed with transaction.
        return true;
    }

    /**
     * Verifies the confirmation token.
     * @param token the token to verify
     * @return true if token is valid
     */
    public boolean verifyConfirmationToken(String token) {
        // In a real system, token would be checked against a store.
        return token != null && token.equals("valid-token");
    }

    /**
     * Updates the refreshment point with form data.
     * @param point existing point
     * @param formData new data
     */
    public void updateRefreshmentPoint(RefreshmentPoint point, RefreshmentPointFormDTO formData) {
        point.setName(formData.getName());
        // Increment version for optimistic locking
        point.setVersion(point.getVersion() + 1);
    }

    /**
     * Stores modified data.
     * Added for REQ-011.
     * @param point the updated point
     */
    public void storeModifiedData(RefreshmentPoint point) {
        try {
            repository.update(point);
            System.out.println("Data stored for point: " + point.getId());
        } catch (Exception e) {
            throw new ETOURConnectionException("Failed to store data", "DB-001");
        }
    }

    /**
     * Executes the full modification after confirmation.
     * This method combines steps from the sequence diagram.
     */
    public Result executeModification(ModifyRefreshmentPointCommand command) {
        String pointId = command.getRefreshmentPointId();
        // Block input controls
        transactionLock.blockInputControls(pointId);

        try {
            RefreshmentPoint point = repository.findById(pointId)
                    .orElseThrow(() -> new RuntimeException("Point not found"));

            updateRefreshmentPoint(point, command.getFormData());
            storeModifiedData(point);

            transactionLock.releaseLock(pointId);
            return Result.success("Data updated", point);
        } catch (ETOURConnectionException e) {
            errorHandler.handleSystemError(e);
            return Result.failure("Database connection error: " + e.getMessage());
        } catch (Exception e) {
            errorHandler.handleSystemError(e);
            return Result.failure("Modification failed: " + e.getMessage());
        }
    }
}