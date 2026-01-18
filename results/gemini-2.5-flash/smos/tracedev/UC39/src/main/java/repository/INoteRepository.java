
package repository;

import domain.Note;

/**
 * Interface for Note data access operations.
 * Defines the contract for interacting with Note entities.
 */
public interface INoteRepository {

    /**
     * Retrieves a Note entity by its unique identifier.
     *
     * @param noteId The unique ID of the note to retrieve.
     * @return The Note entity if found, null otherwise.
     */
    Note getNoteById(String noteId);
}
