package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Adapter for database operations. This is a simplified mock implementation.
 * It simulates database queries and updates using in-memory data.
 */
public class DatabaseAdapter {

    private Map<String, Note> notesTable; // Simulates a table for notes
    private Logger logger;
    private Random random;

    /**
     * Constructor for DatabaseAdapter.
     *
     * @param logger The logger instance to use for logging database operations.
     */
    public DatabaseAdapter(Logger logger) {
        this.logger = logger;
        this.notesTable = new HashMap<>();
        // Populate with some dummy data
        notesTable.put("note101", new Note("note101", "studentA", "Bad behavior in class", "parentA@example.com"));
        notesTable.put("note102", new Note("note102", "studentB", "Late submission of homework", "parentB@example.com"));
        notesTable.put("note103", new Note("note103", "studentC", "Excellent participation", "parentC@example.com"));
        this.random = new Random();
    }

    /**
     * Simulates a database query operation.
     *
     * @param sql The SQL query string (used for logging, not parsed).
     * @return A Map representing a row (e.g., column name to value) or null if not found.
     * @throws DatabaseException if a simulated database error occurs.
     */
    public Map<String, String> query(String sql) throws DatabaseException {
        // Simulate potential database errors
        if (random.nextInt(100) < 5) { // 5% chance of failure
            logger.error("Simulated DatabaseException during query: " + sql);
            throw new DatabaseException("Simulated database connection error during query.");
        }

        logger.info("Executing query: " + sql);
        // Simple parsing to extract noteId from SQL for this mock
        String noteId = extractNoteIdFromSql(sql, "SELECT");

        if (noteId != null && notesTable.containsKey(noteId)) {
            Note note = notesTable.get(noteId);
            Map<String, String> row = new HashMap<>();
            row.put("id", note.getId());
            row.put("studentId", note.getStudentId());
            row.put("content", note.getContent());
            row.put("parentEmail", note.getParentEmail());
            return row;
        }
        return null; // Note not found
    }

    /**
     * Simulates a database update operation (insert, update, delete).
     *
     * @param sql The SQL update string (used for logging, not parsed).
     * @return The number of affected rows (0 or 1 in this mock).
     * @throws DatabaseException if a simulated database error occurs.
     */
    public int update(String sql) throws DatabaseException {
        // Simulate potential database errors
        if (random.nextInt(100) < 5) { // 5% chance of failure
            logger.error("Simulated DatabaseException during update: " + sql);
            throw new DatabaseException("Simulated database write error during update.");
        }

        logger.info("Executing update: " + sql);
        // Simple parsing to extract noteId from SQL for this mock
        String noteId = extractNoteIdFromSql(sql, "DELETE");

        if (noteId != null && notesTable.containsKey(noteId)) {
            notesTable.remove(noteId);
            return 1; // 1 row affected
        }
        return 0; // 0 rows affected
    }

    /**
     * Helper to extract note ID from simplified SQL query strings for mock.
     */
    private String extractNoteIdFromSql(String sql, String operation) {
        String lowerSql = sql.toLowerCase();
        if (lowerSql.startsWith(operation.toLowerCase()) && lowerSql.contains("id = '")) {
            int startIndex = lowerSql.indexOf("id = '") + "id = '".length();
            int endIndex = lowerSql.indexOf("'", startIndex);
            if (endIndex != -1) {
                return sql.substring(startIndex, endIndex);
            }
        }
        return null;
    }
}