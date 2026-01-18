package com.example.notessystem.repository;

import com.example.notessystem.domain.Note;
import com.example.notessystem.exception.ConnectionException;

import java.util.List;

/**
 * Data Access Layer: Interface for abstracting data access operations for Note entities.
 * Defines the contract for how note data is retrieved from persistence.
 */
public interface INoteRepository {

    /**
     * Finds and returns a list of notes associated with a specific student ID.
     * @param studentId The ID of the student whose notes are to be retrieved.
     * @return A list of {@link Note} objects for the given student.
     * @throws ConnectionException if there's an issue connecting to the data source (e.g., SMOS server).
     */
    List<Note> findNotesByStudentId(String studentId) throws ConnectionException;
}