package com.example.interfaceadapters.validators;

import com.example.domain.EditTeachingCommand;

/**
 * Validates the edit teaching command.
 */
public class TeachingValidator {
    /**
     * Validates the command data.
     * Returns true if valid, false otherwise.
     */
    public boolean validate(EditTeachingCommand command) {
        // Simple validation: non-null and non-empty fields, schedule not in the past.
        if (command.getTeachingId() == null || command.getTeachingId().trim().isEmpty()) {
            return false;
        }
        if (command.getTitle() == null || command.getTitle().trim().isEmpty()) {
            return false;
        }
        if (command.getSchedule() == null || command.getSchedule().isBefore(java.time.LocalDateTime.now())) {
            // Assuming schedule cannot be in the past.
            return false;
        }
        return true;
    }
}