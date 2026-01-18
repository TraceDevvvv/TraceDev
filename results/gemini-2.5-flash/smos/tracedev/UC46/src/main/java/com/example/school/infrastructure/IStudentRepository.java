package com.example.school.infrastructure;

import com.example.school.domain.Student;
import java.util.List;

/**
 * Interface for operations related to Student persistence.
 */
public interface IStudentRepository {
    /**
     * Finds all students enrolled in a specific class.
     * @param classId The ID of the class.
     * @return A list of Student objects.
     */
    List<Student> findStudentsByClass(String classId);

    /**
     * Finds a student by their unique identifier.
     * @param studentId The ID of the student.
     * @return The Student object if found, null otherwise.
     */
    Student findStudentById(String studentId);
}