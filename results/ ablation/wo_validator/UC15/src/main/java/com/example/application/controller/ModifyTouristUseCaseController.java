package com.example.application.controller;

import com.example.application.repository.TouristRepository;
import com.example.application.dto.ModifyTouristDTO;
import com.example.core.domain.Tourist;
import com.example.infrastructure.validation.TouristValidator;

/**
 * Controller for the Modify Tourist use case.
 * Orchestrates the flow of loading, validating, and modifying tourist data.
 */
public class ModifyTouristUseCaseController {
    private TouristRepository touristRepository;
    private TouristValidator validator;

    public ModifyTouristUseCaseController(TouristRepository repository) {
        this.touristRepository = repository;
        this.validator = new TouristValidator(); // Assumption: validator is instantiated here
    }

    /**
     * Loads tourist data for a given ID and returns it as a DTO.
     * Called from the form when a tourist is selected.
     */
    public ModifyTouristDTO loadTouristData(Long touristId) {
        Tourist tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist not found with id: " + touristId);
        }
        return ModifyTouristDTO.fromTourist(tourist);
    }

    /**
     * Modifies tourist data after validation and user confirmation.
     * Returns true if the modification was successful.
     */
    public boolean modifyTouristData(ModifyTouristDTO dto) {
        // Step 1: Validate the DTO
        if (!validateTouristData(dto)) {
            return false; // Validation failed
        }

        // Step 2: The form will request confirmation from the user.
        // This controller expects the form to handle confirmation and then call proceedWithUpdate.
        // For the sequence diagram flow, we assume validation passes and proceed.
        // Actual confirmation is handled at the UI level.

        // Step 3: Convert DTO to Tourist and save
        Tourist tourist = dto.toTourist();
        Tourist savedTourist = touristRepository.save(tourist);
        return savedTourist != null;
    }

    /**
     * Validates the tourist data using the TouristValidator.
     */
    public boolean validateTouristData(ModifyTouristDTO dto) {
        return validator.validateAll(dto);
    }

    /**
     * Proceeds with the update after user confirmation.
     * This method is called by the form after confirmation.
     */
    public boolean proceedWithUpdate(ModifyTouristDTO dto) {
        Tourist tourist = dto.toTourist();
        Tourist savedTourist = touristRepository.save(tourist);
        return savedTourist != null;
    }
}