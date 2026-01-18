package com.example.business.interactor;

import com.example.business.UpdateAccountUseCase;
import com.example.business.TouristRepository;
import com.example.business.ValidationService;
import com.example.business.command.UpdateAccountCommand;
import com.example.business.result.UpdateAccountResult;
import com.example.core.TouristEntity;
import java.util.List;

/**
 * Command pattern implementation coordinating the update flow.
 */
public class UpdateAccountInteractor implements UpdateAccountUseCase {
    private TouristRepository touristRepository;
    private ValidationService validationService;

    public UpdateAccountInteractor(TouristRepository repository, ValidationService validationService) {
        this.touristRepository = repository;
        this.validationService = validationService;
    }

    @Override
    public UpdateAccountResult execute(UpdateAccountCommand command) {
        // Step 1: Validate input data
        List<String> errors = validationService.validateAccountData(
                command.getUsername(), command.getEmail(), command.getPhone());

        if (!errors.isEmpty()) {
            // Data invalid: return result with errors (sequence diagram branch "data is invalid")
            return new UpdateAccountResult(false, "Validation failed", errors);
        }

        // Step 2: Load existing tourist entity
        TouristEntity tourist = touristRepository.findById(command.getTouristId());
        if (tourist == null) {
            errors.add("Tourist not found with ID: " + command.getTouristId());
            return new UpdateAccountResult(false, "Tourist not found", errors);
        }

        // Step 3: Update profile data
        tourist.updateProfileData(command.getUsername(), command.getEmail(), command.getPhone());

        // Step 4: Save updated entity (this will be called after confirmation, see controller)
        // The actual save is triggered by controller's proceedWithUpdate() call.
        // For now, we just return a success result requesting confirmation.
        // The sequence diagram shows that after validation, the interactor requests confirmation.
        // We'll signal this by a special result with success=true but no save performed.
        return new UpdateAccountResult(true, "Validation passed, awaiting confirmation", null);
    }

    /**
     * This method is called after user confirmation (sequence diagram opt block).
     * It performs the actual database save.
     */
    public UpdateAccountResult proceedWithUpdate(UpdateAccountCommand command) {
        TouristEntity tourist = touristRepository.findById(command.getTouristId());
        if (tourist == null) {
            List<String> errors = java.util.Collections.singletonList("Tourist not found");
            return new UpdateAccountResult(false, "Tourist not found", errors);
        }
        tourist.updateProfileData(command.getUsername(), command.getEmail(), command.getPhone());
        touristRepository.save(tourist);
        return new UpdateAccountResult(true, "Update successful", null);
    }

    /**
     * Retrieves current account data for pre‑populating the form (sequence diagram steps 3‑7).
     */
    public UpdateAccountResult getAccountData(String touristId) {
        TouristEntity tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            List<String> errors = java.util.Collections.singletonList("Tourist not found");
            return new UpdateAccountResult(false, "Tourist not found", errors);
        }
        java.util.Map<String, String> currentData = new java.util.HashMap<>();
        currentData.put("username", tourist.getUsername());
        currentData.put("email", tourist.getEmail());
        currentData.put("phone", tourist.getPhone());
        return new UpdateAccountResult(true, "Current data retrieved", null, currentData);
    }
}