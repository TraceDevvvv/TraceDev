package com.example.disciplinarynote.infrastructure;

import com.example.disciplinarynote.domain.DisciplinaryNote;

import java.util.Optional;

/**
 * Interface for managing DisciplinaryNote persistence.
 */
public interface IDisciplinaryNoteRepository {
    /**
     * Finds a disciplinary note by its ID.
     * Modified to satisfy requirement R11 by throwing RepositoryConnectionException.
     *
     * @param noteId The unique identifier of the note.
     * @return An Optional containing the DisciplinaryNote if found, otherwise empty.
     * @throws RepositoryConnectionException if there's an issue connecting to the repository.
     */
    Optional<DisciplinaryNote> findById(String noteId) throws RepositoryConnectionException;

    /**
     * Saves a disciplinary note.
     * Modified to satisfy requirement R11 by throwing RepositoryConnectionException.
     *
     * @param note The DisciplinaryNote to save.
     * @throws RepositoryConnectionException if there's an issue connecting to the repository.
     */
    void save(DisciplinaryNote note) throws RepositoryConnectionException;
}