package infrastructure.repository;

import infrastructure.dao.QuadrimestreDAO;
import java.util.List;

/**
 * Interface for Quadrimestre data access operations.
 * Defines the contract for retrieving quadrimestre data from the persistence layer.
 */
public interface IQuadrimestreRepository {
    /**
     * Finds all quadrimestres available in the system.
     * @return A list of QuadrimestreDAO objects.
     */
    List<QuadrimestreDAO> findAll();

    /**
     * Finds a quadrimestre by its ID.
     * @param quadrimestreId The unique identifier of the quadrimestre.
     * @return The QuadrimestreDAO object if found, otherwise null.
     */
    QuadrimestreDAO findById(String quadrimestreId);
}