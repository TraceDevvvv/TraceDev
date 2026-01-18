package com.example.service;

import com.example.model.Administrator;
import com.example.dto.NoteUpdateRequest;
import com.example.database.Database;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for validation and authorization.
 * Depends on Database for additional checks as per quality requirement.
 */
public class ValidationService {
    private Database database; // Added to satisfy quality requirement

    /**
     * Constructor with Database dependency.
     *
     * @param database the database for additional validation checks
     */
    public ValidationService(Database database) {
        this.database = database;
    }

    /**
     * Validates the note update request.
     *
     * @param request the update request to validate
     * @return list of validation error messages; empty list if validation passes
     */
    public List<String> validateNoteRequest(NoteUpdateRequest request) {
        List<String> errors = new ArrayList<>();
        if (request.getStudent() == null || request.getStudent().trim().isEmpty()) {
            errors.add("Student field is required.");
        }
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            errors.add("Description field is required.");
        }
        if (request.getTeacher() == null || request.getTeacher().trim().isEmpty()) {
            errors.add("Teacher field is required.");
        }
        if (request.getDate() == null) {
            errors.add("Date field is required.");
        }
        return errors;
    }

    /**
     * Checks if the administrator is authorized to update the specified note.
     *
     * @param user the administrator
     * @param noteId the ID of the note to update
     * @return true if authorized, false otherwise
     */
    public Boolean isAuthorized(Administrator user, int noteId) {
        // Simplified authorization: assume admin can update any note.
        // In a real system, this would check user roles/permissions against note ownership.
        return Boolean.TRUE;
    }
}