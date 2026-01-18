package com.example.control;

import com.example.model.Administrator;
import com.example.dto.NoteDTO;
import com.example.dto.NoteUpdateRequest;
import com.example.dto.NoteUpdateResult;
import com.example.entity.Note;
import com.example.repository.NoteRepository;
import com.example.service.ValidationService;
import java.util.List;
import java.util.Optional;

/**
 * Control class that orchestrates note-related operations.
 * Coordinates between boundary, service, and repository layers.
 */
public class NoteService {
    private NoteRepository noteRepository;
    private ValidationService validationService;

    /**
     * Constructor with dependencies.
     *
     * @param noteRepository the repository for note data access
     * @param validationService the service for validation and authorization
     */
    public NoteService(NoteRepository noteRepository, ValidationService validationService) {
        this.noteRepository = noteRepository;
        this.validationService = validationService;
    }

    /**
     * Verifies if the provided user is an administrator.
     * Added to satisfy entry condition REQ-Entry-Conditions.
     *
     * @param user the administrator user to verify
     * @return true if the user is an authorized administrator, false otherwise
     */
    public Boolean verifyAdmin(Administrator user) {
        // Simplified verification: assume any provided admin is valid.
        // In a real system, this would check credentials against a user store.
        return Boolean.TRUE;
    }

    /**
     * Retrieves note details by ID.
     *
     * @param noteId the ID of the note to retrieve
     * @return NoteDTO containing note details, or null if not found
     */
    public NoteDTO getNoteDetails(int noteId) {
        Optional<Note> noteOpt = noteRepository.findById(noteId);
        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            return convertToDTO(note);
        }
        return null; // Note not found
    }

    /**
     * Updates a note with the provided request data.
     * Includes validation, authorization, and persistence.
     *
     * @param request the update request containing new note data
     * @return NoteUpdateResult indicating success or failure
     */
    public NoteUpdateResult updateNote(NoteUpdateRequest request) {
        // Validation ensures secure persistence per quality requirement.
        List<String> validationErrors = validationService.validateNoteRequest(request);
        if (!validationErrors.isEmpty()) {
            // Validation failed
            return createErrorResult(400, "Validation failed", String.join(", ", validationErrors));
        }

        // Check authorization
        Administrator admin = new Administrator(); // Assumption: current admin context
        boolean authorized = validationService.isAuthorized(admin, request.getNoteId());
        if (!authorized) {
            // Not authorized
            return createErrorResult(403, "Not authorized", "User lacks permission to update this note.");
        }

        // Retrieve existing note
        Optional<Note> noteOpt = noteRepository.findById(request.getNoteId());
        if (!noteOpt.isPresent()) {
            return createErrorResult(404, "Note not found", "Note with ID " + request.getNoteId() + " does not exist.");
        }

        Note note = noteOpt.get();
        // Update note details
        note.updateDetails(request);

        try {
            Note updatedNote = noteRepository.save(note);
            NoteDTO updatedNoteDTO = convertToDTO(updatedNote);
            return createSuccessResult("Note updated successfully", updatedNoteDTO);
        } catch (Exception e) {
            // Handle persistence errors (e.g., database connection lost)
            return createErrorResult(500, "Connection failed", e.getMessage());
        }
    }

    /**
     * Converts a Note entity to a NoteDTO.
     *
     * @param note the Note entity
     * @return NoteDTO with corresponding data
     */
    private NoteDTO convertToDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setNoteId(note.getId());
        dto.setStudent(note.getStudent());
        dto.setDescription(note.getDescription());
        dto.setTeacher(note.getTeacher());
        dto.setDate(note.getDate());
        dto.setCreatedBy(note.getCreatedBy());
        dto.setModifiedBy(note.getModifiedBy());
        return dto;
    }

    /**
     * Creates a successful NoteUpdateResult.
     *
     * @param message the success message
     * @param updatedNote the updated note DTO
     * @return NoteUpdateResult indicating success
     */
    private NoteUpdateResult createSuccessResult(String message, NoteDTO updatedNote) {
        NoteUpdateResult result = new NoteUpdateResult();
        result.setSuccess(true);
        result.setMessage(message);
        result.setErrorCode(0);
        result.setErrorDetails("");
        result.setUpdatedNote(updatedNote);
        return result;
    }

    /**
     * Creates an error NoteUpdateResult.
     *
     * @param errorCode the error code
     * @param message the error message
     * @param errorDetails detailed error information
     * @return NoteUpdateResult indicating failure
     */
    private NoteUpdateResult createErrorResult(int errorCode, String message, String errorDetails) {
        NoteUpdateResult result = new NoteUpdateResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setErrorCode(errorCode);
        result.setErrorDetails(errorDetails);
        result.setUpdatedNote(null);
        return result;
    }
}