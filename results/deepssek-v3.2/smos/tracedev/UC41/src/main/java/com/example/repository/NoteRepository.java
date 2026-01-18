package com.example.repository;

import com.example.entity.Note;
import com.example.database.Database;
import com.example.external.SMOSServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Repository class for Note entity persistence.
 * Manages database operations and depends on Database and SMOSServer.
 */
public class NoteRepository {
    private Database database;
    private SMOSServer smosServer; // Added to satisfy exit condition REQ-Exit-Conditions

    /**
     * Constructor with dependencies.
     *
     * @param database the database for data operations
     * @param smosServer the external server for connection management
     */
    public NoteRepository(Database database, SMOSServer smosServer) {
        this.database = database;
        this.smosServer = smosServer;
    }

    /**
     * Finds a note by its ID.
     *
     * @param id the note ID
     * @return Optional containing the note if found, empty otherwise
     */
    public Optional<Note> findById(int id) {
        try {
            ResultSet rs = database.executeQuery("SELECT * FROM note WHERE id = " + id);
            if (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setStudent(rs.getString("student"));
                note.setDescription(rs.getString("description"));
                note.setTeacher(rs.getString("teacher"));
                note.setDate(rs.getDate("date"));
                note.setCreatedBy(rs.getString("created_by"));
                note.setModifiedBy(rs.getString("modified_by"));
                note.setCreatedAt(rs.getTimestamp("created_at"));
                note.setUpdatedAt(rs.getTimestamp("updated_at"));
                return Optional.of(note);
            }
        } catch (SQLException e) {
            System.err.println("Error finding note by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Saves a note (insert or update).
     *
     * @param note the note to save
     * @return the saved note (with updated fields like ID if new)
     */
    public Note save(Note note) {
        try {
            // Simulate UPDATE query
            String query = "UPDATE note SET student = '" + note.getStudent() +
                    "', description = '" + note.getDescription() +
                    "', teacher = '" + note.getTeacher() +
                    "', date = '" + note.getDate() +
                    "', modified_by = '" + note.getModifiedBy() +
                    "' WHERE id = " + note.getId();
            database.executeQuery(query);
            database.commitTransaction();
            System.out.println("Note saved successfully: " + note.getId());
        } catch (Exception e) {
            // In case of failure, rollback and disconnect from SMOSServer
            rollbackTransaction();
            smosServer.disconnect();
            throw new RuntimeException("Save operation failed", e);
        }
        return note;
    }

    /**
     * Checks if a note exists by ID.
     *
     * @param id the note ID
     * @return true if exists, false otherwise
     */
    public Boolean existsById(int id) {
        return findById(id).isPresent();
    }

    /**
     * Rolls back the current transaction.
     * Added to satisfy exit condition REQ-Exit-Conditions.
     */
    public void rollbackTransaction() {
        database.rollbackTransaction();
        System.out.println("Transaction rolled back.");
    }
}