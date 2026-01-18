package com.example.infrastructure;

import com.example.domain.Convention;
import com.example.domain.ConventionId;
import com.example.domain.ConventionRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter for the ConventionRepository port, implementing persistence using an in-memory map.
 * In a real application, this would interact with a JPA/Hibernate ORM framework.
 */
public class ConventionJpaAdapter implements ConventionRepository {

    // Using a HashMap to simulate a database for demonstration purposes.
    // In a real application, this would be injected with an EntityManager or JpaRepository.
    private final Map<ConventionId, Convention> conventionStore = new HashMap<>();

    /**
     * Saves a Convention entity to the in-memory store.
     *
     * @param convention The Convention entity to save.
     * @return The saved Convention entity.
     */
    @Override
    public Convention save(Convention convention) {
        System.out.println("Adapter (JPA): Saving Convention ID: " + convention.getId().getValue() + ", Status: " + convention.getStatus());
        conventionStore.put(convention.getId(), convention);
        // In a real JPA scenario, this would involve `entityManager.persist(convention)` or `repository.save(convention)`.
        // We're returning the same object, assuming it's managed after save.
        return convention;
    }

    /**
     * Finds a Convention entity by its unique identifier from the in-memory store.
     *
     * @param id The ConventionId of the convention to find.
     * @return The found Convention entity, or null if not found.
     */
    @Override
    public Convention findById(ConventionId id) {
        System.out.println("Adapter (JPA): Finding Convention by ID: " + id.getValue());
        // In a real JPA scenario, this would involve `entityManager.find(Convention.class, id)` or `repository.findById(id)`.
        return conventionStore.get(id);
    }
}