package com.example;

import java.util.List;

/**
 * Validator for student notes.
 * Supports requirement R3 - Quality Requirement: list must be complete and accurate.
 */
public class NoteValidator {

    /**
     * Validates completeness of the notes list.
     *
     * @param notes the list of StudentNote objects
     * @return true if the list is complete, false otherwise
     */
    public boolean validateCompleteness(List<StudentNote> notes) {
        // Placeholder implementation.
        // In a real scenario, this would check that all required notes are present.
        return notes != null && !notes.isEmpty();
    }

    /**
     * Validates accuracy of a single note.
     *
     * @param note the StudentNote to validate
     * @return true if the note is accurate, false otherwise
     */
    public boolean validateAccuracy(StudentNote note) {
        // Placeholder implementation.
        // In a real scenario, this would check fields for correctness.
        return note != null && note.getId() != null && !note.getId().isEmpty();
    }
}