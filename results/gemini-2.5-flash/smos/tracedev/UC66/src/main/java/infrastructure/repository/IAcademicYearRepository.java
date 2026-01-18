package infrastructure.repository;

import infrastructure.dao.AcademicYearDAO;
import java.util.List;

/**
 * Interface for Academic Year data access operations.
 * Defines the contract for retrieving academic year data from the persistence layer.
 */
public interface IAcademicYearRepository {
    /**
     * Finds all academic years available in the system.
     * @return A list of AcademicYearDAO objects.
     */
    List<AcademicYearDAO> findAll();
}