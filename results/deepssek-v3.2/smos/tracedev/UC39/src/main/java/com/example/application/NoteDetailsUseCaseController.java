package com.example.application;

import com.example.domain.Note;
import com.example.infrastructure.NoteRepository;

/**
 * Use case controller responsible for executing the "View Note Details" use case.
 * Orchestrates the retrieval of a note and its transformation to a DTO.
 */
public class NoteDetailsUseCaseController {
    private NoteRepository noteRepository;
    private NoteDetailsDTOAssembler dtoAssembler;

    public NoteDetailsUseCaseController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        this.dtoAssembler = new NoteDetailsDTOAssembler();
    }

    /**
     * Executes the use case: retrieves a note by ID and returns its details as a DTO.
     * @param noteId the ID of the note to retrieve
     * @return the NoteDetailsDTO containing the note details, or null if note not found
     */
    public NoteDetailsDTO execute(String noteId) {
        System.out.println("UseCaseController: Executing note details retrieval for ID: " + noteId);
        Note note = noteRepository.findById(noteId);
        if (note == null) {
            System.err.println("UseCaseController: Note not found for ID: " + noteId);
            return null;
        }
        return dtoAssembler.toDTO(note);
    }

    /**
     * Cancels any pending operations (e.g., database queries).
     * Called when the administrator disconnects.
     */
    public void cancelPendingOperations() {
        System.out.println("UseCaseController: Cancelling pending operations.");
        // In a real implementation, this would interrupt ongoing operations.
    }
}