package com.example;

import java.util.List;

/**
 * Repository interface for student notes.
 */
public interface StudentNotesRepository {
    /**
     * Finds all notes for a given student ID.
     *
     * @param studentId the student ID
     * @return a list of StudentNote objects
     */
    List<StudentNote> findByStudentId(String studentId);
}