package com.example.infrastructure;

import com.example.domain.Student;

/**
 * Interface for data access operations related to Students.
 * Defines the contract for saving and finding Student entities.
 */
public interface IStudentRepository {
    /**
     * Saves a Student entity to the repository.
     * @param student The Student object to save.
     */
    void save(Student student);

    /**
     * Finds a Student entity by its unique student ID.
     * @param studentId The ID of the student to find.
     * @return The Student object if found, null otherwise.
     */
    Student findById(String studentId);
}