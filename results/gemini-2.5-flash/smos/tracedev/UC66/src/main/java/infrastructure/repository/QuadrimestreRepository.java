package infrastructure.repository;

import infrastructure.dao.QuadrimestreDAO;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of IQuadrimestreRepository.
 * This class simulates interaction with a database to retrieve quadrimestre data.
 */
public class QuadrimestreRepository implements IQuadrimestreRepository {

    // Dummy data to simulate database records
    private final List<QuadrimestreDAO> quadrimestres = Arrays.asList(
        new QuadrimestreDAO("Q1", "First Quadrimestre"),
        new QuadrimestreDAO("Q2", "Second Quadrimestre"),
        new QuadrimestreDAO("Q3", "Third Quadrimestre")
    );

    /**
     * Finds all quadrimestres.
     * @return A list of all QuadrimestreDAO objects.
     */
    @Override
    public List<QuadrimestreDAO> findAll() {
        System.out.println("Database: SELECT quadrimestre_id, name FROM quadrimestres"); // Simulate DB call
        return quadrimestres;
    }

    /**
     * Finds a quadrimestre by its ID.
     * @param quadrimestreId The unique identifier of the quadrimestre.
     * @return The QuadrimestreDAO object if found, otherwise null.
     */
    @Override
    public QuadrimestreDAO findById(String quadrimestreId) {
        return quadrimestres.stream()
                .filter(q -> q.getId().equals(quadrimestreId))
                .findFirst()
                .orElse(null);
    }
}