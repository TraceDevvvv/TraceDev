package com.example.repository;

import com.example.entities.Justice;
import com.example.ports.JusticeRepositoryPort;
import java.util.Optional;

/**
 * Repository implementation that directly accesses the database.
 */
public class JusticeRepository implements JusticeRepositoryPort {
    /**
     * Finds a justice by ID.
     */
    @Override
    public Optional<Justice> findById(int id) {
        // In a real application, this would execute a SELECT query
        // For this example, we simulate returning a justice if id is positive
        if (id > 0) {
            Justice justice = new Justice(id, new java.util.Date(), "Sample justification");
            return Optional.of(justice);
        }
        return Optional.empty();
    }

    /**
     * Saves a justice.
     */
    @Override
    public Justice save(Justice justice) {
        // In a real application, this would execute an UPDATE query
        // For this example, we simulate a successful save by returning the same justice
        return justice;
    }

    /**
     * Execute SELECT query (for sequence diagram).
     */
    public Optional<Justice> selectQuery(int id) {
        return findById(id);
    }

    /**
     * Execute UPDATE query (for sequence diagram).
     */
    public Justice updateQuery(Justice justice) {
        return save(justice);
    }
}