package com.restaurant.menu.validation;

import com.restaurant.menu.Menu;
import com.restaurant.menu.MenuItem;
import java.util.List;
import java.util.ArrayList;

/**
 * Validates menu data.
 * Implements reliability requirement R17.
 */
public class MenuValidator {
    /**
     * Validates a menu.
     * @param menu the menu to validate
     * @return ValidationResult indicating if menu is valid and any errors.
     */
    public ValidationResult validate(Menu menu) {
        List<String> errors = new ArrayList<>();
        boolean valid = true;

        if (!validateRequiredFields(menu)) {
            errors.add("Required fields are missing.");
            valid = false;
        }

        if (!validateItemPr(menu.getItems())) {
            errors.add("Some item pr are invalid (negative).");
            valid = false;
        }

        return new ValidationResult(valid, errors);
    }

    /**
     * Validates that required fields are present.
     * Assumption: dayOfWeek and date must not be null, and items list must not be null.
     */
    public boolean validateRequiredFields(Menu menu) {
        return menu.getDayOfWeek() != null && !menu.getDayOfWeek().isEmpty() &&
               menu.getDate() != null &&
               menu.getItems() != null;
    }

    /**
     * Validates that all item pr are non-negative.
     */
    public boolean validateItemPr(List<MenuItem> items) {
        if (items == null) return false;
        for (MenuItem item : items) {
            if (item.getPrice() < 0) {
                return false;
            }
        }
        return true;
    }
}