package com.example.school.dataaccess;

import com.example.school.domain.Student;
import com.example.school.domain.StudentAcademicRecord;

import java.util.List;

/**
 * Interface for accessing student-related data from a persistent store.
 */
public interface IStudentRepository {

    /**
     * Retrieves a Student object by their unique ID.
     * @param studentId The unique identifier of the student.
     * @return The Student object if found, null otherwise.
     */
    Student getStudentById(String studentId);

    /**
     * Retrieves a list of academic records for a specific student.
     * @param studentId The unique identifier of the student.
     * @return A list of StudentAcademicRecord objects for the specified student.
     */
    List<StudentAcademicRecord> getStudentAcademicRecords(String studentId);
}