package com.example;

import java.util.List;

/**
 * Standard validator for menu data. Checks required fields and item count.
 */
public class StandardMenuValidator implements MenuValidationStrategy {
    private int requiredItemCount;

    public StandardMenuValidator(int requiredItemCount) {
        this.requiredItemCount = requiredItemCount;
    }

    @Override
    public boolean validate(MenuDTO menuData) {
        return checkRequiredFields(menuData) && validateItemCount(menuData);
    }

    private boolean checkRequiredFields(MenuDTO menuData) {
        return menuData != null &&
               menuData.getDayOfWeek() != null &&
               !menuData.getDayOfWeek().trim().isEmpty() &&
               menuData.getMenuItems() != null;
    }

    private boolean validateItemCount(MenuDTO menuData) {
        List<String> items = menuData.getMenuItems();
        return items != null && items.size() >= requiredItemCount;
    }
}