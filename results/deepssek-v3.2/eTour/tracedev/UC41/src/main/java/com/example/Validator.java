package com.example;

/**
 * Validator class encapsulating validation logic.
 */
public class Validator {
    /**
     * Validate menu data DTO.
     * @param menuData The MenuDataDTO to validate.
     * @return ValidationResult containing validation outcome.
     */
    public ValidationResult validate(MenuDataDTO menuData) {
        ValidationResult result = new ValidationResult();
        // Simple validation: day of week must not be empty, must have at least one item.
        if (menuData.getDayOfWeek() == null || menuData.getDayOfWeek().trim().isEmpty()) {
            result.addError("Day of week is required.");
        }
        if (menuData.getMenuItems() == null || menuData.getMenuItems().isEmpty()) {
            result.addError("At least one menu item is required.");
        } else {
            // Validate each item
            for (MenuItemDTO item : menuData.getMenuItems()) {
                if (item.getName() == null || item.getName().trim().isEmpty()) {
                    result.addError("Menu item name is required.");
                }
                if (item.getPrice() < 0) {
                    result.addError("Menu item price cannot be negative.");
                }
            }
        }
        result.setIsValid(result.getErrors().isEmpty());
        return result;
    }
}