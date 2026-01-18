package com.example.reportcard.repository;

import com.example.reportcard.domain.Student;

import java.util.List;

/**
 * Interface for Data Access operations related to Student entities.
 */
public interface IStudentRepository {
    /**
     * Finds all students enrolled in a specific class.
     * @param classId The ID of the class.
     * @return A list of Student entities.
     */
    List<Student> findStudentsByClassId(String classId);

    /**
     * Finds a student by their ID.
     * @param id The ID of the student.
     * @return The Student entity, or null if not found.
     */
    Student findById(String id);
}