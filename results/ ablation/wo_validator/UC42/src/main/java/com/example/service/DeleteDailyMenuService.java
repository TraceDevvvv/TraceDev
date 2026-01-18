
package com.example.service;

import com.example.repository.MenuRepository;
import com.example.model.DailyMenu;
import com.example.usecase.DeleteDailyMenuUseCase;
import java.util.Optional;

/**
 * Service implementing the delete daily menu use case.
 * Follows sequence diagram interactions.
 */
public class DeleteDailyMenuService implements DeleteDailyMenuUseCase {
    private MenuRepository menuRepository;

    public DeleteDailyMenuService(MenuRepository repository) {
        this.menuRepository = repository;
    }

    @Override
    public boolean execute(String dayOfWeek) {
        // Step: Service -> Repository : findByDayOfWeek
        Optional<DailyMenu> optionalMenu = menuRepository.findByDayOfWeek(dayOfWeek);
        if (!optionalMenu.isPresent()) {
            // Menu not found case
            return false;
        }
        DailyMenu menu = optionalMenu.get();

        // Simulating UI confirmation request (as per sequence diagram)
        // In a real app, this would involve UI interaction, here we assume confirmation is handled externally.
        // For simplicity, we assume confirmation is always true; the sequence diagram shows alternate flows.
        boolean userConfirmed = requestConfirmation(menu);
        if (!userConfirmed) {
            // Operator cancels
            return false;
        }

        // Step: Service -> Repository : delete
        try {
            menuRepository.delete(menu);
        } catch (RuntimeException e) {
            // Connection interruption (opt block in sequence diagram)
            throw new RuntimeException("Server connection lost", e);
        }
        return true;
    }

    /**
     * Simulates UI confirmation request.
     * In real scenario, this would be handled by UI layer.
     */
    private boolean requestConfirmation(DailyMenu menu) {
        // Placeholder: assume confirmation is granted.
        // The actual UI interaction is outside the scope of this service.
        return true;
    }
}
