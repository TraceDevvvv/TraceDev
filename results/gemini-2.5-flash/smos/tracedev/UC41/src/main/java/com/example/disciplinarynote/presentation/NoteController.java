package com.example.disciplinarynote.presentation;

import com.example.disciplinarynote.application.AuthenticationService;
import com.example.disciplinarynote.application.ChangeDisciplinaryNoteUseCase;
import com.example.disciplinarynote.dto.DisciplinaryNoteDetailsDto;
import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;
import com.example.disciplinarynote.infrastructure.DisciplinaryNoteRepositoryImpl; // For initial load simulation
import com.example.disciplinarynote.infrastructure.RepositoryConnectionException;

/**
 * Controller for managing disciplinary notes in the presentation layer.
 * Handles user input and orchestrates interactions with the application layer.
 */
public class NoteController {
    private final ChangeDisciplinaryNoteUseCase changeDisciplinaryNoteUseCase;
    private final AuthenticationService authenticationService; // Added to satisfy requirement R3
    private NoteEditView noteEditView; // View dependency
    private RegistryController registryController; // RegistryController dependency for navigation
    private DisciplinaryNoteRepositoryImpl disciplinaryNoteRepository; // For initial data load only, typically handled by another service

    /**
     * Constructs a new NoteController.
     *
     * @param changeDisciplinaryNoteUseCase The use case for changing notes.
     * @param authenticationService The service for user authentication.
     * @param disciplinaryNoteRepository The repository (used here to simulate initial data fetching).
     */
    public NoteController(ChangeDisciplinaryNoteUseCase changeDisciplinaryNoteUseCase,
                          AuthenticationService authenticationService,
                          DisciplinaryNoteRepositoryImpl disciplinaryNoteRepository) { // Using implementation for DTO conversion for initial load
        this.changeDisciplinaryNoteUseCase = changeDisciplinaryNoteUseCase;
        this.authenticationService = authenticationService;
        this.disciplinaryNoteRepository = disciplinaryNoteRepository;
    }

    /**
     * Sets the NoteEditView for this controller.
     * This is a setter injection as the View typically depends on the Controller,
     * creating a circular dependency if both were constructor injected simultaneously.
     * @param noteEditView The view to associate with this controller.
     */
    public void setNoteEditView(NoteEditView noteEditView) {
        this.noteEditView = noteEditView;
    }

    /**
     * Sets the RegistryController for this controller.
     * @param registryController The RegistryController instance.
     */
    public void setRegistryController(RegistryController registryController) {
        this.registryController = registryController;
    }

    /**
     * Shows the edit form for a specific disciplinary note.
     * Supports R4: Note details ARE displayed.
     *
     * @param noteId The ID of the note to edit.
     */
    public void showEditForm(String noteId) {
        System.out.println("[Presentation] NoteController: Request to show edit form for note ID: " + noteId);
        try {
            DisciplinaryNoteDetailsDto details = disciplinaryNoteRepository.getNoteDetailsDto(noteId); // Simulate fetching details
            if (details != null) {
                if (noteEditView != null) {
                    noteEditView.displayNoteDetails(details);
                    System.out.println("[Presentation] NoteController: Edit form displayed.");
                } else {
                    System.err.println("[Presentation] NoteController: NoteEditView not set. Cannot display form.");
                }
            } else {
                System.err.println("[Presentation] NoteController: Note with ID " + noteId + " not found for editing.");
                if (noteEditView != null) {
                    noteEditView.displayErrorMessage("Note with ID " + noteId + " not found.");
                }
            }
        } catch (RepositoryConnectionException e) {
            System.err.println("[Presentation] NoteController: Failed to load note for editing due to repository connection error: " + e.getMessage());
            if (noteEditView != null) {
                noteEditView.displayErrorMessage("Failed to load note: Connection lost.");
            }
        }
    }

    /**
     * Saves the disciplinary note based on the command received from the view.
     * This method implements the core logic from the sequence diagram for saving.
     *
     * @param command The command containing the updated note data.
     */
    public void saveNote(UpdateDisciplinaryNoteCommand command) {
        System.out.println("[Presentation] NoteController: Attempting to save note with command: " + command.getNoteId());

        // Check authentication (R3)
        if (authenticationService.checkLoggedIn()) {
            try {
                // Execute the use case to update and save the note
                changeDisciplinaryNoteUseCase.execute(command);
                System.out.println("[Presentation] NoteController: Note saved successfully.");
                // Redirect to registry screen (R9)
                redirectToRegistryScreen();
            } catch (RepositoryConnectionException e) {
                // Handle repository connection errors (R11)
                System.err.println("[Presentation] NoteController: Failed to save note due to repository connection error: " + e.getMessage());
                displayErrorMessage("Failed to save: Connection lost.");
            } catch (Exception e) {
                // Catch other potential exceptions (e.g., NoSuchElementException from UseCase)
                System.err.println("[Presentation] NoteController: An unexpected error occurred while saving note: " + e.getMessage());
                displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            }
        } else {
            // Not authenticated (R3)
            System.out.println("[Presentation] NoteController: Authentication failed. Cannot save note.");
            displayErrorMessage("Authentication required to save changes.");
        }
    }

    /**
     * Redirects the user to the registry screen.
     * Added to satisfy requirement R9.
     */
    public void redirectToRegistryScreen() {
        System.out.println("[Presentation] NoteController: Redirecting to Registry Screen after save.");
        if (registryController != null) {
            registryController.redirectToRegistryScreen();
        } else {
            System.err.println("[Presentation] NoteController: RegistryController not set for redirection.");
        }
    }

    /**
     * Displays an error message through the view.
     * Added to satisfy requirement R11.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("[Presentation] NoteController: Displaying error message via view: " + message);
        if (noteEditView != null) {
            noteEditView.displayErrorMessage(message);
        } else {
            System.err.println("[Presentation] NoteController: NoteEditView not set. Cannot display error message.");
        }
    }
}