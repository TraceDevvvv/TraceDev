package repository;

import domain.Student;

/**
 * Interface for Data Access operations related to Student entities.
 */
public interface IStudentRepository {
    /**
     * Retrieves a Student by its unique identifier.
     * @param studentId The ID of the student to retrieve.
     * @return The Student object if found, null otherwise.
     */
    Student getStudentById(String studentId);
}