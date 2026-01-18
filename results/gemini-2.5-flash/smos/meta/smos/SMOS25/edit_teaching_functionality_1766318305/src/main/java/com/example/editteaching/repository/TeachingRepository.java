package com.example.editteaching.repository;

import com.example.editteaching.model.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository interface for {@link Teaching} entities.
 * This interface extends Spring Data JPA's JpaRepository, providing standard CRUD operations
 * and query capabilities for the Teaching entity.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@Repository // Marks this interface as a Spring Data repository
public interface TeachingRepository extends JpaRepository<Teaching, String> {

    /**
     * Retrieves a Teaching entity by its unique identifier.
     *
     * @param id The unique ID of the teaching to retrieve.
     * @return An {@link Optional} containing the found Teaching entity, or empty if not found.
     */
    Optional<Teaching> findById(String id);

    /**
     * Saves a given Teaching entity. This can be used for both creating a new teaching
     * or updating an existing one.
     *
     * @param teaching The teaching entity to save.
     * @return The saved teaching entity.
     */
    Teaching save(Teaching teaching);

    /**
     * Retrieves all Teaching entities from the data store.
     *
     * @return A {@link List} of all Teaching entities.
     */
    List<Teaching> findAll();
}