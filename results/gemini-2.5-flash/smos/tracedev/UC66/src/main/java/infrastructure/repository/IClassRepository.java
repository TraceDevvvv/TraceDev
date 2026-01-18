package infrastructure.repository;

import infrastructure.dao.ClassDAO;
import java.util.List;

/**
 * Interface for Class data access operations.
 * Defines the contract for retrieving class data from the persistence layer.
 */
public interface IClassRepository {
    /**
     * Finds classes associated with a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of ClassDAO objects belonging to the given academic year.
     */
    List<ClassDAO> findByAcademicYear(String yearId);

    /**
     * Finds a class by its ID.
     * @param classId The unique identifier of the class.
     * @return The ClassDAO object if found, otherwise null.
     */
    ClassDAO findById(String classId);
}