package com.system.validation;

import java.util.List;

/**
 * Validator for tag-related operations.
 */
public class TagValidator {
    public boolean validateTagIds(List<String> ids) {
        // Basic validation: list not null and not empty.
        return ids != null && !ids.isEmpty();
    }

    public boolean areAllIdsValid(List<String> ids) {
        // Additional validation could check format, existence, etc.
        // For simplicity, we assume all non-empty strings are valid.
        if (ids == null) return false;
        for (String id : ids) {
            if (id == null || id.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}