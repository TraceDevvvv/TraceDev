package com.example.notessystem.repository.impl;

import com.example.notessystem.domain.Note;
import com.example.notessystem.infrastructure.Database;
import com.example.notessystem.repository.INoteRepository;
import com.example.notessystem.exception.ConnectionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link INoteRepository} that interacts with a {@link Database}.
 * This class handles mapping raw database results to domain objects.
 *
 * Note: Consider caching strategies for frequently accessed notes to improve response time. (R8)
 */
public class NoteRepositoryImpl implements INoteRepository {

    private Database database;

    /**
     * Constructs a NoteRepositoryImpl with a dependency on a Database instance.
     * @param database The database instance to interact with.
     */
    public NoteRepositoryImpl(Database database) {
        this.database = database;
    }

    /**
     * {@inheritDoc}
     *
     * Retrieves notes by student ID by querying the underlying database.
     * Simulates a connection error if studentId is "errorStudent".
     */
    @Override
    public List<Note> findNotesByStudentId(String studentId) throws ConnectionException {
        System.out.println("NoteRepository: Finding notes for studentId: " + studentId);

        // Simulate a connection error for testing the 'alt' path in the sequence diagram.
        if ("errorStudent".equals(studentId)) {
            // Note: Exit Condition: connection interrupted
            throw new ConnectionException("SMOS server unavailable: Could not connect to retrieve notes for student " + studentId);
        }

        // Simulate database query execution
        String sqlQuery = "SELECT * FROM notes WHERE student_id = '" + studentId + "'";
        List<Map<String, Object>> rawNotesData = database.executeQuery(sqlQuery); // Repository -> DB call

        // Note: Map ResultSet to List<Note> domain objects
        List<Note> notes = new ArrayList<>();
        for (Map<String, Object> row : rawNotesData) {
            String id = (String) row.get("id");
            String sId = (String) row.get("studentId");
            String content = (String) row.get("content");
            LocalDate date = (LocalDate) row.get("date");
            String author = (String) row.get("author");
            notes.add(new Note(id, sId, content, date, author));
        }
        System.out.println("NoteRepository: Found " + notes.size() + " notes for studentId: " + studentId);
        return notes; // Repository --> Service return
    }
}