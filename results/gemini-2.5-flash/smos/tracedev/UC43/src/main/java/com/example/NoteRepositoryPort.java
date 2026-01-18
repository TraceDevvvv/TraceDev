package com.example;

/**
 * Port interface defining the contract for note repository operations.
 * This abstracts the data access layer for Note entities.
 */
public interface NoteRepositoryPort {

    /**
     * Finds a Note by its unique identifier.
     *
     * @param noteId The ID of the note to find.
     * @return The Note object if found, otherwise null (or throws an exception).
     * @throws DatabaseException if a database access error occurs.
     */
    Note findNoteById(String noteId) throws DatabaseException;

    /**
     * Deletes a Note by its unique identifier.
     *
     * @param noteId The ID of the note to delete.
     * @throws DatabaseException if a database access error occurs.
     */
    void deleteNote(String noteId) throws DatabaseException;
}