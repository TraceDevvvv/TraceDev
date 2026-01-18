package com.example.validation;

import com.example.dto.MenuDTO;

/**
 * Validator for menu data.
 */
public class MenuValidator {
    public ValidationResult validate(MenuDTO menuData) {
        ValidationResult result = new ValidationResult();

        // Validate day of week
        if (menuData.getDayOfWeek() == null || menuData.getDayOfWeek().trim().isEmpty()) {
            result.addError("Day of week is required.");
        } else {
            String[] validDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            boolean valid = false;
            for (String day : validDays) {
                if (day.equalsIgnoreCase(menuData.getDayOfWeek().trim())) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                result.addError("Invalid day of week.");
            }
        }

        // Validate items: at least one item required
        if (menuData.getItems() == null || menuData.getItems().isEmpty()) {
            result.addError("At least one menu item is required.");
        } else {
            for (String item : menuData.getItems()) {
                if (item == null || item.trim().isEmpty()) {
                    result.addError("Menu item cannot be empty.");
                }
            }
        }

        return result;
    }
}