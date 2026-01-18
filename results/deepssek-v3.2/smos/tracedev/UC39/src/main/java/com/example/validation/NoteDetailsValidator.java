package com.example.validation;

import com.example.application.NoteDetailsDTO;

/**
 * Validator for note details.
 * Ensures that the details are accurate and complete.
 */
public class NoteDetailsValidator {
    /**
     * Validates the note details DTO.
     * @param dto the DTO to validate
     * @return true if valid, false otherwise
     */
    public boolean validateNoteDetails(NoteDetailsDTO dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getStudentName() == null || dto.getStudentName().trim().isEmpty()) {
            return false;
        }
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            return false;
        }
        if (dto.getTeacherName() == null || dto.getTeacherName().trim().isEmpty()) {
            return false;
        }
        if (dto.getDate() == null) {
            return false;
        }
        System.out.println("Validator: Note details are valid.");
        return true;
    }

    /**
     * Validates a note ID (not in the class diagram but used in sequence diagram).
     * @param noteId the note ID to validate
     * @return true if valid, false otherwise
     */
    public boolean validateNoteId(String noteId) {
        boolean valid = noteId != null && !noteId.trim().isEmpty();
        System.out.println("Validator: Note ID validation result: " + valid);
        return valid;
    }
}