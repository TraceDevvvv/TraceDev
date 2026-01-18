
package com.example.usecase;

import com.example.dto.MenuDTO;
import com.example.entity.Menu;
import com.example.repository.MenuRepository;
import com.example.validation.MenuValidator;
import com.example.validation.ValidationResult;

import java.util.Optional;

/**
 * Interactor implementing the edit daily menu use case logic.
 */
public class EditDailyMenuInteractor implements EditDailyMenuInputPort {
    private MenuRepository menuRepository;
    private MenuValidator validator;

    // Constructor with dependency injection
    public EditDailyMenuInteractor(MenuRepository menuRepository, MenuValidator validator) {
        this.menuRepository = menuRepository;
        this.validator = validator;
    }

    @Override
    public EditDailyMenuResult execute(Long operatorId, String dayOfWeek, MenuDTO menuData) {
        // Step 6: System loads the data menu into a form (implied retrieval)
        // We need restaurantId; assuming it can be derived from operatorId or MenuDTO.
        // For simplicity, assume we have a method to get restaurantId from operatorId.
        // We'll make an assumption: restaurantId equals operatorId for this example.
        Long restaurantId = operatorId; // Assumption

        Optional<Menu> existingMenuOpt = menuRepository.findByRestaurantIdAndDayOfWeek(restaurantId, dayOfWeek);

        // Step 9: System verifies the data
        ValidationResult validationResult = validator.validate(menuData);
        if (!validationResult.isValid()) {
            return EditDailyMenuResult.VALIDATION_ERROR;
        }

        // Since validation passed, ask for confirmation (simulated via presenter later)
        // In this interactor, we return CONFIRMATION_REQUIRED; confirmation handled by controller.
        // The actual confirmation is done outside via presenter; here we just indicate requirement.
        // In a real scenario, we might check if changes are significant.
        // For simplicity, we always require confirmation before save.
        return EditDailyMenuResult.CONFIRMATION_REQUIRED;
    }

    // Additional method to proceed with saving after confirmation (not in sequence diagram but needed)
    public EditDailyMenuResult proceedWithSave(Long operatorId, String dayOfWeek, MenuDTO menuData) {
        Long restaurantId = operatorId; // Assumption
        // Convert DTO to entity
        Menu menuToSave = menuData.toMenu(restaurantId);

        // Check if menu already exists to update or create new
        Optional<Menu> existingMenuOpt = menuRepository.findByRestaurantIdAndDayOfWeek(restaurantId, dayOfWeek);
        if (existingMenuOpt.isPresent()) {
            Menu existing = existingMenuOpt.get();
            existing.setItems(menuToSave.getItems());
            existing.setDayOfWeek(menuToSave.getDayOfWeek());
            menuToSave = existing;
        }

        try {
            menuRepository.save(menuToSave);
            return EditDailyMenuResult.SUCCESS;
        } catch (Exception e) {
            // Handle persistence errors (e.g., connection lost)
            return EditDailyMenuResult.PERSISTENCE_ERROR;
        }
    }
}
