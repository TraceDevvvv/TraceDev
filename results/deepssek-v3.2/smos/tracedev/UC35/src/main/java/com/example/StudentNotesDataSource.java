package com.example;

import java.util.List;

/**
 * Data source interface for student notes.
 */
public interface StudentNotesDataSource {
    /**
     * Fetches note DTOs for a given student ID.
     *
     * @param studentId the student ID
     * @return a list of StudentNoteDto objects
     */
    List<StudentNoteDto> fetchNotesByStudentId(String studentId);

    /**
     * Simulates sending a SELECT query to the DB.
     * This method corresponds to sequence diagram message m14.
     *
     * @param studentId the student ID
     * @return a list of StudentNoteDto objects
     */
    default List<StudentNoteDto> selectNotesByStudentId(String studentId) {
        // Default implementation that delegates to fetchNotesByStudentId.
        // In a real implementation, this would construct and execute the SQL.
        return fetchNotesByStudentId(studentId);
    }
}