package com.example.controller;

import com.example.usecase.DeleteDailyMenuUseCase;
import com.example.dto.ResponseEntity;

/**
 * Controller handling HTTP requests for deleting daily menus.
 */
public class DeleteDailyMenuController {
    private DeleteDailyMenuUseCase deleteDailyMenuUseCase;

    public DeleteDailyMenuController(DeleteDailyMenuUseCase useCase) {
        this.deleteDailyMenuUseCase = useCase;
    }

    /**
     * Deletes a daily menu for the given day of week.
     * Implements the sequence diagram flow.
     */
    public ResponseEntity<Void> deleteDailyMenu(String dayOfWeek) {
        try {
            boolean success = deleteDailyMenuUseCase.execute(dayOfWeek);
            if (success) {
                return new ResponseEntity<>(200, null); // Success response
            } else {
                // Could be menu not found or user cancelled
                return new ResponseEntity<>(404, null); // Not found or cancelled
            }
        } catch (Exception e) {
            // Service exception due to connection issues (see sequence diagram opt block)
            return new ResponseEntity<>(503, null); // Service unavailable
        }
    }
}