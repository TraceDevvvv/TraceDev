package com.example.attendancesystem;

import java.util.List;

/**
 * Interface for managing Student data.
 * Defined in the Infrastructure layer of the Class Diagram.
 */
public interface StudentRepository {
    /**
     * Finds all students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of students in the specified class.
     */
    List<Student> findStudentsByClass(String classId);
}