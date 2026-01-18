package com.example.disciplinarynote.application;

import com.example.disciplinarynote.domain.DisciplinaryNote;
import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;
import com.example.disciplinarynote.infrastructure.IDisciplinaryNoteRepository;
import com.example.disciplinarynote.infrastructure.RepositoryConnectionException;

import java.util.NoSuchElementException;

/**
 * Use case for changing a disciplinary note.
 * Orchestrates domain and infrastructure layers to perform the update operation.
 */
public class ChangeDisciplinaryNoteUseCase {
    private final IDisciplinaryNoteRepository disciplinaryNoteRepository;

    /**
     * Constructs a new ChangeDisciplinaryNoteUseCase.
     * @param disciplinaryNoteRepository The repository for disciplinary notes.
     */
    public ChangeDisciplinaryNoteUseCase(IDisciplinaryNoteRepository disciplinaryNoteRepository) {
        this.disciplinaryNoteRepository = disciplinaryNoteRepository;
    }

    /**
     * Executes the disciplinary note update operation.
     * This method embodies the core logic of the "Change Disciplinary Note" use case.
     * Modified to satisfy requirement R11 by propagating RepositoryConnectionException.
     *
     * @param command The command containing the updated note details.
     * @throws RepositoryConnectionException if there's a problem connecting to the database.
     * @throws NoSuchElementException if the disciplinary note to be updated is not found.
     */
    public void execute(UpdateDisciplinaryNoteCommand command) throws RepositoryConnectionException {
        System.out.println("[Application] UseCase: Executing update command for note: " + command.getNoteId());

        // 1. Find the existing disciplinary note
        DisciplinaryNote disciplinaryNote;
        try {
            disciplinaryNote = disciplinaryNoteRepository.findById(command.getNoteId())
                                                        .orElseThrow(() -> new NoSuchElementException("Disciplinary Note not found with ID: " + command.getNoteId()));
        } catch (RepositoryConnectionException e) {
            // Sequence diagram specifies this should propagate up to the Controller
            System.err.println("[Application] UseCase: Repository connection interrupted during findById. " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        }

        // 2. Update the note with new data (domain logic)
        disciplinaryNote.update(command);
        System.out.println("[Application] UseCase: Domain logic applied - Note updated internally.");

        // 3. Save the updated note
        try {
            disciplinaryNoteRepository.save(disciplinaryNote);
            System.out.println("[Application] UseCase: Note saved successfully to repository.");
        } catch (RepositoryConnectionException e) {
            // Sequence diagram specifies this should propagate up to the Controller
            System.err.println("[Application] UseCase: Repository connection interrupted during save. " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        }
    }
}