package com.example;

import java.util.Map;

/**
 * Adapter that implements the NoteRepositoryPort using a DatabaseAdapter.
 * It translates domain specific calls to generic database operations.
 */
public class NoteRepositoryAdapter implements NoteRepositoryPort {

    private DatabaseAdapter databaseAdapter;
    private Logger logger;

    /**
     * Constructs a NoteRepositoryAdapter.
     *
     * @param databaseAdapter The underlying database adapter to use for persistence.
     * @param logger          The logger instance for logging operations.
     */
    public NoteRepositoryAdapter(DatabaseAdapter databaseAdapter, Logger logger) {
        this.databaseAdapter = databaseAdapter;
        this.logger = logger;
    }

    @Override
    public Note findNoteById(String noteId) throws DatabaseException {
        logger.info("NoteRepositoryAdapter: Attempting to find note by ID: " + noteId);
        try {
            // Simulate a query to the database
            Map<String, String> row = databaseAdapter.query("SELECT * FROM Notes WHERE id = '" + noteId + "'");

            if (row != null && !row.isEmpty()) {
                // Map the ResultSet (Map in this case) to a Note object
                logger.info("NoteRepositoryAdapter: Found note with ID: " + noteId);
                return new Note(row.get("id"), row.get("studentId"), row.get("content"), row.get("parentEmail"));
            } else {
                logger.warn("NoteRepositoryAdapter: No note found with ID: " + noteId);
                return null; // Or throw a specific "NoteNotFoundException" if business logic requires it
            }
        } catch (DatabaseException e) {
            logger.error("NoteRepositoryAdapter: Failed to retrieve note with ID " + noteId + ": " + e.getMessage());
            throw e; // Re-throw the database exception
        }
    }

    @Override
    public void deleteNote(String noteId) throws DatabaseException {
        logger.info("NoteRepositoryAdapter: Attempting to delete note with ID: " + noteId);
        try {
            // Simulate an update/delete operation
            int affectedRows = databaseAdapter.update("DELETE FROM Notes WHERE id = '" + noteId + "'");
            if (affectedRows > 0) {
                logger.info("NoteRepositoryAdapter: Successfully deleted note with ID: " + noteId);
            } else {
                logger.warn("NoteRepositoryAdapter: No note found to delete with ID: " + noteId);
                // Depending on requirements, might throw NoteNotFoundException here
            }
        } catch (DatabaseException e) {
            logger.error("NoteRepositoryAdapter: Failed to delete note with ID " + noteId + ": " + e.getMessage());
            throw e; // Re-throw the database exception
        }
    }
}