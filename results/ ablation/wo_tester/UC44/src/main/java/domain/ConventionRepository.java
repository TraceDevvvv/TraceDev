package domain;

import java.util.Optional;

/**
 * Repository interface for Convention aggregate.
 */
public interface ConventionRepository {
    void save(Convention convention);
    Optional<Convention> findById(String id);
}