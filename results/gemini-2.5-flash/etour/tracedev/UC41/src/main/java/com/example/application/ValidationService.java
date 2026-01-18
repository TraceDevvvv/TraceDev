package com.example.application;

import com.example.domain.DailyMenu;
import com.example.domain.MenuItem;

/**
 * Service for validating DailyMenu objects.
 */
public class ValidationService {

    /**
     * Validates a DailyMenu object.
     *
     * @param dailyMenu The DailyMenu to validate.
     * @return true if the menu is valid, false otherwise.
     */
    public boolean validate(DailyMenu dailyMenu) {
        System.out.println("[ValidationService] Validating DailyMenu for " + dailyMenu.getDay());
        if (dailyMenu == null) {
            System.out.println("[ValidationService] Validation failed: DailyMenu is null.");
            return false;
        }
        if (dailyMenu.getDay() == null) {
            System.out.println("[ValidationService] Validation failed: Day of week is null.");
            return false;
        }
        if (dailyMenu.getMenuItems() == null || dailyMenu.getMenuItems().isEmpty()) {
            System.out.println("[ValidationService] Validation failed: Menu items list is null or empty.");
            // Assuming for this use case, an empty menu is invalid for submission
            return false;
        }
        for (MenuItem item : dailyMenu.getMenuItems()) {
            if (item == null || item.getName() == null || item.getName().trim().isEmpty() || item.getPrice() <= 0) {
                System.out.println("[ValidationService] Validation failed: Invalid menu item found: " + item);
                return false;
            }
        }
        System.out.println("[ValidationService] Validation successful for DailyMenu for " + dailyMenu.getDay());
        return true;
    }
}