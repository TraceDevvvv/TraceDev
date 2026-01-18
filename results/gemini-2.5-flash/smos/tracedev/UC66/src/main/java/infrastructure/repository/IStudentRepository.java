package infrastructure.repository;

import infrastructure.dao.StudentDAO;
import java.util.List;

/**
 * Interface for Student data access operations.
 * Defines the contract for retrieving student data from the persistence layer.
 */
public interface IStudentRepository {
    /**
     * Finds students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of StudentDAO objects in the given class.
     */
    List<StudentDAO> findByClass(String classId);

    /**
     * Finds a student by their ID.
     * @param studentId The unique identifier of the student.
     * @return The StudentDAO object if found, otherwise null.
     */
    StudentDAO findById(String studentId);
}