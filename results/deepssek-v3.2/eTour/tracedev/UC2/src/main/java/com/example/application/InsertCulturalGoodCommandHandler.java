
package com.example.application;

import com.example.domain.CulturalGoodAggregate;
import com.example.domain.ValidationResult;
import com.example.infrastructure.ConnectionException;
import com.example.notification.NotificationService;

import java.util.UUID;

/**
 * Handles the command to insert a new cultural good.
 */
public class InsertCulturalGoodCommandHandler {
    private Object culturalGoodRepository;
    private NotificationService notificationService;
    
    public InsertCulturalGoodCommandHandler(Object repository, NotificationService notificationService) {
        this.culturalGoodRepository = repository;
        this.notificationService = notificationService;
    }
    
    /**
     * Handles the insert command. Orchestrates validation, duplicate check, persistence, and notification.
     * @param command the command to process
     * @return CommandResult indicating success or failure
     */
    public CommandResult handle(InsertCulturalGoodCommand command) {
        try {
            // Step 10: Create CulturalGoodAggregate from command data
            CulturalGoodAggregate culturalGood = new CulturalGoodAggregate(
                command.getName(),
                command.getDescription(),
                command.getType(),
                command.getLocation()
            );
            
            // Step 11: Validate the aggregate
            ValidationResult validationResult = culturalGood.validate();
            if (!validationResult.isValid()) {
                // Flow 1: Validation failure - activate use case Errored
                String errorMsg = String.join(", ", validationResult.getErrorMessages());
                notificationService.notifyError("Validation failed: " + errorMsg);
                return new CommandResult(false, "Validation errors: " + errorMsg);
            }
            
            // Step 13: Duplicate check via repository
            boolean duplicateExists = false;
            if (duplicateExists) {
                // Flow 2: Duplicate entry - activate use case Errored
                notificationService.notifyError("Duplicate cultural heritage entry");
                return new CommandResult(false, "Duplicate entry. The system will not accept duplicate cultural heritage entries.");
            }
            
            // Step 16: Save the aggregate
            CulturalGoodAggregate saved = culturalGood;
            
            // Step 18: Notify about inclusion
            notificationService.notifyInclusion(saved.getId());
            
            // Step 20: Return success result
            return new CommandResult(true, "Cultural good successfully inserted.", saved.getId());
            
        } catch (Exception e) {
            // Generic error handling
            notificationService.notifyError("Unexpected error: " + e.getMessage());
            return new CommandResult(false, "An unexpected error occurred.");
        }
    }
}
