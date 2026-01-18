package com.example.service;

import com.example.domain.CulturalObject;
import com.example.dto.CulturalObjectDTO;
import com.example.exception.ConnectionException;
import com.example.repository.CulturalObjectRepository;
import com.example.result.UpdateResult;
import com.example.result.ValidationResult;
import com.example.validator.CulturalObjectValidator;
import java.util.Random;

/**
 * Main service for cultural object operations.
 */
public class CulturalObjectService {
    private CulturalObjectRepository repository;
    private CulturalObjectValidator validator;

    public CulturalObjectService(CulturalObjectRepository repository, CulturalObjectValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public CulturalObjectDTO getCulturalObjectById(int id) {
        CulturalObject culturalObject = repository.findById(id);
        return convertToDTO(culturalObject);
    }

    public UpdateResult updateCulturalObject(CulturalObjectDTO dto) throws ConnectionException {
        // Validate data
        ValidationResult validationResult = performValidation(dto);
        if (!validationResult.isValid()) {
            return new UpdateResult(false, "Validation failed", false);
        }

        // Check if confirmation is needed (simulated logic)
        boolean needsConfirmation = requiresConfirmation(dto);

        // Simulate random connection failure for demonstration
        Random rand = new Random();
        if (rand.nextInt(10) < 3) { // 30% chance of connection failure
            throw new ConnectionException("Connection to server ETOUR interrupted");
        }

        // Perform update
        CulturalObject existing = repository.findById(dto.getId());
        existing.updateFromDTO(dto);
        CulturalObject updated = repository.save(existing);

        return new UpdateResult(true, "Updated successfully", needsConfirmation);
    }

    private ValidationResult performValidation(CulturalObjectDTO dto) {
        return validator.validateForUpdate(dto);
    }

    private CulturalObjectDTO convertToDTO(CulturalObject culturalObject) {
        return culturalObject.toDTO();
    }

    private boolean requiresConfirmation(CulturalObjectDTO dto) {
        // Simulated logic: confirmation needed if name or location changed significantly
        // In real implementation, compare with existing data
        return dto.getName().contains("important") || dto.getLocation().contains("sensitive");
    }
}